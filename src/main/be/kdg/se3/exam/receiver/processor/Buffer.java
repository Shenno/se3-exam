package be.kdg.se3.exam.receiver.processor;

import be.kdg.se3.exam.receiver.entity.ShipMessage;
import be.kdg.se3.exam.receiver.service.ServiceException;
import be.kdg.se3.exam.receiver.service.ShipService;
import org.apache.log4j.Logger;

import java.util.*;

/**
 * Created by   Shenno Willaert
 * Date         3/11/2015
 * Project      se3-exam
 * Package      be.kdg.se3.exam.receiver.processor
 */

/**
 * Class Buffer makes it possible to buffer ShipMessages for a given period.
 * It also calls the external service to receive information about a specified shipID.
 */
public class Buffer {
    private final int BUFFERTIME = 60; // in seconds
    private final int SCHEDULETIME = 10; // in seconds
    private ShipService service;
    private Map<String, ArrayList<ShipMessage>> shipMsgs = new HashMap<>();
    private Map<String, String> shipInfos = new HashMap<>();
    private final Logger logger = Logger.getLogger(this.getClass());

    public Buffer() {
        this.service = new ShipService();
        checkBufferTime();
    }

    public ArrayList<ShipMessage> getShipMsgs(String shipID) {
        return shipMsgs.get(shipID);
    }

    public void addMsg(ShipMessage sm) {
        shipMsgs.putIfAbsent(sm.getShipID(), new ArrayList<>());
        ArrayList<ShipMessage> msgList = shipMsgs.get(sm.getShipID());
        msgList.add(sm);
        logger.info(String.format("Shipmessage about ship %s added to buffer",sm.getShipID()));
        try {
            String serviceResponse = service.callShipService(sm.getShipID());
            logger.info(String.format("Shipinfo retrieved for ship %s", sm.getShipID()));
            shipInfos.putIfAbsent(sm.getShipID(), serviceResponse);
        } catch (ServiceException e) {
            logger.error(e.getMessage(), e);
        }
    }

    private void checkBufferTime() {
        final Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Date now = new Date();
                Iterator<Map.Entry<String, ArrayList<ShipMessage>>> iter = shipMsgs.entrySet().iterator();
                while (iter.hasNext()) {
                    Map.Entry<String, ArrayList<ShipMessage>> entry = iter.next();
                    ArrayList<ShipMessage> shipMessages = entry.getValue();
                    if (now.getTime() - shipMessages.get(shipMessages.size() - 1).getTimeStamp().getTime() >= (BUFFERTIME * 1000) ) {
                        iter.remove();
                        shipInfos.remove(entry.getKey());
                        logger.info(String.format("Shipmessages and info about ship %s removed from buffer", entry.getKey()));
                    }
                }
            }
        }, SCHEDULETIME * 1000, SCHEDULETIME * 1000);
    }
}

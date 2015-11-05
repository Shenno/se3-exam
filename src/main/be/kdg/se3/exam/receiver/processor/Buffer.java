package be.kdg.se3.exam.receiver.processor;

import be.kdg.se3.exam.receiver.converter.JsonToShipInfo;
import be.kdg.se3.exam.receiver.converter.XmlToObject;
import be.kdg.se3.exam.receiver.entity.ShipMessage;
import be.kdg.se3.exam.receiver.service.ShipService;

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
    private static final int BUFFERTIME = 20; // in seconds
    private ShipService service;
    private Map<String, ArrayList<ShipMessage>> shipMsgs = new HashMap<>();
    private Map<String, String> shipInfos = new HashMap<>();
    private ETAController etaController;

    public Buffer(ETAController etaController) {
        this.service = new ShipService();
        this.etaController = etaController;
        checkBufferTime();
    }

    public Map<String, ArrayList<ShipMessage>> getShipMsgs() {
        return shipMsgs;
    }

    public void addMsg(ShipMessage sm) {
        shipMsgs.putIfAbsent(sm.getShipID(), new ArrayList<>());
        ArrayList<ShipMessage> c = shipMsgs.get(sm.getShipID());
        c.add(sm);
        shipInfos.put(sm.getShipID(), service.callShipService(sm.getShipID()));
        /* deleten die shit */ //todo
        JsonToShipInfo converter = new JsonToShipInfo();
        System.out.println(converter.convert(service.callShipService(sm.getShipID())));

        if (c.size() > 1)
            checkETAUpdates(sm, c);
    }

    private void checkETAUpdates(ShipMessage sm, ArrayList<ShipMessage> shipMessages) {
        Map<String, ETALogType> etaMap = etaController.getMapETA();
        if (etaMap.containsKey(sm.getShipID())) {
            ShipMessage secondLast = shipMessages.get(shipMessages.size() - 2);
            ShipMessage last = shipMessages.get(shipMessages.size() - 1);
            if (etaMap.get(sm.getShipID()) == ETALogType.NEW_MSG) {
                etaController.calcETA(secondLast, last);
            } else if (etaMap.get(sm.getShipID()) == ETALogType.NEW_POS) {
                if (!secondLast.getPlant().equals(last.getPlant())) {
                    etaController.calcETA(secondLast, last);
                }
            }
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
                    if (now.getTime() - shipMessages.get(shipMessages.size() - 1).getTimeStamp().getTime() >= BUFFERTIME) {
                        iter.remove();
                        shipInfos.remove(entry.getKey());
                        System.out.printf("%s is eruit\n", entry.getKey());
                    }
                }
            }
        }, BUFFERTIME * 1000, BUFFERTIME * 1000);

    }
}

package be.kdg.se3.exam.receiver.processor;

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
    private final int BUFFERTIME;
    private ShipService service;
    private Map<String,ArrayList<ShipMessage>> shipMsgs = new HashMap<>();
    private Map<String,String> shipInfos = new HashMap<>();

    public Buffer(int timeInBuffer) {
        this.BUFFERTIME = timeInBuffer;
        this.service = new ShipService();
        checkBufferTime();
    }

    public Map<String, ArrayList<ShipMessage>> getShipMsgs() {
        return shipMsgs;
    }

    public void addMsg(ShipMessage sm) {
        shipMsgs.putIfAbsent(sm.getShipID(),new ArrayList<>());
        Collection<ShipMessage> c = shipMsgs.get(sm.getShipID());
        c.add(sm);
        shipInfos.put(sm.getShipID(),service.callShipService(sm.getShipID()));
        System.out.println(shipInfos.toString());
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
                    if (now.getTime() - shipMessages.get(shipMessages.size()-1).getTimeStamp().getTime() >= BUFFERTIME) {
                        iter.remove();
                        System.out.printf("%s is eruit\n",entry.getKey());
                    }
                }
            }
        }, BUFFERTIME, BUFFERTIME);

    }
}

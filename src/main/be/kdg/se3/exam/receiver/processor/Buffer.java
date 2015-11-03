package be.kdg.se3.exam.receiver.processor;

import be.kdg.se3.exam.receiver.converter.XmlToObject;
import be.kdg.se3.exam.receiver.entity.ShipMessage;

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
    private final int STAY_IN_BUFFER;
    private final int CLEAR_CACHE_AFTER;
    private Map<String,Collection<ShipMessage>> shipMsgs = new HashMap<>();
    private Map<String,String> shipInfos = new HashMap<>();

    public Buffer(int buffer, int cache) {
        this.STAY_IN_BUFFER = buffer;
        this.CLEAR_CACHE_AFTER = cache;
    }

    public Map<String, Collection<ShipMessage>> getShipMsgs() {
        return shipMsgs;
    }

    public void addMsg(ShipMessage sm) {
        shipMsgs.putIfAbsent(sm.getShipID(),new ArrayList<>());
        Collection<ShipMessage> c = shipMsgs.get(sm.getShipID());
        c.add(sm);
        System.out.println(shipMsgs.toString());
    }
}

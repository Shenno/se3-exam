package be.kdg.se3.exam.receiver.service;

import be.kdg.se3.proxy.ShipServiceProxy;

import java.io.IOException;

/**
 * Created by   Shenno Willaert
 * Date         3/11/2015
 * Project      se3-exam
 * Package      be.kdg.se3.exam.receiver.service
 */
public class ShipService {
    private ShipServiceProxy proxy;

    public ShipService() {
        proxy = new ShipServiceProxy();
    }

    public String callShipService(String shipID) {
        String call = "www.services4se3.com/shipservice/";
        call += shipID;
        try {
            return proxy.get(call);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

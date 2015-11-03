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
    private String response = null;

    private final String CALL = "www.services4se3.com/shipservice/";

    public ShipService() {
        proxy = new ShipServiceProxy();
    }

    /**
     *
     * @param shipID
     * @return
     */
    public String callShipService(String shipID) {
        try {
            response = proxy.get(CALL + shipID);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }
}

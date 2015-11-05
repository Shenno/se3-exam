package be.kdg.se3.exam.receiver.service;
import org.apache.log4j.Logger;
import be.kdg.se3.exam.receiver.entity.ShipMessage;
import be.kdg.se3.proxy.ShipServiceProxy;
import org.apache.log4j.Priority;

import java.io.IOException;
import java.util.*;

/**
 * Created by   Shenno Willaert
 * Date         3/11/2015
 * Project      se3-exam
 * Package      be.kdg.se3.exam.receiver.service
 */
public class ShipService {
    private ShipServiceProxy proxy;
    private final String CALL = "www.services4se3.com/shipservice/";
    private final int TIME_IN_CACHE; // IN SECONDS
    private final int RETRY;
    private Map<String, String> cache = new HashMap<>();
    private final Logger logger = Logger.getLogger(this.getClass());

    public ShipService() {
        this.proxy = new ShipServiceProxy();
        this.TIME_IN_CACHE = 20;
        this.RETRY = 5;
        checkCacheTime();
    }

    /**
     * @param shipID
     * @return
     */
    public String callShipService(String shipID) {
        if (cache.containsKey(shipID))
            return cache.get(shipID);

        String response = new String();
        try {
            response = proxy.get(CALL + shipID);
            cache.put(shipID, response);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    private void checkCacheTime() {
        final Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                cache.clear();
                System.out.println("==Cachecleared==");
            }
        }, TIME_IN_CACHE * 1000, TIME_IN_CACHE * 1000);

    }

}

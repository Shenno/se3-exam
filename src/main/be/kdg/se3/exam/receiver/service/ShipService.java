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

/**
 * Class that will call the external proxy and return its info.
 */
public class ShipService {
    private ShipServiceProxy proxy;
    private final String CALL = "www.services4se3.com/shipservice/";
    private final int TIME_IN_CACHE = 60; // IN SECONDS
    private final int RETRY = 5; // TODO
    private Map<String, String> cache = new HashMap<>();
    private final Logger logger = Logger.getLogger(this.getClass());

    public ShipService() {
        this.proxy = new ShipServiceProxy();
        checkCacheTime();
    }

    public String callShipService(String shipID) throws ServiceException {
        if (cache.containsKey(shipID))
            return cache.get(shipID);

        try {
            String response = proxy.get(CALL + shipID);
            cache.put(shipID, response);
            return response;
        } catch (IOException e) {
            throw new ServiceException("Proxy unavailable", e);
        }
    }

    private void checkCacheTime() {
        final Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                cache.clear();
                logger.info("Cache cleared");
            }
        }, TIME_IN_CACHE * 1000, TIME_IN_CACHE * 1000);

    }

}

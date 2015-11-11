package be.kdg.se3.exam.receiver.processor;

import be.kdg.se3.exam.receiver.entity.ShipMessage;
import org.apache.log4j.Logger;

import java.util.*;

/**
 * Created by   Shenno Willaert
 * Date         4/11/2015
 * Project      se3-exam
 * Package      be.kdg.se3.exam.receiver.processor
 */

/**
 * Class ETAControllerImpl is going to hold the info when and about which ships an ETA has to be calculated.
 * Also this class is responsible for the actual calculation.
 */
public class ETAControllerImpl implements ETAControllerInterface {
    private Map<String, ETALogType> mapETA;
    private final Logger logger = Logger.getLogger(this.getClass());

    public ETAControllerImpl() {
        mapETA = new HashMap<>();
    }

    @Override
    public void addETAParameter(String shipID, ETALogType logType) {
        mapETA.put(shipID, logType);
    }

    @Override
    public void deleteETAParameter(String shipID) {
        mapETA.remove(shipID);
    }

    @Override
    public void checkETAStatus(ShipMessage shipMessage, ArrayList<ShipMessage> shipMessages) {
        Date etaDate;
        if (mapETA.containsKey(shipMessage.getShipID()) && shipMessages.size() > 1) {
            ShipMessage secondLast = shipMessages.get(shipMessages.size() - 2);
            ShipMessage last = shipMessages.get(shipMessages.size() - 1);
            if (mapETA.get(shipMessage.getShipID()) == ETALogType.NEW_MSG) {
                etaDate = calcETA(secondLast, last);
                logger.info(String.format("Ship %s ETA: %s - %s", shipMessage.getShipID(), etaDate, ETALogType.NEW_MSG));
            } else if (mapETA.get(shipMessage.getShipID()) == ETALogType.NEW_POS) {
                if (!secondLast.getPlant().equals(last.getPlant())) {
                    etaDate = calcETA(secondLast, last);
                    logger.info(String.format("Ship %s ETA: %s - %s", shipMessage.getShipID(), etaDate, ETALogType.NEW_POS));
                }
            }
        }
    }

    @Override
    public Date calcETA(ShipMessage secondLastMsg, ShipMessage lastMsg) {
        double timeDiff = lastMsg.getTimeStamp().getTime() - secondLastMsg.getTimeStamp().getTime();
        double quadeDiff = secondLastMsg.getDistanceToLoadingBay() - lastMsg.getDistanceToLoadingBay();
        double currSpeed = quadeDiff / timeDiff;
        double eta = lastMsg.getDistanceToLoadingBay() / currSpeed;
        return new Date(lastMsg.getTimeStamp().getTime() + (long) eta);
    }
}

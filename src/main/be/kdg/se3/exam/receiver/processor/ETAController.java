package be.kdg.se3.exam.receiver.processor;

import be.kdg.se3.exam.receiver.entity.ShipMessage;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by   Shenno Willaert
 * Date         4/11/2015
 * Project      se3-exam
 * Package      be.kdg.se3.exam.receiver.processor
 */
public class ETAController implements ETAControllerInterface {
    private Map<String,ETALogType> mapETA;

    public ETAController() {
        mapETA = new HashMap<>();
    }

    public Map<String, ETALogType> getMapETA() {
        return mapETA;
    }

    @Override
    public void addETAParameter(String shipID, ETALogType logType) {
        mapETA.put(shipID,logType);
    }

    @Override
    public void deleteETAParameter(String shipID) {
        mapETA.remove(shipID);
    }

    @Override
    public void calcETA(ShipMessage secondLastMsg, ShipMessage lastMsg) {
        long timeDiff = lastMsg.getTimeStamp().getTime() - secondLastMsg.getTimeStamp().getTime();
        double quadeDiff = secondLastMsg.getDistanceToLoadingBay() - lastMsg.getDistanceToLoadingBay();
        double currSpeed = quadeDiff / timeDiff;
        double eta = lastMsg.getDistanceToLoadingBay() / currSpeed;
        Date dateEta = new Date(lastMsg.getTimeStamp().getTime() + (long) eta);
        System.out.println(dateEta);
    }
}

package be.kdg.se3.exam.receiver.processor;

import be.kdg.se3.exam.receiver.entity.ShipMessage;

/**
 * Created by   Shenno Willaert
 * Date         4/11/2015
 * Project      se3-exam
 * Package      be.kdg.se3.exam.receiver.processor
 */

/**
 * Interface to control Estimated Time Arrival about specified ships.
 * Possibility to add/remove/print.
 */
public interface ETAControllerInterface {
    /**
     * Add a ship we want to monitor ETA
     * @param shipID Which ship we want to monitor the ETA
     * @param logType When to log the ETA
     */
    void addETAParameter(String shipID, ETALogType logType);

    /**
     * Delete a ship from the ETA monitoring
     * @param shipID
     */
    void deleteETAParameter(String shipID);

    /**
     * Check if a specified ShipMessage is being monitored
     * @param shipMessage
     */
    void checkETAStatus(ShipMessage shipMessage);

    /**
     * Calculate the Estimated Time of Arrival
     * @param secondLastMsg
     * @param lastMsg
     */
    void calcETA(ShipMessage secondLastMsg, ShipMessage lastMsg);
}

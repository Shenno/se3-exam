package be.kdg.se3.exam.receiver.database;

import be.kdg.se3.exam.receiver.entity.ShipMessage;

/**
 * Created by   Shenno Willaert
 * Date         2/11/2015
 * Project      se3-exam
 * Package      be.kdg.se3.exam.receiver.database
 */

/**
 * Interface to create a specific database.
 */
public interface Database {
    /**
     * Insert into the database.
     * @param shipMessage the message to be inserted
     */
    void onInsert(ShipMessage shipMessage);
}

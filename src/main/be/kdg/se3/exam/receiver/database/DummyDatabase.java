package be.kdg.se3.exam.receiver.database;

import be.kdg.se3.exam.receiver.broker.ChannelException;
import be.kdg.se3.exam.receiver.entity.ShipMessage;

/**
 * Created by   Shenno Willaert
 * Date         2/11/2015
 * Project      se3-exam
 * Package      be.kdg.se3.exam.receiver.database
 */

/**
 * Dummydatabase implementation class that is just going to print the inserted messages.
 */
public class DummyDatabase implements Database {
    @Override
    public void onInsert(ShipMessage shipMessage) {
        System.out.printf("Inserted: %s \n", shipMessage.toString());
    }
}

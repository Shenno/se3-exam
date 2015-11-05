package be.kdg.se3.exam.receiver.database;

import be.kdg.se3.exam.receiver.broker.InputChannelException;
import be.kdg.se3.exam.receiver.converter.XmlToObject;
import be.kdg.se3.exam.receiver.entity.ShipMessage;
import be.kdg.se3.exam.receiver.processor.Buffer;

/**
 * Created by   Shenno Willaert
 * Date         2/11/2015
 * Project      se3-exam
 * Package      be.kdg.se3.exam.receiver.database
 */
public class DummyDatabase implements Database {

    @Override
    public void onInsert(ShipMessage shipMessage) {
        System.out.printf("Inserted: %s \n", shipMessage.toString());
    }

    @Override
    public void onError(InputChannelException exception) {
        System.out.println(exception.getMessage());
    }
}

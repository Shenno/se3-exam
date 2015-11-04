package be.kdg.se3.exam.receiver.processor;

import be.kdg.se3.exam.receiver.broker.InputRabbitMQ;
import be.kdg.se3.exam.receiver.database.DummyDatabase;

/**
 * Created by   Shenno Willaert
 * Date         3/11/2015
 * Project      se3-exam
 * Package      be.kdg.se3.exam.receiver.processor
 */
public class Processor {
    private InputRabbitMQ input;
    private DummyDatabase database;
    private Buffer buffer;

    public Processor() {

    }

    public void start() {

    }
}

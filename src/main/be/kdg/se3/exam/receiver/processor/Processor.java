package be.kdg.se3.exam.receiver.processor;

import be.kdg.se3.exam.receiver.broker.InputRabbitMQ;
import be.kdg.se3.exam.receiver.broker.OutputRabbitMQ;
import be.kdg.se3.exam.receiver.converter.ObjectToXml;
import be.kdg.se3.exam.receiver.database.DummyDatabase;

/**
 * Created by   Shenno Willaert
 * Date         3/11/2015
 * Project      se3-exam
 * Package      be.kdg.se3.exam.receiver.processor
 */
public class Processor {
    private InputRabbitMQ inputPosMsgs;
    private DummyDatabase dbPosMsgs;
    private Buffer buffer;

    public Processor() {
        buffer = new Buffer();
        dbPosMsgs = new DummyDatabase(buffer);
        inputPosMsgs = new InputRabbitMQ("POSITION_MESSAGES", dbPosMsgs);
    }


    public void start() {
        inputPosMsgs.init();
        inputPosMsgs.startMonitoring();
    }

    public void createETA() {
        System.out.println(buffer.getShipMsgs().toString());
    }
}

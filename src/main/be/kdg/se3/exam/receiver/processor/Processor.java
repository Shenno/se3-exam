package be.kdg.se3.exam.receiver.processor;

import be.kdg.se3.exam.receiver.broker.InputShipMsgRabbitMQ;

/**
 * Created by   Shenno Willaert
 * Date         3/11/2015
 * Project      se3-exam
 * Package      be.kdg.se3.exam.receiver.processor
 */
public class Processor {
    private InputShipMsgRabbitMQ inputPosMsgs;

    public Processor() {
        this.inputPosMsgs = new InputShipMsgRabbitMQ("POSITION_MESSAGES");
    }


    public void start() {
        inputPosMsgs.init();
        inputPosMsgs.startMonitoring();
    }


}

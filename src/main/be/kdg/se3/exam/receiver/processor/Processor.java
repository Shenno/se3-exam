package be.kdg.se3.exam.receiver.processor;

import be.kdg.se3.exam.receiver.broker.InputRabbitMQ;

/**
 * Created by   Shenno Willaert
 * Date         3/11/2015
 * Project      se3-exam
 * Package      be.kdg.se3.exam.receiver.processor
 */
public class Processor {
    private InputRabbitMQ inputPosMsgs;
    private InputRabbitMQ inputIncidentMsgs;
    private ShipMessageHandler shipMessageHandler;
    private IncidentMessageHandler incidentMessageHandler;

    public Processor() {
        // Handlers
        this.shipMessageHandler = new ShipMessageHandler();
        this.incidentMessageHandler = new IncidentMessageHandler();
        // InputChannels
        this.inputPosMsgs = new InputRabbitMQ("POSITION_MESSAGES", this.shipMessageHandler);
        this.inputIncidentMsgs = new InputRabbitMQ("INCIDENT_MESSAGES", this.incidentMessageHandler);
    }


    public void start() {
        inputPosMsgs.init();
        inputPosMsgs.startMonitoring();

        inputIncidentMsgs.init();
        inputIncidentMsgs.startMonitoring();
    }


}

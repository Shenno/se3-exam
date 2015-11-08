package be.kdg.se3.exam.receiver.processor;

import be.kdg.se3.exam.receiver.broker.ChannelException;
import be.kdg.se3.exam.receiver.broker.InputRabbitMQ;
import org.apache.log4j.Logger;

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
    private final Logger logger = Logger.getLogger(this.getClass());

    public Processor() {
        // Handlers
        this.shipMessageHandler = new ShipMessageHandler();
        this.incidentMessageHandler = new IncidentMessageHandler();
        // InputChannels
        this.inputPosMsgs = new InputRabbitMQ("POSITION_MESSAGES", this.shipMessageHandler);
        this.inputIncidentMsgs = new InputRabbitMQ("INCIDENT_MESSAGES", this.incidentMessageHandler);
    }


    public void start() {
        try {
            inputPosMsgs.init();
            inputPosMsgs.startMonitoring();
            inputIncidentMsgs.init();
            inputIncidentMsgs.startMonitoring();
        } catch (ChannelException e) {
            logger.error("Channelexceptions occured while starting the Processor", e);
        }
    }


}

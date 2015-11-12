package be.kdg.se3.exam.receiver;

import be.kdg.se3.exam.receiver.broker.InputRabbitMQ;
import be.kdg.se3.exam.receiver.processor.*;

/**
 * Created by   Shenno Willaert
 * Date         10/11/2015
 * Project      se3-exam
 * Package      be.kdg.se3.exam.receiver
 */
public class ExamTest {
    public static void main(String[] argv) {
        // ETA
        ETAControllerImpl etaController = new ETAControllerImpl();
        etaController.addETAParameter("1234567", ETALogType.NEW_MSG);
        etaController.addETAParameter("1234568", ETALogType.NEW_POS);
        // Handlers
        ShipMessageHandler shipMessageHandler = new ShipMessageHandler(etaController);
        IncidentMessageHandler incidentMessageHandler = new IncidentMessageHandler();
        // InputChannels
        InputRabbitMQ inputPosMsgs = new InputRabbitMQ("POSITION_MESSAGES", shipMessageHandler);
        InputRabbitMQ inputIncidentMsgs = new InputRabbitMQ("INCIDENT_MESSAGES", incidentMessageHandler);
        Processor p = new Processor();
        p.addInputChannel(inputPosMsgs);
        p.addInputChannel(inputIncidentMsgs);
        p.start();
    }
}

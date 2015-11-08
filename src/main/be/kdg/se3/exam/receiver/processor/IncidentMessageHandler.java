package be.kdg.se3.exam.receiver.processor;

import be.kdg.se3.exam.receiver.broker.ChannelException;
import be.kdg.se3.exam.receiver.converter.JsonToShipInfo;
import be.kdg.se3.exam.receiver.converter.XmlToObject;
import be.kdg.se3.exam.receiver.entity.IncidentMessage;
import be.kdg.se3.exam.receiver.entity.IncidentReport;
import be.kdg.se3.exam.receiver.entity.ShipInfo;
import be.kdg.se3.exam.receiver.entity.ShipMessage;
import be.kdg.se3.exam.receiver.service.ShipService;
import org.apache.log4j.Logger;

/**
 * Created by   Shenno Willaert
 * Date         5/11/2015
 * Project      se3-exam
 * Package      be.kdg.se3.exam.receiver.processor
 */
public class IncidentMessageHandler implements MessageHandler {
    private ReportController reportController;
    private XmlToObject xmlToObject;
    private final Logger logger = Logger.getLogger(this.getClass());

    public IncidentMessageHandler() {
        reportController = new ReportController();
        xmlToObject = new XmlToObject();
    }
    public void handleMessage(String message) {
        IncidentMessage incidentMessage = null;
        try {
            incidentMessage = (IncidentMessage) xmlToObject.convert(message, IncidentMessage.class);
        } catch (ChannelException e) {
            logger.error("Conversionerror xml to IncidentMessage occured in IncidentMsgHandler", e);
        }
        reportController.createAndSendReport(incidentMessage);
    }

}

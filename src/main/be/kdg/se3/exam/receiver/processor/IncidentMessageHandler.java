package be.kdg.se3.exam.receiver.processor;

import be.kdg.se3.exam.receiver.converter.ConvertException;
import be.kdg.se3.exam.receiver.converter.XmlToObject;
import be.kdg.se3.exam.receiver.entity.IncidentMessage;
import org.apache.log4j.Logger;

/**
 * Created by   Shenno Willaert
 * Date         5/11/2015
 * Project      se3-exam
 * Package      be.kdg.se3.exam.receiver.processor
 */

/**
 * Class that handles the incoming incident messages.
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
        try {
            IncidentMessage incidentMessage = (IncidentMessage) xmlToObject.convert(message, IncidentMessage.class);
            if (reportController.getReportableIncidents().contains(incidentMessage.getIncidentType().toLowerCase())) {
                reportController.createAndSendReport(incidentMessage);
            }
        } catch ( ConvertException e ) {
            logger.error(e.getMessage(), e);
        }
    }
}

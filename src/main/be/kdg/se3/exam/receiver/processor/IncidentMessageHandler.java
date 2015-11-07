package be.kdg.se3.exam.receiver.processor;

import be.kdg.se3.exam.receiver.converter.JsonToShipInfo;
import be.kdg.se3.exam.receiver.converter.XmlToObject;
import be.kdg.se3.exam.receiver.entity.IncidentMessage;
import be.kdg.se3.exam.receiver.entity.IncidentReport;
import be.kdg.se3.exam.receiver.entity.ShipInfo;
import be.kdg.se3.exam.receiver.entity.ShipMessage;
import be.kdg.se3.exam.receiver.service.ShipService;

/**
 * Created by   Shenno Willaert
 * Date         5/11/2015
 * Project      se3-exam
 * Package      be.kdg.se3.exam.receiver.processor
 */
public class IncidentMessageHandler implements MessageHandler {
    private ReportController reportController;
    public IncidentMessageHandler() {
        reportController = new ReportController();
    }
    public void handleMessage(String message) {
        IncidentMessage incidentMessage = convertXml(message);
        reportController.createAndSendReport(incidentMessage);
    }



    private IncidentMessage convertXml(String xml) {
        XmlToObject converter = new XmlToObject();
        return (IncidentMessage) converter.convert(xml, IncidentMessage.class);
    }
}

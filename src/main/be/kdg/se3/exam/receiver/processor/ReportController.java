package be.kdg.se3.exam.receiver.processor;

/**
 * Created by   Shenno Willaert
 * Date         7/11/2015
 * Project      se3-exam
 * Package      be.kdg.se3.exam.receiver.processor
 */

import be.kdg.se3.exam.receiver.broker.OutputRabbitMQ;
import be.kdg.se3.exam.receiver.converter.JsonToShipInfo;
import be.kdg.se3.exam.receiver.converter.ObjectToXml;
import be.kdg.se3.exam.receiver.entity.IncidentMessage;
import be.kdg.se3.exam.receiver.entity.IncidentReport;
import be.kdg.se3.exam.receiver.entity.ShipInfo;
import be.kdg.se3.exam.receiver.service.ShipService;

/**
 * Class that handles the creating and sending of IncidentActionReports.
 */
public class ReportController {
    private JsonToShipInfo jsonToShipInfo;
    private ObjectToXml objectToXml;
    private ShipService shipService;
    private OutputRabbitMQ outputChannel;

    public ReportController() {
        jsonToShipInfo = new JsonToShipInfo();
        objectToXml = new ObjectToXml();
        shipService = new ShipService();
        outputChannel = new OutputRabbitMQ("INCIDENT_MESSAGES");
    }
    
    public void createAndSendReport(IncidentMessage incidentMsg) {
        IncidentReport report = createReport(incidentMsg);
        sendReport(report);
    }

    private void sendReport(IncidentReport report) {
        outputChannel.init();
        outputChannel.sendMessage(objectToXml.convert(report));
        outputChannel.stop();
    }

    private IncidentReport createReport(IncidentMessage incidentMsg) {
        String shipID = incidentMsg.getShipID();
        String incident = incidentMsg.getType();
        ShipInfo shipInfo = jsonToShipInfo.convert(shipService.callShipService(shipID));
        String action = decideAction(incident, shipInfo);
        return new IncidentReport(shipID, shipInfo, incident, action);
    }

    private String decideAction(String incident, ShipInfo shipInfo) {
        final String ALL_SHIPS_ANCHOR = "AlleSchepenVoorAnker";
        final String ALL_SHIPS_IN_ZONE_ANCHOR = "AlleSchepenInZoneVoorAnker";
        String action;
        switch ( incident.toLowerCase() ) {
            case "schade":
                if (shipInfo.isDangereousCargo()) {
                    action = ALL_SHIPS_ANCHOR;
                }
                else {
                    action = ALL_SHIPS_IN_ZONE_ANCHOR;
                }
                break;
            case "man overboord":
                action = ALL_SHIPS_ANCHOR;
                break;
            default:
                action = "UnknownIncident";
                // todo throw exception ofzo?

        }
        return action;
    }
}

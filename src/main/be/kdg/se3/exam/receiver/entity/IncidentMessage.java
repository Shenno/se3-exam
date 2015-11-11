package be.kdg.se3.exam.receiver.entity;

/**
 * Created by   Shenno Willaert
 * Date         7/11/2015
 * Project      se3-exam
 * Package      be.kdg.se3.exam.receiver.entity
 */

/**
 * Class that represents an incoming incident message.
 */
public class IncidentMessage {
    private String shipID;
    private String incidentType;

    public IncidentMessage() {

    }

    public IncidentMessage(String shipID, String type) {
        this.shipID = shipID;
        this.incidentType = type;
    }

    public String getShipID() {
        return shipID;
    }

    public void setShipID(String shipID) {
        this.shipID = shipID;
    }

    public String getIncidentType() {
        return incidentType;
    }

    public void setIncidentType(String incidentType) {
        this.incidentType = incidentType;
    }
}

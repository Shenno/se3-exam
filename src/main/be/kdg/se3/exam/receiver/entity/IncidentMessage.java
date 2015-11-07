package be.kdg.se3.exam.receiver.entity;

/**
 * Created by   Shenno Willaert
 * Date         7/11/2015
 * Project      se3-exam
 * Package      be.kdg.se3.exam.receiver.entity
 */
public class IncidentMessage {
    private String shipID;
    private String type;

    public IncidentMessage(String shipID, String type) {
        this.shipID = shipID;
        this.type = type;
    }

    public String getShipID() {
        return shipID;
    }

    public void setShipID(String shipID) {
        this.shipID = shipID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

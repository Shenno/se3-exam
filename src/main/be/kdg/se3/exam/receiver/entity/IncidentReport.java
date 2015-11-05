package be.kdg.se3.exam.receiver.entity;

import java.util.Collection;

/**
 * Created by   Shenno Willaert
 * Date         5/11/2015
 * Project      se3-exam
 * Package      be.kdg.se3.exam.receiver.entity
 */

public class IncidentReport {
    private String shipID;
    private String incident;
    private String action;
    private String imo;
    private boolean dangereousCargo;
    private int numberOfPassangers;

    public IncidentReport(String shipID, ShipInfo shipInfo, String incident, String action) {
        this.shipID = shipID;
        this.incident = incident;
        this.action = action;
        this.imo = shipInfo.getImo();
        this.dangereousCargo = shipInfo.isDangereousCargo();
        this.numberOfPassangers = shipInfo.getNumberOfPassangers();
    }

    public String getShipID() {
        return shipID;
    }

    public void setShipID(String shipID) {
        this.shipID = shipID;
    }

    public String getIncident() {
        return incident;
    }

    public void setIncident(String incident) {
        this.incident = incident;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getImo() {
        return imo;
    }

    public void setImo(String imo) {
        this.imo = imo;
    }

    public boolean isDangereousCargo() {
        return dangereousCargo;
    }

    public void setDangereousCargo(boolean dangereousCargo) {
        this.dangereousCargo = dangereousCargo;
    }

    public int getNumberOfPassangers() {
        return numberOfPassangers;
    }

    public void setNumberOfPassangers(int numberOfPassangers) {
        this.numberOfPassangers = numberOfPassangers;
    }
}

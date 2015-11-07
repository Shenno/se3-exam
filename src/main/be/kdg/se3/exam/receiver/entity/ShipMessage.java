package be.kdg.se3.exam.receiver.entity;

import java.util.Date;

/**
 * Created by   Shenno Willaert
 * Date         1/11/2015
 * Project      se3-exam
 * Package      be.kdg.se3.exam.receiver.entity
 */
public class ShipMessage {
    private String shipID;
    private String plant;
    private Date timeStamp;
    private int distanceToLoadingBay;

    public ShipMessage() {
    }

    public String getShipID() {
        return shipID;
    }

    public void setShipID(String shipID) {
        this.shipID = shipID;
    }

    public String getPlant() {
        return plant;
    }

    public void setPlant(String plant) {
        this.plant = plant;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public int getDistanceToLoadingBay() {
        return distanceToLoadingBay;
    }

    public void setDistanceToLoadingBay(int distanceToLoadingBay) {
        this.distanceToLoadingBay = distanceToLoadingBay;
    }

    @Override
    public String toString() {
        return "ShipMessage{" +
                "shipID='" + shipID + '\'' +
                ", plant='" + plant + '\'' +
                ", timeStamp=" + timeStamp +
                ", distanceToLoadingBay=" + distanceToLoadingBay +
                '}';
    }
}

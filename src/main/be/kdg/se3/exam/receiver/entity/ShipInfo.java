package be.kdg.se3.exam.receiver.entity;

import java.util.Collection;

/**
 * Created by   Shenno Willaert
 * Date         3/11/2015
 * Project      se3-exam
 * Package      be.kdg.se3.exam.receiver.entity
 */

/**
 * Class representing the info about a ship (passangers + cargo).
 */
public class ShipInfo {
    private String imo;
    private boolean dangereousCargo;
    private int numberOfPassangers;
    private Collection<CargoInfo> cargo;

    public ShipInfo(String imo, boolean dangereousCargo, int numberOfPassangers, Collection<CargoInfo> cargo) {
        this.imo = imo;
        this.dangereousCargo = dangereousCargo;
        this.numberOfPassangers = numberOfPassangers;
        this.cargo = cargo;
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

    public Collection<CargoInfo> getCargo() {
        return cargo;
    }

    public void setCargo(Collection<CargoInfo> cargo) {
        this.cargo = cargo;
    }

    @Override
    public String toString() {
        return "ShipInfo{" +
                "imo='" + imo + '\'' +
                ", dangereousCargo=" + dangereousCargo +
                ", numberOfPassangers=" + numberOfPassangers +
                ", cargo=" + cargo +
                '}';
    }
}

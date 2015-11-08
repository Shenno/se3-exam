package be.kdg.se3.exam.receiver.entity;

/**
 * Created by   Shenno Willaert
 * Date         5/11/2015
 * Project      se3-exam
 * Package      be.kdg.se3.exam.receiver.entity
 */

/**
 * Class to represent the cargo from a ship.
 */
public class CargoInfo {
    private String type;
    private int amount;

    public CargoInfo(String type, int amount) {
        this.type = type;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "CargoInfo{" +
                "type='" + type + '\'' +
                ", amount=" + amount +
                '}';
    }
}

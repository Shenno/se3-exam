package be.kdg.se3.exam.receiver.converter;

import be.kdg.se3.exam.receiver.entity.CargoInfo;
import be.kdg.se3.exam.receiver.entity.ShipInfo;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.IOError;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by   Shenno Willaert
 * Date         5/11/2015
 * Project      se3-exam
 * Package      be.kdg.se3.exam.receiver.converter
 */
public class JsonToShipInfo {
    public ShipInfo convert(String json) {
        try {
            JsonReader reader = Json.createReader(new StringReader(json));
            JsonObject jsonShipInfo = reader.readObject();
            reader.close();
            String imo = jsonShipInfo.getString("IMO");
            boolean dangereousCargo = jsonShipInfo.getBoolean("dangereousCargo");
            int numberOfPassengers = jsonShipInfo.getInt("numberOfPassangers");
            JsonArray cargoList = jsonShipInfo.getJsonArray("cargo");
            Collection<CargoInfo> cargoCollection = new ArrayList<>();
            for (int i = 0; i < cargoList.size(); i++) {
                JsonObject cargoInfo = cargoList.getJsonObject(i);
                String type = cargoInfo.getString("type");
                int amount = cargoInfo.getInt("amount");
                cargoCollection.add(new CargoInfo(type, amount));
            }
            return new ShipInfo(imo, dangereousCargo, numberOfPassengers, cargoCollection);

        } catch (Exception e) {
            throw new IOError(e.getCause());
        }
    }
}

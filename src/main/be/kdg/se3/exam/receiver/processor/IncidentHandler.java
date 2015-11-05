package be.kdg.se3.exam.receiver.processor;

import be.kdg.se3.exam.receiver.service.ShipService;

/**
 * Created by   Shenno Willaert
 * Date         5/11/2015
 * Project      se3-exam
 * Package      be.kdg.se3.exam.receiver.processor
 */
public class IncidentHandler {
    private ShipService service;

    public IncidentHandler() {
        this.service = new ShipService();
    }
    public void handleIncident(String message) {

    }
}

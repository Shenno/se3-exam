package be.kdg.se3.exam.receiver.processor;

import be.kdg.se3.exam.receiver.converter.XmlToObject;
import be.kdg.se3.exam.receiver.database.DummyDatabase;
import be.kdg.se3.exam.receiver.entity.ShipMessage;

/**
 * Created by   Shenno Willaert
 * Date         5/11/2015
 * Project      se3-exam
 * Package      be.kdg.se3.exam.receiver.processor
 */

/**
 * Class that handles the incoming shippositionmessages.
 */
public class ShipMessageHandler implements MessageHandler {
    private Buffer buffer;
    private DummyDatabase database;
    private ETAController etaController;

    public ShipMessageHandler() {
        buffer = new Buffer();
        database = new DummyDatabase();
        etaController = new ETAController();
        etaController.addETAParameter("1234567", ETALogType.NEW_MSG); //todo WEGDOEN
    }

    public void handleMessage(String message) {
        ShipMessage inputShipMsg = convertXml(message);
        database.onInsert(inputShipMsg);
        buffer.addMsg(inputShipMsg);
        etaController.checkETAStatus(inputShipMsg);
    }

    private ShipMessage convertXml(String xml) {
        XmlToObject converter = new XmlToObject();
        return (ShipMessage) converter.convert(xml, ShipMessage.class);
    }
}

package be.kdg.se3.exam.receiver.processor;

import be.kdg.se3.exam.receiver.broker.ChannelException;
import be.kdg.se3.exam.receiver.converter.XmlToObject;
import be.kdg.se3.exam.receiver.database.DummyDatabase;
import be.kdg.se3.exam.receiver.entity.ShipMessage;
import org.apache.log4j.Logger;

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
    private XmlToObject xmlToObject;
    private final Logger logger = Logger.getLogger(this.getClass());


    public ShipMessageHandler() {
        this.buffer = new Buffer();
        this.database = new DummyDatabase();
        this.etaController = new ETAController();
        this.xmlToObject = new XmlToObject();
        this.etaController.addETAParameter("1234567", ETALogType.NEW_MSG); //todo WEGDOEN
    }

    public void handleMessage(String message) {
        ShipMessage inputShipMsg = null;
        try {
            inputShipMsg = (ShipMessage) xmlToObject.convert(message, ShipMessage.class);
        } catch (ChannelException e) {
            logger.error("Conversion error xml to ShipMessage occured in handleMessage method", e);
        }
        database.onInsert(inputShipMsg);
        buffer.addMsg(inputShipMsg);
        etaController.checkETAStatus(inputShipMsg);
    }
}

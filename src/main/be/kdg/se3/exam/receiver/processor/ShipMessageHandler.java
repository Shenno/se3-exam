package be.kdg.se3.exam.receiver.processor;

import be.kdg.se3.exam.receiver.converter.ConvertException;
import be.kdg.se3.exam.receiver.converter.XmlToObject;
import be.kdg.se3.exam.receiver.database.DummyDatabase;
import be.kdg.se3.exam.receiver.entity.ShipMessage;
import org.apache.log4j.Logger;

import java.util.ArrayList;

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
    private ETAControllerInterface etaController;
    private XmlToObject xmlToObject;
    private final Logger logger = Logger.getLogger(this.getClass());

    public ShipMessageHandler(ETAControllerInterface etaController) {
        this.buffer = new Buffer();
        this.database = new DummyDatabase();
        this.etaController = etaController;
        this.xmlToObject = new XmlToObject();
    }

    public void handleMessage(String message) {
        try {
            ShipMessage inputShipMsg = (ShipMessage) xmlToObject.convert(message, ShipMessage.class);
            database.onInsert(inputShipMsg);
            buffer.addMsg(inputShipMsg);
            ArrayList<ShipMessage> shipMessages = buffer.getShipMsgs(inputShipMsg.getShipID());
            etaController.checkETAStatus(inputShipMsg, shipMessages);
        } catch (ConvertException e) {
            logger.error(e.getMessage(), e);
        }
    }
}

import be.kdg.se3.exam.receiver.broker.InputRabbitMQ;
import be.kdg.se3.exam.receiver.broker.OutputRabbitMQ;
import be.kdg.se3.exam.receiver.converter.ObjectToXml;
import be.kdg.se3.exam.receiver.database.DummyDatabase;
import be.kdg.se3.exam.receiver.entity.ShipMessage;
import be.kdg.se3.exam.receiver.processor.Processor;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by   Shenno Willaert
 * Date         2/11/2015
 * Project      se3-exam
 * Package      PACKAGE_NAME
 */
public class BigTestMain {
    public static void main(String[] argv) throws InterruptedException {
        new BigTestMain().init();
        Processor p = new Processor();
        p.start();
        Thread.sleep(1000);
        p.createETA();
        Thread.sleep(2000);
        p.createETA();
        Thread.sleep(10000);
        p.createETA();
    }

    public void init() {
        ShipMessage shipMessage = new ShipMessage();
        shipMessage.setShipID("1234578");
        shipMessage.setTimeStamp(new Date());
        shipMessage.setPlant("Banaan");
        shipMessage.setDistanceToLoadingBay(500);
        ShipMessage shipMessage2 = new ShipMessage();
        shipMessage2.setShipID("1234567");
        shipMessage2.setTimeStamp(new Date());
        shipMessage2.setDistanceToLoadingBay(400);
        ObjectToXml converter = new ObjectToXml();
        OutputRabbitMQ output = new OutputRabbitMQ("POSITION_MESSAGES");
        output.init();
        output.sendMessage(converter.convert(shipMessage));
        output.sendMessage(converter.convert(shipMessage2));
        output.stop();
    }


}

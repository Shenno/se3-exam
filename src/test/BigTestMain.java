import be.kdg.se3.exam.receiver.broker.InputRabbitMQ;
import be.kdg.se3.exam.receiver.broker.OutputRabbitMQ;
import be.kdg.se3.exam.receiver.converter.ObjectToXml;
import be.kdg.se3.exam.receiver.entity.ShipMessage;

import java.util.Date;

/**
 * Created by   Shenno Willaert
 * Date         2/11/2015
 * Project      se3-exam
 * Package      PACKAGE_NAME
 */
public class BigTestMain {
    public static void main(String[] argv) {
        new BigTestMain().init();
    }
    public void init() {
        ShipMessage shipMessage = new ShipMessage();
        shipMessage.setShipID("11111");
        shipMessage.setTimeStamp(new Date());
        shipMessage.setPlant("Banaan");
        shipMessage.setDistanceToLoadingBay(500);
        ShipMessage shipMessage2 = new ShipMessage();
        shipMessage2.setShipID("11112");
        shipMessage2.setTimeStamp(new Date());
        shipMessage2.setDistanceToLoadingBay(400);

        DummyDatabase dl = new DummyDatabase();
        InputRabbitMQ input = new InputRabbitMQ("hello", dl);
        input.init();
        input.startMonitoring();
        ObjectToXml converter = new ObjectToXml();
        OutputRabbitMQ output = new OutputRabbitMQ("hello");
        output.init();
        output.sendMessage(converter.convert(shipMessage));
        output.sendMessage(converter.convert(shipMessage2));
        output.stop();

    }
}

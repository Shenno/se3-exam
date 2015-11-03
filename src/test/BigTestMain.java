import be.kdg.se3.exam.receiver.broker.InputRabbitMQ;
import be.kdg.se3.exam.receiver.broker.OutputRabbitMQ;
import be.kdg.se3.exam.receiver.converter.ObjectToXml;
import be.kdg.se3.exam.receiver.database.DummyDatabase;
import be.kdg.se3.exam.receiver.entity.ShipMessage;

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
        shipMessage2.setShipID("11111");
        shipMessage2.setTimeStamp(new Date());
        shipMessage2.setDistanceToLoadingBay(400);

        DummyDatabase dl = new DummyDatabase();
        InputRabbitMQ input = new InputRabbitMQ("POSITION_MESSAGES", dl);
        input.init();
        input.startMonitoring();
        ObjectToXml converter = new ObjectToXml();
        OutputRabbitMQ output = new OutputRabbitMQ("POSITION_MESSAGES");
        output.init();
        output.sendMessage(converter.convert(shipMessage));
        output.sendMessage(converter.convert(shipMessage2));
        output.stop();
        sched(shipMessage);
    }

    private void sched(ShipMessage shipMessage) {
        final Timer timer = new Timer();

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                System.out.println(new Date().getTime()-shipMessage.getTimeStamp().getTime());
            }

        }, 5000, 5000);

    }
}

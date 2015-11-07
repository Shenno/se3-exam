import be.kdg.se3.exam.receiver.broker.OutputRabbitMQ;
import be.kdg.se3.exam.receiver.converter.JsonToShipInfo;
import be.kdg.se3.exam.receiver.converter.ObjectToXml;
import be.kdg.se3.exam.receiver.entity.IncidentReport;
import be.kdg.se3.exam.receiver.entity.ShipInfo;
import be.kdg.se3.exam.receiver.entity.ShipMessage;
import be.kdg.se3.exam.receiver.processor.Processor;
import be.kdg.se3.exam.receiver.service.ShipService;

import java.util.Date;

/**
 * Created by   Shenno Willaert
 * Date         2/11/2015
 * Project      se3-exam
 * Package      PACKAGE_NAME
 */
public class BigTestMain {
    public static void main(String[] argv) throws InterruptedException {
        Processor p = new Processor();
        p.start();
        Thread.sleep(2000);
        new BigTestMain().init();

    }

    public void init() throws InterruptedException {
        ShipMessage shipMessage = new ShipMessage();
        shipMessage.setShipID("1234567");
        shipMessage.setTimeStamp(new Date());
        shipMessage.setPlant("Banaan");
        shipMessage.setDistanceToLoadingBay(500);
        Thread.sleep(5000);
        ShipMessage shipMessage2 = new ShipMessage();
        shipMessage2.setShipID("1234567");
        shipMessage2.setPlant("Banaan");
        shipMessage2.setTimeStamp(new Date());
        shipMessage2.setDistanceToLoadingBay(400);
        ObjectToXml converter = new ObjectToXml();
        OutputRabbitMQ output = new OutputRabbitMQ("POSITION_MESSAGES");
        output.init();
        output.sendMessage(converter.convert(shipMessage));
        output.sendMessage(converter.convert(shipMessage2));
        output.stop();
    //    incidents();
    }

    private void incidents() {
        ShipService shipService = new ShipService();
        JsonToShipInfo conv = new JsonToShipInfo();

        ShipInfo shipInfo = conv.convert(shipService.callShipService("1234569"));
        System.out.println(shipInfo);
        IncidentReport report = new IncidentReport("1234569", shipInfo, "Kevin overboord","Bring baeck into the water");
        ObjectToXml xml = new ObjectToXml();
        System.out.println(xml.convert(report));
    }


}

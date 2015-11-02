package be.kdg.se3.exam.receiver;

import be.kdg.se3.exam.receiver.converter.ObjectToXml;
import be.kdg.se3.exam.receiver.converter.XmlToObject;
import be.kdg.se3.exam.receiver.entity.ShipMessage;
import org.exolab.castor.xml.Marshaller;

import java.io.StringWriter;
import java.io.Writer;

/**
 * Created by   Shenno Willaert
 * Date         31/10/2015
 * Project      se3-exam
 * Package      be.kdg.se3.receiver
 */


public class Init {
    public void Test() {
        ShipMessage shipMessage = new ShipMessage();
       // shipMessage.setId("smooi");
        ObjectToXml objectToXml = new ObjectToXml();
        String lol = objectToXml.convert(shipMessage);
        System.out.println(lol);
        XmlToObject xmlToObject = new XmlToObject();
        ShipMessage sm  = (ShipMessage)xmlToObject.convert(lol, ShipMessage.class);
       // System.out.println(sm.getId());



    }
}

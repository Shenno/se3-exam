package be.kdg.se3.exam.receiver.converter;

import be.kdg.se3.exam.receiver.entity.ShipMessage;
import org.exolab.castor.xml.MarshalException;
import org.exolab.castor.xml.Unmarshaller;
import org.exolab.castor.xml.ValidationException;

import java.io.Reader;
import java.io.StringReader;

/**
 * Created by   Shenno Willaert
 * Date         2/11/2015
 * Project      se3-exam
 * Package      be.kdg.se3.exam.receiver.converter
 */
public class XmlToObject {
    public Object convert(String xml, Class c) {
        Reader reader = new StringReader(xml);
        Object object = new Object();
        try {
            object = Unmarshaller.unmarshal(c, reader);
        } catch (MarshalException e) {
            e.printStackTrace();
        } catch (ValidationException e) {
            e.printStackTrace();
        }
        return object;
    }
}

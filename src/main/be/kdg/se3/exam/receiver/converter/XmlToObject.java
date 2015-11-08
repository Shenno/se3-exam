package be.kdg.se3.exam.receiver.converter;

import be.kdg.se3.exam.receiver.broker.ChannelException;
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

/**
 * Class used to convert xml string to an object.
 */
public class XmlToObject {
    public Object convert(String xml, Class c) throws ChannelException {
        Reader reader = new StringReader(xml);
        Object object;
        try {
            object = Unmarshaller.unmarshal(c, reader);
            return object;
        } catch (Exception e) {
            throw new ChannelException("Error occured while converting xml to an object", e);
        }

    }
}

package be.kdg.se3.exam.receiver.converter;

import org.exolab.castor.xml.MarshalException;
import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.ValidationException;

import java.io.StringWriter;
import java.io.Writer;

/**
 * Created by   Shenno Willaert
 * Date         1/11/2015
 * Project      se3-exam
 * Package      be.kdg.se3.exam.receiver.converter
 */

/**
 * Class to convert any type of object to xml.
 */
public class ObjectToXml {
    public ObjectToXml() {
    }

    public String convert(Object o) throws ConvertException {
        try {
            Writer writer = new StringWriter();
            Marshaller.marshal(o, writer);
            return writer.toString();
        } catch (Exception e) {
            throw new ConvertException("Error occurred while converting object to xml", e);
        }
    }
}

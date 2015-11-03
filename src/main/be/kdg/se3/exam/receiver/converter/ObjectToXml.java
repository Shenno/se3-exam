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
public class ObjectToXml {
    public ObjectToXml() {
    }
    public String convert(Object o)
    {
        Writer writer = new StringWriter();
        try {
            Marshaller.marshal(o, writer);
        }
        catch (Exception e) {

        }
        return writer.toString();
    }
}

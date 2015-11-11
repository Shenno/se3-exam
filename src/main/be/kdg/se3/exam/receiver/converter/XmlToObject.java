package be.kdg.se3.exam.receiver.converter;

import org.exolab.castor.xml.Unmarshaller;

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
    public Object convert(String xml, Class c) throws ConvertException {
        Reader reader = new StringReader(xml);
        Object object;
        try {
            object = Unmarshaller.unmarshal(c, reader);
            return object;
        } catch (Exception e) {
            throw new ConvertException("Error occured while converting xml to an object", e);
        }
    }
}

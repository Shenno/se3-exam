import be.kdg.se3.exam.receiver.broker.InputChannelException;
import be.kdg.se3.exam.receiver.converter.XmlToObject;
import be.kdg.se3.exam.receiver.database.DatabaseListener;
import be.kdg.se3.exam.receiver.entity.ShipMessage;

/**
 * Created by   Shenno Willaert
 * Date         2/11/2015
 * Project      se3-exam
 * Package      PACKAGE_NAME
 */
public class DummyDatabase implements DatabaseListener {
    @Override
    public void onInsert(String s) {
        XmlToObject converter = new XmlToObject();
        ShipMessage shipMessage = (ShipMessage) converter.convert(s, ShipMessage.class);
        System.out.printf("Inserted: %s \n", shipMessage.toString());
    }

    @Override
    public void onError(InputChannelException exception) {
        System.out.println(exception.getMessage());
    }
}

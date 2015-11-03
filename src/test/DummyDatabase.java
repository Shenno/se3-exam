import be.kdg.se3.exam.receiver.broker.InputChannelException;
import be.kdg.se3.exam.receiver.converter.XmlToObject;
import be.kdg.se3.exam.receiver.database.DatabaseListener;
import be.kdg.se3.exam.receiver.entity.ShipMessage;
import be.kdg.se3.exam.receiver.processor.Buffer;

/**
 * Created by   Shenno Willaert
 * Date         2/11/2015
 * Project      se3-exam
 * Package      PACKAGE_NAME
 */
public class DummyDatabase implements DatabaseListener {
    private Buffer buffer;
    public DummyDatabase() {
        this.buffer = new Buffer(1,1);
    }

    @Override
    public void onInsert(String s) {
        XmlToObject converter = new XmlToObject();
        ShipMessage shipMessage = (ShipMessage) converter.convert(s, ShipMessage.class);
        System.out.printf("Inserted: %s \n", shipMessage.toString());
        this.buffer.addMsg(shipMessage);
    }

    @Override
    public void onError(InputChannelException exception) {
        System.out.println(exception.getMessage());
    }
}

package distribution;

import java.io.*;

public class Marshaller {

    public static byte[] marshall(Object obj) throws IOException {
        ByteArrayOutputStream byteArrayOutput = new ByteArrayOutputStream();
        ObjectOutput objOutput = new ObjectOutputStream(byteArrayOutput);
        objOutput.writeObject(obj);

        return byteArrayOutput.toByteArray();
    }

    public static Object unmarshall(byte[] obj) throws ClassNotFoundException, IOException  {
        ByteArrayInputStream byteArrayInput = new ByteArrayInputStream(obj);
        ObjectInput objInput = new ObjectInputStream(byteArrayInput);

        return objInput.readObject();
    }
}

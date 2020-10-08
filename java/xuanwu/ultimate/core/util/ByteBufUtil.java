package xuanwu.ultimate.core.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.ByteBuffer;

public class ByteBufUtil
  implements Serializable
{
  public static byte[] getBytes(Serializable obj)
    throws IOException
  {
    ByteArrayOutputStream bout = new ByteArrayOutputStream();
    ObjectOutputStream out = new ObjectOutputStream(bout);
    out.writeObject(obj);
    out.flush();
    byte[] bytes = bout.toByteArray();
    bout.close();
    out.close();
    return bytes;
  }
  
  public static int sizeof(Serializable obj)
    throws IOException
  {
    return getBytes(obj).length;
  }
  
  public static Object getObject(byte[] bytes)
    throws IOException, ClassNotFoundException
  {
    ByteArrayInputStream bi = new ByteArrayInputStream(bytes);
    ObjectInputStream oi = new ObjectInputStream(bi);
    Object obj = oi.readObject();
    bi.close();
    oi.close();
    return obj;
  }
  
  public static Object getObject(ByteBuffer byteBuffer)
    throws ClassNotFoundException, IOException
  {
    InputStream input = new ByteArrayInputStream(byteBuffer.array());
    ObjectInputStream oi = new ObjectInputStream(input);
    Object obj = oi.readObject();
    input.close();
    oi.close();
    byteBuffer.clear();
    return obj;
  }
  
  public static ByteBuffer getByteBuffer(Serializable obj)
    throws IOException
  {
    byte[] bytes = getBytes(obj);
    ByteBuffer buff = ByteBuffer.wrap(bytes);
    return buff;
  }
  
  public static byte[] toByte(Object obj)
  {
    byte[] bytes = null;
    ByteArrayOutputStream bos = new ByteArrayOutputStream();
    try
    {
      ObjectOutputStream oos = new ObjectOutputStream(bos);
      oos.writeObject(obj);
      oos.flush();
      bytes = bos.toByteArray();
      oos.close();
      bos.close();
    }
    catch (IOException ex)
    {
      ex.printStackTrace();
    }
    return bytes;
  }
  
  public static Object toObject(byte[] bytes)
  {
    Object obj = null;
    try
    {
      ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
      ObjectInputStream ois = new ObjectInputStream(bis);
      obj = ois.readObject();
      ois.close();
      bis.close();
    }
    catch (IOException ex)
    {
      ex.printStackTrace();
    }
    catch (ClassNotFoundException ex)
    {
      ex.printStackTrace();
    }
    return obj;
  }
}

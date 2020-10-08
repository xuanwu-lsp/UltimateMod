package xuanwu.ultimate.core.Data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class StoredData
{
  public File file;
  
  public StoredData(String file)
  {
    this.file = new File("xuanwu/ultimate/data/" + file);
  }
  
  public void set(Object key)
  {
    try
    {
      File file = new File("xuanwu/ultimate/data/" + this.file.getName() + ".ultimatedata");
      if (!file.exists())
      {
    	file.mkdirs();
        file.createNewFile();
        file.delete();
        file.createNewFile();
      }
      else
      {
        file.delete();
        file.createNewFile();
      }
      FileOutputStream fos = new FileOutputStream(file);
      ObjectOutputStream oos = new ObjectOutputStream(fos);
      oos.writeObject(key);
      oos.close();
    }
    catch (Exception exp) {
    	exp.printStackTrace();
    }
  }
  
  public void remove(String key)
  {
    try
    {
      File file = new File("xuanwu/ultimate/data/" + this.file.getName() + ".ultimatedata");
      if (file.exists()) {
        file.delete();
      }
    }
    catch (Exception localException) {}
  }
  
  public Object Read()
  {
    try
    {
      File file = new File("xuanwu/ultimate/data/" + this.file.getName() + ".ultimatedata");
      if (!file.exists()) {
        return null;
      }
      FileInputStream fip = new FileInputStream(file);
      ObjectInputStream ois = new ObjectInputStream(fip);
      Object object = ois.readObject();
      ois.close();
      return object;
    }
    catch (Exception exp) {}
    return null;
  }
}

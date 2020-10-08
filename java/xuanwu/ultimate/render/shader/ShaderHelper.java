package xuanwu.ultimate.render.shader;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.world.World;
import org.lwjgl.opengl.ARBShaderObjects;
import xuanwu.ultimate.render.shader.callback.ShaderCallback;

@SideOnly(Side.CLIENT)
public final class ShaderHelper
{
  private static final int VERT = 35633;
  private static final int FRAG = 35632;
  private static final String PREFIX = "/assets/avaritia/shader/";
  public static int cosmicShader = 0;
  
  public static void initShaders()
  {
    if (!useShaders()) {
      return;
    }
    cosmicShader = createProgram("cosmic.vert", "cosmic.frag");
  }
  
  public static void useShader(int shader, ShaderCallback callback)
  {
    if (!useShaders()) {
      return;
    }
    ARBShaderObjects.glUseProgramObjectARB(shader);
    if (shader != 0)
    {
      int time = ARBShaderObjects.glGetUniformLocationARB(shader, "time");
      Minecraft mc = Minecraft.getMinecraft();
      if ((mc.thePlayer != null) && (mc.thePlayer.worldObj != null)) {
        ARBShaderObjects.glUniform1iARB(time, (int)(mc.thePlayer.worldObj.getWorldTime() % 2147483647L));
      }
      if (callback != null) {
        callback.call(shader);
      }
    }
  }
  
  public static void useShader(int shader)
  {
    useShader(shader, null);
  }
  
  public static void releaseShader()
  {
    useShader(0);
  }
  
  public static boolean useShaders()
  {
    return OpenGlHelper.shadersSupported;
  }
  
  private static int createProgram(String vert, String frag)
  {
    int vertId = 0;int fragId = 0;int program = 0;
    if (vert != null) {
      vertId = createShader("/assets/avaritia/shader/" + vert, 35633);
    }
    if (frag != null) {
      fragId = createShader("/assets/avaritia/shader/" + frag, 35632);
    }
    program = ARBShaderObjects.glCreateProgramObjectARB();
    if (program == 0) {
      return 0;
    }
    if (vert != null) {
      ARBShaderObjects.glAttachObjectARB(program, vertId);
    }
    if (frag != null) {
      ARBShaderObjects.glAttachObjectARB(program, fragId);
    }
    ARBShaderObjects.glLinkProgramARB(program);
    if (ARBShaderObjects.glGetObjectParameteriARB(program, 35714) == 0) {
      return 0;
    }
    ARBShaderObjects.glValidateProgramARB(program);
    if (ARBShaderObjects.glGetObjectParameteriARB(program, 35715) == 0) {
      return 0;
    }
    return program;
  }
  
  private static int createShader(String filename, int shaderType)
  {
    int shader = 0;
    try
    {
      shader = ARBShaderObjects.glCreateShaderObjectARB(shaderType);
      if (shader == 0) {
        return 0;
      }
      ARBShaderObjects.glShaderSourceARB(shader, readFileAsString(filename));
      ARBShaderObjects.glCompileShaderARB(shader);
      if (ARBShaderObjects.glGetObjectParameteriARB(shader, 35713) == 0) {
        throw new RuntimeException("Error creating shader \"" + filename + "\": " + getLogInfo(shader));
      }
      return shader;
    }
    catch (Exception e)
    {
      ARBShaderObjects.glDeleteObjectARB(shader);
      e.printStackTrace();
    }
    return -1;
  }
  
  private static String getLogInfo(int obj)
  {
    return ARBShaderObjects.glGetInfoLogARB(obj, ARBShaderObjects.glGetObjectParameteriARB(obj, 35716));
  }
  
  private static String readFileAsString(String filename)
    throws Exception
  {
    StringBuilder source = new StringBuilder();
    InputStream in = ShaderHelper.class.getResourceAsStream(filename);
    Exception exception = null;
    if (in == null) {
      return "";
    }
    do
    {
      try
      {
        for (;;)
        {
          BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
          
          try
          {
            String line;
            if ((line = reader.readLine()) != null)
            {
              source.append(line).append('\n');
              try
              {
                reader.close();
              }
              catch (Exception exc)
              {

              }
              try
              {
                in.close();
              }
              catch (Exception exc)
              {
                if (exception == null) {
                  exception = exc;
                } else {
                  exc.printStackTrace();
                }
              }
              if (exception != null) {
                throw exception;
              }
            }
            else
            {
              try
              {
                reader.close();
              }
              catch (Exception exc)
              {
              }

            }
          }
          catch (Exception exc)
          {
            exception = exc;
          }
          finally {}
        }
      }
      catch (Exception exc)
      {
        do
        {
          Exception innerExc;
          exception = exc;
          try
          {
            in.close();
          }
          catch (Exception exc1)
          {
            if (exception == null) {
              exception = exc1;
            } else {
              exc1.printStackTrace();
            }
          }
        } while (exception == null);
        throw exception;
      }
      finally
      {
        try
        {
          in.close();
        }
        catch (Exception exc)
        {
          if (exception == null) {
            exception = exc;
          } else {
            exc.printStackTrace();
          }
        }
      }
    } while (exception == null);
  }
}

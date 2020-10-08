package xuanwu.ultimate.render;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import net.minecraftforge.client.model.obj.Face;
import net.minecraftforge.client.model.obj.GroupObject;
import net.minecraftforge.client.model.obj.WavefrontObject;

public class Util
{
  public static void replaceFace(WavefrontObject obj)
  {
    for (GroupObject group : obj.groupObjects)
    {
      ArrayList<Face> newFaces = Lists.newArrayList();
      for (Face face : group.faces) {
        newFaces.add(new FaceEx(face));
      }
      group.faces = newFaces;
    }
  }
}

package xuanwu.ultimate.core.Data;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.entity.Entity;

public class NoUpdateList
{
  public static List<Entity> NoUpdateEntities = new ArrayList();
  
  public static boolean isNoUpdate(Entity entity)
  {
    if (NoUpdateEntities.indexOf(entity) == -1) {
      return false;
    }
    return true;
  }
}

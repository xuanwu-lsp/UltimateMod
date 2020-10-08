package xuanwu.ultimate.Potion;

import cpw.mods.fml.relauncher.ReflectionHelper;
import java.lang.reflect.Field;
import java.util.ArrayList;
import net.minecraft.potion.Potion;

public class PotionHelper
{
  private static ArrayList<Potion> badPotions;
  
  public static void healthInspection()
  {
    badPotions = new ArrayList();
    try
    {
      Field stupidMojangPrivateVariable = ReflectionHelper.findField(Potion.class, new String[] { "isBadEffect", "field_76418_K" });
      for (Potion potion : Potion.potionTypes) {
        if ((potion != null) && (stupidMojangPrivateVariable.getBoolean(potion))) {
          badPotions.add(potion);
        }
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
  
  public static boolean badPotion(Potion effect)
  {
    return badPotions.contains(effect);
  }
}

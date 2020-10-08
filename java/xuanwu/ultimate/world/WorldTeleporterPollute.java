package xuanwu.ultimate.world;

import net.minecraft.entity.Entity;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;

public class WorldTeleporterPollute
  extends Teleporter
{
  public WorldTeleporterPollute(WorldServer p_i1963_1_)
  {
    super(p_i1963_1_);
  }
  
  public boolean placeInExistingPortal(Entity p_77184_1_, double p_77184_2_, double p_77184_4_, double p_77184_6_, float p_77184_8_)
  {
    return false;
  }
  
  public void placeInPortal(Entity p_77185_1_, double p_77185_2_, double p_77185_4_, double p_77185_6_, float p_77185_8_) {}
  
  public boolean makePortal(Entity p_85188_1_)
  {
    return false;
  }
  
  public void removeStalePortalLocations(long p_85189_1_) {}
}

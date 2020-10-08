package xuanwu.ultimate;

import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public abstract interface IUltimateChestItem
{
  public abstract void updateInAlchChest(World paramWorld, int paramInt1, int paramInt2, int paramInt3, ItemStack paramItemStack);
}

package xuanwu.ultimate.Inventory;

import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntityEnderChest;

public class InventoryUltimateChest
  extends InventoryBasic
{
  private TileEntityEnderChest associatedChest;
  private static final String __OBFID = "CL_00001759";
  
  public InventoryUltimateChest()
  {
    super("ultimate.ultimatechest", false, 64);
  }
  
  public void func_146031_a(TileEntityEnderChest p_146031_1_)
  {
    this.associatedChest = p_146031_1_;
  }
  
  public void loadInventoryFromNBT(NBTTagList p_70486_1_)
  {
    for (int i = 0; i < getSizeInventory(); i++) {
      setInventorySlotContents(i, (ItemStack)null);
    }
    for (int i = 0; i < p_70486_1_.tagCount(); i++)
    {
      NBTTagCompound nbttagcompound = p_70486_1_.getCompoundTagAt(i);
      int j = nbttagcompound.getByte("Slot") & 0xFF;
      if ((j >= 0) && (j < getSizeInventory())) {
        setInventorySlotContents(j, ItemStack.loadItemStackFromNBT(nbttagcompound));
      }
    }
  }
  
  public NBTTagList saveInventoryToNBT()
  {
    NBTTagList nbttaglist = new NBTTagList();
    for (int i = 0; i < getSizeInventory(); i++)
    {
      ItemStack itemstack = getStackInSlot(i);
      if (itemstack != null)
      {
        NBTTagCompound nbttagcompound = new NBTTagCompound();
        nbttagcompound.setByte("Slot", (byte)i);
        itemstack.writeToNBT(nbttagcompound);
        nbttaglist.appendTag(nbttagcompound);
      }
    }
    return nbttaglist;
  }
  
  public void openInventory()
  {
    if (this.associatedChest != null) {
      this.associatedChest.func_145969_a();
    }
    super.openInventory();
  }
  
  public void closeInventory()
  {
    if (this.associatedChest != null) {
      this.associatedChest.func_145970_b();
    }
    super.closeInventory();
    this.associatedChest = null;
  }
}

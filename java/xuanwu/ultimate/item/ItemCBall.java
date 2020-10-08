package xuanwu.ultimate.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import xuanwu.ultimate.core.Data.Data;
import xuanwu.ultimate.core.ultimateCore;
import xuanwu.ultimate.register.Register;

public class ItemCBall
  extends Item
{
  public ItemCBall()
  {
    setTextureName("ultimate:CBall");
    setCreativeTab(Register.tab);
    setUnlocalizedName("CBall");
    setMaxStackSize(1);
  }
  
  public List<ItemStack> inventory = new ArrayList();
  
  @SideOnly(Side.CLIENT)
  public String getItemStackDisplayName(ItemStack itemStack)
  {
    return "物质球";
  }
  
  @SideOnly(Side.CLIENT)
  public void addInformation(ItemStack item, EntityPlayer player, List list, boolean b1)
  {
    super.addInformation(item, player, list, b1);
    for (ItemStack stack : this.inventory) {
      list.add(stack.getDisplayName() + "x" + String.valueOf(stack.stackSize));
    }
  }
  
  protected int i = 0;
  
  public boolean onEntitySwing(EntityLivingBase entityLiving, ItemStack stack)
  {
    this.i += 1;
    if (this.i < 2) {
      return true;
    }
    this.i = 0;
    if ((entityLiving instanceof EntityPlayer))
    {
      EntityPlayer player = (EntityPlayer)entityLiving;
      if (!Data.isSNItem)
      {
        player.addChatComponentMessage(ultimateCore.Text("开始收纳物品"));
        Data.isSNItem = true;
      }
      else
      {
        player.addChatComponentMessage(ultimateCore.Text("停止收纳物品"));
        Data.isSNItem = false;
      }
    }
    return true;
  }
  
  public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
  {
    super.onItemRightClick(itemStack, world, player);
    try
    {
      if (!player.isSneaking())
      {
        for (ItemStack stack : this.inventory)
        {
          player.inventory.addItemStackToInventory(stack);
          this.inventory.remove(stack);
        }
        player.inventory.mainInventory[player.inventory.currentItem] = null;
      }
    }
    catch (Exception localException1) {}
    return itemStack;
  }
}

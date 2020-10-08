package xuanwu.ultimate.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import xuanwu.ultimate.ServerLudicrousText;
import xuanwu.ultimate.core.ultimateCore;
import xuanwu.ultimate.net.WebPage;
import xuanwu.ultimate.register.Register;

public class ItemMY
  extends Item
{
  public ItemMY()
  {
    setTextureName("ultimate:heal");
    setCreativeTab(Register.tab);
    setUnlocalizedName("MY");
    setMaxStackSize(1);
  }
  
  @SideOnly(Side.CLIENT)
  public String getItemStackDisplayName(ItemStack itemStack)
  {
    return "名言";
  }
  
  public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
  {
    super.onItemRightClick(itemStack, world, player);
    WebPage page = new WebPage();
    String[] url = { "https://v1.hitokoto.cn/?c=a&encode=text", "https://v1.hitokoto.cn/?c=b&encode=text", "https://v1.hitokoto.cn/?c=c&encode=text", "https://v1.hitokoto.cn/?c=d&encode=text", "https://v1.hitokoto.cn/?c=e&encode=text", "https://v1.hitokoto.cn/?c=f&encode=text", "https://v1.hitokoto.cn/?c=g&encode=text" };
    
    Random rd = new Random();
    int i = rd.nextInt(url.length);
    page.setPageUrl(url[i]);
    String code = page.getPageSourceWithoutHtml();
    if (!code.isEmpty()) {
      ultimateCore.ChatPrint("<" + player.getDisplayName() + "> " + ServerLudicrousText.makeFabulous(code));
    }
    return itemStack;
  }
}

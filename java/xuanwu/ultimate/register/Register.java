package xuanwu.ultimate.register;

import java.util.ArrayList;
import java.util.List;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.IFuelHandler;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.eventhandler.EventBus;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.NetworkRegistry.TargetPoint;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.block.Block;
import net.minecraft.block.BlockOre;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemRecord;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.potion.Potion;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.FakePlayer;
import xuanwu.ultimate.Achivenent.UltimateAchivenent;
import xuanwu.ultimate.CreativeTabs.UltimateTab;
import xuanwu.ultimate.Potion.PotionHelper;
import xuanwu.ultimate.OreGeneration;
import xuanwu.ultimate.UltimateMod;
import xuanwu.ultimate.block.AttackBlock;
import xuanwu.ultimate.block.BlockUltimateTNT;
import xuanwu.ultimate.block.BulletBlock;
import xuanwu.ultimate.block.CannonBlock;
import xuanwu.ultimate.block.DiamondDamageBlock;
import xuanwu.ultimate.block.DogeBlock;
import xuanwu.ultimate.block.EmeraldDamageBlock;
import xuanwu.ultimate.block.ExpBlock;
import xuanwu.ultimate.block.FireBlock;
import xuanwu.ultimate.block.GoldDamageBlock;
import xuanwu.ultimate.block.HealBlock;
import xuanwu.ultimate.block.IronDamageBlock;
import xuanwu.ultimate.block.LightningBoltBlock;
import xuanwu.ultimate.block.SpeedBlock;
import xuanwu.ultimate.block.WitherTurrentBlock;
import xuanwu.ultimate.block.ultimateBlock;
import xuanwu.ultimate.command.command;
import xuanwu.ultimate.core.ultimateCore;
import xuanwu.ultimate.entity.DummyWither;
import xuanwu.ultimate.entity.Entity303;
import xuanwu.ultimate.entity.EntityAccelerator;
import xuanwu.ultimate.entity.EntityDS;
import xuanwu.ultimate.entity.EntityDummy;
import xuanwu.ultimate.entity.EntityFont;
import xuanwu.ultimate.entity.EntityHanling;
import xuanwu.ultimate.entity.EntityHerobrine;
import xuanwu.ultimate.entity.EntityKongJvMoWang;
import xuanwu.ultimate.entity.EntityLaser;
import xuanwu.ultimate.entity.EntityNotch;
import xuanwu.ultimate.entity.EntityNull;
import xuanwu.ultimate.entity.EntityPaojie;
import xuanwu.ultimate.entity.EntitySteve;
import xuanwu.ultimate.entity.UltimateChicken;
import xuanwu.ultimate.entity.tile.AttackBlockTile;
import xuanwu.ultimate.entity.tile.CannonTile;
import xuanwu.ultimate.entity.tile.DamageBlockTile;
import xuanwu.ultimate.entity.tile.FireBlockTile;
import xuanwu.ultimate.entity.tile.TileDoge;
import xuanwu.ultimate.entity.tile.TileExp;
import xuanwu.ultimate.entity.tile.TileHeal;
import xuanwu.ultimate.entity.tile.TileLightningBlock;
import xuanwu.ultimate.entity.tile.TileSpeed;
import xuanwu.ultimate.entity.tile.UnbreakableTile;
import xuanwu.ultimate.entity.tile.WitherTurrentTile;
import xuanwu.ultimate.item.*;
import xuanwu.ultimate.item.Fuel.InfinityFuel;
import xuanwu.ultimate.item.Record.Record;
import xuanwu.ultimate.item.food.BatSoup;
import xuanwu.ultimate.item.food.Coffe;
import xuanwu.ultimate.item.food.DoveSoup;
import xuanwu.ultimate.item.food.Hamburger;
import xuanwu.ultimate.item.food.RainbowApple;
import xuanwu.ultimate.listeners.Attack;
import xuanwu.ultimate.listeners.EnchantmentEventHandler;
import xuanwu.ultimate.listeners.EventHandler;
import xuanwu.ultimate.listeners.LivingAttack;
import xuanwu.ultimate.listeners.LivingDeath;
import xuanwu.ultimate.listeners.LivingFall;
import xuanwu.ultimate.listeners.LivingHurt;
import xuanwu.ultimate.listeners.LivingUpdate;
import xuanwu.ultimate.listeners.PickUpItem;
import xuanwu.ultimate.listeners.PlayerJoinEvent;
import xuanwu.ultimate.listeners.PotionEventHandler;
import xuanwu.ultimate.listeners.TimeStop;
import xuanwu.ultimate.listeners.UltimateTimer;
import xuanwu.ultimate.net.Packets.PacketBreakBlock;
import xuanwu.ultimate.net.Packets.PacketBreakBlock.Handler;
import xuanwu.ultimate.net.Packets.PacketKeyKillAura;
import xuanwu.ultimate.net.Packets.PacketNoDeadGui;
import xuanwu.ultimate.net.Packets.PacketRespawn;
import xuanwu.ultimate.net.Packets.PacketSetConfig;
import xuanwu.ultimate.net.Packets.PacketSpawnEntity;
import xuanwu.ultimate.net.Packets.PacketTimeStop;
import xuanwu.ultimate.world.WorldProviderPollute;

public class Register
{
  public static final List<Enchantment> EnchantmentList = new ArrayList<Enchantment>();
  public static final CreativeTabs tab = new UltimateTab(UltimateMod.MODID);
  public static SimpleNetworkWrapper instance = NetworkRegistry.INSTANCE.newSimpleChannel(UltimateMod.MODID);
  public static Item ultimatesword;
  public static Block UltimateBlock;
  public static ItemFood RainbowApple;
  public static Item totem;
  public static ItemFood DoveSoup;
  public static ItemFood BatSoup;
  public static Item egg;
  public static Potion wuhanvirus;
  public static ItemFood Coffe;
  public static Item InfinityFuel;
  public static ItemFood Hamburger;
  public static ItemPickaxe LavaPickaxe;
  public static ItemRecord MusicRecord;
  public static Item Lava_Ingot;
  public static ItemSword LavaSword;
  public static ItemHoe LavaHoe;
  public static ItemSpade LavaShovel;
  public static ItemAxe LavaAxe;
  public static ItemArmor LavaHelmet;
  public static ItemArmor LavaChest;
  public static ItemArmor LavaLeggins;
  public static ItemArmor LavaBoots;
  public static ItemSword Coal_Sword;
  public static ItemSword Glass_Sword;
  public static Item BigHeal;
  public static ItemArmor CoalHelmet;
  public static ItemArmor CoalChest;
  public static ItemArmor CoalLeggings;
  public static ItemArmor CoalBoots;
  public static Item ItemHorseStack;
  public static Item ItemDead;
  public static Item ItemBB;
  public static ItemArmor NMSLHelmet;
  public static ItemArmor NMSLChest;
  public static ItemArmor NMSLLeggings;
  public static ItemArmor NMSLBoots;
  public static ItemPickaxe ItemCoalPickaxe;
  public static ItemAxe CoalAxe;
  public static ItemSpade ItemCoalShovel;
  public static ItemHoe CoalHoe;
  public static Item praticle;
  public static ItemArmor WoodHelmet;
  public static ItemArmor WoodChest;
  public static ItemArmor ItemWoodLeggings;
  public static ItemArmor WoodBoots;
  public static ItemArmor StoneHelmet;
  public static ItemArmor StoneChest;
  public static ItemArmor StoneLeggings;
  public static ItemArmor StoneBoots;
  public static ItemSword EmeraldSword;
  public static ItemArmor EmeraldHelmet;
  public static ItemArmor EmeraldChest;
  public static ItemArmor EmeraldLeggings;
  public static ItemArmor EmeraldBoots;
  public static ItemArmor DyeHelmet;
  public static ItemArmor DyeChest;
  public static ItemArmor DyeLeggings;
  public static ItemArmor DyeBoots;
  public static ItemSword DyeSword;
  public static ItemAxe DyeAxe;
  public static ItemPickaxe DyePickaxe;
  public static ItemSpade DyeShovel;
  public static ItemHoe DyeHoe;
  public static ItemArmor RedStoneHelmet;
  public static ItemArmor RedStoneChest;
  public static ItemArmor RedStoneLeggings;
  public static ItemArmor RedStoneBoots;
  public static ItemSword RedStoneSword;
  public static ItemPickaxe RedStonePickaxe;
  public static ItemAxe RedStoneAxe;
  public static ItemSpade RedStoneShovel;
  public static ItemHoe RedStoneHoe;
  public static Item ItemYMGGKLK;
  public static Item SpeedClock;
  public static Item MY;
  public static Block UltimateChest;
  public static Item SpawnDummyWither;
  public static Block BlockIronDamage;
  public static Block BlockGoldDamage;
  public static Block BlockDiamondDamage;
  public static Block BlockEmeraldDamage;
  public static Block BlockWitherTurrent;
  public static Block BlockFire;
  public static Block SpeedBlock;
  public static Block BlockLightning;
  public static Block BlockDoge;
  public static Item Lightning;
  public static Block BlockExp;
  public static Block BlockHeal;
  public static Item ItemCBall;
  public static ItemSword KickerPlayer;
  public static ItemSword PlayerBanner;
  public static ItemSword OpFucker;
  public static ItemArmor Huaji_Dadi;
  public static ItemArmor Huaji_Chest;
  public static ItemArmor Huaji_Leggings;
  public static ItemArmor Huaji_Boots;
  public static ItemSword ItemHanbiSword;
  public static ItemSword ItemHuliPickaxe;
  public static Block BlockAttack;
  public static Block BlockCannon;
  public static ItemSword Gun;
  public static Item ItemMoney;
  public static Block BlockBulletChest;
  public static Enchantment Hurt;
  public static Enchantment Infinity;
  public static Enchantment Slowdown;
  public static Enchantment FirePickaxe;
  public static Enchantment Awaken;
  public static ItemSword ItemWitherSword;
  public static Item SpawnPaojie;
  public static ItemSword BasketBall;
  public static Item ItemCoinOfWugui;
  public static Potion Judgement;
  public static ItemSword JudgementSword;
  public static Block UltimateTNT;
  public static Item SpawnAccelerator;
  public static Item Tuj;
  public static Item ZS;
  public static Enchantment ELightningSword;
  public static Item SpawnHanling;
  public static Item SpawnSteve;
  public static ItemSword BackSword;
  public static Item SpawnEntity303;
  public static Item SpawnNull;
  public static Item SpawnNotch;
  public static Item SpawnHerobrine;
  public static Item SpawnKongJvMoWang;
  public static Item SpawnSiLingQiShi;
  public static BlockOre ImageOre;
  public static Item ImagePowder;
  public static Item ImageElement;
  public static Item ImageGrain;
  public static Item ImageIngot;
  public static Block ImageBlock;
  public static ItemPickaxe EmeraldPickaxe;
  public static Item SuperGun;
  public static DamageSource UDS() {
	  return new DamageSource("ultimate").setDamageAllowedInCreativeMode().setDamageBypassesArmor().setDamageIsAbsolute().setDifficultyScaled().setExplosion().setFireDamage().setMagicDamage().setProjectile();
  }
  public static EntityDamageSource EUDS(Entity entity) {
	  return (EntityDamageSource)((DamageSource)new EntityDamageSource("ultimate",entity)).setDamageAllowedInCreativeMode().setDamageBypassesArmor().setDamageIsAbsolute().setDifficultyScaled().setExplosion().setFireDamage().setMagicDamage().setProjectile();
  }
  public static class preload
  {
	    public static void RegisterPotions() {
	    	Judgement = new xuanwu.ultimate.Potion.Judgement();
	    }
    public static void RegisterItems()
    {
      Register.ultimatesword = new ultimateSword(ultimateSword.US).setCreativeTab(Register.tab).setUnlocalizedName("UltimateSword").setTextureName("ultimate:UltimateSword");
      Register.UltimateBlock = new ultimateBlock();
      Register.CoalHelmet = new ItemCoalHelmet();
      Register.RainbowApple = new RainbowApple();
      Register.RainbowApple.setCreativeTab(Register.tab).setUnlocalizedName("RainbowApple");
      Register.egg = new UltimateChickenSpawnEgg();
      Register.totem = new totem();
      Register.DoveSoup = new DoveSoup();
      Register.BatSoup = new BatSoup();
      Register.LavaPickaxe = new LavaPickaxe();
      Register.Coffe = new Coffe();
      Register.InfinityFuel = new InfinityFuel();
      Register.Hamburger = new Hamburger();
      Register.Lava_Ingot = new LavaIngot();
      Register.MusicRecord = new Record();
      Register.Coal_Sword = new CoalSword();
      Register.Glass_Sword = new GlassSword();
      Register.LavaAxe = new LavaAxe();
      Register.LavaSword = new LavaSword();
      Register.LavaHoe = new LavaHoe();
      Register.LavaShovel = new LavaShovel();
      Register.LavaHelmet = new LavaHelmet();
      Register.LavaChest = new LavaChest();
      Register.LavaLeggins = new LavaLeggins();
      Register.LavaBoots = new LavaBoots();
      Register.BigHeal = new Heal();
      Register.CoalChest = new ItemCoalChest();
      Register.CoalLeggings = new ItemCoalLeggings();
      Register.CoalBoots = new ItemCoalBoots();
      Register.ItemDead = new DEAD();
      Register.ItemHorseStack = new HorseStack();
      Register.ItemBB = new BB();
      Register.NMSLHelmet = new ItemNMSLHelmet();
      Register.NMSLChest = new ItemNMSLChest();
      Register.NMSLLeggings = new ItemNMSLLeggings();
      Register.NMSLBoots = new ItemNMSLBoots();
      Register.ItemCoalPickaxe = new CoalPickaxe();
      Register.CoalAxe = new ItemCoalAxe();
      Register.ItemCoalShovel = new CoalShovel();
      Register.CoalHoe = new ItemCoalHoe();
      Register.praticle = new ItemParticle();
      Register.WoodHelmet = new ItemWoodHelmet();
      Register.WoodChest = new ItemWoodChest();
      Register.ItemWoodLeggings = new WoodLeggings();
      Register.WoodBoots = new WoodBoots();
      Register.StoneHelmet = new ItemStoneHelmet();
      Register.StoneChest = new ItemStoneChest();
      Register.StoneLeggings = new ItemStoneLeggings();
      Register.StoneBoots = new ItemStoneBoots();
      Register.EmeraldSword = new ItemEmeraldSword();
      Register.EmeraldHelmet = new ItemEmeraldHelmet();
      Register.EmeraldChest = new ItemEmeraldChest();
      Register.EmeraldLeggings = new ItemEmeraldLeggings();
      Register.EmeraldBoots = new ItemEmeraldBoots();
      Register.DyeHelmet = new ItemDyeHelmet();
      Register.DyeChest = new ItemDyeChest();
      Register.DyeLeggings = new ItemDyeLeggings();
      Register.DyeBoots = new ItemDyeBoots();
      Register.DyeSword = new ItemDyeSword();
      Register.DyePickaxe = new ItemDyePickaxe();
      Register.DyeAxe = new ItemDyeAxe();
      Register.DyeShovel = new ItemDyeShovel();
      Register.DyeHoe = new ItemDyeHoe();
      Register.RedStoneHelmet = new ItemRedStoneHelmet();
      Register.RedStoneChest = new ItemRedStoneChest();
      Register.RedStoneLeggings = new ItemRedStoneLeggings();
      Register.RedStoneBoots = new ItemRedStoneBoots();
      Register.RedStoneSword = new ItemRedStoneSword();
      Register.RedStonePickaxe = new ItemRedStonePickaxe();
      Register.RedStoneAxe = new ItemRedStoneAxe();
      Register.RedStoneShovel = new ItemRedStoneShovel();
      Register.RedStoneHoe = new ItemRedStoneHoe();
      Register.MY = new ItemMY();
      Register.ItemYMGGKLK = new YMGGKLK();
      Register.SpeedClock = new ItemSpeedClock();
      Register.SpawnDummyWither = new ItemEggDummyWither();
      Register.BlockIronDamage = new IronDamageBlock();
      Register.BlockGoldDamage = new GoldDamageBlock();
      Register.BlockDiamondDamage = new DiamondDamageBlock();
      Register.BlockEmeraldDamage = new EmeraldDamageBlock();
      Register.BlockWitherTurrent = new WitherTurrentBlock();
      Register.BlockFire = new FireBlock();
      Register.SpeedBlock = new SpeedBlock();
      Register.BlockLightning = new LightningBoltBlock();
      Register.BlockDoge = new DogeBlock();
      Register.Lightning = new ItemLightning();
      Register.BlockExp = new ExpBlock();
      Register.BlockHeal = new HealBlock();
      Register.ItemCBall = new ItemCBall();
      Register.KickerPlayer = new PlayerKicker();
      Register.PlayerBanner = new ItemPlayerBanner();
      Register.OpFucker = new ItemOpFucker();
      Register.Huaji_Dadi = new ItemHuajiDadi();
      Register.Huaji_Chest = new ItemHuajiChest();
      Register.Huaji_Leggings = new ItemHuajiLeggings();
      Register.Huaji_Boots = new ItemHuajiBoots();
      Register.ItemHanbiSword = new HanbiSword();
      Register.ItemHuliPickaxe = new HuliPickaxe();
      Register.BlockAttack = new AttackBlock();
      Register.BlockCannon = new CannonBlock();
      Register.Gun = new ItemGun();
      Register.ItemMoney = new Money();
      Register.BlockBulletChest = new BulletBlock();
      Register.ItemWitherSword = new WitherSword();
      Register.SpawnPaojie = new ItemSpawnPaojie();
      Register.BasketBall = new ItemBasketBall();
      Register.ItemCoinOfWugui = new CoinOfWugui();
      Register.JudgementSword = new ItemJudgementSword();
      Register.UltimateTNT = new BlockUltimateTNT();
      Register.SpawnAccelerator = new ItemSpawnAccelerator();
      Register.Tuj = new ItemTuj();
      Register.ZS = new ItemZS();
      Register.SpawnHanling = new ItemSpawnHanling();
      Register.SpawnSteve = new ItemSpawnSteve();
      Register.BackSword = new ItemBackSword();
      Register.SpawnEntity303 = new ItemSpawnEntity303();
      Register.SpawnNull = new ItemSpawnEntityNull();
      Register.SpawnNotch = new ItemSpawnEntityNotch();
      Register.SpawnHerobrine = new ItemSpawnHerobrine();
      Register.SpawnKongJvMoWang = new ItemSpawnKongJvMoWang();
      Register.SpawnSiLingQiShi = new ItemSpawnSiLingQiShi();
      Register.ImagePowder = new ItemImagePowder();
      Register.ImageElement = new ItemImageElement();
      Register.ImageGrain = new ItemImageGrain();
      Register.ImageIngot = new ItemImageIngot();
      Register.ImageBlock = new xuanwu.ultimate.block.ImageBlock();
      Register.ImageOre = new xuanwu.ultimate.block.ore.ImageOre("ImageOre",Register.ImagePowder);
      Register.EmeraldPickaxe = new ItemEmeraldPickaxe();
      Register.SuperGun = new ItemSuperGun();
      GameRegistry.registerItem(Register.Hamburger, "Hamburger", UltimateMod.MODID);
      GameRegistry.registerItem(Register.InfinityFuel, "InfinityFuel", UltimateMod.MODID);
      GameRegistry.registerItem(Register.Coffe, "Coffe", UltimateMod.MODID);
      GameRegistry.registerItem(Register.RainbowApple, "RainbowApple");
      GameRegistry.registerBlock(Register.UltimateBlock, "UltimateBlock");
      GameRegistry.registerItem(Register.ultimatesword, "UltimateSword", UltimateMod.MODID);
      GameRegistry.registerItem(Register.egg, "UltimateChicken", UltimateMod.MODID);
      GameRegistry.registerItem(Register.totem, "totem_of_undying", UltimateMod.MODID);
      GameRegistry.registerItem(Register.DoveSoup, "DoveSoup", UltimateMod.MODID);
      GameRegistry.registerItem(Register.BatSoup, "BatSoup", UltimateMod.MODID);
      GameRegistry.registerItem(Register.MusicRecord, "MusicRecord", UltimateMod.MODID);
      GameRegistry.registerItem(Register.Lava_Ingot, "LavaIngot", UltimateMod.MODID);
      GameRegistry.registerItem(Register.LavaSword, "LavaSword", UltimateMod.MODID);
      GameRegistry.registerItem(Register.LavaPickaxe, "LavaPickaxe", UltimateMod.MODID);
      GameRegistry.registerItem(Register.LavaHoe, "LavaHoe", UltimateMod.MODID);
      GameRegistry.registerItem(Register.LavaAxe, "LavaAxe", UltimateMod.MODID);
      GameRegistry.registerItem(Register.LavaShovel, "LavaShovel", UltimateMod.MODID);
      GameRegistry.registerItem(Register.LavaHelmet, "LavaHelmet", UltimateMod.MODID);
      GameRegistry.registerItem(Register.LavaChest, "LavaChest", UltimateMod.MODID);
      GameRegistry.registerItem(Register.LavaLeggins, "LavaLeggins", UltimateMod.MODID);
      GameRegistry.registerItem(Register.LavaBoots, "LavaBoots", UltimateMod.MODID);
      GameRegistry.registerItem(Register.BigHeal, "BigHeal", UltimateMod.MODID);
      GameRegistry.registerItem(Register.Coal_Sword, "CoalSword", UltimateMod.MODID);
      GameRegistry.registerItem(Register.Glass_Sword, "GlassSword", UltimateMod.MODID);
      GameRegistry.registerItem(Register.CoalHelmet, "CoalHelmet", UltimateMod.MODID);
      GameRegistry.registerItem(Register.CoalChest, "CoalChest", UltimateMod.MODID);
      GameRegistry.registerItem(Register.CoalLeggings, "CoalLeggings", UltimateMod.MODID);
      GameRegistry.registerItem(Register.CoalBoots, "CoalBoots", UltimateMod.MODID);
      GameRegistry.registerItem(Register.ItemDead, "DEAD", UltimateMod.MODID);
      GameRegistry.registerItem(Register.ItemHorseStack, "HorseStack", UltimateMod.MODID);
      GameRegistry.registerItem(Register.ItemBB, "BB", UltimateMod.MODID);
      GameRegistry.registerItem(Register.NMSLHelmet, "NMSLHelmet", UltimateMod.MODID);
      GameRegistry.registerItem(Register.NMSLChest, "NMSLChest", UltimateMod.MODID);
      GameRegistry.registerItem(Register.NMSLLeggings, "NMSLLeggings", UltimateMod.MODID);
      GameRegistry.registerItem(Register.NMSLBoots, "NMSLBoots", UltimateMod.MODID);
      GameRegistry.registerItem(Register.ItemCoalPickaxe, "CoalPickaxe", UltimateMod.MODID);
      GameRegistry.registerItem(Register.CoalAxe, "CoalAxe", UltimateMod.MODID);
      GameRegistry.registerItem(Register.ItemCoalShovel, "CoalShovel", UltimateMod.MODID);
      GameRegistry.registerItem(Register.CoalHoe, "CoalHoe", UltimateMod.MODID);
      GameRegistry.registerItem(Register.praticle, "praticle", UltimateMod.MODID);
      GameRegistry.registerItem(Register.WoodHelmet, "WoodHelmet", UltimateMod.MODID);
      GameRegistry.registerItem(Register.WoodChest, "WoodChest", UltimateMod.MODID);
      GameRegistry.registerItem(Register.ItemWoodLeggings, "WoodLeggings", UltimateMod.MODID);
      GameRegistry.registerItem(Register.WoodBoots, "WoodBoots", UltimateMod.MODID);
      GameRegistry.registerItem(Register.StoneHelmet, "StoneHelmet", UltimateMod.MODID);
      GameRegistry.registerItem(Register.StoneChest, "StoneChest", UltimateMod.MODID);
      GameRegistry.registerItem(Register.StoneLeggings, "StoneLeggings", UltimateMod.MODID);
      GameRegistry.registerItem(Register.StoneBoots, "StoneBoots", UltimateMod.MODID);
      GameRegistry.registerItem(Register.EmeraldSword, "EmeraldSword", UltimateMod.MODID);
      GameRegistry.registerItem(Register.EmeraldHelmet, "EmeraldHelmet", UltimateMod.MODID);
      GameRegistry.registerItem(Register.EmeraldChest, "EmeraldChest", UltimateMod.MODID);
      GameRegistry.registerItem(Register.EmeraldLeggings, "EmeraldLeggings", UltimateMod.MODID);
      GameRegistry.registerItem(Register.EmeraldBoots, "EmeraldBoots", UltimateMod.MODID);
      GameRegistry.registerItem(Register.DyeHelmet, "DyeHelmet", UltimateMod.MODID);
      GameRegistry.registerItem(Register.DyeChest, "DyeChest", UltimateMod.MODID);
      GameRegistry.registerItem(Register.DyeLeggings, "DyeLeggings", UltimateMod.MODID);
      GameRegistry.registerItem(Register.DyeBoots, "DyeBoots", UltimateMod.MODID);
      GameRegistry.registerItem(Register.DyeSword, "DyeSword", UltimateMod.MODID);
      GameRegistry.registerItem(Register.DyePickaxe, "DyePickaxe", UltimateMod.MODID);
      GameRegistry.registerItem(Register.DyeAxe, "DyeAxe", UltimateMod.MODID);
      GameRegistry.registerItem(Register.DyeShovel, "DyeShovel", UltimateMod.MODID);
      GameRegistry.registerItem(Register.DyeHoe, "DyeHoe", UltimateMod.MODID);
      GameRegistry.registerItem(Register.RedStoneHelmet, "RedStoneHelmet", UltimateMod.MODID);
      GameRegistry.registerItem(Register.RedStoneChest, "RedStoneChest", UltimateMod.MODID);
      GameRegistry.registerItem(Register.RedStoneLeggings, "RedStoneLeggings", UltimateMod.MODID);
      GameRegistry.registerItem(Register.RedStoneBoots, "RedStoneBoots", UltimateMod.MODID);
      GameRegistry.registerItem(Register.RedStoneSword, "RedStoneSword", UltimateMod.MODID);
      GameRegistry.registerItem(Register.RedStonePickaxe, "RedStonePickaxe", UltimateMod.MODID);
      GameRegistry.registerItem(Register.RedStoneAxe, "RedStoneAxe", UltimateMod.MODID);
      GameRegistry.registerItem(Register.RedStoneShovel, "RedStoneShovel", UltimateMod.MODID);
      GameRegistry.registerItem(Register.RedStoneHoe, "RedStoneHoe", UltimateMod.MODID);
      GameRegistry.registerItem(Register.MY, "MY", UltimateMod.MODID);
      GameRegistry.registerItem(Register.ItemYMGGKLK, "YMGGKLK", UltimateMod.MODID);
      GameRegistry.registerItem(Register.SpeedClock, "SpeedClock", UltimateMod.MODID);
      GameRegistry.registerItem(Register.SpawnDummyWither, "SpawnDummyWither", UltimateMod.MODID);
      GameRegistry.registerBlock(Register.BlockIronDamage, "IronDamageBlock");
      GameRegistry.registerBlock(Register.BlockGoldDamage, "GoldDamageBlock");
      GameRegistry.registerBlock(Register.BlockDiamondDamage, "DiamondDamageBlock");
      GameRegistry.registerBlock(Register.BlockEmeraldDamage, "EmeraldDamageBlock");
      GameRegistry.registerBlock(Register.BlockWitherTurrent, "BlockWitherTurrent");
      GameRegistry.registerBlock(Register.BlockFire, "FireBlock");
      GameRegistry.registerBlock(Register.SpeedBlock, "SpeedBlock");
      GameRegistry.registerBlock(Register.BlockLightning, "BlockLightning");
      GameRegistry.registerBlock(Register.BlockDoge, "DogeBlock");
      GameRegistry.registerItem(Register.Lightning, "Lightning", UltimateMod.MODID);
      GameRegistry.registerBlock(Register.BlockExp, "ExpBlock");
      GameRegistry.registerBlock(Register.BlockHeal, "HealBlock");
      GameRegistry.registerItem(Register.ItemCBall, "CBall", UltimateMod.MODID);
      GameRegistry.registerItem(Register.KickerPlayer, "PlayerKicker", UltimateMod.MODID);
      GameRegistry.registerItem(Register.PlayerBanner, "PlayerBanner", UltimateMod.MODID);
      GameRegistry.registerItem(Register.OpFucker, "OpFucker", UltimateMod.MODID);
      GameRegistry.registerItem(Register.Huaji_Dadi, "HuajiDadi", UltimateMod.MODID);
      GameRegistry.registerItem(Register.Huaji_Chest, "HuajiChest", UltimateMod.MODID);
      GameRegistry.registerItem(Register.Huaji_Leggings, "HuajiLeggings", UltimateMod.MODID);
      GameRegistry.registerItem(Register.Huaji_Boots, "HuajiBoots", UltimateMod.MODID);
      GameRegistry.registerItem(Register.ItemHanbiSword, "HanbiSword", UltimateMod.MODID);
      GameRegistry.registerItem(Register.ItemHuliPickaxe, "HuliPickaxe", UltimateMod.MODID);
      GameRegistry.registerBlock(Register.BlockAttack, "AttackBlock");
      GameRegistry.registerBlock(Register.BlockCannon, "CannonBlock");
      GameRegistry.registerItem(Register.Gun, "Gun",UltimateMod.MODID);
      GameRegistry.registerItem(Register.ItemMoney, "Money",UltimateMod.MODID);
      GameRegistry.registerBlock(Register.BlockBulletChest, "BulletChest");
      GameRegistry.registerItem(Register.ItemWitherSword, "WitherSword",UltimateMod.MODID);
      GameRegistry.registerItem(Register.SpawnPaojie, "SpawnPaojie",UltimateMod.MODID);
      GameRegistry.registerItem(Register.BasketBall, "BasketBall",UltimateMod.MODID);
      GameRegistry.registerItem(Register.ItemCoinOfWugui, "CoinOfWugui",UltimateMod.MODID);
      GameRegistry.registerItem(Register.JudgementSword, "JudgementSword",UltimateMod.MODID);
      GameRegistry.registerBlock(Register.UltimateTNT, "UltimateTNT");
      GameRegistry.registerItem(Register.SpawnAccelerator, "SpawnAccelerator",UltimateMod.MODID);
      GameRegistry.registerItem(Register.Tuj, "Tuj",UltimateMod.MODID);
      GameRegistry.registerItem(Register.ZS, "Zhans",UltimateMod.MODID);
      GameRegistry.registerItem(Register.SpawnHanling, "SpawnHanling",UltimateMod.MODID);
      GameRegistry.registerItem(Register.SpawnSteve, "SpawnSteve",UltimateMod.MODID);
      GameRegistry.registerItem(Register.BackSword, "BackSword",UltimateMod.MODID);
      GameRegistry.registerItem(Register.SpawnEntity303, "SpawnEntity303",UltimateMod.MODID);
      GameRegistry.registerItem(Register.SpawnNull, "SpawnEntityNull",UltimateMod.MODID);
      GameRegistry.registerItem(Register.SpawnNotch, "SpawnNotch",UltimateMod.MODID);
      GameRegistry.registerItem(Register.SpawnHerobrine, "SpawnHerobrine",UltimateMod.MODID);
      GameRegistry.registerItem(Register.SpawnKongJvMoWang, "SpawnKongJvMoWang",UltimateMod.MODID);
      GameRegistry.registerItem(Register.SpawnSiLingQiShi, "SpawnSiLingQiShi",UltimateMod.MODID);
      GameRegistry.registerItem(Register.ImagePowder, "ImagePowder",UltimateMod.MODID);
      GameRegistry.registerBlock(Register.ImageOre, "ImageOre");
      GameRegistry.registerItem(Register.ImageElement, "ImageElement",UltimateMod.MODID);
      GameRegistry.registerItem(Register.ImageGrain, "ImageGrain",UltimateMod.MODID);
      GameRegistry.registerItem(Register.ImageIngot, "ImageIngot",UltimateMod.MODID);
      GameRegistry.registerBlock(Register.ImageBlock, "ImageBlock");
      GameRegistry.registerItem(Register.EmeraldPickaxe, "EmeraldPickaxe",UltimateMod.MODID);
      GameRegistry.registerItem(Register.SuperGun, "SuperGun",UltimateMod.MODID);
    }
	  public static void RegisterEnchantments() {
		  Register.Hurt = new xuanwu.ultimate.Enchantment.Hurt();
		  Enchantment.addToBookList(Register.Hurt);
		  Register.EnchantmentList.add(Register.Hurt);
		  Register.Infinity = new xuanwu.ultimate.Enchantment.Infinity();
		  Enchantment.addToBookList(Register.Infinity);
		  Register.EnchantmentList.add(Register.Infinity);
		  Register.Slowdown = new xuanwu.ultimate.Enchantment.Slowdown();
		  Enchantment.addToBookList(Register.Slowdown);
		  Register.EnchantmentList.add(Register.Slowdown);
		  Register.FirePickaxe = new xuanwu.ultimate.Enchantment.FirePickaxe();
		  Enchantment.addToBookList(Register.FirePickaxe);
		  Register.EnchantmentList.add(Register.FirePickaxe);
		  Register.ELightningSword = new xuanwu.ultimate.Enchantment.LightningSword();
		  Enchantment.addToBookList(Register.ELightningSword);
		  Register.Awaken = new xuanwu.ultimate.Enchantment.Awaken();
		  Register.EnchantmentList.add(Register.Awaken);
		  Enchantment.addToBookList(Register.Awaken);
		  Register.EnchantmentList.add(Register.ELightningSword);
	  }
    public static void RegisterFuels()
    {
      GameRegistry.registerFuelHandler(new IFuelHandler()
      {
        public int getBurnTime(ItemStack fuel)
        {
          return (int)(Register.InfinityFuel != fuel.getItem() ? 0.0D : ultimateCore.Infinity);
        }
      });
    }
    
    static int nextID = 0;
    
    public static void RegisterWorlds()
    {
      DimensionManager.registerProviderType(666, WorldProviderPollute.class, true);
      DimensionManager.registerDimension(666, 666);
    }
    
    public static void RegisterPacket()
    {
      Register.instance.registerMessage(PacketNoDeadGui.Handler.class, PacketNoDeadGui.class, 1, Side.CLIENT);
      Register.instance.registerMessage(PacketRespawn.Handler.class, PacketRespawn.class, 2, Side.SERVER);
      Register.instance.registerMessage(PacketKeyKillAura.Handler.class, PacketKeyKillAura.class, 3, Side.SERVER);
      Register.instance.registerMessage(PacketBreakBlock.Handler.class, PacketBreakBlock.class, 4, Side.SERVER);
      Register.instance.registerMessage(PacketSpawnEntity.Handler.class, PacketSpawnEntity.class, 6, Side.SERVER);
      Register.instance.registerMessage(PacketSetConfig.Handler.class, PacketSetConfig.class, 7, Side.SERVER);
      UltimateMod.proxy.RegisterPackets();
    }
  }
  
  public static class load
  {
	  public static void RegisterWorldGeneration() {
		  GameRegistry.registerWorldGenerator(new OreGeneration(),0);
	  }
    public static void RegisterEvents()
    {
      MinecraftForge.EVENT_BUS.register(new TimeStop());
      MinecraftForge.EVENT_BUS.register(new LivingHurt());
      MinecraftForge.EVENT_BUS.register(new LivingDeath());
      MinecraftForge.EVENT_BUS.register(new Attack());
      MinecraftForge.EVENT_BUS.register(new PlayerJoinEvent());
      MinecraftForge.EVENT_BUS.register(new LivingUpdate());
      MinecraftForge.EVENT_BUS.register(new LivingAttack());
      MinecraftForge.EVENT_BUS.register(new LivingFall());
      MinecraftForge.EVENT_BUS.register(new PickUpItem());
      MinecraftForge.EVENT_BUS.register(new EventHandler());
      MinecraftForge.EVENT_BUS.register(new EnchantmentEventHandler());
      MinecraftForge.EVENT_BUS.register(new PotionEventHandler());
      FMLCommonHandler.instance().bus().register(new PlayerJoinEvent());
    }
    
    public static void RegisterEntities()
    {      
      EntityRegistry.registerModEntity(UltimateChicken.class, "UltimateChicken", 0, UltimateMod.instance, 512, 1, true);
      EntityRegistry.registerModEntity(DummyWither.class, "DummyWither", 1, UltimateMod.instance, 512, 1, true);
      EntityRegistry.registerModEntity(EntityFont.class, "EntityFont", 2, UltimateMod.instance, 512, 1, false);
      EntityRegistry.registerModEntity(EntityPaojie.class, "EntityPaojie", 3, UltimateMod.instance, 512, 1, false);
      EntityRegistry.registerModEntity(xuanwu.ultimate.entity.BasketBall.class, "EntityBasketBall", 4, UltimateMod.instance, 512, 1, false);
      EntityRegistry.registerModEntity(EntityDummy.class, "EntityDummy", 5, UltimateMod.instance, 512, 1, false);
      EntityRegistry.registerModEntity(EntityAccelerator.class, "EntityAccelerator", 6, UltimateMod.instance, 512, 1, false);
      EntityRegistry.registerModEntity(EntityHanling.class, "EntityHanling", 7, UltimateMod.instance, 512, 1, false);
      EntityRegistry.registerModEntity(EntitySteve.class, "EntitySteve", 8, UltimateMod.instance, 512, 1, false);
      EntityRegistry.registerModEntity(EntityLaser.class, "EntityLaset", 9, UltimateMod.instance, 512, 1, false);
      EntityRegistry.registerModEntity(Entity303.class, "Entity303", 10, UltimateMod.instance, 512, 1, false);
      EntityRegistry.registerModEntity(EntityNull.class, "EntityNull", 11, UltimateMod.instance, 512, 1, false);
      EntityRegistry.registerModEntity(EntityNotch.class, "EntityNotch", 12, UltimateMod.instance, 512, 1, false);
      EntityRegistry.registerModEntity(EntityHerobrine.class, "EntityHerobrine", 13, UltimateMod.instance, 512, 1, false);
      EntityRegistry.registerModEntity(EntityKongJvMoWang.class, "EntityKongJvMoWang", 14, UltimateMod.instance, 512, 1, false);
      EntityRegistry.registerModEntity(EntityDS.class, "EntityDS", 15, UltimateMod.instance, 512, 1, false);
      GameRegistry.registerTileEntity(DamageBlockTile.class, "IronDamageBlockTile");
      GameRegistry.registerTileEntity(WitherTurrentTile.class, "WitherTurrentTile");
      GameRegistry.registerTileEntity(FireBlockTile.class, "FireBlockTile");
      GameRegistry.registerTileEntity(UnbreakableTile.class, "UnbreakableTile");
      GameRegistry.registerTileEntity(TileSpeed.class, "SpeedTile");
      GameRegistry.registerTileEntity(TileLightningBlock.class, "TileLightningBlock");
      GameRegistry.registerTileEntity(TileDoge.class, "TileDoge");
      GameRegistry.registerTileEntity(TileExp.class, "TileExp");
      GameRegistry.registerTileEntity(TileHeal.class, "TileHeal");
      GameRegistry.registerTileEntity(AttackBlockTile.class, "AttackBlockTile");
      GameRegistry.registerTileEntity(CannonTile.class, "CannonTile");
    }
  }
  
  public static class postload
  {
    public static void RegisterShapedRicipe()
    {
      GameRegistry.addShapedRecipe(new ItemStack(Register.ultimatesword, 1), new Object[] { " Z ", " Z ", " X ", 
        Character.valueOf('Z'), new ItemStack(Blocks.dirt), 
        Character.valueOf('X'), new ItemStack(Items.stick) });
      GameRegistry.addShapedRecipe(new ItemStack(Register.RainbowApple, 1), new Object[] { "ZZZ", "ZXZ", "ZZZ", 
        Character.valueOf('Z'), new ItemStack(Blocks.dirt), 
        Character.valueOf('X'), new ItemStack(Items.apple) });
      GameRegistry.addShapedRecipe(new ItemStack(Register.totem, 1), new Object[] { "ZZZ", "XXX", " X ", 
        Character.valueOf('X'), new ItemStack(Blocks.dirt), 
        Character.valueOf('Z'), new ItemStack(Blocks.wooden_button) });
      GameRegistry.addShapedRecipe(new ItemStack(Register.egg, 1), new Object[] { "ZZZ", "ZXZ", "ZZZ", 
        Character.valueOf('X'), new ItemStack(Items.egg), 
        Character.valueOf('Z'), new ItemStack(Blocks.dirt) });
      GameRegistry.addShapedRecipe(new ItemStack(Register.UltimateBlock, (int)ultimateCore.Infinity), new Object[] { "ZZZ", "ZXZ", "ZZZ", 
        Character.valueOf('Z'), new ItemStack(Blocks.dirt), 
        Character.valueOf('X'), new ItemStack(Blocks.cobblestone) });
      GameRegistry.addShapedRecipe(new ItemStack(Register.DoveSoup, 1), new Object[] { "ZZZ", "ZXZ", "ZYZ", 
        Character.valueOf('Z'), new ItemStack(Items.feather), 
        Character.valueOf('Y'), new ItemStack(Items.bowl), 
        Character.valueOf('X'), new ItemStack(Items.golden_apple) });
      GameRegistry.addShapedRecipe(new ItemStack(Register.Lava_Ingot, 1), new Object[] { "ZXZ", "   ", "   ", 
        Character.valueOf('Z'), new ItemStack(Items.diamond), 
        Character.valueOf('X'), new ItemStack(Blocks.gold_block) });
      GameRegistry.addShapedRecipe(new ItemStack(Register.LavaSword, 1), new Object[] { "ZX ", "   ", "   ", 
        Character.valueOf('Z'), new ItemStack(Register.Lava_Ingot), 
        Character.valueOf('X'), new ItemStack(Items.diamond_sword) });
      GameRegistry.addShapedRecipe(new ItemStack(Register.LavaPickaxe, 1), new Object[] { "ZX ", "   ", "   ", 
        Character.valueOf('Z'), new ItemStack(Register.Lava_Ingot), 
        Character.valueOf('X'), new ItemStack(Items.diamond_pickaxe) });
      GameRegistry.addShapedRecipe(new ItemStack(Register.LavaHoe, 1), new Object[] { "ZX ", "   ", "   ", 
        Character.valueOf('Z'), new ItemStack(Register.Lava_Ingot), 
        Character.valueOf('X'), new ItemStack(Items.diamond_hoe) });
      GameRegistry.addShapedRecipe(new ItemStack(Register.LavaAxe, 1), new Object[] { "ZX ", "   ", "   ", 
        Character.valueOf('Z'), new ItemStack(Register.Lava_Ingot), 
        Character.valueOf('X'), new ItemStack(Items.diamond_axe) });
      GameRegistry.addShapedRecipe(new ItemStack(Register.LavaShovel, 1), new Object[] { "ZX ", "   ", "   ", 
        Character.valueOf('Z'), new ItemStack(Register.Lava_Ingot), 
        Character.valueOf('X'), new ItemStack(Items.diamond_shovel) });
      GameRegistry.addShapedRecipe(new ItemStack(Register.LavaHelmet, 1), new Object[] { "ZX ", "   ", "   ", 
        Character.valueOf('Z'), new ItemStack(Register.Lava_Ingot), 
        Character.valueOf('X'), new ItemStack(Items.diamond_helmet) });
      GameRegistry.addShapedRecipe(new ItemStack(Register.LavaChest, 1), new Object[] { "ZX ", "   ", "   ", 
        Character.valueOf('Z'), new ItemStack(Register.Lava_Ingot), 
        Character.valueOf('X'), new ItemStack(Items.diamond_chestplate) });
      GameRegistry.addShapedRecipe(new ItemStack(Register.LavaLeggins, 1), new Object[] { "ZX ", "   ", "   ", 
        Character.valueOf('Z'), new ItemStack(Register.Lava_Ingot), 
        Character.valueOf('X'), new ItemStack(Items.diamond_leggings) });
      GameRegistry.addShapedRecipe(new ItemStack(Register.LavaBoots, 1), new Object[] { "ZX ", "   ", "   ", 
        Character.valueOf('Z'), new ItemStack(Register.Lava_Ingot), 
        Character.valueOf('X'), new ItemStack(Items.diamond_boots) });
      GameRegistry.addShapedRecipe(new ItemStack(Register.BigHeal, 1), new Object[] { "ZZZ", "ZZZ", "ZZZ", 
        Character.valueOf('Z'), new ItemStack(Items.apple) });
      GameRegistry.addShapedRecipe(new ItemStack(Register.Coal_Sword, 1), new Object[] { " Z ", " Z ", " X ", 
        Character.valueOf('Z'), new ItemStack(Items.coal), 
        Character.valueOf('X'), new ItemStack(Items.stick) });
      GameRegistry.addShapedRecipe(new ItemStack(Register.Glass_Sword, 1), new Object[] { "CZC", "CZC", "CXC", 
        Character.valueOf('Z'), new ItemStack(Blocks.glass), 
        Character.valueOf('X'), new ItemStack(Items.stick), 
        Character.valueOf('X'), new ItemStack(Blocks.obsidian) });
      GameRegistry.addShapedRecipe(new ItemStack(Register.CoalHelmet, 1), new Object[] { "ZZZ", "Z Z", "   ", 
        Character.valueOf('Z'), new ItemStack(Items.coal) });
      GameRegistry.addShapedRecipe(new ItemStack(Register.CoalChest, 1), new Object[] { "Z Z", "ZZZ", "ZZZ", 
        Character.valueOf('Z'), new ItemStack(Items.coal) });
      GameRegistry.addShapedRecipe(new ItemStack(Register.CoalLeggings, 1), new Object[] { "ZZZ", "Z Z", "Z Z", 
        Character.valueOf('Z'), new ItemStack(Items.coal) });
      GameRegistry.addShapedRecipe(new ItemStack(Register.CoalBoots, 1), new Object[] { "   ", "Z Z", "Z Z", 
        Character.valueOf('Z'), new ItemStack(Items.coal) });
      GameRegistry.addShapedRecipe(new ItemStack(Register.ItemDead, 1), new Object[] { "ZZZ", "ZXZ", "ZZZ", 
        Character.valueOf('Z'), new ItemStack(Blocks.dirt), 
        Character.valueOf('X'), new ItemStack(Blocks.web) });
      GameRegistry.addShapedRecipe(new ItemStack(Register.NMSLHelmet, 1), new Object[] { "ZZZ", "Z Z", "   ", 
        Character.valueOf('Z'), new ItemStack(Register.ItemHorseStack) });
      GameRegistry.addShapedRecipe(new ItemStack(Register.NMSLChest, 1), new Object[] { "Z Z", "ZZZ", "ZZZ", 
        Character.valueOf('Z'), new ItemStack(Register.ItemHorseStack) });
      GameRegistry.addShapedRecipe(new ItemStack(Register.NMSLLeggings, 1), new Object[] { "ZZZ", "Z Z", "Z Z", 
        Character.valueOf('Z'), new ItemStack(Register.ItemHorseStack) });
      GameRegistry.addShapedRecipe(new ItemStack(Register.NMSLBoots, 1), new Object[] { "   ", "Z Z", "Z Z", 
        Character.valueOf('Z'), new ItemStack(Register.ItemHorseStack) });
      GameRegistry.addShapedRecipe(new ItemStack(Register.ItemCoalPickaxe, 1), new Object[] { "ZZZ", " X ", " X ", 
        Character.valueOf('Z'), new ItemStack(Items.coal), 
        Character.valueOf('X'), new ItemStack(Items.stick) });
      GameRegistry.addShapedRecipe(new ItemStack(Register.CoalAxe, 1), new Object[] { "ZZ ", "ZX ", " X ", 
        Character.valueOf('Z'), new ItemStack(Items.coal), 
        Character.valueOf('X'), new ItemStack(Items.stick) });
      GameRegistry.addShapedRecipe(new ItemStack(Register.ItemCoalShovel, 1), new Object[] { " Z ", " X ", " X ", 
        Character.valueOf('Z'), new ItemStack(Items.coal), 
        Character.valueOf('X'), new ItemStack(Items.stick) });
      GameRegistry.addShapedRecipe(new ItemStack(Register.CoalHoe, 1), new Object[] { "ZZ ", " X ", " X ", 
        Character.valueOf('Z'), new ItemStack(Items.coal), 
        Character.valueOf('X'), new ItemStack(Items.stick) });
      GameRegistry.addShapedRecipe(new ItemStack(Register.praticle, 1), new Object[] { "ZZZ", "ZXZ", "ZZZ", 
        Character.valueOf('Z'), new ItemStack(Items.diamond), 
        Character.valueOf('X'), new ItemStack(Items.golden_apple) });
      GameRegistry.addShapedRecipe(new ItemStack(Register.WoodHelmet, 1), new Object[] { "ZZZ", "Z Z", "   ", 
        Character.valueOf('Z'), new ItemStack(Blocks.log) });
      GameRegistry.addShapedRecipe(new ItemStack(Register.WoodChest, 1), new Object[] { "Z Z", "ZZZ", "ZZZ", 
        Character.valueOf('Z'), new ItemStack(Blocks.log) });
      GameRegistry.addShapedRecipe(new ItemStack(Register.ItemWoodLeggings, 1), new Object[] { "ZZZ", "Z Z", "Z Z", 
        Character.valueOf('Z'), new ItemStack(Blocks.log) });
      GameRegistry.addShapedRecipe(new ItemStack(Register.WoodBoots, 1), new Object[] { "   ", "Z Z", "Z Z", 
        Character.valueOf('Z'), new ItemStack(Blocks.log) });
      GameRegistry.addShapedRecipe(new ItemStack(Register.StoneHelmet, 1), new Object[] { "ZZZ", "Z Z", "   ", 
        Character.valueOf('Z'), new ItemStack(Blocks.cobblestone) });
      GameRegistry.addShapedRecipe(new ItemStack(Register.StoneChest, 1), new Object[] { "Z Z", "ZZZ", "ZZZ", 
        Character.valueOf('Z'), new ItemStack(Blocks.cobblestone) });
      GameRegistry.addShapedRecipe(new ItemStack(Register.StoneLeggings, 1), new Object[] { "ZZZ", "Z Z", "Z Z", 
        Character.valueOf('Z'), new ItemStack(Blocks.cobblestone) });
      GameRegistry.addShapedRecipe(new ItemStack(Register.StoneBoots, 1), new Object[] { "   ", "Z Z", "Z Z", 
        Character.valueOf('Z'), new ItemStack(Blocks.cobblestone) });
      GameRegistry.addShapedRecipe(new ItemStack(Register.EmeraldSword, 1), new Object[] { " Z ", " Z ", " X ", 
        Character.valueOf('Z'), new ItemStack(Items.emerald), 
        Character.valueOf('X'), new ItemStack(Items.stick) });
      GameRegistry.addShapedRecipe(new ItemStack(Register.EmeraldHelmet, 1), new Object[] { "ZZZ", "Z Z", "   ", 
        Character.valueOf('Z'), new ItemStack(Items.emerald) });
      GameRegistry.addShapedRecipe(new ItemStack(Register.EmeraldChest, 1), new Object[] { "Z Z", "ZZZ", "ZZZ", 
        Character.valueOf('Z'), new ItemStack(Items.emerald) });
      GameRegistry.addShapedRecipe(new ItemStack(Register.EmeraldLeggings, 1), new Object[] { "ZZZ", "Z Z", "Z Z", 
        Character.valueOf('Z'), new ItemStack(Items.emerald) });
      GameRegistry.addShapedRecipe(new ItemStack(Register.EmeraldBoots, 1), new Object[] { "   ", "Z Z", "Z Z", 
        Character.valueOf('Z'), new ItemStack(Items.emerald) });
      GameRegistry.addShapedRecipe(new ItemStack(Register.DyeHelmet, 1), new Object[] { "ZZZ", "Z Z", "   ", 
        Character.valueOf('Z'), new ItemStack(Items.dye,1,4) });
      GameRegistry.addShapedRecipe(new ItemStack(Register.DyeChest, 1), new Object[] { "Z Z", "ZZZ", "ZZZ", 
        Character.valueOf('Z'), new ItemStack(Items.dye,1,4) });
      GameRegistry.addShapedRecipe(new ItemStack(Register.DyeLeggings, 1), new Object[] { "ZZZ", "Z Z", "Z Z", 
        Character.valueOf('Z'), new ItemStack(Items.dye,1,4) });
      GameRegistry.addShapedRecipe(new ItemStack(Register.DyeBoots, 1), new Object[] { "   ", "Z Z", "Z Z", 
        Character.valueOf('Z'), new ItemStack(Items.dye,1,4) });
      GameRegistry.addShapedRecipe(new ItemStack(Register.DyeSword, 1), new Object[] { " Z ", " Z ", " X ", 
        Character.valueOf('Z'), new ItemStack(Items.dye,1,4), 
        Character.valueOf('X'), new ItemStack(Items.stick) });
      GameRegistry.addShapedRecipe(new ItemStack(Register.DyePickaxe, 1), new Object[] { "ZZZ", " X ", " X ", 
        Character.valueOf('Z'), new ItemStack(Items.dye,1,4), 
        Character.valueOf('X'), new ItemStack(Items.stick) });
      GameRegistry.addShapedRecipe(new ItemStack(Register.DyeAxe, 1), new Object[] { "ZZ ", "ZX ", " X ", 
        Character.valueOf('Z'), new ItemStack(Items.dye,1,4), 
        Character.valueOf('X'), new ItemStack(Items.stick) });
      GameRegistry.addShapedRecipe(new ItemStack(Register.DyeShovel, 1), new Object[] { " Z ", " X ", " X ", 
        Character.valueOf('Z'), new ItemStack(Items.dye,1,4), 
        Character.valueOf('X'), new ItemStack(Items.stick) });
      GameRegistry.addShapedRecipe(new ItemStack(Register.DyeHoe, 1), new Object[] { "ZZ ", " X ", " X ", 
        Character.valueOf('Z'), new ItemStack(Items.dye,1,4), 
        Character.valueOf('X'), new ItemStack(Items.stick) });
      GameRegistry.addShapedRecipe(new ItemStack(Register.RedStoneHelmet, 1), new Object[] { "ZZZ", "Z Z", "   ", 
        Character.valueOf('Z'), new ItemStack(Blocks.redstone_block) });
      GameRegistry.addShapedRecipe(new ItemStack(Register.RedStoneChest, 1), new Object[] { "Z Z", "ZZZ", "ZZZ", 
        Character.valueOf('Z'), new ItemStack(Blocks.redstone_block) });
      GameRegistry.addShapedRecipe(new ItemStack(Register.RedStoneLeggings, 1), new Object[] { "ZZZ", "Z Z", "Z Z", 
        Character.valueOf('Z'), new ItemStack(Blocks.redstone_block) });
      GameRegistry.addShapedRecipe(new ItemStack(Register.RedStoneBoots, 1), new Object[] { "   ", "Z Z", "Z Z", 
        Character.valueOf('Z'), new ItemStack(Blocks.redstone_block) });
      GameRegistry.addShapedRecipe(new ItemStack(Register.RedStoneSword, 1), new Object[] { " Z ", " Z ", " X ", 
        Character.valueOf('Z'), new ItemStack(Blocks.redstone_block), 
        Character.valueOf('X'), new ItemStack(Items.stick) });
      GameRegistry.addShapedRecipe(new ItemStack(Register.RedStonePickaxe, 1), new Object[] { "ZZZ", " X ", " X ", 
        Character.valueOf('Z'), new ItemStack(Blocks.redstone_block), 
        Character.valueOf('X'), new ItemStack(Items.stick) });
      GameRegistry.addShapedRecipe(new ItemStack(Register.RedStoneAxe, 1), new Object[] { "ZZ ", "ZX ", " X ", 
        Character.valueOf('Z'), new ItemStack(Blocks.redstone_block), 
        Character.valueOf('X'), new ItemStack(Items.stick) });
      GameRegistry.addShapedRecipe(new ItemStack(Register.RedStoneShovel, 1), new Object[] { " Z ", " X ", " X ", 
        Character.valueOf('Z'), new ItemStack(Blocks.redstone_block), 
        Character.valueOf('X'), new ItemStack(Items.stick) });
      GameRegistry.addShapedRecipe(new ItemStack(Register.RedStoneHoe, 1), new Object[] { "ZZ ", " X ", " X ", 
        Character.valueOf('Z'), new ItemStack(Blocks.redstone_block), 
        Character.valueOf('X'), new ItemStack(Items.stick) });
      GameRegistry.addShapedRecipe(new ItemStack(Register.BlockIronDamage, 1), new Object[] { "ZX ", "   ", "   ", 
        Character.valueOf('Z'), new ItemStack(Blocks.iron_block), 
        Character.valueOf('X'), new ItemStack(Items.iron_ingot) });
      GameRegistry.addShapedRecipe(new ItemStack(Register.BlockGoldDamage, 1), new Object[] { "ZX ", "   ", "   ", 
        Character.valueOf('Z'), new ItemStack(Blocks.gold_block), 
        Character.valueOf('X'), new ItemStack(Items.gold_ingot) });
      GameRegistry.addShapedRecipe(new ItemStack(Register.BlockDiamondDamage, 1), new Object[] { "ZX ", "   ", "   ", 
        Character.valueOf('Z'), new ItemStack(Blocks.diamond_block), 
        Character.valueOf('X'), new ItemStack(Items.diamond) });
      GameRegistry.addShapedRecipe(new ItemStack(Register.BlockEmeraldDamage, 1), new Object[] { "ZX ", "   ", "   ", 
        Character.valueOf('Z'), new ItemStack(Blocks.emerald_block), 
        Character.valueOf('X'), new ItemStack(Items.emerald) });
      GameRegistry.addShapedRecipe(new ItemStack(Register.BlockWitherTurrent, 1), new Object[] { " X ", " Z  ", "   ", 
        Character.valueOf('Z'), new ItemStack(Blocks.obsidian), 
        Character.valueOf('X'), new ItemStack(Blocks.skull) });
      GameRegistry.addShapedRecipe(new ItemStack(Register.BlockFire, 1), new Object[] { "ZX ", "    ", "   ", 
        Character.valueOf('Z'), new ItemStack(Items.fire_charge), 
        Character.valueOf('X'), new ItemStack(Blocks.obsidian) });
      GameRegistry.addShapedRecipe(new ItemStack(Register.SpeedBlock, 1), new Object[] { "ZX ", "    ", "   ", 
        Character.valueOf('Z'), new ItemStack(Blocks.obsidian), 
        Character.valueOf('X'), new ItemStack(Items.clock) });
      GameRegistry.addShapedRecipe(new ItemStack(Register.BlockDoge, 1), new Object[] { "ZZZ", "ZXZ", "ZZZ", 
        Character.valueOf('Z'), new ItemStack(Blocks.cobblestone), 
        Character.valueOf('X'), new ItemStack(Register.ItemYMGGKLK) });
      GameRegistry.addShapedRecipe(new ItemStack(Register.Lightning, 1), new Object[] { "ZZZ", "ZXZ", "ZZZ", 
        Character.valueOf('Z'), new ItemStack(Items.paper), 
        Character.valueOf('X'), new ItemStack(Items.diamond) });
      GameRegistry.addShapedRecipe(new ItemStack(Register.BlockLightning, 1), new Object[] { "ZX ", "   ", "   ", 
        Character.valueOf('Z'), new ItemStack(Register.Lightning), 
        Character.valueOf('X'), new ItemStack(Items.diamond) });
      GameRegistry.addShapedRecipe(new ItemStack(Register.BlockExp, 1), new Object[] { "ZZZ", "ZXZ", "ZZZ", 
        Character.valueOf('Z'), new ItemStack(Blocks.stone), 
        Character.valueOf('X'), new ItemStack(Items.emerald) });
      GameRegistry.addShapedRecipe(new ItemStack(Register.BlockHeal, 1), new Object[] { "ZZZ", "ZXZ", "ZZZ", 
        Character.valueOf('Z'), new ItemStack(Blocks.stone), 
        Character.valueOf('X'), new ItemStack(Register.BigHeal) });
      GameRegistry.addShapedRecipe(new ItemStack(Register.KickerPlayer, 1), new Object[] { " Z ", " Y ", " X ", 
    	        Character.valueOf('Z'), new ItemStack(Blocks.cobblestone), 
    	        Character.valueOf('Z'), new ItemStack(Items.dye), 
    	        Character.valueOf('X'), new ItemStack(Items.stick) });
      GameRegistry.addShapedRecipe(new ItemStack(Register.PlayerBanner, 1), new Object[] { " Z ", " Y ", " X ", 
  	        Character.valueOf('Z'), new ItemStack(Blocks.cobblestone), 
  	        Character.valueOf('Z'), new ItemStack(Blocks.dirt), 
  	        Character.valueOf('X'), new ItemStack(Items.stick) });
      GameRegistry.addShapedRecipe(new ItemStack(Register.OpFucker, 1), new Object[] { " Z ", " Z ", " X ", 
    	        Character.valueOf('Z'), new ItemStack(Register.PlayerBanner), 
    	        Character.valueOf('X'), new ItemStack(Items.stick) });
      GameRegistry.addShapedRecipe(new ItemStack(Register.Huaji_Dadi, 1), new Object[] { " Z ", "XCV", "   ", 
  	        Character.valueOf('Z'), new ItemStack(Blocks.dirt), 
  	        Character.valueOf('X'), new ItemStack(Register.Huaji_Chest),
  	      Character.valueOf('C'), new ItemStack(Register.Huaji_Leggings),
  	    Character.valueOf('V'), new ItemStack(Register.Huaji_Boots)});
      GameRegistry.addShapedRecipe(new ItemStack(Register.Huaji_Chest, 1), new Object[] { "Z Z", " Z ", "Z Z", 
  	        Character.valueOf('Z'), new ItemStack(Blocks.dirt)});
      GameRegistry.addShapedRecipe(new ItemStack(Register.Huaji_Leggings, 1), new Object[] { "  Z", "   ", "Z  ", 
    	        Character.valueOf('Z'), new ItemStack(Blocks.dirt)});
      GameRegistry.addShapedRecipe(new ItemStack(Register.Huaji_Boots, 1), new Object[] { "  Z", " Z ", "Z  ", 
    	        Character.valueOf('Z'), new ItemStack(Blocks.dirt)});
      GameRegistry.addShapedRecipe(new ItemStack(Register.ItemHanbiSword, 1), new Object[] { "   ", " Z ", " X ", 
  	        Character.valueOf('Z'), new ItemStack(Register.ItemDead),
  	      Character.valueOf('X'), new ItemStack(Items.stick)});
      GameRegistry.addShapedRecipe(new ItemStack(Register.ItemMoney, 1), new Object[] { "ZZZ", "Z Z", "ZZZ", 
    	        Character.valueOf('Z'), new ItemStack(Items.gold_ingot)});
      GameRegistry.addShapedRecipe(new ItemStack(Register.BlockBulletChest, 1), new Object[] { "ZZZ", "ZXZ", "ZZZ", 
  	        Character.valueOf('Z'), new ItemStack(Blocks.iron_block),
  	      Character.valueOf('Z'), new ItemStack(Items.iron_ingot)});
      GameRegistry.addShapedRecipe(new ItemStack(Register.JudgementSword, 1), new Object[] { " Z ", " Z ", " X ", 
    	        Character.valueOf('Z'), new ItemStack(Register.Lightning),
    	      Character.valueOf('Z'), new ItemStack(Items.stick)});
      GameRegistry.addShapedRecipe(new ItemStack(Register.ImageElement, 1), new Object[] { "XXX", "XXX", "XXX", 
  	      Character.valueOf('X'), new ItemStack(Register.ImagePowder)});
      GameRegistry.addShapedRecipe(new ItemStack(Register.ImageGrain, 1), new Object[] { "XXX", "XXX", "XXX", 
      	      Character.valueOf('X'), new ItemStack(Register.ImageElement)});
      GameRegistry.addShapedRecipe(new ItemStack(Register.ImageIngot, 1), new Object[] { "XXX", "XXX", "XXX", 
      	      Character.valueOf('X'), new ItemStack(Register.ImageGrain)});
      GameRegistry.addShapedRecipe(new ItemStack(Register.ImageBlock, 1), new Object[] { "XXX", "XXX", "XXX", 
      	      Character.valueOf('X'), new ItemStack(Register.ImageIngot)});
    }
    public static void RegisterAchieve() {
    	UltimateAchivenent.achieve();
    }
  }
  
  public static class ServerStart
  {
    public static void RegisterCommands(FMLServerStartingEvent event)
    {
      event.registerServerCommand(new command());
    }
  }
  
  public static class Packet
  {
    public static void sendToServer(IMessage msg)
    {
      Register.instance.sendToServer(msg);
    }
    
    public static void sendToAll(IMessage msg)
    {
      Register.instance.sendToAll(msg);
    }
    
    public static void sendToAllAround(IMessage msg, NetworkRegistry.TargetPoint point)
    {
      Register.instance.sendToAllAround(msg, point);
    }
    
    public static void sendTo(IMessage msg, EntityPlayerMP player)
    {
      if (!(player instanceof FakePlayer)) {
        Register.instance.sendTo(msg, player);
      }
    }
    
    public static void sendToDimension(IMessage msg, int dimension)
    {
      Register.instance.sendToDimension(msg, dimension);
    }
  }
}

package xuanwu.ultimate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.EnchantmentProtection;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;

public class UltimateExplosion
  extends Explosion
{
  public boolean isFlaming;
  public boolean isSmoking = true;
  private int field_77289_h = 16;
  private Random explosionRNG = new Random();
  private World worldObj;
  public double explosionX;
  public double explosionY;
  public double explosionZ;
  public Entity exploder;
  public float explosionSize;
  public List affectedBlockPositions = new ArrayList();
  private Map field_77288_k = new HashMap();
  private static final String __OBFID = "CL_00000134";
  
  public UltimateExplosion(World p_i1948_1_, Entity p_i1948_2_, double p_i1948_3_, double p_i1948_5_, double p_i1948_7_, float p_i1948_9_)
  {
    super(p_i1948_1_, p_i1948_2_, p_i1948_3_, p_i1948_5_, p_i1948_5_, p_i1948_9_);
    this.worldObj = p_i1948_1_;
    this.exploder = p_i1948_2_;
    this.explosionSize = p_i1948_9_;
    this.explosionX = p_i1948_3_;
    this.explosionY = p_i1948_5_;
    this.explosionZ = p_i1948_7_;
  }
  
  public void doExplosionA()
  {
    float f = this.explosionSize;
    HashSet hashset = new HashSet();
    for (int i = 0; i < this.field_77289_h; i++) {
      for (int j = 0; j < this.field_77289_h; j++) {
        for (int k = 0; k < this.field_77289_h; k++) {
          if ((i == 0) || (i == this.field_77289_h - 1) || (j == 0) || (j == this.field_77289_h - 1) || (k == 0) || (k == this.field_77289_h - 1))
          {
            double d0 = i / (this.field_77289_h - 1.0F) * 2.0F - 1.0F;
            double d1 = j / (this.field_77289_h - 1.0F) * 2.0F - 1.0F;
            double d2 = k / (this.field_77289_h - 1.0F) * 2.0F - 1.0F;
            double d3 = Math.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
            d0 /= d3;
            d1 /= d3;
            d2 /= d3;
            float f1 = this.explosionSize * (0.7F + this.worldObj.rand.nextFloat() * 0.6F);
            double d5 = this.explosionX;
            double d6 = this.explosionY;
            double d7 = this.explosionZ;
            for (float f2 = 0.3F; f1 > 0.0F; f1 -= f2 * 0.75F)
            {
              int j1 = MathHelper.floor_double(d5);
              int k1 = MathHelper.floor_double(d6);
              int l1 = MathHelper.floor_double(d7);
              Block block = this.worldObj.getBlock(j1, k1, l1);
              if (block.getMaterial() != Material.air)
              {
                float f3 = this.exploder != null ? this.exploder.func_145772_a(this, this.worldObj, j1, k1, l1, block) : block.getExplosionResistance(this.exploder, this.worldObj, j1, k1, l1, this.explosionX, this.explosionY, this.explosionZ);
                f1 -= (f3 + 0.3F) * f2;
              }
              if ((f1 > 0.0F) && ((this.exploder == null) || (this.exploder.func_145774_a(this, this.worldObj, j1, k1, l1, block, f1)))) {
                hashset.add(new ChunkPosition(j1, k1, l1));
              }
              d5 += d0 * f2;
              d6 += d1 * f2;
              d7 += d2 * f2;
            }
          }
        }
      }
    }
    this.affectedBlockPositions.addAll(hashset);
    this.explosionSize *= 2.0F;
    double i = MathHelper.floor_double(this.explosionX - this.explosionSize - 1.0D);
    int j = MathHelper.floor_double(this.explosionX + this.explosionSize + 1.0D);
    int k = MathHelper.floor_double(this.explosionY - this.explosionSize - 1.0D);
    int i2 = MathHelper.floor_double(this.explosionY + this.explosionSize + 1.0D);
    int l = MathHelper.floor_double(this.explosionZ - this.explosionSize - 1.0D);
    int j2 = MathHelper.floor_double(this.explosionZ + this.explosionSize + 1.0D);
    List list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this.exploder, AxisAlignedBB.getBoundingBox(i, k, l, j, i2, j2));
    ForgeEventFactory.onExplosionDetonate(this.worldObj, this, list, this.explosionSize);
    Vec3 vec3 = Vec3.createVectorHelper(this.explosionX, this.explosionY, this.explosionZ);
    for (int i1 = 0; i1 < list.size(); i1++)
    {
      Entity entity = (Entity)list.get(i1);
      double d4 = entity.getDistance(this.explosionX, this.explosionY, this.explosionZ) / this.explosionSize;
      if (d4 <= 1.0D)
      {
        double d5 = entity.posX - this.explosionX;
        double d6 = entity.posY + entity.getEyeHeight() - this.explosionY;
        double d7 = entity.posZ - this.explosionZ;
        double d9 = MathHelper.sqrt_double(d5 * d5 + d6 * d6 + d7 * d7);
        if (d9 != 0.0D)
        {
          d5 /= d9;
          d6 /= d9;
          d7 /= d9;
          double d10 = this.worldObj.getBlockDensity(vec3, entity.boundingBox);
          double d11 = (1.0D - d4) * d10;
          entity.attackEntityFrom(DamageSource.setExplosionSource(this), (int)((d11 * d11 + d11) / 2.0D * 8.0D * this.explosionSize + 1.0D));
          double d8 = EnchantmentProtection.func_92092_a(entity, d11);
          entity.motionX += d5 * d8;
          entity.motionY += d6 * d8;
          entity.motionZ += d7 * d8;
          if ((entity instanceof EntityPlayer)) {
            this.field_77288_k.put((EntityPlayer)entity, Vec3.createVectorHelper(d5 * d11, d6 * d11, d7 * d11));
          }
        }
      }
    }
    this.explosionSize = f;
  }
  
  public void doExplosionB(boolean p_77279_1_)
  {
    this.worldObj.playSoundEffect(this.explosionX, this.explosionY, this.explosionZ, "random.explode", 4.0F, (1.0F + (this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) * 0.2F) * 0.7F);
    if ((this.explosionSize >= 2.0F) && (this.isSmoking)) {
      this.worldObj.spawnParticle("hugeexplosion", this.explosionX, this.explosionY, this.explosionZ, 1.0D, 0.0D, 0.0D);
    } else {
      this.worldObj.spawnParticle("largeexplode", this.explosionX, this.explosionY, this.explosionZ, 1.0D, 0.0D, 0.0D);
    }
    if (this.isSmoking)
    {
      Iterator iterator = this.affectedBlockPositions.iterator();
      while (iterator.hasNext())
      {
        ChunkPosition chunkposition = (ChunkPosition)iterator.next();
        int i = chunkposition.chunkPosX;
        int j = chunkposition.chunkPosY;
        int k = chunkposition.chunkPosZ;
        Block block = this.worldObj.getBlock(i, j, k);
        if (p_77279_1_)
        {
          double d0 = i + this.worldObj.rand.nextFloat();
          double d1 = j + this.worldObj.rand.nextFloat();
          double d2 = k + this.worldObj.rand.nextFloat();
          double d3 = d0 - this.explosionX;
          double d4 = d1 - this.explosionY;
          double d5 = d2 - this.explosionZ;
          double d6 = MathHelper.sqrt_double(d3 * d3 + d4 * d4 + d5 * d5);
          d3 /= d6;
          d4 /= d6;
          d5 /= d6;
          double d7 = 0.5D / (d6 / this.explosionSize + 0.1D);
          d7 *= (this.worldObj.rand.nextFloat() * this.worldObj.rand.nextFloat() + 0.3F);
          d3 *= d7;
          d4 *= d7;
          d5 *= d7;
          this.worldObj.spawnParticle("explode", (d0 + this.explosionX * 1.0D) / 2.0D, (d1 + this.explosionY * 1.0D) / 2.0D, (d2 + this.explosionZ * 1.0D) / 2.0D, d3, d4, d5);
          this.worldObj.spawnParticle("smoke", d0, d1, d2, d3, d4, d5);
        }
        if (block.getMaterial() != Material.air)
        {
          if (block.canDropFromExplosion(this)) {
            block.dropBlockAsItemWithChance(this.worldObj, i, j, k, this.worldObj.getBlockMetadata(i, j, k), 1.0F / this.explosionSize, 0);
          }
          block.onBlockExploded(this.worldObj, i, j, k, this);
        }
      }
    }
    if (this.isFlaming)
    {
      Iterator iterator = this.affectedBlockPositions.iterator();
      while (iterator.hasNext())
      {
        ChunkPosition chunkposition = (ChunkPosition)iterator.next();
        int i = chunkposition.chunkPosX;
        int j = chunkposition.chunkPosY;
        int k = chunkposition.chunkPosZ;
        Block block = this.worldObj.getBlock(i, j, k);
        Block block1 = this.worldObj.getBlock(i, j - 1, k);
        if ((block.getMaterial() == Material.air) && (block1.func_149730_j()) && (this.explosionRNG.nextInt(3) == 0)) {
          this.worldObj.setBlock(i, j, k, Blocks.fire);
        }
      }
    }
  }
  
  public Map func_77277_b()
  {
    return this.field_77288_k;
  }
  
  public EntityLivingBase getExplosivePlacedBy()
  {
    return (this.exploder instanceof EntityLivingBase) ? (EntityLivingBase)this.exploder : (this.exploder instanceof EntityTNTPrimed) ? ((EntityTNTPrimed)this.exploder).getTntPlacedBy() : this.exploder == null ? null : null;
  }
}

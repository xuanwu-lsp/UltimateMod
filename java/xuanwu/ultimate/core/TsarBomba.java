package xuanwu.ultimate.core;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import xuanwu.ultimate.entity.EntityTsarBlast;

public class TsarBomba
{
  public int posX;
  public int posY;
  public int posZ;
  public int lastposX;
  public int lastposZ;
  public int radius;
  public World worldObj;
  private int n;
  private int nlimit;
  private int shell;
  private int leg;
  private int element;
  private boolean isTree;
  private int treeHeight;
  private int repeatCount = 0;
  
  public TsarBomba(int x, int y, int z, World world, int rad)
  {
    this.posX = x;
    this.posY = y;
    this.posZ = z;
    this.worldObj = world;
    this.radius = rad;
    this.nlimit = ((this.radius + 25) * (this.radius + 25) * 4);
    this.lastposX = 0;
    this.lastposZ = 0;
    this.repeatCount = 0;
  }
  
  public void detonate(EntityTsarBlast tsarblast)
  {
    this.n = 1;
    int rad = (int)(this.radius * 1.3D);
    for (int X = -rad; X < rad; X++) {
      for (int Z = -rad; Z < rad; Z++)
      {
        double dist = Math.sqrt(X * X + Z * Z);
        if (dist < rad) {
          for (int Y = 128; Y > 0; Y--)
          {
            Block block = this.worldObj.getBlock(X + this.posX, Y, Z + this.posZ);
            if ((block == Blocks.water) || (block == Blocks.lava) || (block == Blocks.flowing_water) || (block == Blocks.flowing_lava)) {
              this.worldObj.setBlockToAir(X + this.posX, Y, Z + this.posZ);
            }
          }
        }
      }
    }
  }
  
  public void update(EntityTsarBlast tsarblast)
  {
    if ((this.n > 0) && (this.n < this.nlimit))
    {
      boolean repeat = processChunk(this.lastposX, this.lastposZ);
      
      this.shell = ((int)Math.floor((Math.sqrt(this.n) + 1.0D) / 2.0D));
      int shell2 = 2 * this.shell;
      this.leg = ((int)Math.floor((this.n - (shell2 - 1) * (shell2 - 1)) / shell2));
      this.element = (this.n - (shell2 - 1) * (shell2 - 1) - shell2 * this.leg - this.shell + 1);
      this.lastposX = (this.leg == 0 ? this.shell : this.leg == 1 ? -this.element : this.leg == 2 ? -this.shell : this.element);
      
      this.lastposZ = (this.leg == 0 ? this.element : this.leg == 1 ? this.shell : this.leg == 2 ? -this.element : -this.shell);
      
      this.n += 1;
      if (!repeat)
      {
        this.repeatCount += 1;
        if (this.repeatCount < 200)
        {
          update(tsarblast);
        }
        else
        {
          this.repeatCount = 0;
          return;
        }
      }
    }
    else
    {
      tsarblast.tsar = null;
      tsarblast.setDead();
    }
  }
  
  boolean isPrime(int number)
  {
    if (number <= 1) {
      return false;
    }
    for (int i = 2; i * i <= number; i++) {
      if (number % i == 0) {
        return false;
      }
    }
    return true;
  }
  
  private boolean processChunk(int x, int z)
  {
    double dist = Math.sqrt(x * x + z * z);
    if (dist < this.radius + 1)
    {
      int y = getTopBlock(x + this.posX, z + this.posZ, dist);
      int ylimit = (int)Math.floor(this.posY + (y - this.posY) * 0.5D - (this.radius - dist) / 2.0D + Math.sin(dist * 0.5D) * 1.15D);
      for (int Y = y; Y > ylimit; Y--) {
        if (Y != 0)
        {
          Block block = this.worldObj.getBlock(x + this.posX, Y, z + this.posZ);
          this.worldObj.setBlock(x + this.posX, Y, z + this.posZ, Blocks.air);
        }
      }
      double limit = this.radius / 2 + this.worldObj.rand.nextInt(this.radius / 4) + 7.5D;
      if (dist < limit)
      {
        int blockType = this.worldObj.rand.nextInt(4) + 1;
        if ((x >= 0) && (z < 0)) {
          blockType = 1;
        }
        if ((x > 0) && (z >= 0)) {
          blockType = 2;
        }
        if ((x <= 0) && (z > 0)) {
          blockType = 3;
        }
        if ((x < 0) && (z <= 0)) {
          blockType = 4;
        }
        int metadata = (int)Math.ceil(16.0D / limit * dist);
        metadata -= this.radius / 10 - 1;
        if (metadata < 0) {
          metadata = -metadata;
        }
        metadata++;
        if (metadata > 15) {
          metadata = 15;
        }
        for (int Y = ylimit; Y > ylimit - (this.worldObj.rand.nextInt(5) + 2); Y--) {
          if (Y != 0)
          {
            Block block = this.worldObj.getBlock(x + this.posX, Y, z + this.posZ);
            if (blockType == 1) {
              this.worldObj.setBlock(x + this.posX, Y, z + this.posZ, Blocks.air);
            } else if (blockType == 2) {
              this.worldObj.setBlock(x + this.posX, Y, z + this.posZ, Blocks.air);
            } else if (blockType == 3) {
              this.worldObj.setBlock(x + this.posX, Y, z + this.posZ, Blocks.air);
            } else {
              this.worldObj.setBlock(x + this.posX, Y, z + this.posZ, Blocks.air);
            }
            this.worldObj.setBlockMetadataWithNotify(x + this.posX, Y, z + this.posZ, metadata, 3);
          }
        }
      }
      if (this.isTree)
      {
        this.isTree = false;
        int metadata = (int)Math.floor(16.0D / this.radius * dist);
        if (metadata < 0) {
          metadata = 0;
        }
        metadata++;
        if (metadata > 15) {
          metadata = 15;
        }
        for (int Y = ylimit; Y > ylimit - this.treeHeight; Y--) {
          if (Y != 0)
          {
            this.worldObj.setBlock(x + this.posX, Y, z + this.posZ, Blocks.air);
            this.worldObj.setBlockMetadataWithNotify(x + this.posX, Y, z + this.posZ, metadata, 3);
          }
        }
      }
      return true;
    }
    if (dist <= this.radius * 1.3125D)
    {
      int y = getTopBlock(x + this.posX, z + this.posZ, dist);
      int ylimit = (int)Math.ceil(Math.sin((dist - this.radius - this.radius / 16) * this.radius * 0.001875D) * (this.radius / 16));
      if (dist >= this.radius + 5)
      {
        int metadata = (int)Math.floor(16.0D / this.radius * dist);
        if (metadata < 0) {
          metadata = 0;
        }
        metadata++;
        if (metadata > 15) {
          metadata = 15;
        }
        for (int Y = ylimit; Y >= 0; Y--) {
          if (Y != 0)
          {
            int yy = Y + y;
            Block blockID = this.worldObj.getBlock(x + this.posX, yy, z + this.posZ);
            if (!this.isTree)
            {
              Block blockID1 = this.worldObj.getBlock(x + this.posX, yy - ylimit, z + this.posZ);
              int datavalue = this.worldObj.getBlockMetadata(x + this.posX, yy - ylimit, z + this.posZ);
              this.worldObj.setBlock(x + this.posX, yy, z + this.posZ, blockID1, datavalue, 3);
            }
            else
            {
              this.isTree = false;
              for (int Yy = 0; Yy >= -this.treeHeight; Yy--)
              {
                this.worldObj.setBlock(x + this.posX, yy + Yy, z + this.posZ, Blocks.air);
                this.worldObj.setBlockMetadataWithNotify(x + this.posX, yy + Yy, z + this.posZ, metadata, 3);
              }
              break;
            }
          }
        }
      }
      else
      {
        Block blockID = this.worldObj.getBlock(x + this.posX, y, z + this.posZ);
        if ((blockID != Blocks.bedrock) && 
          (blockID != null) && (!blockID.isOpaqueCube())) {
          this.worldObj.setBlock(x + this.posX, y, z + this.posZ, Blocks.air);
        }
        if (this.isTree)
        {
          this.isTree = false;
          int metadata = (int)Math.floor(16.0D / this.radius * dist);
          if (metadata < 0) {
            metadata = 0;
          }
          metadata++;
          if (metadata > 15) {
            metadata = 15;
          }
          for (int Y = ylimit; Y > ylimit - this.treeHeight; Y--)
          {
            this.worldObj.setBlock(x + this.posX, Y, z + this.posZ, Blocks.air);
            this.worldObj.setBlockMetadataWithNotify(x + this.posX, Y, z + this.posZ, metadata, 3);
          }
        }
      }
      return true;
    }
    return false;
  }
  
  private int getTopBlock(int x, int z, double dist)
  {
    int foundY = 0;
    boolean found = false;
    for (int y = 256; y > 0; y--)
    {
      Block blockID = this.worldObj.getBlock(x, y, z);
      if (blockID != Blocks.air) {
        if ((!blockID.isOpaqueCube()) || (blockID == Blocks.log))
        {
          this.worldObj.setBlockToAir(x, y, z);
          if ((dist > this.radius / 2) && (blockID == Blocks.log) && (this.worldObj.getBlock(x, y - 1, z) == Blocks.log)) {
            this.isTree = true;
          }
          if ((!found) && (this.isTree))
          {
            foundY = y;
            found = true;
          }
        }
        else
        {
          if (!found) {
            return y;
          }
          this.treeHeight = (foundY - y);
          return foundY;
        }
      }
    }
    return foundY;
  }
}

package xuanwu.ultimate;

import cpw.mods.fml.common.IWorldGenerator;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import xuanwu.ultimate.register.Register;

public class OreGeneration implements IWorldGenerator {
  public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
	 int id = world.provider.dimensionId;
	 if(id == 0) {
	  generateOre(random,world,chunkX * 16, chunkZ * 16,Register.ImageOre,Blocks.stone,4,5,10);
	 }
  }
  
  protected void generateOre(Random rand, World world, int chunkX, int chunkZ, Block state, Block generateIn, int veinSize, int veinsPerChunk, int maxY) {
    for (int vein = 0; vein < veinsPerChunk; vein++) {
      int randPosX = chunkX + rand.nextInt(16);
      int randPosY = rand.nextInt(maxY);
      int randPosZ = chunkZ + rand.nextInt(16);
      WorldGenMinable genMinable = new WorldGenMinable(state, veinSize, generateIn);
      genMinable.generate(world, rand, randPosX, randPosY, randPosZ);
    } 
  }
}

package xuanwu.ultimate.world;

import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.NoiseGeneratorOctaves;
import net.minecraft.world.gen.structure.MapGenScatteredFeature;
import net.minecraft.world.storage.WorldInfo;

public class ChunkProviderPollute
  implements IChunkProvider
{
  private Random rand;
  private NoiseGeneratorOctaves noiseGeneratorOctaves;
  private World worldObj;
  private WorldType field_147435_p;
  private MapGenScatteredFeature scatteredFeatureGenerator = new MapGenScatteredFeature();
  private BiomeGenBase[] biomesForGeneration;
  double[] genBuff;
  
  public ChunkProviderPollute(World worldObj, long seed)
  {
    this.worldObj = worldObj;
    this.field_147435_p = worldObj.getWorldInfo().getTerrainType();
    this.rand = new Random(seed);
    this.noiseGeneratorOctaves = new NoiseGeneratorOctaves(this.rand, 16);
    this.genBuff = new double['?'];
  }
  
  public void generation(int x, int z, Block[] blocks)
  {
    this.genBuff = this.noiseGeneratorOctaves.generateNoiseOctaves(this.genBuff, x * 16, 0, z * 16, 16, 1, 16, 1000.0D, 8000.0D, 1000.0D);
    int i = 0;
    for (double d : this.genBuff)
    {
      int by = (int)(60.0D + d / 8000.0D);
      for (int in = 0; in < by; in++)
      {
        int bi = i * 256 | in;
        blocks[bi] = Blocks.bedrock;
      }
      int bi = i * 256 | by;
      
      blocks[bi] = (blocks[bi] = Blocks.bedrock);
      i++;
    }
  }
  
  public Chunk loadChunk(int p_73158_1_, int p_73158_2_)
  {
    return provideChunk(p_73158_1_, p_73158_2_);
  }
  
  public Chunk provideChunk(int x, int z)
  {
    this.rand.setSeed(x * 341873128712L + z * 132897987541L);
    Block[] ablock = new Block[65536];
    byte[] abyte = new byte[65536];
    generation(x, z, ablock);
    this.biomesForGeneration = this.worldObj.getWorldChunkManager().loadBlockGeneratorData(this.biomesForGeneration, x * 16, z * 16, 16, 16);
    Chunk chunk = new Chunk(this.worldObj, ablock, abyte, x, z);
    byte[] abyte1 = chunk.getBiomeArray();
    for (int k = 0; k < abyte1.length; k++) {
      abyte1[k] = ((byte)this.biomesForGeneration[k].biomeID);
    }
    chunk.generateSkylightMap();
    return chunk;
  }
  
  public boolean chunkExists(int p_73149_1_, int p_73149_2_)
  {
    return true;
  }
  
  public void populate(IChunkProvider provider, int x, int z) {}
  
  public boolean saveChunks(boolean p_73151_1_, IProgressUpdate p_73151_2_)
  {
    return true;
  }
  
  public void saveExtraData() {}
  
  public boolean unloadQueuedChunks()
  {
    return false;
  }
  
  public boolean canSave()
  {
    return true;
  }
  
  public String makeString()
  {
    return "RandomLevelSource";
  }
  
  public List getPossibleCreatures(EnumCreatureType p_73155_1_, int p_73155_2_, int p_73155_3_, int p_73155_4_)
  {
    BiomeGenBase biomegenbase = this.worldObj.getBiomeGenForCoords(p_73155_2_, p_73155_4_);
    return (p_73155_1_ == EnumCreatureType.monster) && (this.scatteredFeatureGenerator.func_143030_a(p_73155_2_, p_73155_3_, p_73155_4_)) ? this.scatteredFeatureGenerator.getScatteredFeatureSpawnList() : biomegenbase.getSpawnableList(p_73155_1_);
  }
  
  public ChunkPosition func_147416_a(World p_147416_1_, String p_147416_2_, int p_147416_3_, int p_147416_4_, int p_147416_5_)
  {
    return null;
  }
  
  public int getLoadedChunkCount()
  {
    return 0;
  }
  
  public void recreateStructures(int p_82695_1_, int p_82695_2_) {}
}

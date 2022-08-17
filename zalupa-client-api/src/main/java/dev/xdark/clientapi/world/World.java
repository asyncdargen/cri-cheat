package dev.xdark.clientapi.world;

import dev.xdark.clientapi.Side;
import dev.xdark.clientapi.SidedApi;
import dev.xdark.clientapi.biome.Biome;
import dev.xdark.clientapi.entity.Entity;
import dev.xdark.clientapi.entity.EntityWeatherEffect;
import dev.xdark.clientapi.sound.SoundCategory;
import dev.xdark.clientapi.sound.SoundEvent;
import dev.xdark.clientapi.tile.TileEntity;
import dev.xdark.clientapi.util.ParticleType;
import dev.xdark.clientapi.world.chunk.Chunk;
import dev.xdark.clientapi.world.chunk.ChunkProvider;

@SidedApi(Side.BOTH)
public interface World extends BlockAccess {

  boolean canSeeSky(int x, int y, int z);

  int getLight(int x, int y, int z);

  int getLight(int x, int y, int z, boolean checkNeighbours);

  int getHeight(int x, int z);

  int getChunksLowestHorizon(int x, int z);

  int getCombinedLight(int x, int y, int z, int value);

  float getLightBrightness(int x, int y, int z);

  float getStarBrightness(float partialTicks);

  long getTotalTime();

  long getTime();

  boolean isThundering();

  boolean isRaining();

  boolean isRainingAt(int x, int y, int z);

  double getHorizon();

  int getDimension();

  @SidedApi(Side.BOTH)
  boolean spawnEntity(Entity entity);

  @SidedApi(Side.BOTH)
  void removeEntity(Entity entity);

  @SidedApi(Side.BOTH)
  TileEntity getTileEntity(int x, int y, int z);

  @SidedApi(Side.BOTH)
  ChunkProvider getChunkProvider();

  @SidedApi(Side.BOTH)
  void spawnParticle(
      ParticleType type,
      boolean isLongDistance,
      double x,
      double y,
      double z,
      double motionX,
      double motionY,
      double motionZ,
      int... data);

  @SidedApi(Side.BOTH)
  void setTime(long time);

  @SidedApi(Side.BOTH)
  void playSound(
      double x,
      double y,
      double z,
      SoundEvent event,
      SoundCategory category,
      float volume,
      float pitch,
      boolean checkDistance);

  @SidedApi(Side.BOTH)
  boolean addWeatherEffect(EntityWeatherEffect weatherEffect);

  @SidedApi(Side.BOTH)
  void removeWeatherEffect(EntityWeatherEffect weatherEffect);

  @SidedApi(Side.BOTH)
  float getThunderStrength(float delta);

  @SidedApi(Side.BOTH)
  void setThunderStrength(float thunderingStrength);

  @SidedApi(Side.BOTH)
  float getRainStrength(float delta);

  @SidedApi(Side.BOTH)
  void setRainStrength(float rainingStrength);

  @SidedApi(Side.BOTH)
  Entity getEntity(int id);

  @SidedApi(Side.BOTH)
  void markBlockRangeForRenderUpdate(int x1, int y1, int z1, int x2, int y2, int z2);

  int getHeight();

  @SidedApi(Side.BOTH)
  void setBiome(int x, int z, Biome biome);

  @SidedApi(Side.BOTH)
  Chunk getChunk(int x, int z);

  @SidedApi(Side.BOTH)
  boolean isChunkGeneratedAt(int x, int z);
}

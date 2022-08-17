package dev.xdark.clientapi.entity;

import dev.xdark.clientapi.Side;
import dev.xdark.clientapi.SidedApi;
import dev.xdark.clientapi.nbt.NBTTagCompound;
import dev.xdark.clientapi.world.World;

@SidedApi(Side.BOTH)
public interface EntityProvider {

  int ITEM = 1;
  int XP_ORB = 2;
  int AREA_EFFECT_CLOUD = 3;
  int ELDER_GUARDIAN = 4;
  int WITHER_SKELETON = 5;
  int STRAY = 6;
  int EGG = 7;
  int LEASH_KNOT = 8;
  int PAINTING = 9;
  int ARROW = 10;
  int SNOWBALL = 11;
  int FIREBALL = 12;
  int SMALL_FIREBALL = 13;
  int ENDER_PEARL = 14;
  int EYE_OF_ENDER = 15;
  int POTION = 16;
  int XP_BOTTLE = 17;
  int ITEM_FRAME = 18;
  int WITHER_SKULL = 19;
  int TNT = 20;
  int FALLING_BLOCK = 21;
  int FIREWORKS_ROCKET = 22;
  int HUSK = 23;
  int SPECTRAL_ARROW = 24;
  int SHULKER_BULLET = 25;
  int DRAGON_FIREBALL = 26;
  int ZOMBIE_VILLAGER = 27;
  int SKELETON_HORSE = 28;
  int ZOMBIE_HORSE = 29;
  int ARMOR_STAND = 30;
  int DONKEY = 31;
  int MULE = 32;
  int EVOCATION_FANGS = 33;
  int EVOCATION_ILLAGER = 34;
  int VEX = 35;
  int VINDICATION_ILLAGER = 36;
  int ILLUSION_ILLAGER = 37;
  int COMMANDBLOCK_MINECART = 40;
  int BOAT = 41;
  int MINECART = 42;
  int CHEST_MINECART = 43;
  int FURNACE_MINECART = 44;
  int TNT_MINECART = 45;
  int HOPPER_MINECART = 46;
  int SPAWNER_MINECART = 47;
  int CREEPER = 50;
  int SKELETON = 51;
  int SPIDER = 52;
  int GIANT = 53;
  int ZOMBIE = 54;
  int SLIME = 55;
  int GHAST = 56;
  int ZOMBIE_PIGMAN = 57;
  int ENDERMAN = 58;
  int CAVE_SPIDER = 59;
  int SILVERFISH = 60;
  int BLAZE = 61;
  int MAGMA_CUBE = 62;
  int ENDER_DRAGON = 63;
  int WITHER = 64;
  int BAT = 65;
  int WITCH = 66;
  int ENDERMITE = 67;
  int GUARDIAN = 68;
  int SHULKER = 69;
  int PIG = 90;
  int SHEEP = 91;
  int COW = 92;
  int CHICKEN = 93;
  int SQUID = 94;
  int WOLF = 95;
  int MOOSHROOM = 96;
  int SNOWMAN = 97;
  int OCELOT = 98;
  int VILLAGER_GOLEM = 99;
  int HORSE = 100;
  int RABBIT = 101;
  int POLAR_BEAR = 102;
  int LLAMA = 103;
  int LLAMA_SPIT = 104;
  int PARROT = 105;
  int VILLAGER = 120;
  int ENDER_CRYSTAL = 200;
  int PLAYER = 1000;
  int LIGHTNING_BOLT = 1001;

  Entity newEntity(NBTTagCompound info, World world);

  Entity newEntity(int id, World world);
}
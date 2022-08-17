package dev.xdark.clientapi.block;

import static dev.xdark.clientapi.util.SideEffects.objectValue;

import dev.xdark.clientapi.Side;
import dev.xdark.clientapi.SidedApi;

@SidedApi(Side.BOTH)
public final class Blocks {

  public static final Block AIR = objectValue(),
      STONE = objectValue(),
      GRASS = objectValue(),
      DIRT = objectValue(),
      COBBLESTONE = objectValue(),
      PLANKS = objectValue(),
      SAPLING = objectValue(),
      BEDROCK = objectValue(),
      FLOWING_WATER = objectValue(),
      WATER = objectValue(),
      FLOWING_LAVA = objectValue(),
      LAVA = objectValue(),
      SAND = objectValue(),
      GRAVEL = objectValue(),
      GOLD_ORE = objectValue(),
      IRON_ORE = objectValue(),
      COAL_ORE = objectValue(),
      LOG = objectValue(),
      LOG2 = objectValue(),
      LEAVES = objectValue(),
      LEAVES2 = objectValue(),
      SPONGE = objectValue(),
      GLASS = objectValue(),
      LAPIS_ORE = objectValue(),
      LAPIS_BLOCK = objectValue(),
      DISPENSER = objectValue(),
      SANDSTONE = objectValue(),
      NOTEBLOCK = objectValue(),
      BED = objectValue(),
      GOLDEN_RAIL = objectValue(),
      DETECTOR_RAIL = objectValue(),
      STICKY_PISTON = objectValue(),
      WEB = objectValue(),
      TALLGRASS = objectValue(),
      DEADBUSH = objectValue(),
      PISTON = objectValue(),
      PISTON_HEAD = objectValue(),
      WOOL = objectValue(),
      PISTON_EXTENSION = objectValue(),
      YELLOW_FLOWER = objectValue(),
      RED_FLOWER = objectValue(),
      BROWN_MUSHROOM = objectValue(),
      RED_MUSHROOM = objectValue(),
      GOLD_BLOCK = objectValue(),
      IRON_BLOCK = objectValue(),
      DOUBLE_STONE_SLAB = objectValue(),
      STONE_SLAB = objectValue(),
      BRICK_BLOCK = objectValue(),
      TNT = objectValue(),
      BOOKSHELF = objectValue(),
      MOSSY_COBBLESTONE = objectValue(),
      OBSIDIAN = objectValue(),
      TORCH = objectValue(),
      FIRE = objectValue(),
      MOB_SPAWNER = objectValue(),
      OAK_STAIRS = objectValue(),
      CHEST = objectValue(),
      REDSTONE_WIRE = objectValue(),
      DIAMOND_ORE = objectValue(),
      DIAMOND_BLOCK = objectValue(),
      CRAFTING_TABLE = objectValue(),
      WHEAT = objectValue(),
      FARMLAND = objectValue(),
      FURNACE = objectValue(),
      LIT_FURNACE = objectValue(),
      STANDING_SIGN = objectValue(),
      OAK_DOOR = objectValue(),
      SPRUCE_DOOR = objectValue(),
      BIRCH_DOOR = objectValue(),
      JUNGLE_DOOR = objectValue(),
      ACACIA_DOOR = objectValue(),
      DARK_OAK_DOOR = objectValue(),
      LADDER = objectValue(),
      RAIL = objectValue(),
      STONE_STAIRS = objectValue(),
      WALL_SIGN = objectValue(),
      LEVER = objectValue(),
      STONE_PRESSURE_PLATE = objectValue(),
      IRON_DOOR = objectValue(),
      WOODEN_PRESSURE_PLATE = objectValue(),
      REDSTONE_ORE = objectValue(),
      LIT_REDSTONE_ORE = objectValue(),
      UNLIT_REDSTONE_TORCH = objectValue(),
      REDSTONE_TORCH = objectValue(),
      STONE_BUTTON = objectValue(),
      SNOW_LAYER = objectValue(),
      ICE = objectValue(),
      SNOW = objectValue(),
      CACTUS = objectValue(),
      CLAY = objectValue(),
      REEDS = objectValue(),
      JUKEBOX = objectValue(),
      OAK_FENCE = objectValue(),
      SPRUCE_FENCE = objectValue(),
      BIRCH_FENCE = objectValue(),
      JUNGLE_FENCE = objectValue(),
      DARK_OAK_FENCE = objectValue(),
      ACACIA_FENCE = objectValue(),
      PUMPKIN = objectValue(),
      NETHERRACK = objectValue(),
      SOUL_SAND = objectValue(),
      GLOWSTONE = objectValue(),
      PORTAL = objectValue(),
      LIT_PUMPKIN = objectValue(),
      CAKE = objectValue(),
      UNPOWERED_REPEATER = objectValue(),
      POWERED_REPEATER = objectValue(),
      TRAPDOOR = objectValue(),
      MONSTER_EGG = objectValue(),
      STONEBRICK = objectValue(),
      BROWN_MUSHROOM_BLOCK = objectValue(),
      RED_MUSHROOM_BLOCK = objectValue(),
      IRON_BARS = objectValue(),
      GLASS_PANE = objectValue(),
      MELON_BLOCK = objectValue(),
      PUMPKIN_STEM = objectValue(),
      MELON_STEM = objectValue(),
      VINE = objectValue(),
      OAK_FENCE_GATE = objectValue(),
      SPRUCE_FENCE_GATE = objectValue(),
      BIRCH_FENCE_GATE = objectValue(),
      JUNGLE_FENCE_GATE = objectValue(),
      DARK_OAK_FENCE_GATE = objectValue(),
      ACACIA_FENCE_GATE = objectValue(),
      BRICK_STAIRS = objectValue(),
      STONE_BRICK_STAIRS = objectValue(),
      MYCELIUM = objectValue(),
      WATERLILY = objectValue(),
      NETHER_BRICK = objectValue(),
      NETHER_BRICK_FENCE = objectValue(),
      NETHER_BRICK_STAIRS = objectValue(),
      NETHER_WART = objectValue(),
      ENCHANTING_TABLE = objectValue(),
      BREWING_STAND = objectValue(),
      CAULDRON = objectValue(),
      END_PORTAL = objectValue(),
      END_PORTAL_FRAME = objectValue(),
      END_STONE = objectValue(),
      DRAGON_EGG = objectValue(),
      REDSTONE_LAMP = objectValue(),
      LIT_REDSTONE_LAMP = objectValue(),
      DOUBLE_WOODEN_SLAB = objectValue(),
      WOODEN_SLAB = objectValue(),
      COCOA = objectValue(),
      SANDSTONE_STAIRS = objectValue(),
      EMERALD_ORE = objectValue(),
      ENDER_CHEST = objectValue(),
      TRIPWIRE_HOOK = objectValue(),
      TRIPWIRE = objectValue(),
      EMERALD_BLOCK = objectValue(),
      SPRUCE_STAIRS = objectValue(),
      BIRCH_STAIRS = objectValue(),
      JUNGLE_STAIRS = objectValue(),
      COMMAND_BLOCK = objectValue(),
      BEACON = objectValue(),
      COBBLESTONE_WALL = objectValue(),
      FLOWER_POT = objectValue(),
      CARROTS = objectValue(),
      POTATOES = objectValue(),
      WOODEN_BUTTON = objectValue(),
      SKULL = objectValue(),
      ANVIL = objectValue(),
      TRAPPED_CHEST = objectValue(),
      LIGHT_WEIGHTED_PRESSURE_PLATE = objectValue(),
      HEAVY_WEIGHTED_PRESSURE_PLATE = objectValue(),
      UNPOWERED_COMPARATOR = objectValue(),
      POWERED_COMPARATOR = objectValue(),
      DAYLIGHT_DETECTOR = objectValue(),
      DAYLIGHT_DETECTOR_INVERTED = objectValue(),
      REDSTONE_BLOCK = objectValue(),
      QUARTZ_ORE = objectValue(),
      HOPPER = objectValue(),
      QUARTZ_BLOCK = objectValue(),
      QUARTZ_STAIRS = objectValue(),
      ACTIVATOR_RAIL = objectValue(),
      DROPPER = objectValue(),
      STAINED_HARDENED_CLAY = objectValue(),
      BARRIER = objectValue(),
      IRON_TRAPDOOR = objectValue(),
      HAY_BLOCK = objectValue(),
      CARPET = objectValue(),
      HARDENED_CLAY = objectValue(),
      COAL_BLOCK = objectValue(),
      PACKED_ICE = objectValue(),
      ACACIA_STAIRS = objectValue(),
      DARK_OAK_STAIRS = objectValue(),
      SLIME_BLOCK = objectValue(),
      DOUBLE_PLANT = objectValue(),
      STAINED_GLASS = objectValue(),
      STAINED_GLASS_PANE = objectValue(),
      PRISMARINE = objectValue(),
      SEA_LANTERN = objectValue(),
      STANDING_BANNER = objectValue(),
      WALL_BANNER = objectValue(),
      RED_SANDSTONE = objectValue(),
      RED_SANDSTONE_STAIRS = objectValue(),
      DOUBLE_STONE_SLAB2 = objectValue(),
      STONE_SLAB2 = objectValue(),
      END_ROD = objectValue(),
      CHORUS_PLANT = objectValue(),
      CHORUS_FLOWER = objectValue(),
      PURPUR_BLOCK = objectValue(),
      PURPUR_PILLAR = objectValue(),
      PURPUR_STAIRS = objectValue(),
      PURPUR_DOUBLE_SLAB = objectValue(),
      PURPUR_SLAB = objectValue(),
      END_BRICKS = objectValue(),
      BEETROOTS = objectValue(),
      GRASS_PATH = objectValue(),
      END_GATEWAY = objectValue(),
      REPEATING_COMMAND_BLOCK = objectValue(),
      CHAIN_COMMAND_BLOCK = objectValue(),
      FROSTED_ICE = objectValue(),
      MAGMA = objectValue(),
      NETHER_WART_BLOCK = objectValue(),
      RED_NETHER_BRICK = objectValue(),
      BONE_BLOCK = objectValue(),
      STRUCTURE_VOID = objectValue(),
      OBSERVER = objectValue(),
      WHITE_SHULKER_BOX = objectValue(),
      ORANGE_SHULKER_BOX = objectValue(),
      MAGENTA_SHULKER_BOX = objectValue(),
      LIGHT_BLUE_SHULKER_BOX = objectValue(),
      YELLOW_SHULKER_BOX = objectValue(),
      LIME_SHULKER_BOX = objectValue(),
      PINK_SHULKER_BOX = objectValue(),
      GRAY_SHULKER_BOX = objectValue(),
      SILVER_SHULKER_BOX = objectValue(),
      CYAN_SHULKER_BOX = objectValue(),
      PURPLE_SHULKER_BOX = objectValue(),
      BLUE_SHULKER_BOX = objectValue(),
      BROWN_SHULKER_BOX = objectValue(),
      GREEN_SHULKER_BOX = objectValue(),
      RED_SHULKER_BOX = objectValue(),
      BLACK_SHULKER_BOX = objectValue(),
      WHITE_GLAZED_TERRACOTTA = objectValue(),
      ORANGE_GLAZED_TERRACOTTA = objectValue(),
      MAGENTA_GLAZED_TERRACOTTA = objectValue(),
      LIGHT_BLUE_GLAZED_TERRACOTTA = objectValue(),
      YELLOW_GLAZED_TERRACOTTA = objectValue(),
      LIME_GLAZED_TERRACOTTA = objectValue(),
      PINK_GLAZED_TERRACOTTA = objectValue(),
      GRAY_GLAZED_TERRACOTTA = objectValue(),
      SILVER_GLAZED_TERRACOTTA = objectValue(),
      CYAN_GLAZED_TERRACOTTA = objectValue(),
      PURPLE_GLAZED_TERRACOTTA = objectValue(),
      BLUE_GLAZED_TERRACOTTA = objectValue(),
      BROWN_GLAZED_TERRACOTTA = objectValue(),
      GREEN_GLAZED_TERRACOTTA = objectValue(),
      RED_GLAZED_TERRACOTTA = objectValue(),
      BLACK_GLAZED_TERRACOTTA = objectValue(),
      CONCRETE = objectValue(),
      CONCRETE_POWDER = objectValue(),
      STRUCTURE_BLOCK = objectValue(),
      BLUE_ICE = objectValue(),
      DEAD_TUBE_CORAL = objectValue(),
      DEAD_BRAIN_CORAL = objectValue(),
      DEAD_BUBBLE_CORAL = objectValue(),
      DEAD_FIRE_CORAL = objectValue(),
      DEAD_HORN_CORAL = objectValue(),
      TUBE_CORAL = objectValue(),
      BRAIN_CORAL = objectValue(),
      BUBBLE_CORAL = objectValue(),
      FIRE_CORAL = objectValue(),
      HORN_CORAL = objectValue(),
      NETHERITE = objectValue(),
      CRYING_OBSIDIAN = objectValue(),
      CRIMSON_NYLIUM = objectValue(),
      WARPED_NYLIUM = objectValue(),
      TARGET = objectValue(),
      LODESTONE = objectValue(),
      HONEY = objectValue(),
      SHROOMLIGHT = objectValue();

  private Blocks() {
  }
}

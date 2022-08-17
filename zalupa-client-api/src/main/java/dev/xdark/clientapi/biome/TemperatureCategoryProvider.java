package dev.xdark.clientapi.biome;

import dev.xdark.clientapi.Side;
import dev.xdark.clientapi.SidedApi;

@SidedApi(Side.BOTH)
public interface TemperatureCategoryProvider {

    TemperatureCategory getCategory(Biome biome);
}

package dev.xdark.clientapi.attribute;

import dev.xdark.clientapi.Side;
import dev.xdark.clientapi.SidedApi;

@SidedApi(Side.BOTH)
public interface Attribute {

  String getName();

  double clampValue(double value);

  double getDefaultValue();

  boolean shouldWatch();

  Attribute getParent();
}

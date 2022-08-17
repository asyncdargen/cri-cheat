package dev.xdark.clientapi.attribute;

import dev.xdark.clientapi.Side;
import dev.xdark.clientapi.SidedApi;
import dev.xdark.clientapi.util.CompileStub;

@SidedApi(Side.BOTH)
public interface RangedAttribute extends Attribute {

  double getMinValue();

  double getMaxValue();

  String getDescription();

  @SidedApi(Side.BOTH)
  interface Builder {

    static Builder builder() {
      throw CompileStub.create();
    }

    Builder minValue(double minValue);

    Builder maxValue(double maxValue);

    Builder description(String description);

    RangedAttribute build();
  }
}

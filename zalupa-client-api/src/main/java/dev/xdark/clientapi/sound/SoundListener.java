package dev.xdark.clientapi.sound;

import dev.xdark.clientapi.Side;
import dev.xdark.clientapi.SidedApi;

@SidedApi(Side.BOTH)
public interface SoundListener {

  void soundPlay(String source);

  void soundStop(String source);
}

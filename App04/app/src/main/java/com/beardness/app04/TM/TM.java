package com.beardness.app04.TM;

public interface TM {
  void start();
  void stop();
  void reset();
  int getSeconds();
  boolean isRun();
}

package com.beardness.app04.TM;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.widget.TextView;
import java.util.Locale;

import com.beardness.app04.R;

public class TimeManager implements TM {
  
  private final Handler handler = new Handler();
  private final Runnable runner = getRunner();
  
  private boolean isRun = false;
  private int seconds = 0;
  
  private TextView tv;
  
  public TimeManager(TextView tv) {
    this.tv = tv;
  }
  
  public TimeManager(boolean isRun, int seconds, TextView tv) {
    this.isRun = isRun;
    this.seconds = seconds;
    this.tv = tv;
    
    setTime();
    
    if (isRun) {
      handler.post(runner);
    }
  }
  
  @Override
  public int getSeconds() {
    return seconds;
  }
  
  @Override
  public boolean isRun() {
    return isRun;
  }
  
  @Override
  public void start() {
    handler.post(runner);
    isRun = true;
  }
  
  @Override
  public void stop() {
    isRun = false;
    handler.removeCallbacks(runner);
  }
  
  @Override
  public void reset() {
    isRun = false;
    seconds = 0;
    handler.removeCallbacks(runner);
    tv.setText(R.string.watch);
  }
  
  private void setTime() {
    int hrs = seconds / 3600;
    int mns = (seconds % 3600) / 60;
    int scs = seconds % 60;
  
    String time = String.format(Locale.getDefault(), "%02d:%02d:%02d", hrs, mns, scs);
    tv.setText(time);
  }
  
  private Runnable getRunner() {
    return new Runnable() {
      @Override
      public void run() {
        setTime();
    
        if (isRun) {
          seconds++;
        }
        handler.postDelayed(this, 1000);
      }
    };
  }
  
}

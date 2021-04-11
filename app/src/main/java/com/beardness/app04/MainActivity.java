package com.beardness.app04;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.beardness.app04.TM.TM;
import com.beardness.app04.TM.TimeManager;

public class MainActivity extends AppCompatActivity {

  private TextView tv;
  private TM timeManager;
  
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Log.d("ON", "CREATE");
    setContentView(R.layout.activity_main);
    tv = findViewById(R.id.watch);
  
    if (savedInstanceState == null) {
      timeManager = new TimeManager(tv);
    }
    else {
      timeManager = new TimeManager(
              savedInstanceState.getBoolean("isRun"),
              savedInstanceState.getInt("seconds"),
              tv
      );
    }
  }
  
  @Override
  protected void onStart() {
    super.onStart();
    Log.d("ON", "START");
  }
  
  @Override
  protected void onResume() {
    super.onResume();
    Log.d("ON", "RESUME");
  }
  
  @Override
  protected void onPause() {
    super.onPause();
    Log.d("ON", "PAUSE");
  }
  
  @Override
  protected void onStop() {
    Log.d("ON", "STOP");
    super.onStop();
    timeManager.stop();
  }
  
  @Override
  protected void onRestart() {
    super.onRestart();
    Log.d("ON", "RESTART");
  }
  
  @Override
  protected void onDestroy() {
    Log.d("ON", "DESTROY");
    super.onDestroy();
  }
  
  @Override
  protected void onSaveInstanceState(@NonNull Bundle outState) {
    super.onSaveInstanceState(outState);
    outState.putInt("seconds", timeManager.getSeconds());
    outState.putBoolean("isRun", timeManager.isRun());
  }
  
  public void onClickStart(View view) {
    timeManager.start();
  }
  
  public void onClickStop(View view) {
    timeManager.stop();
  }
  
  public void onClickReset(View view) {
    timeManager.reset();
  }
}
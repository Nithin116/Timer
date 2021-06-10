package com.nappdevelopment.timers;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView timerTextView;
    SeekBar timerSeekBar;
    boolean active = false;
    Button goButton;
    CountDownTimer countDownTimer;

    public void resetTimer (){
        timerTextView.setText("0:30");
        timerSeekBar.setProgress(30);
        timerSeekBar.setEnabled(true);
        countDownTimer.cancel();
        goButton.setText("Go!");
        active = false;
    }
    public void buttonClicked (View view){
            if (active) {
                resetTimer();

            }else{
                active = true;
                timerSeekBar.setEnabled(false);
                goButton.setText("STOP!");

                              //Log.i("button Pressed","Hi");
                countDownTimer = new CountDownTimer(timerSeekBar.getProgress() * 1000 + 100, 1000) {
                    @Override
                    public void onTick(long l) {
                        updateTimer((int) l / 1000);
                    }

                    @Override
                    public void onFinish() {
                        //Log.i("Finished","Times Up!!");
                        //to play a noise once timer is finished
                        //MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext() , R.raw.airhorn);
                        //mediaPlayer.start();
                    }
                }.start();
            }

    }
     public void updateTimer (int secondsLeft){
         int minutes = secondsLeft / 60 ;
         int seconds = secondsLeft - (minutes * 60);

         String secondString = Integer.toString(seconds);

         if (seconds <= 9){
             secondString = "0" + secondString;
         }

         timerTextView.setText(Integer.toString(minutes) + ":" + secondString );


     }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timerSeekBar = findViewById(R.id.timerSeekBar);
        timerTextView = findViewById(R.id.countDown);
        goButton = findViewById(R.id.button);
        timerSeekBar.setMax(600);
        timerSeekBar.setProgress(30);

        timerSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
             updateTimer(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

       // ImageView homeImage = (ImageView) findViewById(R.drawable.Home);
/*
        new CountDownTimer(10000,1000){

            public void onTick(long milliSecondsUntilDone){
                Log.i("Seconds left",String.valueOf(milliSecondsUntilDone / 1000));
            }

            public void onFinish(){
                Log.i("Completed","how are you!");
            }
        }.start();



/*        final Handler handler = new Handler();
        Runnable run = new Runnable() {
            @Override
            public void run() {
                Log.i("Hi","how are you!");
                handler.postDelayed(this, 1000);  //to run the handler after some time
            }
        };
    handler.post(run);
*/
    }
}

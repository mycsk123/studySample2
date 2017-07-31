package project5_1.cookandroid.com.chap13_exam13_2;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mPlayer;
    SeekBar sbMP3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Switch switch1 = (Switch)findViewById(R.id.swtich1);
        sbMP3 = (SeekBar)findViewById(R.id.sbMP3);

        sbMP3.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if(b)
                {
                    mPlayer.seekTo(i);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        switch1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(switch1.isChecked() == true){
                    mPlayer = MediaPlayer.create(MainActivity.this, R.raw.song1);
                    mPlayer.start();

                    new Thread(){
                        public void run(){
                            if(mPlayer == null) return;
                            sbMP3.setMax(mPlayer.getDuration());

                            while (mPlayer.isPlaying()){
                                sbMP3.setProgress(mPlayer.getCurrentPosition());
                                SystemClock.sleep(200);
                            }
                        }

                    }.start();

                }
                else
                {
                    mPlayer.stop();
                    sbMP3.setProgress(0);
                }
            }
        });


    }
}
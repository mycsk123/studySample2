package project5_1.cookandroid.com.chap13_sample01;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final MediaPlayer mPlayer;
        mPlayer = MediaPlayer.create(MainActivity.this, R.raw.song1);

        final Switch switch1 = (Switch)findViewById(R.id.swtich1);

        switch1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(switch1.isChecked()){
                    mPlayer.start();
                }
                else
                {
                    mPlayer.stop();
                }
            }
        });



    }
}

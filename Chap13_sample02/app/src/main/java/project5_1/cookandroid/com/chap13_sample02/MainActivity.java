package project5_1.cookandroid.com.chap13_sample02;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ProgressBar pb1;
        Button btnInc, btnDec;
        pb1 = (ProgressBar)findViewById(R.id.progressBar1);
        btnInc = (Button)findViewById(R.id.btnInc);
        btnDec = (Button)findViewById(R.id.btnDec);

        btnInc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //ProgressBar 10씩 증가
                pb1.incrementProgressBy(10);
            }
        });

        btnDec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pb1.incrementProgressBy(-10);
            }
        });

        final TextView tvSeek = (TextView)findViewById(R.id.tvSeek);
        SeekBar seekBar1 = (SeekBar)findViewById(R.id.seekBar1);

        seekBar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                tvSeek.setText("진행률 : "+ i + " %");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
}

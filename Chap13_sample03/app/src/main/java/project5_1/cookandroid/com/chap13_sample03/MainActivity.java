package project5_1.cookandroid.com.chap13_sample03;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {
    ProgressBar pb1, pb2;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pb1 = (ProgressBar)findViewById(R.id.pb1);
        pb2 = (ProgressBar)findViewById(R.id.pb2);
        btn = (Button)findViewById(R.id.button1);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //스레드 사용
                new Thread(){
                    public void run(){
                        for(int i = pb1.getProgress(); i<100; i=i+2){
                            pb1.setProgress(pb1.getProgress()+2);
                            SystemClock.sleep(100);
                        }
                    }
                }.start();

                new Thread(){
                    public void run(){
                        for(int i = pb2.getProgress(); i<100; i++){
                            pb2.setProgress(pb2.getProgress() + 1);
                            SystemClock.sleep(100);
                        }
                    }

                }.start();
            }
        });

    }
}

package project5_1.cookandroid.com.chap13_sample04;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    ProgressBar pb1, pb2;
    Button btn;
    TextView tv1, tv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pb1 = (ProgressBar)findViewById(R.id.pb1);
        pb2 = (ProgressBar)findViewById(R.id.pb2);
        btn = (Button)findViewById(R.id.button1);
        tv1 = (TextView)findViewById(R.id.tv1);
        tv2 = (TextView)findViewById(R.id.tv2);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(){
                    public void run(){
                        for(int i = pb1.getProgress(); i<100; i=i+2){
                            //스래드 내부에 위젯의 글자를 변경 시 오류를 막기 위한 조치
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    pb1.setProgress(pb1.getProgress()+2);
                                    tv1.setText("1번 진행률 : "+pb1.getProgress()+ "%");
                                }
                            });

                            SystemClock.sleep(100);
                        }
                    }
                }.start();

                new Thread(){
                    public void run() {
                        for (int i = pb2.getProgress(); i < 100; i++) {

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    pb2.setProgress(pb2.getProgress() + 1);
                                    tv2.setText("2번 진행률 : "+pb2.getProgress()+ "%");
                                }
                            });
                            SystemClock.sleep(100);
                        }
                    }
                }.start();

            }
        });



    }
}

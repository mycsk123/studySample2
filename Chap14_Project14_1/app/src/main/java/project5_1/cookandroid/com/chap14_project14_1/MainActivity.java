package project5_1.cookandroid.com.chap14_project14_1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Intent intent;
    Button btnStart, btnStop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("서비스 테스트 예제");

        intent = new Intent(this, MusicService.class);
        btnStart = (Button)findViewById(R.id.btnStart);
        btnStop = (Button)findViewById(R.id.btnStop);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startService(intent);
                Log.i("서비스 테스트", "startService()");
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopService(intent);
                Log.i("서비스 테스트", "stopService()");
            }
        });

    }
}

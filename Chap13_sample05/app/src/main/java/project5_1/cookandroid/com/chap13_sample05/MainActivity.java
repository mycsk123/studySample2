package project5_1.cookandroid.com.chap13_sample05;

import android.Manifest;
import android.icu.text.SimpleDateFormat;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.SystemClock;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listViewMP3;
    Button btnPlay, btnStop;
    TextView tvMP3;
    ProgressBar pbMP3;
    TextView tvTime;

    ArrayList<String> mp3List;
    String selectedMP3;

    String mp3Path = Environment.getExternalStorageDirectory().getPath() + "/";

    MediaPlayer mPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("간단 MP3 플레이어");

        //manifests에도 권한 추가할 것
        ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE}, MODE_PRIVATE);

        mp3List = new ArrayList<String>();

        File[] listFiles = new File(mp3Path).listFiles();
        String fileName, extName;

        for(File file : listFiles){
            //파일명
            fileName = file.getName();
            //확장자
            extName = fileName.substring(fileName.length() - 3);

            if(extName.equals((String) "mp3"))
                mp3List.add(fileName);
        }

        listViewMP3 = (ListView)findViewById(R.id.listViewMP3);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice, mp3List);

        listViewMP3.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listViewMP3.setAdapter(adapter);
        listViewMP3.setItemChecked(0, true);

        tvTime = (TextView)findViewById(R.id.tvTime);


        listViewMP3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                selectedMP3 = mp3List.get(i);
            }
        });

        selectedMP3 = mp3List.get(0);


        btnPlay = (Button)findViewById(R.id.btnPlay);
        btnStop = (Button)findViewById(R.id.btnStop);
        tvMP3 = (TextView)findViewById(R.id.tvMP3);
        pbMP3 = (ProgressBar)findViewById(R.id.pbMP3);

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                try{
                    mPlayer = new MediaPlayer();
                    mPlayer.setDataSource(mp3Path + selectedMP3);
                    mPlayer.prepare();
                    mPlayer.start();

                    btnPlay.setClickable(false);
                    btnStop.setClickable(true);

                    tvMP3.setText("실행중인 음악 : "+selectedMP3);

                    new Thread(){
                        SimpleDateFormat timeFormat = new SimpleDateFormat("mm:ss");

                        public void run(){
                            if(mPlayer == null) return;
                            pbMP3.setMax(mPlayer.getDuration());

                            while(mPlayer.isPlaying()){
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        pbMP3.setProgress(mPlayer.getCurrentPosition());
                                        tvTime.setText("진행 시간 : "+timeFormat.format(mPlayer.getCurrentPosition()));
                                    }
                                });
                                SystemClock.sleep(200);
                            }
                        }

                    }.start();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPlayer.stop();
                mPlayer.reset();

                btnPlay.setClickable(true);
                btnStop.setClickable(false);

                tvMP3.setText("실행중인 음악 : ");

                //음악 중지 시 프로그레스바와 진행시간 초기화
                pbMP3.setProgress(0);
                tvTime.setText("진행 시간 : ");
            }
        });

        btnStop.setClickable(false);


    }
}

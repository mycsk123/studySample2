package cookmap.cookandroid.com.chap08_exam8_2;

import android.Manifest;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    Button btnPrev, btnNext;
    //커스텀 위젯
    myPictureView myPicture;
    int curNum;
    int test;
    String testS;
    File[] imageFiles;
    String imageFname;
    TextView tv1;
    String pageNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("간단 이미지 뷰어(변경)");

        ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE}, MODE_PRIVATE);

        btnPrev = (Button)findViewById(R.id.btnPrev);
        btnNext = (Button)findViewById(R.id.btnNext);
        tv1 = (TextView)findViewById(R.id.tv1);

        myPicture = (myPictureView)findViewById(R.id.myPictureView1);

        //sd카드에서 파일을 읽어 listfiles 메소드로 배열 생성
        imageFiles = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/Pictures").listFiles();

        //imageFiles 배열에 읽어들이는 순서의 규칙성은??
        for(int i = 0; i<imageFiles.length; i++){
            Log.v("파일", i+"번째 값 " + imageFiles[i].toString());
        }

        imageFname = imageFiles[0].toString();
        myPicture.imagePath = imageFname;



        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(curNum <= 0)
                {
                    curNum = imageFiles.length;
                }

                curNum--;
                pageNum = Integer.toString(curNum+1) + "/" + Integer.toString(imageFiles.length);
                tv1.setText(pageNum);

                imageFname = imageFiles[curNum].toString();
                myPicture.imagePath = imageFname;
                myPicture.invalidate();

            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(curNum >= imageFiles.length -1)
                {
                    curNum = -1;
                }
                curNum++;
                pageNum = Integer.toString(curNum+1) + "/" + Integer.toString(imageFiles.length);
                tv1.setText(pageNum);
                imageFname = imageFiles[curNum].toString();
                myPicture.imagePath = imageFname;
                myPicture.invalidate();

            }
        });

    }
}

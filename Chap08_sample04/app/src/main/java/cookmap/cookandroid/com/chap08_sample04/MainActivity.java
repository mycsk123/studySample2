package cookmap.cookandroid.com.chap08_sample04;

import android.Manifest;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                MODE_PRIVATE);

        Button btnMkdir, btnRmdir;
        btnMkdir = (Button)findViewById(R.id.btnMkdir);
        btnRmdir = (Button)findViewById(R.id.btnRmdir);

        final String strSDpath = Environment.getExternalStorageDirectory().getAbsolutePath();
        final File myDir = new File(strSDpath + "/mydir");

        btnMkdir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDir.mkdir();
            }
        });

        btnRmdir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDir.delete();
            }
        });
    }
}

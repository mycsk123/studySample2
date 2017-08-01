package cookmap.cookandroid.com.chap08_sample03;

import android.Manifest;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.FileInputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnRead;
        final EditText edtSD;

        btnRead = (Button)findViewById(R.id.btnRead);
        edtSD = (EditText)findViewById(R.id.edtSD);

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                MODE_PRIVATE);

        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    FileInputStream inFs = new FileInputStream("/storage/emulated/0/raw_text.txt");
                    byte[] txt = new byte[inFs.available()];
                    inFs.read(txt);
                    edtSD.setText(new String(txt));
                    inFs.close();
                }catch (IOException e){

                }
            }
        });

    }
}

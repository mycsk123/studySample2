package cookmap.cookandroid.com.chap08_sample05;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnFilelist;
        final EditText edtFilelist;
        btnFilelist = (Button)findViewById(R.id.btnFilelist);
        edtFilelist = (EditText)findViewById(R.id.edtFilelist);

        btnFilelist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sysDir = Environment.getRootDirectory().getAbsolutePath();
                File[] sysFiles = (new File(sysDir).listFiles());

                String strFname;
                for(int i =0; i<sysFiles.length; i++){
                    if(sysFiles[i].isDirectory() == true){
                        strFname = "<폴더> "+sysFiles[i].toString();
                    }
                    else{
                        strFname = "<파일> "+sysFiles[i].toString();
                    }

                    edtFilelist.setText(edtFilelist.getText() + "\n" +strFname);

                }
            }
        });
    }
}

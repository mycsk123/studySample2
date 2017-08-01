package cookmap.cookandroid.com.chap08_sample02;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnRead;
        final EditText edtRaw;
        btnRead = (Button)findViewById(R.id.btnRead);
        edtRaw = (EditText)findViewById(R.id.edtRaw);

        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    //raw에서 파일 읽기는 inputStrea 사용(읽기 전용)
                    InputStream inputS = getResources().openRawResource(R.raw.raw_text);
                    //available() 메소드는 입력 스트림에서 읽을 수 있는 바이트 수를 반환
                    byte[] txt = new byte[inputS.available()];
                    inputS.read(txt);
                    edtRaw.setText(new String(txt));
                    inputS.close();
                }catch (IOException e){

                }
            }
        });
    }
}

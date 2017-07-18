package project5_1.cookandroid.com.mid_a_1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    EditText edt1, edt2, edt3;
    Button btn1, btn2;

    TextView textView[] = new TextView[9];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edt1 = (EditText)findViewById(R.id.edt1);
        edt2 = (EditText)findViewById(R.id.edt2);
        edt3 = (EditText)findViewById(R.id.edt3);

        btn1 = (Button)findViewById(R.id.btn1);
        btn2 = (Button)findViewById(R.id.btn2);

        textView[0] = (TextView)findViewById(R.id.textView1);
        textView[1] = (TextView)findViewById(R.id.textView2);
        textView[2] = (TextView)findViewById(R.id.textView3);
        textView[3] = (TextView)findViewById(R.id.textView4);
        textView[4] = (TextView)findViewById(R.id.textView5);
        textView[5] = (TextView)findViewById(R.id.textView6);
        textView[6] = (TextView)findViewById(R.id.textView7);
        textView[7] = (TextView)findViewById(R.id.textView8);
        textView[8] = (TextView)findViewById(R.id.textView9);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int rand1 = new Random().nextInt(8)+2;
                int rand2 = new Random().nextInt(8)+2;

                edt1.setText(Integer.toString(rand1));
                edt2.setText(Integer.toString(rand2));

            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int result = Integer.parseInt(edt1.getText().toString()) * Integer.parseInt(edt2.getText().toString());

                if( Integer.parseInt(edt3.getText().toString()) == result)
                {
                    Toast.makeText(getApplicationContext(), "정답입니다!", Toast.LENGTH_SHORT).show();

                    for(int i = 0; i<textView.length; i++)
                    {
                        textView[i].setText("");
                    }

                }else
                {
                    Toast.makeText(getApplicationContext(), "틀렸습니다!", Toast.LENGTH_SHORT).show();
                    result(Integer.parseInt(edt1.getText().toString()));
                }

            }
        });

    }

    public  void result(int num){

        for(int i =0; i < textView.length; i++)
        {
            int tempNum = i+1;
            String tempResult = num + " * " + tempNum + " = " + (num*tempNum);
            textView[i].setText(tempResult);
        }

    }
}

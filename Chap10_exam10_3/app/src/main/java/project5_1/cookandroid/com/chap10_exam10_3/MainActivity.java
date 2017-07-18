package project5_1.cookandroid.com.chap10_exam10_3;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    EditText edtNum1, edtNum2;
    Intent intent

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        RadioGroup rg = (RadioGroup)findViewById(R.id.rg);
        RadioButton rdSum = (RadioButton)findViewById(R.id.rdSum);
        RadioButton rdSub = (RadioButton)findViewById(R.id.rdSub);
        RadioButton rdMul = (RadioButton)findViewById(R.id.rdMul);
        RadioButton rdDiv = (RadioButton)findViewById(R.id.rdDiv);


        Button btnNewActivity = (Button)findViewById(R.id.btnNewActivity);
        btnNewActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                switch (rg.getCheckedRadioButtonId()){
                    case R.id.rdSum:
                        break;
                    default:

                }


                edtNum1 = (EditText)findViewById(R.id.edtNum1);
                edtNum2 = (EditText)findViewById(R.id.edtNum2);

                intent = new Intent(getApplicationContext(), SecondActivity.class);

                intent.putExtra("Num1", Integer.parseInt(edtNum1.getText().toString()));
                intent.putExtra("Num2", Integer.parseInt(edtNum2.getText().toString()));

                startActivityForResult(intent, 0);
            }
        });
    }
}

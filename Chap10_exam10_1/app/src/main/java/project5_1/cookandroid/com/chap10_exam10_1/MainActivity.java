package project5_1.cookandroid.com.chap10_exam10_1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final RadioGroup rg1 = (RadioGroup)findViewById(R.id.rg1);
        RadioButton rdSecond = (RadioButton)findViewById(R.id.rdSecond);
        RadioButton rdThird = (RadioButton)findViewById(R.id.rdThird);

        Button btnNewActivity = (Button)findViewById(R.id.btnNewActivity);


        btnNewActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                switch(rg1.getCheckedRadioButtonId()){
                    case R.id.rdSecond:
                        Intent intent1 = new Intent(getApplicationContext(), SecondActivity.class);
                        startActivity(intent1);
                        break;
                    case R.id.rdThird:
                        Intent intent2 = new Intent(getApplicationContext(), ThirdActivity.class);
                        startActivity(intent2);
                        break;
                    default:
                }

            }
        });

    }
}

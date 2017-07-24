package project5_1.cookandroid.com.chap10_exam10_3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class SecondActivity extends AppCompatActivity {

    Button btnBack;
    double getNum1, getNum2;
    int getcalNum;
    double result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        setTitle("second 액티비티");

        Intent inIntent = getIntent();
        getNum1 = inIntent.getIntExtra("Num1", 0);
        getNum2 = inIntent.getIntExtra("Num2", 0);
        getcalNum = inIntent.getIntExtra("Cal", 0);

        btnBack = (Button)findViewById(R.id.btnBack);

        btnBack.setOnClickListener(new View.OnClickListener() {

            Intent outIntent = new Intent(getApplicationContext(), MainActivity.class);

            @Override
            public void onClick(View view) {
                switch (getcalNum){
                    case 1:
                        result = getNum1 + getNum2;
                        break;
                    case 2:
                        result = getNum1 - getNum2;
                        break;
                    case 3:
                        result = getNum1 * getNum2;
                        break;
                    case 4:
                        result = getNum1 / getNum2;
                        break;
                    default:
                        break;
                }

                outIntent.putExtra("hap", result);
                setResult(RESULT_OK, outIntent);

                finish();

            }
        });
    }
}

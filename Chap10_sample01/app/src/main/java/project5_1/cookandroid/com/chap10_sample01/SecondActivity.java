package project5_1.cookandroid.com.chap10_sample01;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        setTitle("second 액티비티");

        Intent inIntent = getIntent();
        final int hapValue = inIntent.getIntExtra("Num1", 0)+inIntent.getIntExtra("Num2", 0);

        Button btnReturn = (Button)findViewById(R.id.btnRetrun);
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent outIntet = new Intent(getApplicationContext(), MainActivity.class);
                outIntet.putExtra("Hap", hapValue);
                setResult(RESULT_OK, outIntet);
                finish();
            }
        });
    }
}

package project5_1.cookandroid.com.chap10_exam10_2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        setTitle("투표 결과");

        Intent intent = getIntent();
        int[] voteResult = intent.getIntArrayExtra("VoteCount");
        String[] imageName  = intent.getStringArrayExtra("ImageName");

        TextView tv[] = new TextView[imageName.length];
        RatingBar rbar[] = new RatingBar[imageName.length];

        Integer tvID[] = { R.id.tv1, R.id.tv2, R.id.tv3, R.id.tv4, R.id.tv5,
                R.id.tv6, R.id.tv7, R.id.tv8, R.id.tv9};
        Integer rbarID[] = { R.id.rbar1,R.id.rbar2, R.id.rbar3, R.id.rbar4,
                R.id.rbar5,R.id.rbar6, R.id.rbar7, R.id.rbar8,
                R.id.rbar9};
        Integer imageFileId[] = { R.drawable.m001, R.drawable.m002, R.drawable.m003
                R.drawable.m004, R.drawable.m005, R.drawable.m006,
                R.drawable.m007, R.drawable.m008, R.drawable.m009};

        int maxCount = 0;
        for(int i = 0; i< voteResult.length; i++){
            if(maxCount < voteResult[i]){
                maxCount = i;
            }
        }

        TextView voteText = (TextView)findViewById(R.id.voteText);
        voteText.setText(imageName[maxCount]);

        ImageView imgView = (ImageView)findViewById(R.id.imgView);
        imgView.setImageResource(imageFileId[maxCount]);

        for(int i = 0; i<voteResult.length; i++){
            tv[i] = (TextView)findViewById(tvID[i]);
            rbar[i] = (RatingBar)findViewById(rbarID[i]);
        }

        for(int i =0; i<voteResult.length; i++){
            tv[i].setText(imageName[i]);
            rbar[i].setRating((float)voteResult[i]);
        }

        Button btnReturn = (Button)findViewById(R.id.btnReturn);
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}

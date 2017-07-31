package project5_1.cookandroid.com.chap14_sample01;

import android.Manifest;
import android.database.Cursor;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.provider.CallLog;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Date;

public class MainActivity extends AppCompatActivity {
    Button btnCall;
    EditText edtCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_CALL_LOG}, MODE_PRIVATE);

        btnCall = (Button)findViewById(R.id.btnCall);
        edtCall = (EditText)findViewById(R.id.edtCall);

        btnCall.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                edtCall.setText(getCallHistory());
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public String getCallHistory(){
        String[] callSet = new String[]{
                CallLog.Calls.DATE, CallLog.Calls.TYPE,
                CallLog.Calls.NUMBER, CallLog.Calls.DURATION};

        Cursor c = getContentResolver().query(CallLog.Calls.CONTENT_URI, callSet, null, null, null);

        if(c==null)
            return "통화 기록 없음";

        StringBuffer callBuff = new StringBuffer();
        callBuff.append("\n날짜 : 구분 : 전화번호 : 통화시간\n\n");
        c.moveToFirst();

        do{
            long callDate = c.getLong(0);
            SimpleDateFormat datePattern = new SimpleDateFormat("yyyy-MM-dd");
            String date_str = datePattern.format(new Date(callDate));
            callBuff.append(date_str + ":");

            if(c.getInt(1) == CallLog.Calls.INCOMING_TYPE){
                callBuff.append("착신 : ");
            }
            else{
                callBuff.append("발신 : ");
            }

            callBuff.append(c.getString(2) + ":");
            callBuff.append(c.getString(3) + ":");

        }while(c.moveToNext());

        c.close();
        return callBuff.toString();
    }
}

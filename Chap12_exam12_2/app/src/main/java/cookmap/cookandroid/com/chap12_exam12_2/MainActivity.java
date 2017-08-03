package cookmap.cookandroid.com.chap12_exam12_2;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    myDBHelper myHelper;
    EditText edtName, edtNumber;
    TextView tvNameResult, tvNumberResult;
    Button btnInit, btnInsert, btnSelect, btnChange, btnRemove;
    SQLiteDatabase sqlDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("가수 그룹 관리 DB");

        edtName = (EditText)findViewById(R.id.edtName);
        edtNumber = (EditText)findViewById(R.id.edtNumber);

        tvNameResult = (TextView)findViewById(R.id.tvNameResult);
        tvNumberResult = (TextView)findViewById(R.id.tvNumberResult);

        btnInit = (Button)findViewById(R.id.btnInit);
        btnInsert = (Button)findViewById(R.id.btnInsert);
        btnSelect = (Button)findViewById(R.id.btnSelect);
        btnChange = (Button)findViewById(R.id.btnChange);
        btnRemove = (Button)findViewById(R.id.btnRemove);

        myHelper = new myDBHelper(this);

        btnInit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v("테스트", "초기화");
                sqlDB = myHelper.getWritableDatabase();
                myHelper.onUpgrade(sqlDB, 1, 2);
                sqlDB.close();
            }
        });

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v("테스트", "쓰기");
                sqlDB = myHelper.getWritableDatabase();
                sqlDB.execSQL("insert into groupTBL values('"
                        + edtName.getText().toString() +"',"
                        + edtNumber.getText().toString()+");");
                sqlDB.close();
                Toast.makeText(getApplicationContext(), "입력됨", Toast.LENGTH_SHORT).show();
                btnSelect.callOnClick();
            }
        });

        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v("테스트", "읽기");
                sqlDB = myHelper.getReadableDatabase();
                Cursor cursor;
                cursor = sqlDB.rawQuery("select * from groupTBL;", null);

                String strNames = "그룹 이름" + "\r\n" + "-------" + "\r\n";
                String strNumbers = "인원" + "\r\n" + "-------" + "\r\n";

                while(cursor.moveToNext()){
                    strNames += cursor.getString(0) + "\r\n";
                    strNumbers += cursor.getString(1) + "\r\n";
                }

                tvNameResult.setText(strNames);
                tvNumberResult.setText(strNumbers);

                cursor.close();
                sqlDB.close();
            }
        });

        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v("테스트", "수정");
                sqlDB = myHelper.getWritableDatabase();

                sqlDB.execSQL("update groupTBL set gNumber = "
                        + edtNumber.getText().toString()
                        + " where gName = \""
                        + edtName.getText().toString() + "\";");

                sqlDB.close();
                Toast.makeText(getApplicationContext(), "수정됨", Toast.LENGTH_SHORT).show();
                btnSelect.callOnClick();

            }
        });

        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v("테스트", "삭제");
                sqlDB = myHelper.getWritableDatabase();
                sqlDB.execSQL("delete from groupTBL where gName=\""
                        + edtName.getText().toString() +"\";");
                sqlDB.close();
                Toast.makeText(getApplicationContext(), "삭제됨", Toast.LENGTH_SHORT).show();
                btnSelect.callOnClick();
            }
        });

    }


    public class myDBHelper extends SQLiteOpenHelper{

        public myDBHelper(Context context){
            super(context, "groupDB", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL("create table groupTBL (gName CHAR(20) PRIMARY KEY, gNumber INTEGER);");
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            sqLiteDatabase.execSQL("drop table if exists groupTBL");
            onCreate(sqLiteDatabase);
        }
    }

}

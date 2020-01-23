package com.example.jitu_jisu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SleepAddActivity extends AppCompatActivity {
    private final String mTableName = "situation_table";
    private EditText msituation_name;
    private EditText mpre_time;
    private DBHelper helper;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sleep_add);
        helper =  new DBHelper(this);
        msituation_name =(EditText)findViewById(R.id.situation_name);
        mpre_time = (EditText)findViewById(R.id.pre_time);

    }


        public void onclick (View view){
            if(msituation_name.length() != 0 && mpre_time.length() != 0) {
                String name_text = msituation_name.getText().toString();
                String time_text = mpre_time.getText().toString();
                db = helper.getWritableDatabase();
                //保存に成功した際のトースト表示
                Context context = getApplicationContext();
                CharSequence text = "保存しました！";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
                saveData(db, name_text, Integer.valueOf(time_text));
            }
            else {
                //値がnullの場合（未入力の場合）
                Context context = getApplicationContext();
                CharSequence text = "値いれろバーカ";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
    }

    public void saveData(SQLiteDatabase db,String name,int time){
        ContentValues values = new ContentValues();

        values.put("name",name);
        values.put("time",time);
        db.insert(mTableName,null,values);
        db.close();
        finish();

    }
    public void onBackButtonTapped(View view) {
        finish();
    } }

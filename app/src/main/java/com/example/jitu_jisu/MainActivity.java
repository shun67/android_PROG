package com.example.jitu_jisu;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class MainActivity extends AppCompatActivity {
    private int flag =0;
    private String situ_n;
    private String  time;
    private String time_U_s;
    private int flag_l_B=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        String situ_n = intent.getStringExtra("name_s");
        time= intent.getStringExtra("time");

        if(situ_n != null && time != null){
            TextView text_name=(TextView)findViewById(R.id.situation_name);
            TextView text_time=(TextView)findViewById(R.id.set_time);
            text_name.setText( "名前　　:"+situ_n );
            text_time.setText("準備時間:"+time+"分");
            TextView textView = (TextView) findViewById(R.id.button5);
            textView.setText(time+"分");
            flag=1;
        }

    }


    //タイムピッカー表示＆家を出る時間設定
    public void ontimeClick(View view){
        if(flag != 0) {
            Calendar calendar = Calendar.getInstance();
            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            int minute = calendar.get(Calendar.MINUTE);
            TimePickerDialog dialog = new TimePickerDialog(
                    this,
                    new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                            Log.d("test", String.format("%02d:%02d", hourOfDay, minute));
                            String str = String.format(Locale.US, "%d:%d", hourOfDay, minute);
                            //id　go_time_tに設定した時間を表示させている。
                            TextView textView = (TextView) findViewById(R.id.go_time_t);
                            time_U_s=str;
                            textView.setText(str);
                        }
                    },
                    hour, minute, true);
            flag_l_B = 1;
            dialog.show();
        }
        else {
            Context context = getApplicationContext();
            CharSequence text = "先に会う人と時間を登録してください";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }

    public void calwaketime_time(View view){
        String time_s;
        if(flag_l_B == 1 && flag == 1) {
            //ユーザ設定時間
            SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
            Date date = null;
            try {
                date = dateFormat.parse(time_U_s);
            } catch (ParseException e) {
                e.printStackTrace();
            }
//convert date->calender
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            time_s = "-" + time;
            int time_t = Integer.parseInt(time_s);
            calendar.add(Calendar.MINUTE, time_t);
            Date date_c = calendar.getTime();
            String str_change = dateFormat.format(date_c);
            TextView textView = (TextView) findViewById(R.id.test);
            textView.setText(str_change);
        }
        else;

    }

    //シチュエーション設定画面
    public void situationClick(View view) {
        Intent intent = new Intent(this,SituationActivity.class);
        int requestCode = 1001;
// 返却値を考慮したActivityの起動を行う
        startActivityForResult( intent, requestCode );
    }

    //おやすみなさい
    public void sleep_click(View view) {
        Intent intent;
        intent = new Intent(this,wakeup_time.class);
        startActivity(intent);

    }
    }




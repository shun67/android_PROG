package com.example.jitu_jisu;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
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
import java.time.Year;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class MainActivity extends AppCompatActivity {
    private int flag = 0;
    private String situ_n;
    private String time;
    private String time_U_s;
    private int flag_l_B = 0;
    private String ave_time;
    private int flag_B2 = 0;
    Calendar wake_cal = Calendar.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        String situ_n = intent.getStringExtra("name_s");
        time = intent.getStringExtra("time");
        ave_time = intent.getStringExtra("ave");

        if (situ_n != null && time != null) {
            TextView text_name = (TextView) findViewById(R.id.situation_name);
            TextView text_time = (TextView) findViewById(R.id.set_time);
            TextView text_ave = (TextView) findViewById(R.id.ave_time) ;
            text_name.setText("名前　　:" + situ_n);
            text_time.setText("準備時間:" + time + "分");
            text_ave.setText("準備時間:" + ave_time + "分");
            TextView textView = (TextView) findViewById(R.id.button5);
            textView.setText(time + "分");
            TextView textView_a = (TextView) findViewById(R.id.button4);
            textView.setText(time + "分");
            textView_a.setText(ave_time + "分");
            flag = 1;
        }

    }


    //タイムピッカー表示＆家を出る時間設定
    public void ontimeClick(View view) {
        if (flag != 0) {
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
                            time_U_s = str;
                            textView.setText(str);
                        }
                    },
                    hour, minute, true);

            dialog.show();
            flag_l_B = 1;
        } else {
            Context context = getApplicationContext();
            CharSequence text = "先に会う人と時間を登録してください";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }

    public void calwaketime_time(View view) {
        String time_s;
        if (flag_l_B == 1 && flag == 1) {
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
            wake_cal.setTime(date);
            time_s = "-" + time;
            int time_t = Integer.parseInt(time_s);
            wake_cal.add(Calendar.MINUTE, time_t);
            int com_c;
            int com_s;
            int h, m;
            h = wake_cal.get(Calendar.HOUR_OF_DAY);
            m = wake_cal.get(Calendar.MINUTE);
            com_c = calendar.get(Calendar.HOUR_OF_DAY);//now hour
            com_s = wake_cal.get(Calendar.HOUR_OF_DAY); //setting hour
            wake_cal=calendar;
            wake_cal.set(Calendar.HOUR_OF_DAY,h);
            wake_cal.set(Calendar.MINUTE,m);
            wake_cal.set(Calendar.SECOND,0);
            if ((com_s - com_c) >= 0) ;
            else {
                wake_cal.add(Calendar.DAY_OF_MONTH, 1);
            }

            Date date_c = wake_cal.getTime();
            String str_change = dateFormat.format(date_c);
            TextView textView = (TextView) findViewById(R.id.test);
            textView.setText(str_change);
            flag_B2 = 1;
        } else ;

    }

    public void calwaketime_ave(View view) {
        String time_s;
        if (flag_l_B == 1 && flag == 1) {
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
            wake_cal.setTime(date);
            time_s = "-" + ave_time;
            int time_t = Integer.parseInt(time_s);
            wake_cal.add(Calendar.MINUTE, time_t);
            int com_c;
            int com_s;
            int h, m;
            h = wake_cal.get(Calendar.HOUR_OF_DAY);
            m = wake_cal.get(Calendar.MINUTE);
            com_c = calendar.get(Calendar.HOUR_OF_DAY);//now hour
            com_s = wake_cal.get(Calendar.HOUR_OF_DAY); //setting hour
            wake_cal=calendar;
            wake_cal.set(Calendar.HOUR_OF_DAY,h);
            wake_cal.set(Calendar.MINUTE,m);
            wake_cal.set(Calendar.SECOND,0);
            if ((com_s - com_c) >= 0) ;
            else {
                wake_cal.add(Calendar.DAY_OF_MONTH, 1);
            }

            Date date_c = wake_cal.getTime();
            String str_change = dateFormat.format(date_c);
            TextView textView = (TextView) findViewById(R.id.test);
            textView.setText(str_change);
            flag_B2 = 1;
        } else ;

    }

    //シチュエーション設定画面
    public void situationClick(View view) {
        Intent intent = new Intent(this, SituationActivity.class);
        int requestCode = 1001;
// 返却値を考慮したActivityの起動を行う
        startActivityForResult(intent, requestCode);
    }

    //おやすみなさい
    public void sleep_click(View view) {
        Intent intent, intent_s;
        if (flag_B2 == 1) {
            intent = new Intent(this, wakeup_time.class);
            intent_s = new Intent(getApplicationContext(), Alarmservice.class);
            Context ct = getApplication();
            PendingIntent pendingIntent = PendingIntent.getService(ct, PendingIntent.FLAG_ONE_SHOT, intent_s, PendingIntent.FLAG_UPDATE_CURRENT);
            AlarmManager am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            am.set(AlarmManager.RTC_WAKEUP, wake_cal.getTimeInMillis(), pendingIntent);
            startActivity(intent);

        }
        else {
            Context context = getApplicationContext();
            CharSequence text = "必須項目を入力してください";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }

    }
}




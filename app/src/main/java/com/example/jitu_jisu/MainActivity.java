package com.example.jitu_jisu;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Locale;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    //タイムピッカー表示＆家を出る時間設定
    public void ontimeClick(View view){
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        TimePickerDialog dialog = new TimePickerDialog(
                this,
                new TimePickerDialog.OnTimeSetListener(){
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        Log.d("test",String.format("%02d:%02d", hourOfDay,minute));
                        String str = String.format(Locale.US, "%d:%d", hourOfDay, minute);
                        //id　go_time_tに設定した時間を表示させている。
                        TextView textView=(TextView)findViewById(R.id.go_time_t);
                        textView.setText( str );
                    }
                },
                hour,minute,true);
        dialog.show();
    }

    //シチュエーション設定画面
    public void situationClick(View view) {
        Intent intent = new Intent(this,SituationActivity.class);
        startActivity(intent);
    }
    }




package com.example.jitu_jisu;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class Main_masurement extends AppCompatActivity {

    private Handler handler = new Handler();

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            count ++;
            timerText.setText(dataFormat.
                    format(count*period));
            handler.postDelayed(this, period);
        }
    };

    private TextView timerText;
    private SimpleDateFormat dataFormat =
            new SimpleDateFormat("mm:ss", Locale.US);

    private int count, period;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_masurement);
        count = 0;
        period = 100;
        timerText = findViewById(R.id.timer_text);
        timerText.setText(dataFormat.format(0));

        handler.post(runnable);

        // タイマー終了

    }
    public void finish(View view){
        AlertDialog.Builder alertDialog=new AlertDialog.Builder(this);

        // ダイアログの設定
        //アイコン設定
        alertDialog.setTitle("メッセージ");      //タイトル設定
        alertDialog.setMessage("準備時間記録してアプリを終了しますか");  //内容(メッセージ)設定

        // OK(肯定的な)ボタンの設定
        alertDialog.setPositiveButton("はい", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // OKボタン押下時の処理
                android.os.Process.killProcess(android.os.Process.myPid());
                Log.d("AlertDialog", "Positive which :" + which);
            }
        });

        // NG(否定的な)ボタンの設定
        alertDialog.setNegativeButton("いいえ", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // NGボタン押下時の処理
                Log.d("AlertDialog", "Negative which :" + which);
            }
        });

        // ダイアログの作成と描画

        alertDialog.create();
        alertDialog.show();

    }
    }

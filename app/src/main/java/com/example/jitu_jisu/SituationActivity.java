package com.example.jitu_jisu;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class SituationActivity extends AppCompatActivity {
    ListView mylistview;
    DBHelper helper;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_situation);
        //listView
        mylistview=(ListView)findViewById(R.id.r_view);
        helper =  new DBHelper(this);
        db = helper.getReadableDatabase();
       // Cursor cursor = db.query(
       //         "situation_table",
        //        new String[]{"name","time"},
         //       null,
      //          null,
       //         null,
      //          null,
       //         null,
      //          null
      //  );


        //adapter生成
        Cursor cursor = db.rawQuery("select * from situation_table",null);
        String[] from = {"name","time"};
        int[] to = {android.R.id.text1,android.R.id.text2};
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,android.R.layout.simple_list_item_2,cursor,from,to,0);

        //bindして表示
        mylistview.setAdapter(adapter);

        //クリックしたとき各行のデータ（特に_id）を取得
        mylistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                //各要素を取得
               String s1 = ((TextView)view.findViewById(android.R.id.text1)).getText().toString();
                String s2 = ((TextView)view.findViewById(android.R.id.text2)).getText().toString();

                // intentの作成

                Intent intent = new Intent(SituationActivity.this, MainActivity.class);
// intentへ添え字付で値を保持させる
                intent.putExtra( "name_s", s1);
                intent.putExtra("time",s2);
                intent.putExtra("ave","25");

// 返却したい結果ステータスをセットする
                setResult( Activity.RESULT_OK, intent );

// アクティビティを終了させる//

                //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                //intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);

                setResult( Activity.RESULT_OK, intent );
                startActivity(intent);
          }
       });

        //loop(いらない）
       // while(cursor.moveToNext()){
       ///     Log.v("tama", cursor.getString(cursor.getColumnIndex("name")));
       // }
    }


    public void situation_addClick(View view) {
        Intent intent;
        intent = new Intent(this,SleepAddActivity.class);
        startActivity(intent);

    }



}

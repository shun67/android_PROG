package com.example.jitu_jisu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SituationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_situation);
    }
    public void situation_addClick(View view) {
        Intent intent;
        intent = new Intent(this,SleepAddActivity.class);
        startActivity(intent);

    }
}

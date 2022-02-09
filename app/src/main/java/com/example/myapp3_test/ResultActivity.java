package com.example.myapp3_test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    TextView text, tvRekord;
    ConstraintLayout btnHome, btnReply;
    int rekord=0, result=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);


        String txtIntent = getIntent().getExtras().getString("key");

        result = Integer.parseInt(txtIntent);

        rekord = MySharedPreferences.getInstance(ResultActivity.this).getRekord();

        if (result>=rekord) {
            rekord =result;
            MySharedPreferences.getInstance(ResultActivity.this).setRekord(rekord);
        }

        tvRekord = findViewById(R.id.tvRekord);
        text = findViewById(R.id.text);
        tvRekord.setText(rekord+" ball");
        text.setText(result+" ball");




        findViewById(R.id.btnHome).setOnClickListener(view -> {
            Intent intent = new Intent(ResultActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });

        findViewById(R.id.btnReply).setOnClickListener(view -> {
            startActivity(new Intent(ResultActivity.this, TestActivity.class));
            finish();
        });

    }
}
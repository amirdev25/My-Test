package com.example.myapp3_test;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class TestActivity extends AppCompatActivity {

    TextView tvTime, tvBall, tvNumberQuestion, tvQuestion, btnNext;
    RadioButton Answer1, Answer2, Answer3, Answer4;
    RadioGroup radioGroup;
    List<TestData> data;
    int currentIndex = 0;
    int counter = 0;
    int timeCounter = 30;
    CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        loadViews();
        loadTestData();
        setTestData(currentIndex);

        boolean checkboxChecked = MySharedPreferences.getInstance(this).getCheckboxChecked();
        if (checkboxChecked == false) {
            showCustomDialog();
        } else {
            timeManager(currentIndex);
        }

        btnNext.setOnClickListener(view -> {

            int checkedRadioButoonId = radioGroup.getCheckedRadioButtonId();
            if (checkedRadioButoonId == -1) {
                Toast.makeText(TestActivity.this, "Belgilanmadi!", Toast.LENGTH_SHORT).show();
            } else {
                RadioButton radioButton = findViewById(checkedRadioButoonId);
                String text = radioButton.getText().toString();
                if (text == data.get(currentIndex).getRealAnswer()) {
                    counter++;
                    tvBall.setText("" + counter * 5);
                }

                if (data.size() - 1 <= currentIndex) {
                    countDownTimer.cancel();
                    Intent intent = new Intent(TestActivity.this, ResultActivity.class);
                    intent.putExtra("key", tvBall.getText().toString());
                    startActivity(intent);
                    finish();
                } else {
                    currentIndex++;
                    setTestData(currentIndex);
                    radioGroup.clearCheck();
                    timeManager(currentIndex);
                }
            }
        });
    }

    private void showCustomDialog() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(TestActivity.this);
        View view = LayoutInflater.from(TestActivity.this).inflate(R.layout.custom_alert_dialog, null);
        dialog.setView(view);
        dialog.setCancelable(false);
        CheckBox checkBox = view.findViewById(R.id.checkbox);
        dialog.setPositiveButton("Boshlash", (dialogInterface, i) -> {

            boolean isChecked = checkBox.isChecked();
            if (isChecked) {
                MySharedPreferences.getInstance(TestActivity.this).setCheckboxChecked(true);
            }
            timeManager(currentIndex);
        });
        dialog.create().show();
    }

    private void timeManager(int currentIndex1) {
        if (countDownTimer != null) {
            countDownTimer.cancel();
            countDownTimer = null;
        }

        loadViews();
        loadTestData();
        timeCounter = 30;

        countDownTimer = new CountDownTimer(31000, 1000) {
            @Override
            public void onTick(long l) {
                if (timeCounter >= 10) {
                    tvTime.setText("00:" + timeCounter);
                } else {
                    tvTime.setText("00:0" + timeCounter);
                }
                timeCounter--;
            }

            @Override
            public void onFinish() {

                int checkedRadioGroupId = radioGroup.getCheckedRadioButtonId();
                RadioButton radioButton = findViewById(checkedRadioGroupId);
                if (checkedRadioGroupId != -1) {
                    String txt = radioButton.getText().toString();
                    if (txt.equals(data.get(currentIndex1).getRealAnswer())) {
                        counter++;
                        tvBall.setText("" + counter * 5);
                    }
                }

                if (data.size() - 1 <= currentIndex) {
                    Intent intent = new Intent(TestActivity.this, ResultActivity.class);
                    String txt = tvBall.getText().toString() + " ball";
                    intent.putExtra("key", txt);
                    startActivity(intent);
                    finish();
                } else {
                    currentIndex++;
                    setTestData(currentIndex);
                    timeManager(currentIndex);
                    Toast.makeText(TestActivity.this, "Vaqt tugadi!", Toast.LENGTH_SHORT).show();
                }
            }
        }.start();
    }

    private void setTestData(int currentIndex) {
        loadViews();
        tvNumberQuestion.setText(currentIndex + 1 + " - savol");
        Answer1.setText(data.get(currentIndex).getAnswer1());
        Answer2.setText(data.get(currentIndex).getAnswer2());
        Answer3.setText(data.get(currentIndex).getAnswer3());
        Answer4.setText(data.get(currentIndex).getAnswer4());
        tvQuestion.setText(data.get(currentIndex).getQuestion());


    }

    private void loadTestData() {
        data = new ArrayList<>();
        data.add(new TestData("Algoritm nima?", "Masalaning yechimi", "Biror muammoni hal qilish uchun amallar ketme ketligi", "choy damlash usuli", "Android dasturlash asosi", "Biror muammoni hal qilish uchun amallar ketme ketligi"));
        data.add(new TestData("c++ dasturlash tilida consolga ma'lumotlarni chiqarish operatori bu-...", "cin", "cout", "return", "char", "cout"));
        data.add(new TestData("Bitta blok bir necha marta bajariladigan harakatlarni tashkil etish shakli ... deyiladi ", "Quyidagi", "Tarmoqlanish", "tsikl", "algoritm", "tsikl"));
    }

    private void loadViews() {

        tvTime = findViewById(R.id.tv_time);
        tvBall = findViewById(R.id.tv_ball);
        tvNumberQuestion = findViewById(R.id.number_qusetion);
        tvQuestion = findViewById(R.id.Qusetion);
        btnNext = findViewById(R.id.btn_next);
        Answer1 = findViewById(R.id.answer1);
        Answer2 = findViewById(R.id.answer2);
        Answer3 = findViewById(R.id.answer3);
        Answer4 = findViewById(R.id.answer4);
        radioGroup = findViewById(R.id.radio_group);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(TestActivity.this, MainActivity.class));
        finish();
    }
}
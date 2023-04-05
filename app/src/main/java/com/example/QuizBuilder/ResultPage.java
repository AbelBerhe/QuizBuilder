package com.example.QuizBuilder;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultPage extends AppCompatActivity {

    TextView result, comment;
    Button btn_close, btn_restart;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_page);

        result = findViewById(R.id.txt_result);
        comment = findViewById(R.id.txt_comment);
        btn_close = findViewById(R.id.btn_close);
        btn_restart = findViewById(R.id.btn_restart);

        result.setText("Your score is:  ");


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            int value = extras.getInt("KEY");
            result.append(String.valueOf(value));
            if(value >= 8){
                comment.setText("Well Done!");
            }else if(value >= 5){
                comment.setText("Good Try!");
            }else {
                comment.setText("Try Again!");
            }
        }

        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                intent.putExtra("EXIT", true);
//                startActivity(intent);
                finishAffinity();
            }
        });

        btn_restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ResultPage.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }//end onCreate
}// end class
package com.example.QuizBuilder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btn_start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_start = findViewById(R.id.btn_start_id);

        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Creating intent going to main page
                Intent intent = new Intent(MainActivity.this, QuizPage.class);
                startActivity(intent);
                //Toast.makeText(getApplicationContext(),"button start is  clicked",Toast.LENGTH_SHORT).show();
            }
        });


    }//end onCreate()


}//end MainActivity
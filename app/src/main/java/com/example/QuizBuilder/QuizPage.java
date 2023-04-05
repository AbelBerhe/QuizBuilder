package com.example.QuizBuilder;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class QuizPage extends AppCompatActivity {
    TextView txt_def,txt_trackQN;
    Button btn_back,btn_opt1,btn_opt2,btn_opt3,btn_opt4,btn_next;
    ArrayList<Button> options;
    ArrayList<String> defs;
    ArrayList<String> terms;
    Map<String, String> match;
    int countNumberOfQ=1;
    int totalScore =0;
    final String IOTAG = "IO Error";
    final String GTAG = "Error";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        btn_back = findViewById(R.id.btn_back); // back button
        txt_def = findViewById(R.id.tex_def);
        txt_trackQN = findViewById(R.id.txt_trackQN);
        btn_opt1 = findViewById(R.id.btn_opt1); // back option 1
        btn_opt2 = findViewById(R.id.btn_opt2); // back option 2
        btn_opt3 = findViewById(R.id.btn_opt3); // back option 3
        btn_opt4 = findViewById(R.id.btn_opt4); // back option 4
        btn_next = findViewById(R.id.btn_next); // back option next

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Creating an intent going to back to landing page
                Intent intent = new Intent(QuizPage.this, MainActivity.class);
                startActivity(intent);
            }
        }); //end btn_back listener
        //Creating an array of buttons
        Button [] buttons = {btn_opt1,btn_opt2,btn_opt3,btn_opt4};
        options  = new ArrayList<>(Arrays.asList(buttons));
        //Creating new objects of ArrayList of type String
        defs = new ArrayList<>();
        terms = new ArrayList<>();

        String line;
        BufferedReader br;
        try{
            //Opening file and reading one byte at a time using inputstream
            InputStream inputStream = getResources().openRawResource(R.raw.termsanddefs);
            //Reading one line at a time form inputstream using bufferreader
            br = new BufferedReader(new InputStreamReader(inputStream));
            //Checking for eof
            while((line = br.readLine()) != null){
                //Splitting a string in to array of string using split
                String[] arrStr = line.split("#");
                //Adding elements  of the array to ArrayList
                defs.add(arrStr[0]);
                terms.add(arrStr[1]);
            }

            inputStream.close();

        }catch (IOException e){
            Log.e(IOTAG,"Error occurred while opening text file!");
        }catch (Exception e){
            Log.e(GTAG,"Error occurred!");
        }

        //Creating new map object
        match = new HashMap<>();
        for(int i=0; i< terms.size();i++){
            //Adding defs and terms as keys and values respectively
            match.put(defs.get(i),terms.get(i));
        }
        txt_trackQN.setText("Question: ".concat(String.valueOf(countNumberOfQ)).concat("/").concat(String.valueOf(terms.size())));
        Collections.shuffle(defs);
        populateDefinition();
        populateButtons();
        Collections.shuffle(options);

        btn_opt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Objects.equals(match.get(txt_def.getText().toString()), btn_opt1.getText().toString())) {
                    btn_opt1.setBackground(getDrawable(R.drawable.green_round));
                    totalScore++;
                }else {
                    btn_opt1.setBackground(getDrawable(R.drawable.error_round));
                }
                disableButtons();
            }
        });

        btn_opt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Objects.equals(match.get(txt_def.getText().toString()), btn_opt2.getText().toString())){
                    btn_opt2.setBackground(getDrawable(R.drawable.green_round));
                    totalScore++;
                }else {
                    btn_opt2.setBackground(getDrawable(R.drawable.error_round));
                }
                disableButtons();

            }
        });
        btn_opt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Objects.equals(match.get(txt_def.getText().toString()), btn_opt3.getText().toString())){
                    btn_opt3.setBackground(getDrawable(R.drawable.green_round));
                    totalScore++;
                }else {
                    btn_opt3.setBackground(getDrawable(R.drawable.error_round));
                }
                disableButtons();
            }
        });
        btn_opt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Objects.equals(match.get(txt_def.getText().toString()), btn_opt4.getText().toString())){
                    btn_opt4.setBackground(getDrawable(R.drawable.green_round));
                    totalScore++;
                }else {
                    btn_opt4.setBackground(getDrawable(R.drawable.error_round));
                }
                disableButtons();
            }
        });

        btn_next.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if(btn_next.getText().toString().equals("Finish")){
                    Intent intent = new Intent("ResultPage");
                    Bundle extras = new Bundle();
                    extras.putInt("KEY",totalScore);
                    intent.putExtras(extras);
                    startActivityForResult(intent,1);

                }else{
                    populateDefinition();
                    populateButtons();
                    Collections.shuffle(options);
                    countNumberOfQ++;
                    txt_trackQN.setText("Question: ".concat(String.valueOf(countNumberOfQ)).concat("/").concat(String.valueOf(terms.size())));
                    if(countNumberOfQ ==terms.size()){
                        btn_next.setText("Finish");
                    }
//                if(countNumberOfQ > terms.size()){
//
//                }

                    //Setting button background color to the original one
                    btn_opt1.setBackground(getDrawable(R.drawable.round_corner2));
                    btn_opt2.setBackground(getDrawable(R.drawable.round_corner2));
                    btn_opt3.setBackground(getDrawable(R.drawable.round_corner2));
                    btn_opt4.setBackground(getDrawable(R.drawable.round_corner2));
                    enableButtons();
                }
            }
        });


    }


    //Populate buttons with terms
    public void  populateButtons(){
        Collections.shuffle(terms);
        String matchValue = match.get(txt_def.getText().toString());

        int count=0;
        while (count < options.size()){
            if(count==0){
                options.get(count).setText(matchValue);
            }else if(terms.get(count).equals(matchValue)) {
                options.get(count).setText(terms.get(count+3));
            }else{
                options.get(count).setText(terms.get(count));
            }
            count++;
        }

    }

    //Populate textview with defs
    public void populateDefinition(){
       // Collections.shuffle(defs)
        if (defs.size() != 0) {
            txt_def.setText(defs.get(0));
            defs.remove(0);
        }
    }


    //Disable buttons if one buttons is clicked
    public void disableButtons(){
        int count =0;
        while (count < options.size()){
            options.get(count).setEnabled(false);
            count++;
        }
    }

   //Enable buttons if next button is clicked
    public void enableButtons(){
        int count =0;
        while (count < options.size()){
            options.get(count).setEnabled(true);
            count++;
        }
    }

}
package com.myapplicationdev.android.p04_revisionnotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText EtNote;
    Button insert,showList;
    RadioGroup yes;
    RadioButton chosenStar, star1, star2, star3, star4, star5;
    ArrayList<Note> note = new ArrayList<Note>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getApplicationContext().deleteDatabase("stars.db");
        insert = findViewById(R.id.buttonInsertNote);
        showList = findViewById(R.id.buttonShowList);
        EtNote = findViewById(R.id.editTextNote);
        yes = findViewById(R.id.radioGroupStars);
        star1 = findViewById(R.id.radio1);
        star2 = findViewById(R.id.radio2);
        star3 = findViewById(R.id.radio3);
        star4 = findViewById(R.id.radio4);
        star5 = findViewById(R.id.radio5);
        insert.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                int starInt = 0;
                int stars = yes.getCheckedRadioButtonId();
                chosenStar = (RadioButton)findViewById(stars);
                int ChosenNumber = Integer.parseInt(chosenStar.getText().toString());

                DBHelper db = new DBHelper(MainActivity.this);
                db.insertNote(EtNote.getText().toString(), ChosenNumber);
                Log.d("starnumber",""+ChosenNumber);
                
                db.close();
            }
        });

        showList.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),SecondActivity.class);
                startActivity(intent);
            }
        });


    }
}

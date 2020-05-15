package com.myapplicationdev.android.p04_revisionnotes;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    ListView lv;
    ArrayAdapter aa;
    ArrayList<Note> note = new ArrayList<Note>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        lv = findViewById(R.id.lv);

        DBHelper db = new DBHelper(SecondActivity.this);

        // Insert a task
        ArrayList<Note> data = db.getAllNotes();

        aa = new RevisionNotesArrayAdapter(this, R.layout.row, data);
        lv.setAdapter(aa);

	}


}

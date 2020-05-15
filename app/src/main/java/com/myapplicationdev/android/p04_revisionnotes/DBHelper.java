package com.myapplicationdev.android.p04_revisionnotes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

	private static final int DATABASE_VER = 1;
	private static final String DATABASE_NAME = "stars.db";

	private static final String TABLE_NOTE = "task";
	private static final String COLUMN_STARS = "_star";
	private static final String COLUMN_NOTE = "note";
	private static final String COLUMN_ID = "ID";


	public DBHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VER);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String createTableSql = "CREATE TABLE " + TABLE_NOTE + "("
				+ COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ COLUMN_STARS + " INTEGER,"
				+ COLUMN_NOTE + " TEXT )";
		db.execSQL(createTableSql);
		Log.i("info", "created tables");

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTE);
		onCreate(db);
	}

	public void insertNote(String note, int stars) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(COLUMN_NOTE, note);
		values.put(COLUMN_STARS, stars);
		db.insert(TABLE_NOTE, null, values);
		db.close();
	}

	public ArrayList<Note> getAllNotes() {
		ArrayList<Note> notes = new ArrayList<Note>();
		String selectQuery = "SELECT " + COLUMN_ID + ", "
				+ COLUMN_NOTE + ", "
				+ COLUMN_STARS
				+ " FROM " + TABLE_NOTE;

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		if (cursor.moveToFirst()) {
			do {
				int id = cursor.getInt(0);
				int stars = cursor.getInt(1);
				String note = cursor.getString(2);
				Note note1 = new Note(id, stars, note);
				notes.add(note1);
			} while (cursor.moveToNext());
		}
		cursor.close();
		db.close();
		return notes;
	}

	public ArrayList<String> getNoteContent() {
		ArrayList<String> note = new ArrayList<String>();
		String selectQuery = "SELECT " + COLUMN_NOTE
				+ " FROM " + TABLE_NOTE;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		if (cursor.moveToFirst()) {
			do {
				note.add(cursor.getString(0));
			} while (cursor.moveToNext());
		}
		// Close connection
		cursor.close();
		db.close();
		return note;
	}
}

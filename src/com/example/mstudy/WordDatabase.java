package com.example.mstudy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class WordDatabase {
	private UsersDatabaseHelper myDbHelper;
	private SQLiteDatabase myDb;
	private Cursor myCursor;
	private static final String DATABASE_NAME = "DB_DICTIONARY";
	private static final String TABLE_NAME = "WORDS";
	private static final String TABLE_ID = "id";
	private static final String WORD_NAME = "name";
	private static final String WORD_MEANING = "meaning";
	private static final String CREATE_DATABASE = "CREATE TABLE " + TABLE_NAME + "(" +
												  TABLE_ID + " integer primary key autoincrement, " +
												  WORD_NAME + " text not null, " +
												  WORD_MEANING + " text not null)";
	private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
	private Context myContext;
	
	private class UsersDatabaseHelper extends SQLiteOpenHelper
	{
		public UsersDatabaseHelper(Context context) {
			super(context, DATABASE_NAME, null, 1);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			Log.i("Test", "Creating db...");
			db.execSQL(CREATE_DATABASE);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			Log.i("test", "Upgrading Db...");
			db.execSQL(DROP_TABLE);		//Kiem tra table, neu khong giong thi drop
			onCreate(db);				//Tao moi CSDL
		}
		
		@Override
		public void onOpen(SQLiteDatabase db) {
			super.onOpen(db);
		}
	}
	
	//Mo CSDL
	public void openToWrite()
	{
		myDbHelper = new UsersDatabaseHelper(myContext);
		myDb = myDbHelper.getWritableDatabase();		//Mo database
	}
	
	//Them ban ghi vao CSDL
	public void insertWord(String name, String meaning)
	{
		openToWrite();		//Mo database
		ContentValues myValue = new ContentValues();	//Dung de luu gia tri
		myValue.put(WORD_NAME, name);
		myValue.put(WORD_MEANING, meaning);
		myDb.insert(TABLE_NAME, null, myValue);
		myDb.close();	//Dong ket noi
	}
	
	//Sua ban ghi trong CSDL dua vao id
	public void updateWord(Word word)
	{
		openToWrite();
		ContentValues myValue = new ContentValues();
		myValue.put(WORD_NAME, word.getName());
		myValue.put(WORD_MEANING, word.getMeaning());
		myDb.update(TABLE_NAME, myValue, TABLE_ID + "=?", new String[] {String.valueOf(word.getId())});
		myDb.close();
	}
	
	//Xoa ban ghi trong CSDL dua vao id
	public void deleteWord(Word word)
	{
		openToWrite();
		ContentValues myValue = new ContentValues();
		myDb.delete(TABLE_NAME, TABLE_ID + "=?", new String[] {String.valueOf(word.getId())});
		myDb.close();
	}
	
	//Lay thong tin 1 Word dua vao id
	public void getInfoWord(int id)
	{
		myDbHelper = new UsersDatabaseHelper(myContext);
		myDb = myDbHelper.getReadableDatabase();
		String query = "select * from " + TABLE_NAME;
		myCursor = myDb.rawQuery(query, new String[] {String.valueOf(id)});
		//Kiem tra du lieu rong hay khong
		if (myCursor != null)
		{
			myCursor.moveToFirst();
		}
		
	}
}

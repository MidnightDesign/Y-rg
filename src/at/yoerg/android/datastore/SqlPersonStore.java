package at.yoerg.android.datastore;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import at.yoerg.businesslogic.player.Person;
import at.yoerg.businesslogic.player.Player;

public class SqlPersonStore extends SQLiteOpenHelper implements PersonStore {
	private static final int DATABASE_VERSION = 2;
	private static final String PEOPLE_TABLE_NAME = "people";
	private static final String PEOPLE_TABLE_CREATE = "CREATE TABLE "
			+ PEOPLE_TABLE_NAME
			+ " (name VARCHAR(32), id UNSIGNED INT AUTO_INCREMENT, PRIMARY KEY (id))";

	public SqlPersonStore(Context context) {
		super(context, "YOERG", null, DATABASE_VERSION);
	}

	@Override
	public ArrayList<Person> getPeople() {
		ArrayList<Person> people = new ArrayList<Person>();
		SQLiteDatabase db = getReadableDatabase();
		Cursor cursor = db.rawQuery("SELECT * FROM " + PEOPLE_TABLE_NAME + ";", null);
		while(cursor.moveToNext()) {
			String playerName = cursor.getString(cursor.getColumnIndex("name"));
			people.add(Person.create(playerName));
		}
		return people;
	}

	@Override
	public void savePerson(Person p) {
		SQLiteDatabase db = getWritableDatabase();
		db.execSQL("INSERT INTO " + PEOPLE_TABLE_NAME + " (name) VALUES ('" + p.getName() + "')");
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(PEOPLE_TABLE_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}

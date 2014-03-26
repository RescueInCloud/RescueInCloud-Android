package es.ucm.ric.dao;


import es.ucm.ric.MyApp;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public final class DatabaseHelper extends SQLiteOpenHelper {

	@SuppressWarnings("unused")
	private static final String TAG = DatabaseHelper.class.getSimpleName();
	public static final String DATABASE_NAME = "rescue_lite_db";
	private static final int DATABASE_VERSION = 1;
	
	public DatabaseHelper() {
		super(MyApp.getContext(), DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
	
		Context context = MyApp.getContext();
		database = context.openOrCreateDatabase(DATABASE_NAME, Context.MODE_PRIVATE,null);
//		this.
//		database.
//		SQLiteDatabase.openDatabase("", null,
//                SQLiteDatabase.CREATE_IF_NECESSARY);
		
//		final String friends = "CREATE TABLE " + FriendDAO.TABLE + "(" + 
//				FriendDAO.NAME + " varchar(20) primary key, " +
//				FriendDAO.STATE + " int not null default 0)";
//		database.execSQL(friends);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// first iteration. do nothing.
	}
	

}

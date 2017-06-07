package se.benjaminchrist.bibliotekv52;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Benjamin on 2017-02-14.
 */

public class DatabankHelper extends SQLiteOpenHelper {

    public static final String TABLE_NAME = "bibliotek";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_AUTHOR = "author";
    private SQLiteDatabase database;

    public DatabankHelper(Context context) {
        super(context, "bibliotek.db", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        String DATABASE_CREATE2 = "create table bibliotek (id integer primary key autoincrement, title text not null, author text);";
        database.execSQL(DATABASE_CREATE2);

    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(database);
    }

    public void open() throws SQLException{
        database = getWritableDatabase();
    }

    public Bock createBock(String title){
        ContentValues values = new ContentValues();
        values.put(DatabankHelper.COLUMN_TITLE, title);
        long insertId = database.insert(DatabankHelper.TABLE_NAME, null, values);
        Bock newBock = new Bock();
        newBock.setId(insertId);
        newBock.setTitle(title);
        return newBock;
    }

    public void deleteBock(Bock bock){
        open();
        long id = bock.getId();
        database.delete(DatabankHelper.TABLE_NAME, DatabankHelper.COLUMN_ID + " = " + id, null);
        close();
    }

    public List<Bock> getAllBock(){
        List<Bock> title = new ArrayList<Bock>();
        String[] allColumns = {COLUMN_ID, COLUMN_TITLE, COLUMN_AUTHOR};
        Cursor cursor = database.query(DatabankHelper.TABLE_NAME, allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            Bock bock = cursorToBock(cursor);
            title.add(bock);
            cursor.moveToNext();
        }
        cursor.close();
        return title;
    }

    private Bock cursorToBock(Cursor cursor) {
        Bock bock = new Bock();
        bock.setId(cursor.getLong(0));
        String title = cursor.getString(1);
        bock.setTitle(title);
        bock.setAuthor(cursor.getString(2));
        return  bock;
    }

    public Bock getBock(int position){
        open();
        String[] allColumns = {COLUMN_ID, COLUMN_TITLE, COLUMN_AUTHOR};
        Cursor cursor = database.query(DatabankHelper.TABLE_NAME,allColumns, null, null, null, null, null);

        cursor.moveToPosition(position);
        Bock bock = cursorToBock(cursor);
        cursor.close();
        close();
        return bock;
    }

    public void upateData (Bock bock){
        open();
        ContentValues values = new ContentValues();
        values.put(DatabankHelper.COLUMN_ID, bock.getId());
        values.put(DatabankHelper.COLUMN_TITLE, bock.getTitle());
        values.put(DatabankHelper.COLUMN_AUTHOR, bock.getAuthor());
        database.update(TABLE_NAME, values, "id = " + bock.getId(), null);
        close();

    }



}

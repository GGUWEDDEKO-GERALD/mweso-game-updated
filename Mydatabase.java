package com.example.don.mymweso;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.NumberFormat;


class Attributes{
    public long index;
    public String name;
    public int wins;
    public int loses;

}


/* database for storing scores of wins and loses for each player */
public class Mydatabase {

    /* our rows */
    public static final String ID = "_id";
    public static final String NAME = "name";
    public static final String WINS = "wins";
    public static final String loses = "loses";


    //    @SuppressWarnings("unused")
//	private static final String TAG = "DBAdapter";
//
    /* db info */
    private static final String DATABASE_NAME = "stats4";
    private static final String DATABASE_TABLE = "stats";
    private static final int DATABASE_VERSION = 1;

    /* command to make the db  */
    private static final String DATABASE_CREATE =
            "create table stats (_id integer primary key autoincrement, "
                    + "name text not null, wins text not null, "
                    + "loses text not null);";

    private final Context context;
    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;

    public Mydatabase(Context ctx) {
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
    }

    private static class DatabaseHelper extends SQLiteOpenHelper {
        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DATABASE_CREATE);
        }

        public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
        }
    }

    /* open db */
    public Mydatabase open() throws SQLException {
        db = DBHelper.getWritableDatabase();
        return this;
    }

    /* close db */
    public void close() {
        DBHelper.close();
    }

    /* input data  into db */
    public long insertStat(String name, String wins, String loses) {
        ContentValues current = new ContentValues();
        current.put(NAME, name);
        current.put(WINS, wins);
        current.put(loses, loses);

        return db.insert(DATABASE_TABLE, null, current);
    }

    /* delete stat from db */
    public boolean deleteStat(long rowId) {
        return db.delete(DATABASE_TABLE, ID + "=" + rowId, null) > 0;
    }

    /* get all stats */
    public Cursor getAllStats() {
        return db.query(DATABASE_TABLE, new String[]{
                        ID,
                        NAME,
                        WINS,
                        loses,
                },
                null,
                null,
                null,
                null,
                null);
    }

    /* get a particular stat */
    public Cursor getStat(long rowId) throws SQLException {
        Cursor cursor =
                db.query(true, DATABASE_TABLE, new String[]{
                                ID,
                                NAME,
                                WINS,
                                loses,

                        },
                        ID + "=" + rowId,
                        null,
                        null,
                        null,
                        null,
                        null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    /* updates a stat */
    public boolean UpdateScore(long rowId, String name, String wins, String loses) {
        ContentValues args = new ContentValues();
        args.put(NAME, name);
        args.put(WINS, wins);
        args.put(loses, loses);

        return db.update(DATABASE_TABLE, args, ID + "=" + rowId, null) > 0;
    }

    public Attributes Getdata(String name) {
        Attributes d = new Attributes();
        Cursor c = getAllStats();
        if (c.moveToFirst()) {
            do {
                if (c.getString(1).equals(name)) {
                    d.index = Long.parseLong(c.getString(0));
                    d.name = c.getString(1);
                    d.wins = Integer.parseInt(c.getString(2));
                    d.loses = Integer.parseInt(c.getString(3));


                    return d;
                }
            } while (c.moveToNext());
        }

        return null;
    }
    public void Enterdata(String name, int plus_wins, int plus_loses) {
        NumberFormat nf = NumberFormat.getInstance( );
        nf.setGroupingUsed(false);

        Attributes entry = Getdata(name);
        if(entry == null) {
    		/* make new record */
            insertStat(name,
                    nf.format(plus_wins),
                    nf.format(plus_loses)
                    );

        } else {
    		/* update existing record */
            UpdateScore(entry.index, entry.name,
                    nf.format(entry.wins + plus_wins),
                    nf.format(entry.loses + plus_loses)
                   );

        }

    }
    public void Enterwins(String name){
        Enterdata(name,1,0);

    }
}
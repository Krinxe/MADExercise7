package sg.edu.np.s10177991h.madexercise7;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Dbhandler extends SQLiteOpenHelper {// acts similar to adapter in listview
    //private static final String TAG = "MyDBhandler";
    public static final int DATABASE_VERSION = 1;//can be used when you want to upgrade your version number (new database)
    private static final String DATABASE_NAME = "accountDB.db";
    public static final String ACCOUNTS = "Accounts";// just creating table name
    public static final String COLUMN_USERNAME = "UserName";// column names
    public static final String COLUMN_PASSWORD = "Password";

    public Dbhandler(Context c,
                     String name,
                     SQLiteDatabase.CursorFactory factory,
                     int version) {
        super(c, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {//initializing new database

        //Create Table Accounts (UserName TEXT, Password)
        String CREATE_ACCOUNTS_TABLE = "CREATE TABLE " + ACCOUNTS +
                " (" + COLUMN_USERNAME + " TEXT," +
                COLUMN_PASSWORD + " TEXT)";
        db.execSQL(CREATE_ACCOUNTS_TABLE);// execute sql command
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)//handle differences between db schemas, need to handle all permutations
    {
        db.execSQL("DROP TABLE IF EXISTS " + ACCOUNTS);//only within the context of the module
        onCreate(db);// if there is an existing table, deletes the table and creates a new one
    }

    public void addAccount(Account a) {
        ContentValues values = new ContentValues();//similar concept to intent
        values.put(COLUMN_PASSWORD, a.getPassword());
        values.put(COLUMN_USERNAME, a.getUsername());

        SQLiteDatabase db = this.getWritableDatabase();//open session and connect to database, only resides in this andriod
        db.insert(ACCOUNTS, null, values);//attack proof, nullColumnHack = put col name if you want hack
        //query - read; delete; updates
        db.close();//when you open a database connection, you have to close it if not there might be a database leak

    }

    public Account findAccount(String username) {
        String query = "SELECT * FROM " + ACCOUNTS + " WHERE"
                + COLUMN_USERNAME + " =\"" + username + "\"";// sql command to select where the username keyed in = username in data base

        Account a = new Account();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);//can put "= ?" to be replaced with String[] in db.delete()
        //when you first call, it will point to -1 (smth out of data).
        if (cursor.moveToFirst())//moving the cursor to the first row in the data, returns true if there is a row
        {
            a.setUsername(cursor.getString(0));//from column 0
            a.setPassword(cursor.getString(1));//from column 1
            cursor.close();
        }
        /*while (cursor.moveToNext() == true)
        {
            a.setUsername(cursor.getString(0));//from column 0
            a.setPassword(cursor.getString(1));//from column 1
            //cursor.close();
        }*/
        else
            a = null;

        db.close();
        return a;
    }
}
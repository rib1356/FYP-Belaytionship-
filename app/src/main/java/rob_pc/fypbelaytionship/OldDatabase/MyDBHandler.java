package rob_pc.fypbelaytionship.OldDatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import rob_pc.fypbelaytionship.UserInformation;


public class MyDBHandler extends SQLiteOpenHelper {

    //information of database
    private static final String DATABASE_NAME = "userinformation.db";
    public static final String TABLE_NAME = "Profile";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_NAME = "Name";
    public static final String COLUMN_AGE = "Age";
    public static final String COLUMN_GENDER = "Gender";
    public static final String COLUMN_OSBOULDERING = "OsBouldering";
    public static final String COLUMN_OSTRAD = "OsTrad";
    public static final String COLUMN_OSSPORT = "OsSport";
    public static final String COLUMN_RPBOULDERING = "RpBouldering";  //RP (red point)
    public static final String COLUMN_RPTRAD = "RpTrad";
    public static final String COLUMN_RPSPORT = "RpSport";
    public static final String COLUMN_YEARSCLIMBING = "YearsClimbing";
    public static final String COLUMN_CLIMBINGFREQ = "ClimbingFreq";
    public static final String COLUMN_FAVCRAG = "FavCrag";
    public static final String COLUMN_FAVROCK = "FavRock";
    public static final String COLUMN_FAVCLIMBING = "FavClimbing";

    //default constructor
    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, null, 1);
    }

    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Generate Create SQL Statement
        String CREATE_PROFILE_TABLE = "CREATE TABLE "
                + TABLE_NAME
                + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_NAME + " TEXT,"
                + COLUMN_AGE + " TEXT,"
                + COLUMN_GENDER + " TEXT,"
                + COLUMN_OSBOULDERING + " TEXT,"
                + COLUMN_OSTRAD + " TEXT,"
                + COLUMN_OSSPORT + " TEXT,"
                + COLUMN_RPBOULDERING + " TEXT,"
                + COLUMN_RPTRAD + " TEXT,"
                + COLUMN_RPSPORT + " TEXT,"
                + COLUMN_YEARSCLIMBING + " TEXT,"
                + COLUMN_CLIMBINGFREQ + " TEXT,"
                + COLUMN_FAVCRAG + " TEXT,"
                + COLUMN_FAVROCK + " TEXT,"
                + COLUMN_FAVCLIMBING + " TEXT"
                + ")";
        // Execute/run create SQL statement
        db.execSQL(CREATE_PROFILE_TABLE);
        Log.d("Database", "Database Created.");
    }

    public void onUpgrade(SQLiteDatabase db, int oldNum, int newNum) {
        //Drop older table if exists and create a new
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public long addProfile(UserInformation list) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, list.getId());
        values.put(COLUMN_NAME, list.getName());
        values.put(COLUMN_AGE, list.getAge());
        values.put(COLUMN_GENDER, list.getGender());
        values.put(COLUMN_OSBOULDERING, list.getOnSightBouldering());
        values.put(COLUMN_OSTRAD, list.getOnSightTrad());
        values.put(COLUMN_OSSPORT, list.getOnSightSport());
        values.put(COLUMN_RPBOULDERING, list.getWorkedBouldering());
        values.put(COLUMN_RPTRAD, list.getWorkedTrad());
        values.put(COLUMN_RPSPORT, list.getOnSightSport());
        values.put(COLUMN_YEARSCLIMBING, list.getYearsClimbing());
        values.put(COLUMN_CLIMBINGFREQ, list.getClimbingFreq());
        values.put(COLUMN_FAVCRAG, list.getFavCrag());
        values.put(COLUMN_FAVROCK, list.getFavRock());
        values.put(COLUMN_FAVCLIMBING, list.getFavClimbing());


        //Add record to db and get id of new record( must be long )
        long id = db.insert(TABLE_NAME, null, values);

        db.close();

        return id;
    }

 //   public List<UserInformation> getAll() {

//        //Create empty list
//        List<UserInformation> list = new ArrayList<UserInformation>();
//
//        //Connect to db and read
//        SQLiteDatabase db = this.getReadableDatabase();
//
//        //Execute select statement
//        String selectQuery = "SELECT * FROM " + TABLE_NAME;
//
//        Cursor cursor = db.rawQuery(selectQuery, null);
//        if (cursor.moveToFirst()) {
//            //Get position of each of the column names
//            int idIdx = cursor.getColumnIndex(COLUMN_ID);
//            int nameIdx = cursor.getColumnIndex(COLUMN_NAME);
//            int ageIdx = cursor.getColumnIndex(COLUMN_AGE);
//            int genderIdx = cursor.getColumnIndex(COLUMN_GENDER);
//            int osBoulderingIdx = cursor.getColumnIndex(COLUMN_OSBOULDERING);
//            int osTradIdx = cursor.getColumnIndex(COLUMN_OSTRAD);
//            int osSportIdx = cursor.getColumnIndex(COLUMN_OSSPORT);
//            int rpBoulderingIdx = cursor.getColumnIndex(COLUMN_RPBOULDERING);
//            int rpTradIdx = cursor.getColumnIndex(COLUMN_RPTRAD);
//            int rpSportIdx = cursor.getColumnIndex(COLUMN_RPSPORT);
//            int yearsClimbingIdx = cursor.getColumnIndex(COLUMN_YEARSCLIMBING);
//            int climbingFreqIdx = cursor.getColumnIndex(COLUMN_CLIMBINGFREQ);
//            int favCragIdx = cursor.getColumnIndex(COLUMN_FAVCRAG);
//            int favRockIdx = cursor.getColumnIndex(COLUMN_FAVROCK);
//            int favClimbingIdx = cursor.getColumnIndex(COLUMN_FAVCLIMBING);
//            do {
//                // Create list object for current database record
//                UserInformation profileList = new UserInformation(
//                        cursor.getInt(idIdx),
//                        cursor.getString(nameIdx),
//                        cursor.getString(ageIdx),
//                        cursor.getString(genderIdx),
//                        cursor.getString(osBoulderingIdx),
//                        cursor.getString(osTradIdx),
//                        cursor.getString(osSportIdx),
//                        cursor.getString(rpBoulderingIdx),
//                        cursor.getString(rpTradIdx),
//                        cursor.getString(rpSportIdx),
//                        cursor.getString(yearsClimbingIdx),
//                        cursor.getString(climbingFreqIdx),
//                        cursor.getString(favCragIdx),
//                        cursor.getString(favRockIdx),
//                        cursor.getString(favClimbingIdx)
//
//                );
//
//                list.add(profileList);
//
//            } while (cursor.moveToNext());
//        }
//        return list;
 //   }

    public void updateProfile(int id, String name, String age, String gender, String onSightBouldering, String onSightTrad, String onSightSport, String workedBouldering, String workedTrad, String workedSport,
                              String yearsClimbing, String climbingFreq, String favCrag, String favRock, String favClimbing) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, id);
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_AGE, age);
        values.put(COLUMN_GENDER, gender);
        values.put(COLUMN_OSBOULDERING, onSightBouldering);
        values.put(COLUMN_OSTRAD, onSightTrad);
        values.put(COLUMN_OSSPORT, onSightSport);
        values.put(COLUMN_RPBOULDERING, workedBouldering);
        values.put(COLUMN_RPTRAD, workedTrad);
        values.put(COLUMN_RPSPORT, workedSport);
        values.put(COLUMN_YEARSCLIMBING, yearsClimbing);
        values.put(COLUMN_CLIMBINGFREQ, climbingFreq);
        values.put(COLUMN_FAVCRAG, favCrag);
        values.put(COLUMN_FAVROCK, favRock);
        values.put(COLUMN_FAVCLIMBING, favClimbing);
        db.update(TABLE_NAME, values, COLUMN_ID + "=" + id, null);
    }

    public boolean checkForTables(){

        SQLiteDatabase db;


        db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM " + TABLE_NAME, null);

        if(cursor != null){

            cursor.moveToFirst();

            int count = cursor.getInt(0);

            if(count > 0){
                return true;
            }

            cursor.close();
        }

        return false;
    }


    public void removeAll() {

        //Connect to tables
        SQLiteDatabase db = this.getWritableDatabase();

        //Exe delete table SQL command
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        //call create method to regen table
        onCreate(db);
    }

    public void deleteItem(int id) {

        SQLiteDatabase db = this.getWritableDatabase();
        int softDelete = 0;

        ContentValues values = new ContentValues();
        //values.put(COL_ACTIVE, softDelete);
        db.update(TABLE_NAME, values, COLUMN_ID + "=" + id, null);
    }
}

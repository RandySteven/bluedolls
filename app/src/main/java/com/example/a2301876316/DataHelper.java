package com.example.a2301876316;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.a2301876316.factory.UserFactory;
import com.example.a2301876316.models.Doll;
import com.example.a2301876316.models.User;

import java.util.ArrayList;
import java.util.List;

public class DataHelper extends SQLiteOpenHelper {
    public Context context;
    private static final String DATABASE_NAME = "bluedoll.db";
    private static final String TABLE_USER = "users";
    private static final String TABLE_DOLL = "dolls";
    private static final int DATABASE_VERSION = 1;

    private static final String CREATE_TABLE_USER = "CREATE TABLE " + TABLE_USER + "(" +
            "UserID CHAR(5) PRIMARY KEY," +
            "UserName VARCHAR(20) NOT NULL," +
            "UserEmail VARCHAR(30) NOT NULL," +
            "UserPassword VARCHAR(30) NOT NULL," +
            "UserGender VARCHAR(6) NOT NULL," +
            "UserRole VARCHAR(10) NOT NULL" +
            ")";

    private static final String CREATE_TABLE_DOLL = "CREATE TABLE " + TABLE_DOLL + "(" +
            "DollID CHAR(5) PRIMARY KEY," +
            "UserID CHAR(5) NOT NULL," +
            "DollName VARCHAR(30) NOT NULL," +
            "DollDescription TEXT NOT NULL," +
            "DollImage BLOB," +
            "FOREIGN KEY (UserID) REFERENCES " + TABLE_USER + "(UserID))";

//    public static SQLiteDatabase db;
//    public Cursor cursor;

    public DataHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_USER);
        sqLiteDatabase.execSQL(CREATE_TABLE_DOLL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_USER + "");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_DOLL + "");
        onCreate(sqLiteDatabase);
    }

    /**
     * User query management
     */
    public void addUser(User user){
        SQLiteDatabase db = getWritableDatabase();
        try {
            ContentValues values = new ContentValues();
            values.put("UserID", user.getUserId());
            values.put("UserName", user.getUserName());
            values.put("UserEmail", user.getUserEmail());
            values.put("UserPassword", user.getUserPassword());
            values.put("UserGender", user.getUserGender());
            values.put("UserRole", user.getUserRole());

            db.insertOrThrow(TABLE_USER, null, values);
            db.setTransactionSuccessful();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(db != null && db.inTransaction()){
                db.endTransaction();
            }
        }
    }

    @SuppressLint("Range")
    public List<User> getAllUsers(){
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_USER;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor =  db.rawQuery(query, null);
        try{
            if(cursor.moveToFirst()){
                do{
                    User newUser;
                    String userId, userGender, userName, userEmail, userPassword, userRole;
                    userId = cursor.getString(cursor.getColumnIndex("UserID"));
                    userName = cursor.getString(cursor.getColumnIndex("UserName"));
                    userEmail = cursor.getString(cursor.getColumnIndexOrThrow("UserEmail"));
                    userPassword = cursor.getString(cursor.getColumnIndexOrThrow("UserPassword"));
                    userRole = cursor.getString(cursor.getColumnIndexOrThrow("UserRole"));
                    userGender = cursor.getString(cursor.getColumnIndex("UserGender"));
                    newUser = new User(userId, userName, userEmail, userPassword, userGender, userRole);
                    users.add(newUser);
                }while(cursor.moveToNext());
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(cursor != null && !cursor.isClosed()){
                cursor.close();
            }
        }
        return users;
    }

    public User login(String email, String password){
        User user = null;
        String query = "SELECT * FROM " + TABLE_USER + " WHERE UserEmail = '" + email + "' AND UserPassword = '" + password + "'";
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        try{
            if(cursor.moveToFirst()){
                    String userId, userGender, userName, userEmail, userPassword, userRole;
                    userId = cursor.getString(cursor.getColumnIndex("UserID"));
                    userName = cursor.getString(cursor.getColumnIndex("UserName"));
                    userEmail = cursor.getString(cursor.getColumnIndexOrThrow("UserEmail"));
                    userPassword = cursor.getString(cursor.getColumnIndexOrThrow("UserPassword"));
                    userRole = cursor.getString(cursor.getColumnIndexOrThrow("UserRole"));
                    userGender = cursor.getString(cursor.getColumnIndex("UserGender"));
                    user = new User(userId, userName, userEmail, userPassword, userGender, userRole);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(cursor != null && !cursor.isClosed()){
                cursor.close();
            }
        }
        return user;
    }

    /**
     * Doll Query Management
     */
    public void addDoll(Doll doll){
        SQLiteDatabase db = getWritableDatabase();
        try {
            ContentValues values = new ContentValues();
            values.put("DollID", doll.getDollId());
            values.put("DollName", doll.getDollName());
            values.put("DollDescription", doll.getDollDescription());
            values.put("DollImage", doll.getDollImage());
            values.put("UserID", doll.getUser().getUserId());

            db.insertOrThrow(TABLE_DOLL, null, values);
            db.setTransactionSuccessful();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(db != null && db.inTransaction()){
                db.endTransaction();
            }
        }
    }

    @SuppressLint("Range")
    public ArrayList<Doll> getAllDolls(){
        List<Doll> dolls = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_DOLL;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        try{
            if(cursor.moveToFirst()){
                while(!cursor.isAfterLast()){
                    String dollId, dollName, dollDescription, userId;
                    byte[] dollImage; User user;

                    dollId = cursor.getString(cursor.getColumnIndex("DollID"));
                    dollImage = cursor.getBlob(cursor.getColumnIndex("DollImage"));
                    dollName = cursor.getString(cursor.getColumnIndex("DollName"));
                    dollDescription = cursor.getString(cursor.getColumnIndex("DollDescription"));
                    userId = cursor.getString(cursor.getColumnIndex("UserID"));

                    user = getUser(userId);

                    Doll newDoll = new Doll(dollId, dollName, dollDescription, dollImage, user);
                    dolls.add(newDoll);
                    System.out.println(dolls.size());
                    cursor.moveToNext();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(cursor != null && !cursor.isClosed()){
                cursor.close();
            }
        }
        return (ArrayList<Doll>) dolls;
    }

    public void deleteDoll(String dollId){
        String query = "DELETE FROM " + TABLE_DOLL + " WHERE DollID = '" + dollId + "'";
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(query);
        db.close();
    }

    public void updateDoll(String dollId, String dollName, String dollDesc, byte[] dollImage){
//        String query = "UPDATE " + TABLE_DOLL + " SET DollName = '" +dollName+ "', " +
//                "DollDescription = '" + dollDesc + "', " +
//                "DollImage = '" + dollImage + "' " +
//                "WHERE DollID = '" + dollId +"'";
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("DollName", dollName);
        values.put("DollDescription", dollDesc);
        values.put("DollImage", dollImage);

        db.update(TABLE_DOLL, values,"DollID=?", new String[]{dollId});
        db.close();
    }

    /**
     * Get User Object
     */
    public User getUser(String userId){
        String query = "SELECT * FROM " + TABLE_USER + " WHERE UserID = '" + userId + "'";
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        User user = null;
        try{
            if(cursor.moveToFirst()){
                String userGender, userName, userEmail, userPassword, userRole;
                userId = cursor.getString(cursor.getColumnIndex("UserID"));
                userName = cursor.getString(cursor.getColumnIndex("UserName"));
                userEmail = cursor.getString(cursor.getColumnIndexOrThrow("UserEmail"));
                userPassword = cursor.getString(cursor.getColumnIndexOrThrow("UserPassword"));
                userRole = cursor.getString(cursor.getColumnIndexOrThrow("UserRole"));
                userGender = cursor.getString(cursor.getColumnIndex("UserGender"));
                user = new User(userId, userName, userEmail, userPassword, userGender, userRole);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            if(cursor != null && !cursor.isClosed()){
                cursor.isClosed();
            }
            if(db != null && db.inTransaction()){
                db.endTransaction();
            }
        }
        return user;
    }

    public Doll getDoll(String dollId){
        String query = "SELECT * FROM " + TABLE_DOLL + " WHERE DollID = '" + dollId + "'";
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Doll doll = null;
        try{
            if(cursor.moveToFirst()){
                String dollName, dollDescription, userId;
                byte[] dollImage; User user;

                dollId = cursor.getString(cursor.getColumnIndex("DollID"));
                dollImage = cursor.getBlob(cursor.getColumnIndex("DollImage"));
                dollName = cursor.getString(cursor.getColumnIndex("DollName"));
                dollDescription = cursor.getString(cursor.getColumnIndex("DollDescription"));
                userId = cursor.getString(cursor.getColumnIndex("UserID"));

                user = getUser(userId);

                doll = new Doll(dollId, dollName, dollDescription, dollImage, user);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            if(cursor != null && !cursor.isClosed()){
                cursor.isClosed();
            }
            if(db != null && db.inTransaction()){
                db.endTransaction();
            }
        }
        return doll;
    }
}

package com.example.bookdatabase.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.bookdatabase.model.*;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.id;

/**
 * Created by SUNGWOO on 2017-02-07.
 */

public class MySQLiteHelper extends SQLiteOpenHelper {

    // Books table name
    private static final String TABLE_BOOKS = "books";

    // Books Table Columns names
    private static final String KEY_ID = "id";
    private static final String COL_TITLE = "title";
    private static final String COL_AUTHOR = "author";
    private static final String COL_PRESS = "press";

    private static final String[] COLUMNS = {KEY_ID,COL_TITLE,COL_AUTHOR,COL_PRESS};

    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "BookDB";

    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL statement to create book table
        String CREATE_BOOK_TABLE = "CREATE TABLE books ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "title TEXT, "+
                "author TEXT, "+
                "press TEXT )";

        // create books table
        db.execSQL(CREATE_BOOK_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older books table if existed
        db.execSQL("DROP TABLE IF EXISTS books");

        // create fresh books table
        this.onCreate(db);
    }

    public void addBook(Book book){
        //for logging
        Log.d("addBook", book.toString());

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(COL_TITLE, book.getTitle()); // get title
        values.put(COL_AUTHOR, book.getAuthor()); // get author
        values.put(COL_PRESS, book.getPress()); // get press

        // 3. insert
        db.insert(TABLE_BOOKS, // table
                null, //nullColumnHack
                values); // key/value -> keys = column names/ values = column values

        // 4. close
        db.close();
    }

    public Book getBook(int id){

        // 1. get reference to readable DB
        SQLiteDatabase db = this.getReadableDatabase();

        // 2. build query
        Cursor cursor =
                db.query(TABLE_BOOKS, // a. table
                        COLUMNS, // b. column names
                        " id = ?", // c. selections
                        new String[] { String.valueOf(id) }, // d. selections args
                        null, // e. group by
                        null, // f. having
                        null, // g. order by
                        null); // h. limit

        // 3. if we got results get the first one
        if (cursor != null)
            cursor.moveToFirst();

        // 4. build book object
        Book book = new Book();
        book.setId(Integer.parseInt(cursor.getString(0)));
        book.setTitle(cursor.getString(1));
        book.setAuthor(cursor.getString(2));
        book.setPress(cursor.getString(3));

        //log
        Log.d("getBook("+id+")", book.toString());

        // 5. return book
        return book;
    }

    public Book getBookByName(String title){
        Book book = new Book();
        Cursor cursor = null;

        // 1. get reference to readable DB
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + TABLE_BOOKS
                        + " WHERE " + COL_TITLE + " = " + title;
        /*String query = SQLiteQueryBuilder.buildQueryString(false, TABLE_BOOKS, null,
                COL_TITLE + " LIKE '%"+title+"%'", null, null, null, null);*/

        // 2. build query
        /*Cursor cursor =
                db.query(TABLE_BOOKS, // a. table
                        COLUMNS, // b. column names
                        " title LIKE ?", // c. selections
                        new String[] { "'%"+title+"%'" }, // d. selections args
                        null, // e. group by
                        null, // f. having
                        null, // g. order by
                        null); // h. limit*/
        System.out.println("Query: "+query);
        cursor = db.rawQuery(query, null);

        // 3. if we got results get the first one
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();

            // 4. build book object
            book.setId(Integer.parseInt(cursor.getString(0)));
            book.setTitle(cursor.getString(1));
            book.setAuthor(cursor.getString(2));
            book.setPress(cursor.getString(3));

            //log
            Log.d("getBook("+id+")", book.toString());

            // 5. return book
            return book;
        } else {
            return null;
        }
    }


   public ArrayList<Book> getAllBooks() {
        ArrayList<Book> books = new ArrayList<Book>();

        // 1. build the query
        String query = "SELECT  * FROM " + TABLE_BOOKS;

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // 3. go over each row, build book and add it to list
       if (cursor.moveToFirst()) {
            do {
                Book book = null;
                book = new Book();
                book.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
                book.setTitle(cursor.getString(cursor.getColumnIndex(COL_TITLE)));
                book.setAuthor(cursor.getString(cursor.getColumnIndex(COL_AUTHOR)));
                book.setPress(cursor.getString(cursor.getColumnIndex(COL_PRESS)));

                // Add book to books
                books.add(book);
            } while (cursor.moveToNext());
        }

        Log.d("getAllBooks()", books.toString());

        // return books
        return books;
    }

/*
 * 문자열 List를 반환하는 전체 조회
 */
/*    public List<String> getAllBooks() {
        List<String> books = new ArrayList<>();

        // 1. build the query
        String query = "SELECT  * FROM " + TABLE_BOOKS;

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // 3. go over each row, build book and add it to list
        Book book = null;
        if (cursor.moveToFirst()) {
            do {
                *//*book = new Book();
                book.setId(Integer.parseInt(cursor.getString(0)));
                book.setTitle(cursor.getString(1));
                book.setAuthor(cursor.getString(2));
                book.setPress(cursor.getString(3));*//*

                // Add book to books
                books.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }

        Log.d("getAllBooks()", books.toString());

        // return books
        return books;
    }*/
    
    public void updateBook(Book book) {
        SQLiteDatabase db = this.getWritableDatabase();
        
        ContentValues values = new ContentValues();
        
        
        //db.update(TABLE_BOOKS, );
    }

    public void deleteBook(Book book) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. delete
        db.delete(TABLE_BOOKS, //table name
                KEY_ID+" = ?",  // selections
                new String[] { String.valueOf(book.getId()) }); //selections args

        // 3. close
        db.close();

        //log
        Log.d("deleteBook", book.toString());

    }

    public void deleteAll() {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. delete
        db.delete(TABLE_BOOKS, //table name
                null,  // selections
                null); //selections args

        // 3. close
        db.close();

    }
}

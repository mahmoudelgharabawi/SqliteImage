package com.example.sqliteimage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DbContact extends SQLiteOpenHelper {
    private static final String DB_NAME = "myphone_db";
    private static final int DB_VESION = 2;

    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_PHONE = "phone";
    private static final String KEY_IMG = "image";

    private static final String TABLE_CONTACT = "contacts";


    public DbContact(Context context) {
        super(context, DB_NAME, null, DB_VESION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_table = "create table " + TABLE_CONTACT + "(" + KEY_ID + " integer primary key autoincrement,"
                + KEY_NAME + " varchar(255) DEFAULT'',"
                + KEY_PHONE + " integer ,"
                + KEY_IMG + " blob)";

        Log.d("create", create_table);
        db.execSQL(create_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String delete_query = "DROP table if exists " + TABLE_CONTACT;
        db.execSQL(delete_query);

        onCreate(db);
    }

    public void addContact(Contact contact) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contact.getName());
        values.put(KEY_PHONE, contact.getPhone());
        values.put(KEY_IMG, contact.getImage());

        db.insert(TABLE_CONTACT, null, values);

    }

    public ArrayList<Contact> getAllContacts() {
        ArrayList<Contact> contacts = new ArrayList<>();

        String select_query = "select * from " + TABLE_CONTACT;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(select_query, null);

        if (cursor.moveToFirst()) {

            do {

                int id = cursor.getInt(cursor.getColumnIndex(KEY_ID));
                String name = cursor.getString(cursor.getColumnIndex(KEY_NAME));
                int phone = cursor.getInt(cursor.getColumnIndex(KEY_PHONE));
                byte[] image = cursor.getBlob(cursor.getColumnIndex(KEY_IMG));

                Contact contact = new Contact(id, name, phone, image);

                contacts.add(contact);

            } while (cursor.moveToNext());

        }

        return contacts;
    }

    public Contact getContactById(int id) {

        SQLiteDatabase db = this.getReadableDatabase();

        String select_query = "select * from " + TABLE_CONTACT + "where id=" + id;

        Cursor cursor = db.rawQuery(select_query, null);

        Contact contact = null;

        if (cursor.moveToFirst()) {

            int id_contact = cursor.getInt(cursor.getColumnIndex(KEY_ID));
            String name = cursor.getString(cursor.getColumnIndex(KEY_NAME));
            int phone = cursor.getInt(cursor.getColumnIndex(KEY_PHONE));
            byte[] image = cursor.getBlob(cursor.getColumnIndex(KEY_IMG));


            contact = new Contact(id, name, phone, image);

        }
        return contact;
    }

    public Contact getContactById2(int id) {

        Contact contact = null;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_CONTACT, new String[]{"id", "name", "phone", "image"}, "id=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor.moveToFirst()) {

            int id_contact = cursor.getInt(cursor.getColumnIndex(KEY_ID));
            String name = cursor.getString(cursor.getColumnIndex(KEY_NAME));
            int phone = cursor.getInt(cursor.getColumnIndex(KEY_PHONE));
            byte[] image = cursor.getBlob(cursor.getColumnIndex(KEY_IMG));

            contact = new Contact(id, name, phone, image);

        }

        return contact;

    }

    public void updateContact(Contact contact) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contact.getName());
        values.put(KEY_PHONE, contact.getPhone());
        values.put(KEY_IMG, contact.getImage());

        db.update(TABLE_CONTACT, values, "id=?", new String[]{String.valueOf(contact.getId())});

    }


    public void deletContact(int id) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_CONTACT, "id=?", new String[]{String.valueOf(id)});

    }


}

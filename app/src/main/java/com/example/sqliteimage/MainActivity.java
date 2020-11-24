package com.example.sqliteimage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView contactList;
    Button btnAdd;

    DbContact db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DbContact(this);

        contactList = (ListView) findViewById(R.id.contactList);
        btnAdd = (Button) findViewById(R.id.btnAdd);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, AddContact.class);
                startActivity(intent);

            }
        });


        contactList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Contact selected_contact = (Contact) parent.getItemAtPosition(position);

                Intent intent = new Intent(MainActivity.this, UpdateContact.class);

                intent.putExtra("id", selected_contact.getId());

                startActivity(intent);

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        ArrayList<Contact> contacts = db.getAllContacts();

        ContactAdapter contactAdapter = new ContactAdapter(this, R.layout.item_contact, contacts);

        contactList.setAdapter(contactAdapter);

    }
}
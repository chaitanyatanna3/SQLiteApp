package com.example.chaitanya.sqliteapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    SQLiteDatabaseManager sqLiteDatabaseManager;
    EditText editTextName, editTextAddress;
    Button buttonSubmit, buttonViewUsers, buttonGetUserId, buttonGetAddress, buttonUpdateName, buttonUpdateAddress, buttonDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        editTextName  = (EditText) findViewById(R.id.et_name);
        editTextAddress = (EditText) findViewById(R.id.et_address);

        buttonSubmit = (Button) findViewById(R.id.btn_submit);
        buttonViewUsers = (Button) findViewById(R.id.btn_view_users);
        buttonGetUserId = (Button) findViewById(R.id.btn_get_id);
        buttonGetAddress = (Button) findViewById(R.id.btn_get_address);
        buttonUpdateName = (Button) findViewById(R.id.btn_update_name);
        buttonUpdateAddress = (Button) findViewById(R.id.btn_update_address);
        buttonDelete = (Button) findViewById(R.id.btn_delete);

        sqLiteDatabaseManager = new SQLiteDatabaseManager(this);

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userValue = editTextName.getText().toString();
                String addressValue = editTextAddress.getText().toString();
                long id = sqLiteDatabaseManager.insertData(userValue, addressValue);
                if (id < 0) {
                    Toast.makeText(MainActivity.this, "Unsuccessful", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, "Successful", Toast.LENGTH_LONG).show();
                }
            }
        });

        buttonViewUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String users = sqLiteDatabaseManager.getData();
                Toast.makeText(MainActivity.this, users, Toast.LENGTH_LONG).show();
            }
        });

        buttonGetUserId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userValue = editTextName.getText().toString();
                String addressValue = editTextAddress.getText().toString();
                String users = sqLiteDatabaseManager.getUserId(userValue, addressValue);
                Toast.makeText(MainActivity.this, users, Toast.LENGTH_LONG).show();
            }
        });

        buttonGetAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uservalue = editTextName.getText().toString();
                String users = sqLiteDatabaseManager.getAddress(uservalue);
                Toast.makeText(MainActivity.this, users, Toast.LENGTH_LONG).show();
            }
        });

        buttonUpdateName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentName = editTextName.getText().toString();
                String newName = editTextAddress.getText().toString();
                int  count = sqLiteDatabaseManager.updateName(currentName, newName);
                Toast.makeText(MainActivity.this, "Updated: " + count, Toast.LENGTH_LONG).show();
            }
        });

        buttonUpdateAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userValue = editTextName.getText().toString();
                String newAddress = editTextAddress.getText().toString();
                int count = sqLiteDatabaseManager.updateAddress(userValue, newAddress);
                Toast.makeText(MainActivity.this, "Updated: " + count, Toast.LENGTH_LONG).show();
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userValue = editTextName.getText().toString();
                int count = sqLiteDatabaseManager.deleteName(userValue);
                Toast.makeText(MainActivity.this, "Deleted: " + count, Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

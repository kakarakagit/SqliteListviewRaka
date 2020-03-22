package com.kakaraka.sqlitelistviewraka;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";

    protected Cursor cursor;
    DataHelper dataHelper;

    private ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_btn);
        listView = findViewById(R.id.listview);

        dataHelper = new DataHelper(this);
        
        PopulateListView();



        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, CreateDataActivity.class);
                startActivity(i);
            }
        });


    }

    private void PopulateListView() {

        //get the data and append to a list
        Cursor data = dataHelper.getData();
        ArrayList<String> listData = new ArrayList<>();
        while (data.moveToNext()){
            //get the value from the database column 1
            // then add it to array list
            listData.add(data.getString(1));

        }
        //create the list adapter and set the adapter
        final ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String name = adapterView.getItemAtPosition(i).toString();
                Log.d(TAG, "onItemClick: You clicked on " + name);

                Cursor data = dataHelper.getItemId(name);
                int itemID = -1;
                while (data.moveToNext()){
                    itemID = data.getInt(0);
                }
                if (itemID > -1){
                    //oke
                    //move to next activity
                    Toast.makeText(MainActivity.this, name, Toast.LENGTH_SHORT).show();

                    Intent gotoIntent = new Intent(MainActivity.this, ReadDataActivity.class);
                    gotoIntent.putExtra("ID", itemID);
                    gotoIntent.putExtra("NAMA", name);
                    startActivity(gotoIntent);



                }
                else {
                    Toast.makeText(MainActivity.this, "Something Wrong", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}

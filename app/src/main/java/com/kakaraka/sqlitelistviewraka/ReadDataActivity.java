package com.kakaraka.sqlitelistviewraka;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ReadDataActivity extends AppCompatActivity {


    TextView tvName;
    Button editBtn, dltBtn;

    DataHelper dataHelper;

    String selectedName;
    int selectedId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_data);


        dataHelper = new DataHelper(this);
        tvName = findViewById(R.id.rd_nama);

        editBtn = findViewById(R.id.rd_edit);
        dltBtn = findViewById(R.id.rd_hapus);

        // get intent extra from main activity
        Intent receivedIntent = getIntent();

        // get itemId and name we passed as an extra
        selectedId = receivedIntent.getIntExtra("ID", -1); // NOTE : -1 is just the default value
        selectedName = receivedIntent.getStringExtra("NAMA");

        tvName.setText(selectedName);

        // edit go to UpdateDataActivity
        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent gotoIntent = new Intent(ReadDataActivity.this, UpdateDataActivity.class);
                gotoIntent.putExtra("ID", selectedId);
                gotoIntent.putExtra("NAMA", selectedName);
                startActivity(gotoIntent);

            }
        });


        dltBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataHelper.deleteData(selectedId, selectedName);
                tvName.setText("-");
                toMainActivity();
                toastM("Removed successfuly");
            }
        });



    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(ReadDataActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void toMainActivity()
    {
        Intent intent = new Intent(ReadDataActivity.this, MainActivity.class);
        startActivity(intent);
    }



    public void toastM(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}

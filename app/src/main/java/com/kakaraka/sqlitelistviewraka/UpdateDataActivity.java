package com.kakaraka.sqlitelistviewraka;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UpdateDataActivity extends AppCompatActivity {

    EditText nameEt;
    Button saveBtn, clrBtn;

    DataHelper dataHelper;

    private String selectedName;
    private int selectedId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_data);


        dataHelper = new DataHelper(this);
        nameEt = findViewById(R.id.up_nama);
        saveBtn = findViewById(R.id.ud_saveBtn);
        clrBtn = findViewById(R.id.ud_clrBtn);

        // get intent extra from main activity
        Intent receivedIntent = getIntent();

        // get itemId and name we passed as an extra
        selectedId = receivedIntent.getIntExtra("ID", -1); // NOTE : -1 is just the default value
        selectedName = receivedIntent.getStringExtra("NAMA");

        nameEt.setText(selectedName);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String item = nameEt.getText().toString();
                if (!item.equals("")){

                    dataHelper.updateName(item, selectedId, selectedName);
                    toMainActivity();
                    toastM("Saved");

                } else {
                    toastM("Cannot be empty!");
                }
            }
        });

        clrBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nameEt.setText("");
                toastM("Cleared");
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(UpdateDataActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void toMainActivity()
    {
        Intent intent = new Intent(UpdateDataActivity.this, MainActivity.class);
        startActivity(intent);
    }



    public void toastM(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}

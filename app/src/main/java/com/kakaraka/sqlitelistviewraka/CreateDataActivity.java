package com.kakaraka.sqlitelistviewraka;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CreateDataActivity extends AppCompatActivity {

    TextView cdid;
    EditText cdnama, cdhp, cdalamat, cdkampus;
    Button cdsimpan, cdcancel;

    DataHelper dataHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_data);

        cdid = findViewById(R.id.cd_id);

        cdnama = findViewById(R.id.cd_nama);
        cdhp = findViewById(R.id.cd_hp);
        cdalamat = findViewById(R.id.cd_alamat);
        cdkampus = findViewById(R.id.cd_kampus);

        cdcancel = findViewById(R.id.cd_cancel);
        cdsimpan = findViewById(R.id.cd_simpan);

        dataHelper = new DataHelper(this);




        cdcancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cdnama.getText().clear();
                cdhp.getText().clear();
                cdalamat.getText().clear();
                cdkampus.getText().clear();
                toMainActivity();
            }
        });




        cdsimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String newNama = cdnama.getText().toString();
                //String newHp = cdhp.getText().toString();
                //String newAlamat = cdalamat.getText().toString();
                //String newKampus = cdkampus.getText().toString();


                if (cdnama.getText().toString().isEmpty()){
                    Toast.makeText(CreateDataActivity.this, "Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
                }
                else {
                    AddData(newNama);
                    cdnama.setText("");
                    toMainActivity();
                   }

            }
        });

    }



    public void AddData(String nama){

        boolean insertData = dataHelper.addData(nama);

        if (insertData){
            Toast.makeText(this, "Berhasil masukin data", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Gagal euy", Toast.LENGTH_SHORT).show();
        }
    }

    public void toMainActivity()
    {
        Intent intent = new Intent(CreateDataActivity.this, MainActivity.class);
        startActivity(intent);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(CreateDataActivity.this, MainActivity.class);
        startActivity(intent);
    }
}

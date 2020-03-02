package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.app.AlertDialog;

public class MainActivity extends AppCompatActivity {
    DataHelper mydb;
    EditText namaSiswa, jurusanSiswa, hobiSiswa, idSiswa;
    Button btnTambah, btnHapus, btnUbah, btnLihat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mydb = new DataHelper(this);
        namaSiswa = (EditText)findViewById(R.id.EditTextNama);
        jurusanSiswa = (EditText)findViewById(R.id.EditTextJurusan);
        hobiSiswa = (EditText)findViewById(R.id.EditTextHobi);
        idSiswa = (EditText)findViewById(R.id.EditTextIdSiswa);

        btnTambah = (Button)findViewById(R.id.ButtonTambah);
        btnHapus = (Button)findViewById(R.id.ButtonHapus);
        btnUbah = (Button)findViewById(R.id.ButtonUbah);
        btnLihat = (Button)findViewById(R.id.ButtonLihat);

        InsertDataSiswa();
        getAllDataSiswa();
        DeleteSiswa();
        UpdateDataSiswa();
    }

    // insert data
    public void InsertDataSiswa()
    {
        btnTambah.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean insert = mydb.InsertData(namaSiswa.getText().toString(), jurusanSiswa.getText().toString(),
                                hobiSiswa.getText().toString());

                        if(insert == true)
                        {
                            Toast.makeText(MainActivity.this, "Data Berhasil Di Tambahkan", Toast.LENGTH_LONG).show();
                        }
                        else{
                            Toast.makeText(MainActivity.this, "Data Gagal Di Tambahkan", Toast.LENGTH_LONG).show();
                        }
                    }
                }
        );


    }

    public void getAllDataSiswa()
    {
        btnLihat.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res = mydb.getAllData();
                        if (res.getCount() == 0)
                        {
                            // tampilkan pesan
                            showMessage("Error", "Data Tidak Ada");
                            return;
                        }

                        StringBuffer buffer = new StringBuffer();
                        while(res.moveToNext())
                        {
                            buffer.append("Id siswa : "+res.getString(0)+"\n");
                            buffer.append("Nama siswa : "+res.getString(1)+"\n");
                            buffer.append("Jurusan siswa : "+res.getString(2)+"\n");
                            buffer.append("Hobi siswa : "+res.getString(3)+"\n");
                        }

                        showMessage("Data", buffer.toString());
                    }
                }
        );
    }

    public void DeleteSiswa()
    {
        btnHapus.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Integer deletedRow = mydb.DeleteData(idSiswa.getText().toString());
                        if (deletedRow > 0)
                        {
                            Toast.makeText(MainActivity.this, "Data Berhasil Di Hapus", Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(MainActivity.this, "Data Gagal Di Hapus", Toast.LENGTH_LONG).show();
                        }
                    }
                }
        );
    }

    public void UpdateDataSiswa()
    {
        btnUbah.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean update = mydb.UpdateData(idSiswa.getText().toString() ,namaSiswa.getText().toString(), jurusanSiswa.getText().toString(),
                                hobiSiswa.getText().toString());

                        if(update == true)
                        {
                            Toast.makeText(MainActivity.this, "Data Berhasil Di Update", Toast.LENGTH_LONG).show();
                        }
                        else{
                            Toast.makeText(MainActivity.this, "Data Gagal Di Update", Toast.LENGTH_LONG).show();
                        }
                    }
                }
        );
    }

    public void showMessage(String title, String message)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }




}

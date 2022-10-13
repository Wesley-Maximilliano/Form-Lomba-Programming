package com.if31.formlombaprogramming;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class ConfirmActivity extends AppCompatActivity {
    DatePickerDialog datePickerDialog;
 TextView tvNama, tvJK, tvNoWhatsapp, tvAlamat, tvTanggal;
 Button btnTanggal, btnKonfirmasi;

 String nama, jk, noWhatsapp, alamat, choosenDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);

         tvNama = findViewById(R.id.tv_nama);
         tvJK = findViewById(R.id.tv_jk);
         tvNoWhatsapp = findViewById(R.id.tv_no_wa);
         tvAlamat = findViewById(R.id.tv_alamat);
         tvTanggal = findViewById(R.id.tv_tanggal);

         btnTanggal = findViewById(R.id.btn_tanggal);
         btnKonfirmasi = findViewById(R.id.btn_konfirmasi);

         //ambil Intent yang dikirim oleh main activity
            Intent terima = getIntent();
            nama = terima.getStringExtra("varNama");
            jk = terima.getStringExtra("varJenisKelamin");
            noWhatsapp = terima.getStringExtra("varNoWhatsapp");
            alamat = terima.getStringExtra("varAlamat");

            //set variabel ke dalam text view
            tvNama.setText(nama);
            tvJK.setText(jk);
            tvNoWhatsapp.setText(noWhatsapp);
            tvAlamat.setText(alamat);

            btnTanggal.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Calendar newCalendar = Calendar.getInstance();

                    datePickerDialog = new DatePickerDialog(ConfirmActivity.this, new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                            String tahun = Integer.toString(year);
                            String bulan = Integer.toString(month + 1);
                            String tanggal = Integer.toString(dayOfMonth);
                            choosenDate = tanggal + "/" + bulan + "/" + tahun;
                            tvTanggal.setText(choosenDate);
                        }
                    },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH),newCalendar.get(Calendar.DAY_OF_MONTH));
                    // tampilkan datePickerDialog
                    datePickerDialog.show();
                }
            });

            btnKonfirmasi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder dialog = new AlertDialog.Builder(ConfirmActivity.this);
                            dialog.setTitle("Perhatian");
                            dialog.setMessage("Apakah Data Anda Sudah Benar ?");

                            // button positif
                    dialog.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Toast.makeText(ConfirmActivity.this, "Terima kasih, Pendaftaran Anda Berhasil", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    });
                    //button negatif
                    dialog.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }

                    });
                    //tampilkan dialog
                    dialog.show();
                }

            });
    }
}
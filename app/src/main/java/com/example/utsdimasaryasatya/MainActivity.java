package com.example.utsdimasaryasatya;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    // definisikan object variabel
    FirebaseAuth auth;
    TextView textView;
    TextView email;;
    FirebaseUser user;

    // Jika session login ada, maka pindahkan ke MainActivity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Definisikan variabel object dengane element pada activity_login
        setContentView(R.layout.activity_intro);
        auth = FirebaseAuth.getInstance();
        textView = findViewById(R.id.btn_logout);
        email = findViewById(R.id.email);
        user = auth.getCurrentUser(); // ambil data user yang login saat ini dari firebase

        //Jika Session user tidak ada
        if(user == null){
            Intent intent = new Intent(getApplicationContext(), Login.class);
            startActivity(intent);
            finish();
        }

        // set element email dengan data email dari user yang login saaat ini
        email.setText(user.getEmail());

        // Jikla klik tombol logout
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();
            }
        });

    }
}
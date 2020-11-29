package com.example.a7classestask;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class Profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ConstraintLayout constraintLayout = findViewById(R.id.layout);
        AnimationDrawable animationDrawable = (AnimationDrawable) constraintLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(4000);
        animationDrawable.start();

        String name = getIntent().getStringExtra("name");
        String inclass = getIntent().getStringExtra("class");
        String board = getIntent().getStringExtra("board");

        TextView one,two,three;

        one = findViewById(R.id.textView3);
        one.setText(String.format(name));
        two = findViewById(R.id.textView4);
        two.setText("Class : " + String.format(inclass));
        three = findViewById(R.id.textView5);
        three.setText("Board : " + String.format(board));

        Button logout;
        logout = findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent i = new Intent(getApplicationContext(), OtpSend.class);
                startActivity(i);
                finish();
            }
        });
    }
}
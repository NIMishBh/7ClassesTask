package com.example.a7classestask;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class Redg extends AppCompatActivity {

    EditText inclass, board;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_redg);
        final String name = getIntent().getStringExtra("name");
        TextView greet = findViewById(R.id.greet);
        greet.setText("Hi " + String.format(name)+ "!");
        inclass = findViewById(R.id.inclass);
        board = findViewById(R.id.board);
        submit = findViewById(R.id.submit2);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Profile.class);
                i.putExtra("class",inclass.getText().toString());
                i.putExtra("board",board.getText().toString());
                i.putExtra("name",name);
                startActivity(i);
                finish();
            }
        });


    }
}
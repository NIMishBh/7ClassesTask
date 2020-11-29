package com.example.a7classestask;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Nameredg extends AppCompatActivity {

    EditText name;
    Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nameredg);
        name = findViewById(R.id.name);
        submit = findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Redg.class);
                i.putExtra("name",name.getText().toString());
                startActivity(i);
                finish();
            }
        });

    }

}
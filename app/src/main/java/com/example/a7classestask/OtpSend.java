package com.example.a7classestask;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class OtpSend extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText mobileno = findViewById(R.id.mobileno);
        final Button getotp = findViewById(R.id.getotp);

        final ProgressBar pbar = findViewById(R.id.progress);

        getotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mobileno.getText().toString().trim().isEmpty())
                {
                    Toast.makeText(getApplicationContext(),"Enter Mobile Number!",Toast.LENGTH_SHORT).show();
                    return;
                }
                pbar.setVisibility(View.VISIBLE);
                getotp.setVisibility(View.INVISIBLE);

                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        "+91" + mobileno.getText().toString(),
                        60,
                        TimeUnit.SECONDS,
                        OtpSend.this,
                        new PhoneAuthProvider.OnVerificationStateChangedCallbacks(){
                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                pbar.setVisibility(View.GONE);
                                getotp.setVisibility(View.VISIBLE);
                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {

                                pbar.setVisibility(View.GONE);
                                getotp.setVisibility(View.VISIBLE);
                                Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();

                            }

                            @Override
                            public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                pbar.setVisibility(View.GONE);
                                getotp.setVisibility(View.VISIBLE);
                                Intent i = new Intent(getApplicationContext(),OtpVerify.class);
                                i.putExtra("mobileno",mobileno.getText().toString());
                                i.putExtra("vid",s);
                                startActivity(i);
                                finish();

                            }
                        }
                );
            }
        });
    }
}
package com.example.a7classestask;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class OtpVerify extends AppCompatActivity {

    private EditText otp;
    private String vid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_verify);
        TextView textmobile = findViewById(R.id.textmobile);
        textmobile.setText(String.format(
                getIntent().getStringExtra("mobileno")
        ));
        otp = findViewById(R.id.otp);

        final ProgressBar pbar = findViewById(R.id.progress2);
        final Button verify = findViewById(R.id.verify);

        vid = getIntent().getStringExtra("vid");

        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(otp.getText().toString().trim().isEmpty())
                {
                    Toast.makeText(getApplicationContext(),"Enter the OTP!",Toast.LENGTH_SHORT).show();
                }
                String code = otp.getText().toString();
                if(vid != null)
                {
                    pbar.setVisibility(View.VISIBLE);
                    verify.setVisibility(View.INVISIBLE);
                    PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(
                            vid,
                            code
                    );
                    FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    pbar.setVisibility(View.GONE);
                                    verify.setVisibility(View.VISIBLE);
                                    if(task.isSuccessful())
                                    {
                                        Intent i = new Intent(getApplicationContext(),Nameredg.class);
                                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        startActivity(i);
                                        finish();
                                    }
                                    else
                                    {
                                        Toast.makeText(getApplicationContext(),"Verification Failed",Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });

         Button resend =  findViewById(R.id.resend);
         resend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        "+91" + getIntent().getStringExtra("mobileno"),
                        60,
                        TimeUnit.SECONDS,
                        OtpVerify.this,
                        new PhoneAuthProvider.OnVerificationStateChangedCallbacks(){
                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                Toast.makeText(OtpVerify.this,"OTP sent Again",Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {
                                Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();

                            }

                            @Override
                            public void onCodeSent(@NonNull String newvid, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                vid = newvid;
                                Toast.makeText(OtpVerify.this,"OTP sent Again",Toast.LENGTH_SHORT).show();

                            }
                        }
                );
            }
        });
    }
}
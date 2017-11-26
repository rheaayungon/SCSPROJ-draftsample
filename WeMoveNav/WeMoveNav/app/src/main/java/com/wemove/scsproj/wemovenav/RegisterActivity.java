package com.wemove.scsproj.wemovenav;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by rheaayungon on 31/10/2017.
 */

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    //defining view objects
    private EditText regUsername;
    private EditText regName;
    private EditText regEmailadd;
    private EditText regMobileno;
    private EditText regPassword;
    private CardView regButton;

    private ProgressDialog progressDialog;

    //defining firebaseauth object
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //initializing firebase auth object
        firebaseAuth = FirebaseAuth.getInstance();

        //if getCurrentUser doed not returns null
        if (firebaseAuth.getCurrentUser() != null) {
            //that means user is already logged in
            //so close this activity
            finish();

            //and open profile activity
            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
        }

        //initializing views
        regUsername = (EditText) findViewById(R.id.regUsername);
        regName = (EditText) findViewById(R.id.name);
        regEmailadd = (EditText) findViewById(R.id.regEmailadd);
        regMobileno = (EditText) findViewById(R.id.mobileno);
        regPassword = (EditText) findViewById(R.id.regPassword);

        regButton = (CardView) findViewById(R.id.btnRegister);

        progressDialog = new ProgressDialog(this);

        //attaching listener to button
        regButton.setOnClickListener(this);

    }

    private void registerUser() {

        //getting infos from edit texts
        String name = regName.getText().toString().trim();
        String email = regEmailadd.getText().toString().trim();
        String regname = regName.getText().toString().trim();
        String mobileno = regMobileno.getText().toString().trim();
        String password = regPassword.getText().toString().trim();

        //cheking if infos are empty
        if (TextUtils.isEmpty(regname)) {
            Toast.makeText(this, "Please enter email", Toast.LENGTH_LONG).show();
            return;

        }

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Please enter email", Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "Please enter name", Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(mobileno)) {
            Toast.makeText(this, "Please enter mobile number", Toast.LENGTH_LONG).show();
            return;

        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please enter password", Toast.LENGTH_LONG).show();
            return;

        }

        //if the email and password are not empty
        //displaying a progress dialog

        progressDialog.setMessage("Registering. Please Wait...");
        progressDialog.show();

        //creating a new user
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //checking if success
                        if (task.isSuccessful()) {
                            finish();
                            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                        } else {
                            //display some message here
                            Toast.makeText(RegisterActivity.this, "Registration Error", Toast.LENGTH_LONG).show();
                        }
                        progressDialog.dismiss();
                    }
                });
    }

    @Override
    public void onClick(View view) {

        if (view == regButton) {
            registerUser();
        }
    }
}
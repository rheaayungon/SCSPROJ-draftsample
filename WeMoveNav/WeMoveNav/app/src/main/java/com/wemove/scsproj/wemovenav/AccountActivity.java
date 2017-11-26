package com.wemove.scsproj.wemovenav;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by rheaayungon on 07/11/2017.
 */

public class AccountActivity extends AppCompatActivity implements View.OnClickListener {

    //firebase auth object
    private FirebaseAuth firebaseAuth;

    //view objects
    private TextView acctName;
    private TextView acctEmail;
    private CardView acctLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        acctEmail = (TextView) findViewById(R.id.accEmail);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference().child("0");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                acctName.setText(value);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });

        //initializing firebase authentication object
        firebaseAuth = firebaseAuth.getInstance();

        //if the user is not logged in
        //that means current user will return null
        if (firebaseAuth.getCurrentUser() == null) {
            //closing the activity
            finish();
            //starting login activity
            startActivity(new Intent(this, LoginActivity.class));
        }

        //getting current user
        FirebaseUser user = firebaseAuth.getCurrentUser();

        //initializing views
        acctName = (TextView) findViewById(R.id.accName);
        acctLogout = (CardView) findViewById(R.id.btnLogout);

        //displaying logged in user name
        acctName.setText("Welcome " + user.getDisplayName());

        //adding listener to button
        acctLogout.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        //if logout is pressed
        if(view == acctLogout) {
            //logging out the user
            firebaseAuth.signOut();
            //closing activity
            finish();;
            //starting login activity
            startActivity(new Intent(this, LoginActivity.class));
        }
    }

}

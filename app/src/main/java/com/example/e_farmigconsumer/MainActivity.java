package com.example.e_farmigconsumer;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseException;
import com.parse.ParseUser;

public class MainActivity extends AppCompatActivity {

    EditText name, email, pass;
    TextView textView;


    public void Login (View vIew){

        ParseUser.logInInBackground(name.getText().toString(), pass.getText().toString(), new LogInCallback() {
            public void done(ParseUser user, ParseException e) {
                if (user != null) {
                    Toast.makeText(MainActivity.this,"yoo",Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this,"SORRRYYYY",Toast.LENGTH_SHORT).show();
                    // Signup failed. Look at the ParseException to see what happened.
                }
            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.Name);
        email = findViewById(R.id.email);
        pass = findViewById(R.id.pass);
        textView = (TextView)findViewById(R.id.textView2);


          ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser.getUsername() != null) {
            Intent intent =  new Intent(getApplicationContext(),dashboardUser.class);
            startActivity(intent);
        } else {
            // show the signup or login screen
        }

    }

    public void onClick (View view){
        Intent intent =  new Intent(getApplicationContext(),Register.class);
        startActivity(intent);
    }
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Exit")
                .setMessage("Are you sure you want to Exit")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ParseUser.logOutInBackground();
                        finish();
                        ParseUser.logOutInBackground();
                    }

                })
                .setNegativeButton("No", null)
                .show();
    }
}

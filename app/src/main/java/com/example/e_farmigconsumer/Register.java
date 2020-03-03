package com.example.e_farmigconsumer;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;

public class Register extends AppCompatActivity {

    EditText name, email, pass, phone, pin, state;

    public void Register (View view){

        if(phone.getText().toString().length() <10 || pin.getText().toString().length() <6 || name.getText().toString().matches("") || email.getText().toString().matches("") || state.getText().toString().matches("")){
            Toast.makeText(Register.this,"Please provide proper details",Toast.LENGTH_SHORT).show();
        }else{
        ParseUser user = new ParseUser();
        user.setUsername(name.getText().toString());
        user.setPassword(pass.getText().toString());
        user.setEmail(email.getText().toString());

// other fields can be set just like with ParseObject

            user.signUpInBackground(new SignUpCallback() {
            public void done(ParseException e) {
                if (e == null) {
                    // Hooray! Let them use the app now.
                    Intent intent =  new Intent(getApplicationContext(),dashboardUser.class);
                    intent.putExtra("phone",phone.getText().toString());
                    startActivity(intent);
                    Toast.makeText(Register.this,"SIGN IN Compl",Toast.LENGTH_SHORT).show();

                    ParseObject efarming = new ParseObject("userDetail");
                    efarming.put("Full_name",name.getText().toString());
                    efarming.put("Phone_number",phone.getText().toString());
                    efarming.put("State",state.getText().toString());
                    efarming.put("Pin",pin.getText().toString());
                    efarming.saveInBackground(new SaveCallback() {
                        @Override
                        public void done(ParseException e) {

                            if (e == null) {

                                Log.i("YEEEEEEEEEEEEESSSSSSSSSSSSSS","YOOOOOOOOO");
                                Log.i("FULL NAME ::::::::::::",name.getText().toString());

                                // request.put("phone1_emg",ParseUser.getCurrentUser().getString("phone1")); // from user phone1 to request phone DB
                                //phone.add(request.getString("HelperName"));

                            }
                            else{
                                Log.i("NOOOOOOOOOOOOOOOOOOOOOOOOO",e.getMessage());
                            }

                        }
                    });



                } else {
                    // Sign up didn't succeed. Look at the ParseException
                    Toast.makeText(Register.this,e.getMessage(),Toast.LENGTH_SHORT).show();

                    // to figure out what went wrong
                }
            }
        });





    }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        name = findViewById(R.id.Name);
        email = findViewById(R.id.email);
        pass = findViewById(R.id.pass);
        phone = findViewById(R.id.phone);
        state = findViewById(R.id.state);
        pin = findViewById(R.id.pin);


    }
    public void onClick(View view){
        Intent intent =  new Intent(getApplicationContext(),MainActivity.class);
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
                    }

                })
                .setNegativeButton("No", null)
                .show();
    }
}

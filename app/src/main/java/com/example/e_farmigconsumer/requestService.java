package com.example.e_farmigconsumer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.List;
import java.util.Random;

public class requestService extends AppCompatActivity {

    Random random;
    boolean requestActive=false;
    String selected;
    TextView userName, order, howMuch ,phone, pin, price;
    Button incre, decre;
    String phoneNumber, pinAddress, Full_name, state;

    int j=26, k= 32;
    int i=0;
    int pr;
    public void incre(View view){
        i+=1;
        if(selected.matches("Potato") || selected.matches("Onion") || selected.matches("Brinjal")){
           pr= j*i;
            price.setText("Total amount you have to pay : "+pr+ "/Rs");
        }else if(selected.matches("Carrot") || selected.matches("Cabbage") || selected.matches("Cauli")){
            pr=k*i;
            price.setText("Total amount you have to pay : "+pr+ "/Rs");
        }
        howMuch.setText( String.valueOf(i));

    }
    public void decre(View view){
        if(i==0){
            Toast.makeText(requestService.this, "Not possible",Toast.LENGTH_SHORT).show();
        }else {
            i -= 1;
            if(selected.matches("Potato") || selected.matches("Onion") || selected.matches("Brinjal")){

                pr=j*i;
                price.setText("Total amount you have to pay : "+pr+ "/Rs");
            }else if(selected.matches("Carrot") || selected.matches("Cabbage") || selected.matches("Cauli")){

                pr=k*i;
                price.setText("Total amount you have to pay : "+pr+ "/Rs");
            }

            howMuch.setText( String.valueOf(i));
        }
    }

    public void proceed(View view){

            final ParseObject request = new ParseObject("Request");

            request.put("username", ParseUser.getCurrentUser().getUsername());

            request.put("order",selected);
            request.put("amount",howMuch.getText().toString());
            request.put("phone",phoneNumber);
            request.put("State",state);
            request.put("pin",pinAddress);
            request.put("price",String.valueOf(pr));// wrong
            request.put("random",new Random().nextInt(61) + 20 + new Random().nextInt(100) + 20);
            request.saveInBackground(new SaveCallback() {
                @Override
                public void done(ParseException e) {

                    if (e == null) {

                        requestActive = true;
                        Intent intent = new Intent(getBaseContext(), Final.class);
                        intent.putExtra("ordered_item",selected);
                        startActivity(intent);

                        // request.put("phone1_emg",ParseUser.getCurrentUser().getString("phone1")); // from user phone1 to request phone DB
                        //phone.add(request.getString("HelperName"));

                    }else {
                        Log.i("OOOOOOOOOOOOOO",e.getMessage());
                    }

                }
            });



    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_service);
        selected = getIntent().getStringExtra("selected");

        userName= findViewById(R.id.userName);
        order = findViewById(R.id.order);

        incre = findViewById(R.id.incre);
        howMuch = findViewById(R.id.howMuch);
        decre = findViewById(R.id.decre);
        price = findViewById(R.id.price);

        phone = findViewById(R.id.yourNumber);
        pin = findViewById(R.id.yourPIN);
        random = new Random();


        ParseQuery<ParseObject> query1 = ParseQuery.getQuery("userDetail");
        query1.whereEqualTo("Full_name",ParseUser.getCurrentUser().getUsername());

        query1.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> user, ParseException e) {
                if (e == null) {

                    Log.d("police", "Retrieved " + user.size() + " scores");

                    for(ParseObject users : user){
                        Log.d("police", "Retrieved " + users.getString("Full_name") );
                        Log.d("police", "Retrieved " + users.getString("Phone_number") );
                        Log.d("police", "Retrieved " + users.getString("Pin") );
                        Full_name = users.getString("Full_name");
                        phoneNumber = users.getString("Phone_number");
                        pinAddress = users.getString("Pin");
                        state = users.getString("State");

                        userName.setText("Thank you : "+Full_name);
                        order.setText("You have ordered for : "+selected);
                        users.put("order",selected);
                        users.put("amount",howMuch.getText().toString());
                        phone.setText("Your phone number is :"+phoneNumber);
                        pin.setText("Your PIN address :"+pinAddress);

                    }
                } else {
                    Log.d("score", "Error: " + e.getMessage());
                }
            }
        }); // has to change


    }
}

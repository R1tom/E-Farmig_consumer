package com.example.e_farmigconsumer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

public class Final extends AppCompatActivity {

    Button goBack;
    TextView textView;
    ImageView imageView;
    String orderd_item;

    public void setGoBack(View view){
        Intent intent =  new Intent(getApplicationContext(),dashboardUser.class);
        startActivity(intent);
    }
    public void logout(View view){
        ParseUser.logOutInBackground();
        Intent intent =  new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
    }
    public void cancel(View view){
        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Request");

        query.whereEqualTo("username", ParseUser.getCurrentUser().getUsername());
        query.whereEqualTo("order",orderd_item);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {

                if (e == null) {

                    if (objects.size() > 0) {

                        for (ParseObject object : objects) {

                            object.deleteInBackground();

                        }

                        textView.setText("Order cancelled");
                        imageView.setVisibility(View.INVISIBLE);


                    }

                }

            }
        });

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);

        textView = findViewById(R.id.textView);
        orderd_item = getIntent().getStringExtra("ordered_item");
        goBack = findViewById(R.id.goBack);
        imageView= findViewById(R.id.cancelBu);
    }
}

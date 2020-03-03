package com.example.e_farmigconsumer;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.parse.ParseUser;

public class dashboardUser extends AppCompatActivity {

    public void potato(View view){

        Intent intent = new Intent(getBaseContext(), requestService.class);
        intent.putExtra("selected", "Potato");
        startActivity(intent);
    }
    public void onion(View view){

        Intent intent = new Intent(getBaseContext(), requestService.class);
        intent.putExtra("selected", "Onion");
        startActivity(intent);
    }
    public void brinjal(View view){

        Intent intent = new Intent(getBaseContext(), requestService.class);
        intent.putExtra("selected", "Brinjal");
        startActivity(intent);
    }
    public void carrot(View view){

        Intent intent = new Intent(getBaseContext(), requestService.class);
        intent.putExtra("selected", "Carrot");
        startActivity(intent);
    }
    public void cabbage(View view){
        Intent intent = new Intent(getBaseContext(), requestService.class);
        intent.putExtra("selected", "Cabbage");
        startActivity(intent);
    }
    public void cauli(View view){
        Intent intent = new Intent(getBaseContext(), requestService.class);
        intent.putExtra("selected", "Cauli");
        startActivity(intent);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_user);
    }
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Exit")
                .setMessage("Are you sure you want to Logout")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ParseUser.logOutInBackground();
                        finish();
                        Intent intent =  new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(intent);
                    }

                })
                .setNegativeButton("No", null)
                .show();
    }
}

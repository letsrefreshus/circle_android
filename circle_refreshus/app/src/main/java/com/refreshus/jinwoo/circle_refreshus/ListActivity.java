package com.refreshus.jinwoo.circle_refreshus;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        Button NwkBtn = (Button) findViewById(R.id.NetworkBtn);
        NwkBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                ConnectivityManager connMgr = (ConnectivityManager)
                        getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
                if(networkInfo != null && networkInfo.isConnected()){
                    // fetch data
                    Toast.makeText(ListActivity.this,"Connected to the web!", Toast.LENGTH_LONG).show();
                } else{
                    // display error
                    Toast.makeText(ListActivity.this,"Device not connected to network", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
/*
    public void myClickHandler(View view){


    }*/


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch( item.getItemId() ){
            case android.R.id.home:
                startActivity(new Intent(ListActivity.this,MainActivity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}

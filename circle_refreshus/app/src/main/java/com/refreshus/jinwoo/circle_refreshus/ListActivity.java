package com.refreshus.jinwoo.circle_refreshus;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;

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
                    new FeedTask().execute();
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

    public class FeedTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            try {
                // Create okHttp Client object
                OkHttpClient client = new OkHttpClient();

                // Define request being sent to the server
                RequestBody formBody = new FormEncodingBuilder()
                        .add("operation", "QUERY")
                        .add("query_type", "listitems")
                        .add("list_name", "Groceries")
                        //.add("user", "T1")
                        .build();

                Request request = new Request.Builder()
                        .url("http://evident-relic-120823.appspot.com/")
                        .post(formBody)
                        .addHeader("content type","application/json")
                        .build();

                // Transport the request and wait for response to process next
                Response response = client.newCall(request).execute();
                if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
                return response.body().string();
            } catch (Exception e){
                return null;
            }
        }

        @Override
        protected void onPostExecute(String s){
            super.onPostExecute(s);
            TextView testText = (TextView) findViewById(R.id.testText);
            testText.setText(s);
            testText.append("Done!");
        }

    }
}

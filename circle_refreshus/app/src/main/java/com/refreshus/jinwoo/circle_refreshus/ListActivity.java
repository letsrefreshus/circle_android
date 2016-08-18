package com.refreshus.jinwoo.circle_refreshus;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ListActivity extends AppCompatActivity {

    private ProgressDialog progress;

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
                    /*
                    new FeedTask().execute();
                    */
                    sendPostRequest(v);

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
            case R.id.add:

                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // ///////////////////////////////////////// different http protocal

    public void sendPostRequest(View View) {
        new PostClass(this).execute();
    }

    private class PostClass extends AsyncTask<String, Void, Void> {
        private final Context context;
        public PostClass(Context c){
            this.context = c;
        }
        protected void onPreExecute(){
            progress= new ProgressDialog(this.context);
            progress.setMessage("Loading");
            progress.show();
        }
        @Override
        protected Void doInBackground(String... params) {
            try {
                final TextView outputView = (TextView) findViewById(R.id.testText);
                URL url = new URL("http://evident-relic-120823.appspot.com");
                HttpURLConnection connection = (HttpURLConnection)url.openConnection();
                //String urlParameters = "{\"operation\": \"QUERY\", \"query_type\": \"list\", \"user\": \"T1\"}";
                String urlParameters = "{\"operation\": \"QUERY\", \"query_type\": \"listitems\", \"list_name\": \"Groceries\"}";
                connection.setRequestMethod("POST");
                connection.setRequestProperty("USER-AGENT", "Mozilla/5.0");
                connection.setRequestProperty("ACCEPT-LANGUAGE", "en-US,en;0.5");
                connection.setDoOutput(true);
                DataOutputStream dStream = new DataOutputStream(connection.getOutputStream());
                dStream.writeBytes(urlParameters);
                dStream.flush();
                dStream.close();
                int responseCode = connection.getResponseCode();
                System.out.println("\nSending 'POST' request to URL : " + url);
                System.out.println("Post parameters : " + urlParameters);
                System.out.println("Response Code : " + responseCode);
                final StringBuilder output = new StringBuilder("");

                BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line = "";
                StringBuilder responseOutput = new StringBuilder();

                while((line = br.readLine()) != null ) {
                    responseOutput.append(line);
                }
                br.close();
                output.append(responseOutput.toString());
                String finalJson = output.toString();

                int id;
                String itemName;
                int isActive;
                String finaloutput = "Nothing";

                // Parsing JSON OBJECT;
                try {
                    JSONArray parentArray = new JSONArray(finalJson);       // gets the "" array item
                    JSONObject finalObject = parentArray.getJSONObject(0);  // gets the first (and only) list of key-value string
                    id = finalObject.getInt("id");
                    itemName = finalObject.getString("item_name");
                    isActive = finalObject.getInt("isActive");
                    finaloutput = "ID:" + String.valueOf(id) + "\nItem name:" + itemName + "\nIs Active:" + String.valueOf(isActive)+ "\n";

                } catch (JSONException e) {
                    e.printStackTrace();
                }


                final String finalOutput1 = finaloutput;
                ListActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        outputView.setText(finalOutput1);
                        progress.dismiss();
                    }
                });
            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;
        }
        protected void onPostExecute() {
            progress.dismiss();
        }
    }
    /*
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

    }*/
}

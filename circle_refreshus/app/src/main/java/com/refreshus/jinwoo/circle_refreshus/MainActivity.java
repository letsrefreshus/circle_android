package com.refreshus.jinwoo.circle_refreshus;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setLogo(R.drawable.logo_final_google);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        /*
        Button mainTOlistBtn = (Button) findViewById(R.id.fromMaintoList);

        mainTOlistBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent listActivity = new Intent(MainActivity.this,ListActivity.class);
                startActivity(listActivity);

                overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
            }
        });*/

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch( item.getItemId() ){
            case R.id.action_viewList:
                startActivity(new Intent(MainActivity.this, ListActivity.class));
                overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

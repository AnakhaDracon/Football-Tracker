package com.wordpress.anakhadracon.footballtracker;

import android.app.Activity;
import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class FootballGameActivity extends Activity {

    //LinearLayout layout;
    Integer numOfficials;

    Integer playCounter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //layout = new LinearLayout(this);
        //layout.setOrientation(LinearLayout.VERTICAL);

        //LayoutParams linLayoutParam = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);

        Intent intent = getIntent();
        numOfficials = intent.getIntExtra("NumOfficials", 5);
        playCounter = 1;

        ListView lView = (ListView) findViewById(R.id.officialsListView);
        ArrayList<String> officialsName = intent.getStringArrayListExtra("OfficialsList");

        // This is the array adapter, it takes the context of the activity as a
        // first parameter, the type of list view as a second parameter and your
        // array as a third parameter.
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, officialsName);

        lView.setAdapter(arrayAdapter);
        //layout.addView(lView);

        // set LinearLayout as a root element of the screen
        //setContentView(layout);
        setContentView(R.layout.activity_football_game);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_football_game, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

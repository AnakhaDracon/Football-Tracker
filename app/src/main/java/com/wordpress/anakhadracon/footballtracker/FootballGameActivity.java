package com.wordpress.anakhadracon.footballtracker;

import android.app.Activity;
import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class FootballGameActivity extends Activity {

    //LinearLayout layout;
    Integer numOfficials;
    Integer playCounter;

    ArrayList<PlayRecord> mPlays;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // set LinearLayout as a root element of the screen
        //setContentView(layout);
        setContentView(R.layout.activity_football_game);

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

        TextView tView = (TextView)findViewById(R.id.textViewPlayCounter);
        tView.setText(playCounter.toString());

        TextView tView2 = (TextView)findViewById(R.id.textViewNumOfficials);
        tView2.setText(numOfficials.toString());

        mPlays = new ArrayList<PlayRecord>();
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

    public void SavePlayClicked(View view)
    {
        ArrayList<EFouls> fouls = new ArrayList<>();
        PlayRecord record = new PlayRecord(playCounter, EPlayType.KICK_OFF, fouls);
        mPlays.add(record);

        playCounter++;
        TextView tView = (TextView)findViewById(R.id.textViewPlayCounter);
        tView.setText(playCounter.toString());
    }
}

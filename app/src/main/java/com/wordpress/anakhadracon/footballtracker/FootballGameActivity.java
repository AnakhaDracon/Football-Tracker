package com.wordpress.anakhadracon.footballtracker;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
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
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class FootballGameActivity extends Activity {

    //LinearLayout layout;
    Integer numOfficials;
    Integer playCounter;

    ArrayList<PlayRecord> mPlays;
    ArrayList<FoulRecord> fouls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_football_game);

        // Create our ArrayList to hold the fouls on a play and the play records.
        fouls = new ArrayList<>();
        mPlays = new ArrayList<>();

        // Grab the resources so I can get the string arrays
        // Grab the intent data for the number of officials
        // Set the play counter to 1 since we are at the beginning of the game
        Resources res = getResources();
        Intent intent = getIntent();
        numOfficials = intent.getIntExtra("NumOfficials", 5);
        playCounter = 1;

        // Setup the officials name list using an array adapter from the data that was passed
        Spinner lView = (Spinner) findViewById(R.id.spinnerOfficials);
        ArrayList<String> officialsName = intent.getStringArrayListExtra("OfficialsList");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, officialsName);
        lView.setAdapter(arrayAdapter);

        // Grab the foul data from the resources and get it into its spinner
        Spinner sFouls = (Spinner) findViewById(R.id.spinnerFouls);
        ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, res.getStringArray(R.array.fouls_name_array));
        sFouls.setAdapter(arrayAdapter2);

        // Same as above, get the play types from the resources
        Spinner sPlays = (Spinner) findViewById(R.id.spinnerPlayType);
        ArrayAdapter<String> arrayAdapter3 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, res.getStringArray(R.array.plays_name_array));
        sPlays.setAdapter(arrayAdapter3);

        // Get the play counter set up.
        TextView tView = (TextView)findViewById(R.id.textViewPlayCounter);
        tView.setText(playCounter.toString());

        // Set the number of officials.
        TextView tView2 = (TextView)findViewById(R.id.textViewNumOfficials);
        tView2.setText(numOfficials.toString());
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
        // Get the play type
        Spinner spinner = (Spinner) findViewById(R.id.spinnerFouls);
        String playType = spinner.getSelectedItem().toString();

        // Create the play record and store it in the array list.
        PlayRecord record = new PlayRecord(playCounter, playType, fouls);
        mPlays.add(record);

        // Update the playCounter and update the screen.
        playCounter++;
        TextView tView = (TextView)findViewById(R.id.textViewPlayCounter);
        tView.setText(playCounter.toString());

        // Clear fouls as we have saved a play. Also update the screen counter.
        fouls.clear();
        TextView tView2 = (TextView)findViewById(R.id.textViewNumFouls);
        Integer size = fouls.size();
        tView2.setText(size.toString());
    }

    public void SaveFoul(View view)
    {
        // Grab the calling official
        Spinner officialSpinner = (Spinner)findViewById(R.id.spinnerOfficials);
        String officialText = officialSpinner.getSelectedItem().toString();

        // Grab the foul name
        Spinner foulSpinner = (Spinner)findViewById(R.id.spinnerFouls);
        String foulText = foulSpinner.getSelectedItem().toString();
        FoulRecord foul = new FoulRecord(officialText, foulText);

        fouls.add(foul);

        // Find the number of fouls we have saved for this play and update screen.
        TextView tView = (TextView)findViewById(R.id.textViewNumFouls);
        Integer size = fouls.size();
        tView.setText(size.toString());
    }
}

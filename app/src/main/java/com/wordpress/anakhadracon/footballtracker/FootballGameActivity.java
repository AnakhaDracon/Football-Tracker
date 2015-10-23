package com.wordpress.anakhadracon.footballtracker;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class FootballGameActivity extends Activity {
    Integer _numOfficials;
    Integer _playCounter;

    ArrayList<PlayRecord> _plays;
    ArrayList<FoulRecord> _fouls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_football_game);

        // Create our ArrayList to hold the _fouls on a play and the play records.
        _fouls = new ArrayList<>();
        _plays = new ArrayList<>();

        // Grab the resources so I can get the string arrays
        // Grab the intent data for the number of officials
        // Set the play counter to 1 since we are at the beginning of the game
        Resources res = getResources();
        Intent intent = getIntent();
        _numOfficials = intent.getIntExtra("NumOfficials", 5);
        _playCounter = 1;

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

        // Now we are grabbing team names, at this time just home and visitors, but there
        // is an enhancement here to actually grab team names from the user during the setup.
        Spinner sTeams = (Spinner) findViewById(R.id.spinnerTeams);
        ArrayAdapter<String> arrayAdapter4 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, res.getStringArray(R.array.teams_name_array));
        sTeams.setAdapter(arrayAdapter4);

        // Get the play counter set up.
        TextView tView = (TextView)findViewById(R.id.textViewPlayCounter);
        tView.setText(_playCounter.toString());

        // Set the number of officials.
        TextView tView2 = (TextView)findViewById(R.id.textViewNumOfficials);
        tView2.setText(_numOfficials.toString());
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

    public void SaveGameClicked(View view)
    {
        File dir = getDir("GameData", Context.MODE_WORLD_WRITEABLE | Context.MODE_WORLD_READABLE);

        FileOutputStream fileOut;
        //File dir = new File("Football_Tracker/");
        //dir.mkdirs();

        try
        {
            fileOut = openFileOutput("Game.txt", Context.MODE_WORLD_WRITEABLE | Context.MODE_WORLD_READABLE);
            for(Integer i = 0; i < _plays.size(); i++)
            {
                PlayRecord play = _plays.get(i);
                fileOut.write(play.toString().getBytes());
            }
            fileOut.flush();
            fileOut.close();
            FileInputStream fileIn = openFileInput("Game.txt");

            TextView tView = (TextView)findViewById(R.id.textView4);
            int data = fileIn.read();
            tView.setText(data);
            fileIn.close();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void SavePlayClicked(View view)
    {
        // Get the play type
        Spinner spinner = (Spinner) findViewById(R.id.spinnerFouls);
        String playType = spinner.getSelectedItem().toString();

        // Create the play record and store it in the array list.
        PlayRecord record = new PlayRecord(_playCounter, playType, _fouls);
        _plays.add(record);

        // Update the _playCounter and update the screen.
        _playCounter++;
        TextView tView = (TextView)findViewById(R.id.textViewPlayCounter);
        tView.setText(_playCounter.toString());

        // Clear _fouls as we have saved a play. Also update the screen counter.
        _fouls.clear();
        TextView tView2 = (TextView)findViewById(R.id.textViewNumFouls);
        Integer size = _fouls.size();
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

        Spinner teamSpinner = (Spinner)findViewById(R.id.spinnerTeams);
        String team = teamSpinner.getSelectedItem().toString();

        FoulRecord foul = new FoulRecord(officialText, foulText, team);
        _fouls.add(foul);

        // Find the number of _fouls we have saved for this play and update screen.
        TextView tView = (TextView)findViewById(R.id.textViewNumFouls);
        Integer size = _fouls.size();
        tView.setText(size.toString());
    }
}

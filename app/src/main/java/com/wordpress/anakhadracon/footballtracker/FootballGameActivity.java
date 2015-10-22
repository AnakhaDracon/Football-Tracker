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
        // set LinearLayout as a root element of the screen
        //setContentView(layout);
        setContentView(R.layout.activity_football_game);

        //layout = new LinearLayout(this);
        //layout.setOrientation(LinearLayout.VERTICAL);

        //LayoutParams linLayoutParam = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        fouls = new ArrayList<>();

        Resources res = getResources();
        Intent intent = getIntent();
        numOfficials = intent.getIntExtra("NumOfficials", 5);
        playCounter = 1;

        Spinner lView = (Spinner) findViewById(R.id.spinnerOfficials);
        ArrayList<String> officialsName = intent.getStringArrayListExtra("OfficialsList");

        // This is the array adapter, it takes the context of the activity as a
        // first parameter, the type of list view as a second parameter and your
        // array as a third parameter.
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, officialsName);
        lView.setAdapter(arrayAdapter);

        Spinner sFouls = (Spinner) findViewById(R.id.spinnerFouls);
        ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, res.getStringArray(R.array.fouls_name_array));
        sFouls.setAdapter(arrayAdapter2);

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
        PlayRecord record = new PlayRecord(playCounter, EPlayType.KICK_OFF, fouls);
        mPlays.add(record);

        playCounter++;
        TextView tView = (TextView)findViewById(R.id.textViewPlayCounter);
        tView.setText(playCounter.toString());

        fouls.clear();
        TextView tView2 = (TextView)findViewById(R.id.textViewNumFouls);
        Integer size = fouls.size();
        tView2.setText(size.toString());
    }

    public void SaveFoul(View view)
    {
        Spinner officialSpinner = (Spinner)findViewById(R.id.spinnerOfficials);
        String officialText = officialSpinner.getSelectedItem().toString();

        Spinner foulSpinner = (Spinner)findViewById(R.id.spinnerFouls);
        String foulText = foulSpinner.getSelectedItem().toString();
        FoulRecord foul = new FoulRecord(officialText, foulText);

        fouls.add(foul);
        TextView tView = (TextView)findViewById(R.id.textViewNumFouls);
        Integer size = fouls.size();
        tView.setText(size.toString());
    }
}

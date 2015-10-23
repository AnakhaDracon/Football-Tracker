package com.wordpress.anakhadracon.footballtracker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class OfficialsNameActivity extends Activity {

    LinearLayout layout;
    Integer numOfficials;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);

        //LayoutParams linLayoutParam = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);

        Intent intent = getIntent();
        numOfficials = intent.getIntExtra("NumOfficials", 5);

        Integer loopCount = 0;
        while(loopCount < numOfficials)
        {
            EditText eText = new EditText(this);
            eText.setHint("Official #" + (loopCount+1) + " Name");
            eText.setId(500 + loopCount);

            layout.addView(eText);
            loopCount++;
        }
        Button startGame = new Button(this);
        startGame.setText(R.string.begin_game);
        View.OnClickListener listener = new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                onClickBegin(view);
            }
        };
        startGame.setOnClickListener(listener);
        layout.addView(startGame);

        // set LinearLayout as a root element of the screen
        setContentView(layout);

        //setContentView(R.layout.activity_officials_name);
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_officials_name, menu);
//        return true;
//    }

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

    public void onClickBegin(View view)
    {
        Intent intent = new Intent(this, FootballGameActivity.class);
        ArrayList<String> officials = new ArrayList<String>(numOfficials);

        Integer loopCount = 0;
        while(loopCount < numOfficials) {
            EditText eText = (EditText) findViewById(500+loopCount);
            officials.add(loopCount, eText.getText().toString());

            loopCount++;
        }
        intent.putExtra("NumOfficials", numOfficials);
        intent.putStringArrayListExtra("OfficialsList", officials);

        startActivity(intent);
    }
}

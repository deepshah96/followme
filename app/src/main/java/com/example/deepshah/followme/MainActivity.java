package com.example.deepshah.followme;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;

/**
 * Presents the title screen with GUI by which the player can
 * go into the game, or display high scores.
 */
public class MainActivity extends Activity {
    /**
     * Called when the activity is first created.
     *
     * @param  savedInstanceState  If the activity is being re-initialized after previously being shut down
     *                             then this Bundle contains the data it most recently supplied in
     *                             onSaveInstanceState(Bundle). Note: Otherwise it is null.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SavedData.initialize(getApplicationContext());
    }

    /**
     * Called whenever the play button is clicked.
     *
     * @param  view  The view that was clicked.  This is unused.
     */
    public void playGameButtonClicked(View view) {
        Intent i = new Intent(this, GameActivity.class);
        startActivity(i);
    }

    /**
     * Called whenever the high scores button is clicked.
     *
     * @param  view  The view that was clicked.  This is unused.
     */
    public void viewScoresButtonClicked(View view) {
        Intent i = new Intent(this, ScoresActivity.class);
        startActivity(i);
    }
}
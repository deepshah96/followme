package com.example.deepshah.followme;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class ScoresActivity extends Activity {

    /**
     * Fields
     */
    private TextView[] nameTextViews = new TextView[SavedData.MAXSCORES];
    private TextView[] scoreTextViews = new TextView[SavedData.MAXSCORES];

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
        setContentView(R.layout.activity_scores);

        for (int i = 0; i < SavedData.MAXSCORES; ++i) {
            int nameId = getResources().getIdentifier("high_score_name" + i, "id", getPackageName());
            nameTextViews[i] = (TextView) findViewById(nameId);

            int scoreId = getResources().getIdentifier("high_score" + i, "id", getPackageName());
            scoreTextViews[i] = (TextView) findViewById(scoreId);
        }

        ArrayList<NameValuePair<Integer>> scores = SavedData.getScores();
        for (int i = 0; i < scores.size(); ++i) {
            NameValuePair<Integer> pair = scores.get(i);
            nameTextViews[i].setText(pair.getName());
            scoreTextViews[i].setText(pair.getValue().toString());
        }
    }

    /**
     * Called whenever the clear button is clicked.
     *
     * @param  view  The view that was clicked.  This is unused.
     */
    public void clearScoresButtonClicked(View view) {
        SavedData.clearScores();

        for (int i = 0; i < SavedData.MAXSCORES; ++i) {
            nameTextViews[i].setText("");
            scoreTextViews[i].setText("");
        }
    }
}

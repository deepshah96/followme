package com.example.deepshah.followme;

/**
 * Created by Deep Shah on 17/11/2016.
 */

import android.content.Context;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Reads and writes persisted data and provides access to it
 * via public, static methods.
 */
public class SavedData {
    /**
     * Fields
     */
    public static final int MAXSCORES = 10;
    private static String FILENAME = "HighScores";
    private static ArrayList<NameValuePair<Integer>> scores = new ArrayList<NameValuePair<Integer>>();
    private static String lastPlayer = "Player";
    private static Context context;

    /**
     * Reads the persisted data.
     *
     * @param _context    The application's context - used for reading and writing data files
     */
    public static void initialize(Context _context) {
        try {
            context = _context;
            FileInputStream inputFile = context.openFileInput(FILENAME);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputFile));

            lastPlayer = reader.readLine();

            String line = "";
            String name = "";
            int value = 0;
            for (int i = 0; i < MAXSCORES; ++i) {
                line = reader.readLine();
                if (null == line) {
                    break;
                }

                String[] tokens = line.split(",");
                if (2 != tokens.length) {
                    break;
                }

                name = tokens[0];
                value = Integer.parseInt(tokens[1]);
                scores.add(new NameValuePair<Integer>(name, value));
            }
        }
        catch (IOException ex) {
        }
    }

    /**
     * Determines if the passed-in score is higher than one in the
     * high scores list.
     *
     * @param score    The score to check if it is a high score
     *
     * @return   True if the score is a high score, false otherwise
     */
    public static Boolean isHighScore(int score) {
        if (scores.size() < MAXSCORES) {
            return true;
        }

        int lowestHighScore = scores.get(scores.size() - 1).getValue();
        if  (score > lowestHighScore) {
            return true;
        }

        return false;
    }

    /**
     * Saves the provided high score data, if the passed-in score
     * is a high score.
     *
     * @param name     The player's name
     * @param score    The player's score
     */
    public static void saveScore(String name, int score) {
        if (!isHighScore(score)) {
            return;
        }

        if (scores.size() == MAXSCORES) {
            scores.remove(scores.size() - 1);
        }

        lastPlayer = name;

        scores.add(new NameValuePair<Integer>(name, score));
        Collections.sort(scores, Collections.reverseOrder());

        writeScoresFile();
    }

    /**
     * Clears all high score data.
     */
    public static void clearScores() {
        scores.clear();
        writeScoresFile();
    }

    /**
     * Gets the last player name entered.
     *
     * @return    The last player name entered
     */
    public static String getLastPlayer() {
        return lastPlayer;
    }

    /**
     * Gets the high score data.
     *
     * @return   A collection of high scores, sorted in descending order,
     *           and the names of the players that achieved them.
     */
    public static ArrayList<NameValuePair<Integer>> getScores() {
        return scores;
    }

    /**
     * Writes the high score data to a file in internal memory.
     */
    private static void writeScoresFile() {
        try {
            FileOutputStream outputFile = context.openFileOutput(FILENAME, Context.MODE_PRIVATE);
            PrintStream printer = new PrintStream(outputFile);

            printer.println(lastPlayer);
            NameValuePair<Integer> pair = null;
            for (int i = 0; i < scores.size(); ++i) {
                pair = scores.get(i);
                printer.println(pair.getName() + "," + pair.getValue().toString());
            }

            printer.close();
        }
        catch (FileNotFoundException ex) {
        }
    }
}


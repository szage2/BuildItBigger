package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.jokedisplayer.JokeActivity;

import java.util.concurrent.ExecutionException;


public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void tellJoke(View view) throws ExecutionException, InterruptedException {

        // Retrieve jokes
        String jokeString;
        jokeString = new EndpointsAsyncTask().execute(this).get();
        // In case we have joke
        if (jokeString != null) {
            // launch Joke Activity
            startJokeActivity(jokeString);
        }
    }

    public void startJokeActivity(String joke) {
        // Create an intent that starts Joke Activity
        Intent jokeIntent = new Intent(this, JokeActivity.class);
        // Pass a joke to intent
        jokeIntent.putExtra("joke", joke);
        // start the activity
        startActivity(jokeIntent);
    }
}

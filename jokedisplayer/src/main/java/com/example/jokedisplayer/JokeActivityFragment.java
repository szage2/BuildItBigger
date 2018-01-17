package com.example.jokedisplayer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * Displays the joke received from MainActivity
 */
public class JokeActivityFragment extends Fragment {

    public JokeActivityFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_joke_activity, container, false);
        // Get the intent with it's extra
        Intent intent = getActivity().getIntent();
        String jokeString = intent.getStringExtra("joke");
        // Find the view
        TextView jokeTextView = rootView.findViewById(R.id.joke_text);
        // If the joke exist
        if (jokeString != null && jokeString.length() != 0) {
            // Set the text on the view
            jokeTextView.setText(jokeString);
        }

        // Inflate the layout for this fragment
        return rootView;
    }
}

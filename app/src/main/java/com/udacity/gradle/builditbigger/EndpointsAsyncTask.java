package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Button;

import com.example.jokedisplayer.JokeActivity;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;


import java.io.IOException;


/**
 * Created by szage on 2017. 12. 15..
 */

public class EndpointsAsyncTask extends AsyncTask<Context, Void, String>{
    private static MyApi myApiService = null;
    private Context context;

    public EndpointsAsyncTask() {
    }

    @Override
    protected String doInBackground(Context... contexts) {
        if (myApiService == null) {
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> request) throws IOException {
                            request.setDisableGZipContent(true);
                        }
                    });

            myApiService = builder.build();
        }
        context = contexts[0];

        try {
            return myApiService.getJoke().execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        if (result != null) {

            //JokeActivity jokeActivity = new JokeActivity();
            //Button

            //onJokeReceived(result);
        }
        //Intent intent = new Intent(context, JokeActivity.class);
        //intent.putExtra("message", result);
        //context.startActivity(intent);
        //super.onPostExecute(s);
    }



    public static String onJokeReceived(String jokeString) {
        return jokeString;
    }
}

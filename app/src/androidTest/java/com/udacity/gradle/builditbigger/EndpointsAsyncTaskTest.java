package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.test.mock.MockContext;
import android.util.Log;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static org.junit.Assert.*;

/**
 * Test that the EndpointsAsyncTask successfully retrieves a non-empty string
 */
@RunWith(AndroidJUnit4.class)
public class EndpointsAsyncTaskTest {

    private String testJoke;
    private EndpointsAsyncTask endpointsAsyncTask = new EndpointsAsyncTask();
    private Context mContext;

    public EndpointsAsyncTaskTest() {
        super();
    }

    @Before
    public void setup() {
        // Get the context
        mContext = InstrumentationRegistry.getTargetContext();
    }

    @Test
    public void testJokeValue() {
        // Try to run Async task and get the desired joke
        try {
            testJoke = endpointsAsyncTask.execute(mContext).get(60, TimeUnit.SECONDS);
            // Handling different error cases
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException ex) {
            ex.printStackTrace();
        } catch (TimeoutException exception) {
            exception.printStackTrace();
        }
        // Test that the joke is not null
        assertNotNull(testJoke);
        // And the joke string's length is larger than 0
        assertTrue("test passed", testJoke.length() > 0);
        // Also checking if there's an issue with backend
        assertTrue(testJoke.contains("connect timed out"));
    }
}
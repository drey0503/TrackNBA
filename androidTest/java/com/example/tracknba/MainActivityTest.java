package com.example.tracknba;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.pressMenuKey;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.test.espresso.Espresso;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;



public class MainActivityTest {
    @Rule
    public ActivityScenarioRule<MainActivity> LogOutTestRule = new ActivityScenarioRule<MainActivity>(MainActivity.class);

    @Test
    public void LogOutTest() {
//        Espresso.onView(withId(R.id.btnlogout)).perform(click());

    }
}
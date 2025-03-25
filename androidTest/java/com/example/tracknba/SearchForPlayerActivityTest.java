package com.example.tracknba;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

import androidx.test.espresso.Espresso;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class SearchForPlayerActivityTest {
    @Rule
    public ActivityScenarioRule<SearchForPlayerActivity> SearchTestRule = new ActivityScenarioRule<SearchForPlayerActivity>(SearchForPlayerActivity.class);
   // public ActivityScenarioRule<SearchForPlayerActivity> mActivityTestRule = new ActivityScenarioRule<SearchForPlayerActivity>(SearchForPlayerActivity.class);

    private String removedPlayer = "Lebron James";
    private String addedPlayer = "Kevin Durant";

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testUserInputScenario() throws InterruptedException {
        Espresso.onView(withId(R.id.player_et)).perform(typeText(removedPlayer));

        Espresso.closeSoftKeyboard();

        Espresso.onView(withId(R.id.btn_search)).perform(click());
        Thread.sleep(2000);
//        Espresso.onView(withId(R.id.addBtn)).perform(click());
        Thread.sleep(1000);


        Espresso.onView(withId(R.id.player_et)).perform(replaceText(addedPlayer));
        Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.btn_search)).perform(click());
        Thread.sleep(2000);
//        Espresso.onView(withId(R.id.addBtn)).perform(click());

        Thread.sleep(2000);

//        Espresso.onView(withId(R.id.player_et)).perform(replaceText(removedPlayer));

        Espresso.closeSoftKeyboard();

//        Espresso.onView(withId(R.id.btn_search)).perform(click());
        Thread.sleep(2000);
//        Espresso.onView(withId(R.id.remove)).perform(click());






    }
    @After
    public void tearDown() throws Exception {
    }
}
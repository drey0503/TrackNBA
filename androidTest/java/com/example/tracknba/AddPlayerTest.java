package com.example.tracknba;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.espresso.Espresso;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;


public class AddPlayerTest {
    @Rule
    public ActivityScenarioRule<SearchForPlayerActivity> mActivityTestRule = new ActivityScenarioRule<SearchForPlayerActivity>(SearchForPlayerActivity.class);

    private String playerSearch = "Yuta Watanabe";

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testAddPlayerScenario() throws InterruptedException {
        Espresso.onView(withId(R.id.player_et)).perform(click());
        Espresso.onView(withId(R.id.player_et)).perform(typeText(playerSearch));
        Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.btn_search)).perform(click());
        Thread.sleep(2000);
//        Espresso.onView(withId(R.id.addBtn)).perform(click());
    }

    @After
    public void tearDown() throws Exception {

    }
}

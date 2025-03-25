package com.example.tracknba;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.espresso.Espresso;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class AddTeamTest {
    @Rule
    public ActivityScenarioRule<SearchForTeamActivity> mActivityTestRule = new ActivityScenarioRule<SearchForTeamActivity>(SearchForTeamActivity.class);

    private String teamSearch = "The Michael Jordans";
    private String teamSearch2 = "Chicago Bulls";

    @Before
    public void setUp() throws Exception {

    }
/*
In this test, the first search should not work because it isn't found in the database.
Second search with 'Chicago Bulls' should work and bring up API data for the team.
 */

    @Test
    public void testAddPlayerScenario() throws InterruptedException {
        Espresso.onView(withId(R.id.team_et)).perform(click());
        Espresso.onView(withId(R.id.team_et)).perform(typeText(teamSearch));
        Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.btn_fet)).perform(click());
        Thread.sleep(2000);

        Espresso.onView(withId(R.id.team_et)).perform(click());
        Espresso.onView(withId(R.id.team_et)).perform(replaceText(teamSearch2));
        Espresso.closeSoftKeyboard();

        Espresso.onView(withId(R.id.btn_fet)).perform(click());
        Thread.sleep(2000);
//        Espresso.onView(withId(R.id.addBtn)).perform(click());
    }

    @After
    public void tearDown() throws Exception {

    }
}

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

public class ChatActivityTest {
    @Rule
    public ActivityScenarioRule<ChatActivity> ChatTestRule = new ActivityScenarioRule<ChatActivity>(ChatActivity.class);
    // public ActivityScenarioRule<SearchForPlayerActivity> mActivityTestRule = new ActivityScenarioRule<SearchForPlayerActivity>(SearchForPlayerActivity.class);

    private String chattext = "Hello";

    @Before
    public void setUp() throws Exception {
    }
   /* @Test
    public void MessageTest (){
        Espresso.onView(withId(R.id.message)).perform(typeText(chattext));
        Espresso.onView(withId(R.id.button_message)).perform(click());
    }*/

}

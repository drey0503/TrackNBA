package com.example.tracknba;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.pressMenuKey;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import android.view.Menu;

import androidx.test.espresso.Espresso;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class NewUserActivityTest {

    @Rule
    public ActivityScenarioRule<RegisterActivity> mActivityTestRule = new ActivityScenarioRule<RegisterActivity>(RegisterActivity.class);
    //  public ActivityScenarioRule<MainActivity> lt = new ActivityScenarioRule<LoginActivity>(LoginActivity.class);
    //    ActivityTestRule<LoginActivity> mActivityTestRule = new ActivityTestRule<MainActivity>(LoginActivity.class);

    private String nEmail = "xyz@gmail.com";
    private String nUser = "Rahul";
    private String nPassword = "Password";

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testUserLoginScenario() {
        Espresso.onView(withId(R.id.register_user)).perform(typeText(nUser));

        Espresso.closeSoftKeyboard();

        Espresso.onView(withId(R.id.register_email)).perform(typeText(nEmail));

        Espresso.closeSoftKeyboard();

        Espresso.onView(withId(R.id.register_password)).perform(typeText(nPassword));

        Espresso.closeSoftKeyboard();

        Espresso.onView(withId(R.id.register)).perform(click());


        //Espresso.onView(withId(R.id.home)).perform(click());
        //Espresso.onView(withId(R.id.chat)).perform(click());


        // Espresso.onView(withId(R.id.chat)).perform(click());

        // Espresso.onView(withId(R.menu.menu)).perform(click());

    }
}

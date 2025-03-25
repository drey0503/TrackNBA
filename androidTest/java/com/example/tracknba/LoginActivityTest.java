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

public class LoginActivityTest {

    @Rule
    public ActivityScenarioRule<LoginActivity> mActivityTestRule = new ActivityScenarioRule<LoginActivity>(LoginActivity.class);
  //  public ActivityScenarioRule<MainActivity> lt = new ActivityScenarioRule<LoginActivity>(LoginActivity.class);
    //    ActivityTestRule<LoginActivity> mActivityTestRule = new ActivityTestRule<MainActivity>(LoginActivity.class);

    private String nUser = "jakekrawczyk@yahoo.com";
    private String nPassword = "Password";

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testUserLoginScenario() {
        Espresso.onView(withId(R.id.login_email)).perform(typeText(nUser));

        Espresso.closeSoftKeyboard();

        Espresso.onView(withId(R.id.login_password)).perform(typeText(nPassword));

        Espresso.closeSoftKeyboard();

        Espresso.onView(withId(R.id.login)).perform(click());


        //Espresso.onView(withId(R.id.home)).perform(click());
        //Espresso.onView(withId(R.id.chat)).perform(click());


        // Espresso.onView(withId(R.id.chat)).perform(click());

       // Espresso.onView(withId(R.menu.menu)).perform(click());

    }
   /* @Rule
    public ActivityScenarioRule<MainActivity> LogOutTestRule = new ActivityScenarioRule<MainActivity>(MainActivity.class);

    @Test
    public void LogOutTest(){
        Espresso.onView(withId(R.id.btnlogout)).perform(click());
    }*/



    /*
    @Rule
    public ActivityScenarioRule<MainActivity> LogOutTest = new ActivityScenarioRule<MainActivity>(MainActivity.class);

    @Before
    public void setUp1() throws Exception {

    }


    @Test
    public void testFeed (){
         Espresso.onView(withId(R.id.btnlogout)).perform(click());
    }


*/
    @After
    public void tearDown() throws Exception {

    }

}

//public class LoginActivityTest {
//
//    public LoginActivity loginActivity;
//
//    @Test
//    public void testLogin() {
//
//        getInstrumentation().runOnMainSync(new Runnable() {
////            EditText pass=  loginActivity.findViewById(R.id.login_password);
//
//            public void run() {
//                EditText email;
//                email = loginActivity.findViewById(R.id.login_email);
//
//                email.setText("email");
////                pass.setText("pass");
////                Button loginBtn = loginActivity.findViewById(R.id.login);
////                loginBtn.performClick();
//
//                assertEquals(email.toString(), "email");
////                try{
////                    run();
////                }
////                catch (NullPointerException e){
////                    throw e;
////                }
//            }
//
//
//
//        });
//    }
//
//
//
// }


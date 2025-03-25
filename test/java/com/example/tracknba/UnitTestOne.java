package com.example.tracknba;
import static org.junit.Assert.assertEquals;

import android.content.Context;
import android.content.Intent;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import org.checkerframework.checker.units.qual.Current;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class UnitTestOne {
    public LoginActivity loginActivity;
    public RegisterActivity registerActivity;
    public MainActivity mainActivity;


    @Test
    public void testRegister(){
        try {

            Button rButton = loginActivity.findViewById(R.id.button_register);
            rButton.performClick();
            assertEquals(this,this.registerActivity);
            if(this.loginActivity.equals(this.registerActivity)){

                System.out.println("Hello");
            }
            else {
                System.out.println("Bye");
            }
        }
        catch (NullPointerException e)
        {
            System.out.println("Caught");
        }

//        assertEquals(this, this.registerActivity);
    }
    @Test
    public void testEmailText(){
        try{
            EditText email =  loginActivity.findViewById(R.id.login_email);
            email.setText("Email");
            Button loginBtn = loginActivity.findViewById(R.id.login);
            loginBtn.performClick();
            System.out.println("Error");
        }
        catch (NullPointerException e){
            System.out.println("Caught");
        }

    }

}

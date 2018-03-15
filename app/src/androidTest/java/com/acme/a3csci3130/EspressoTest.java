package com.acme.a3csci3130;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.google.firebase.database.FirebaseDatabase;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertEquals;



/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class EspressoTest {
    Context appContext = InstrumentationRegistry.getTargetContext();
    MyApplicationData appData = (MyApplicationData) appContext.getApplicationContext();


    @Rule
    public ActivityTestRule<MainActivity> main =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void create() throws Exception {
        MainActivity m = main.getActivity();
        //REMOVE TEST VALUE TO AVOID DUPLICATES
        appData.firebaseDBInstance = FirebaseDatabase.getInstance();
        appData.firebaseReference = appData.firebaseDBInstance.getReference("Businesses");
        appData.firebaseReference.removeValue();

        int count = m.getCount();

        onView(withId(R.id.submitButton)).perform(click());
        onView(withId(R.id.name)).perform(clearText()).perform(typeText("BName"));
        onView(withId(R.id.bNum)).perform(clearText()).perform(typeText("999999999"));
        onView(withId(R.id.address)).perform(clearText()).perform(typeText("17 address lane"));
        onView(withId(R.id.province)).perform(clearText()).perform(typeText("NS"));
        onView(withId(R.id.bType)).perform(click());
        onData(allOf(is(instanceOf(String.class)))).atPosition(2).perform(click());
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.submitButton)).perform(click());


        assertEquals(count + 1, m.getCount());
    }

    @Test
    public void read() throws Exception {
        MainActivity m = main.getActivity();
        //REMOVE TEST VALUE TO AVOID DUPLICATES
        appData.firebaseDBInstance = FirebaseDatabase.getInstance();
        appData.firebaseReference = appData.firebaseDBInstance.getReference("Businesses");
        appData.firebaseReference.child("999999999").removeValue();
        //ADD VALUE TO READ
        onView(withId(R.id.submitButton)).perform(click());
        onView(withId(R.id.name)).perform(clearText()).perform(typeText("BName"));
        onView(withId(R.id.bNum)).perform(clearText()).perform(typeText("999999999"));
        onView(withId(R.id.address)).perform(clearText()).perform(typeText("17 address lane"));
        onView(withId(R.id.province)).perform(clearText()).perform(typeText("NS"));
        onView(withId(R.id.bType)).perform(click());
        onData(allOf(is(instanceOf(String.class)))).atPosition(2).perform(click());
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.submitButton)).perform(click());
        //READ
        boolean populated = false;
        if(m.getCount() > 0)
            populated = true;
        assertEquals(true, populated);
    }

    @Test
    public void update() throws Exception {
        MainActivity m = main.getActivity();

        //REMOVE TEST VALUE TO AVOID DUPLICATES
        appData.firebaseDBInstance = FirebaseDatabase.getInstance();
        appData.firebaseReference = appData.firebaseDBInstance.getReference("Businesses");
        appData.firebaseReference.child("999999999").removeValue();
        //ADD VALUE TO READ
        onView(withId(R.id.submitButton)).perform(click());
        onView(withId(R.id.name)).perform(clearText()).perform(typeText("BName"));
        onView(withId(R.id.bNum)).perform(clearText()).perform(typeText("999999999"));
        onView(withId(R.id.address)).perform(clearText()).perform(typeText("17 address lane"));
        onView(withId(R.id.province)).perform(clearText()).perform(typeText("NS"));
        onView(withId(R.id.bType)).perform(click());
        onData(allOf(is(instanceOf(String.class)))).atPosition(2).perform(click());
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.submitButton)).perform(click());

        int count = m.getCount();

        //UPDATE VALUE
        onData(anything()).atPosition(0).perform(click());
        onView(withId(R.id.name)).perform(clearText()).perform(typeText("BName2"));
        onView(withId(R.id.bNum)).perform(clearText()).perform(typeText("000000001"));
        onView(withId(R.id.address)).perform(clearText()).perform(typeText("42 address lane"));
        onView(withId(R.id.province)).perform(clearText()).perform(typeText("NB"));
        onView(withId(R.id.bType)).perform(click());
        onData(allOf(is(instanceOf(String.class)))).atPosition(3).perform(click());
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.updateButton)).perform(click());

        onView(withId(R.id.listView)).check(matches(isDisplayed()));
        assertEquals(count, m.getCount());
    }

    @Test
    public void delete() throws Exception {
        MainActivity m = main.getActivity();
        //REMOVE TEST VALUE TO AVOID DUPLICATES
        appData.firebaseDBInstance = FirebaseDatabase.getInstance();
        appData.firebaseReference = appData.firebaseDBInstance.getReference("Businesses");
        appData.firebaseReference.child("999999999").removeValue();
        //ADD VALUE TO DELETE
        onView(withId(R.id.submitButton)).perform(click());
        onView(withId(R.id.name)).perform(clearText()).perform(typeText("BName"));
        onView(withId(R.id.bNum)).perform(clearText()).perform(typeText("999999999"));
        onView(withId(R.id.address)).perform(clearText()).perform(typeText("17 address lane"));
        onView(withId(R.id.province)).perform(clearText()).perform(typeText("NS"));
        onView(withId(R.id.bType)).perform(click());
        onData(allOf(is(instanceOf(String.class)))).atPosition(2).perform(click());
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.submitButton)).perform(click());
        //COUNT BEFORE DELETE
        int count = m.getCount();
        //DELETE
        onData(anything()).atPosition(0).perform(click());
        onView(withId(R.id.deleteButton)).perform(click());

        assertEquals(count - 1, m.getCount());
    }

}

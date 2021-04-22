package com.example.androiddev;


import androidx.test.espresso.contrib.PickerActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class ThanksActivityTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityScenarioRule
            = new ActivityScenarioRule<MainActivity>(MainActivity.class);


    @Test
    public void canGoBackAndFormIsEmpty() throws InterruptedException {
        onView(withId(R.id.names)).perform(typeText("Dylan Eastridge"));
        onView(withId(R.id.email)).perform(typeText("theillusionofthegift@gmail.com"));
        onView(withId(R.id.username)).perform(typeText("Illusion"));

        onView(withId(R.id.btPickDate)).perform((click()));

        onView(withClassName(Matchers.equalTo(android.widget.DatePicker.class.getName()))).perform(PickerActions.setDate(1999 , 4, 21));
        onView(withText("OK")).perform(click());

        onView(withId(R.id.button2)).perform((click()));

        Thread.sleep(1500);

        onView(withId(R.id.button3)).perform(click());



        onView(withId(R.id.names)).check(matches(withText("")));
        onView(withId(R.id.email)).check(matches(withText("")));
        onView(withId(R.id.username)).check(matches(withText("")));
    }
}
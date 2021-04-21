package com.example.androiddev;


import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class ThanksActivityTest {

    @Rule
    public ActivityScenarioRule<ThanksActivity> activityScenarioRule
            = new ActivityScenarioRule<ThanksActivity>(ThanksActivity.class);


    @Test
    public void canEnterNameAndSignUp() {
        onView(withId(R.id.text)).check(matches(withText("Thanks for signing up Blank!")));
    }
}
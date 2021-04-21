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
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
@RunWith(AndroidJUnit4.class)
public class FormActivityTest {


    @Rule
    public ActivityScenarioRule<ForumActivity> activityScenarioRule
            = new ActivityScenarioRule<>(ForumActivity.class);

    @Test
    public void canEnterNameAndSignUp() {
        onView(withId(R.id.names)).perform(typeText("Dylan Eastridge"));
        onView(withId(R.id.email)).perform(typeText("theillusionofthegift@gmail.com"));
        onView(withId(R.id.username)).perform(typeText("Illusion"));
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(1999, 04, 20));

        onView(withId(R.id.button2)).perform(click());

        onView(withId(R.id.text));

    }
}
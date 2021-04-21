package com.example.androiddev;


import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static java.util.regex.Pattern.matches;
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {


    @Rule
    public ActivityScenarioRule<ForumActivity> activityScenarioRule
            = new ActivityScenarioRule<>(ForumActivity.class);

    @Test
    public void canEnterNameAndLoginWithRotate() {
        onView(withId(R.id.username)).perform(typeText("Illusion"));

        onView(withId(R.id.button2)).perform(click());

        onView(withId(R.id.text)).check(matches(withText("Thanks for signing up Illusion!")));

        TestUtils.rotateScreen(activityTestRule.getActivity());

        // Make sure text view still has Welcome Kyle McNutt after screen rotation
        onView(withId(R.id.textView))
                .check(matches(withText("Welcome: Kyle McNutt")));
    }

    private Object withText(String s) {
    }
}
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
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasErrorText;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {


    @Rule
    public ActivityScenarioRule<MainActivity> activityScenarioRule
            = new ActivityScenarioRule<MainActivity>(MainActivity.class);

    @Test
    public void canEnterNameAndSignUp() throws InterruptedException {
        onView(withId(R.id.name)).perform(typeText("Dylan Eastridge"));
        onView(withId(R.id.email)).perform(typeText("theillusionofthegift@gmail.com"));
        onView(withId(R.id.username)).perform(typeText("Illusion"));
        onView(withId(R.id.bio)).perform(typeText("A very good programmer"));
        onView(withId(R.id.occupation)).perform(typeText("Software Engineer"));

        onView(withId(R.id.btPickDate)).perform(scrollTo(),(click()));

        onView(withClassName(Matchers.equalTo(android.widget.DatePicker.class.getName()))).perform(PickerActions.setDate(1999 , 4, 21));
        onView(withText("OK")).perform(click());

        onView(withId(R.id.button2)).perform(scrollTo(), (click()));

        onView(withId(R.id.profile)).check(matches(withText("Your Profile!")));
        onView(withId(R.id.name)).check(matches(withText("Dylan Eastridge")));
        onView(withId(R.id.bio)).check(matches(withText("A very good programmer")));
        onView(withId(R.id.occupation)).check(matches(withText("Software Engineer")));
        onView(withId(R.id.age)).check((matches(withText(": 22 Years Old."))));

    }


    @Test
    public void dataResistOrientationChange() {
        onView(withId(R.id.name)).perform(typeText("Dylan Eastridge"));
        onView(withId(R.id.email)).perform(typeText("theillusionofthegift@gmail.com"));
        onView(withId(R.id.username)).perform(typeText("Illusion"));
        onView(withId(R.id.bio)).perform(typeText("A very good programmer"));
        onView(withId(R.id.occupation)).perform(typeText("Software Engineer"));

        TestUtils.rotateScreen(TestUtils.getActivity(activityScenarioRule));


        onView(withId(R.id.name)).check(matches(withText("Dylan Eastridge")));
        onView(withId(R.id.bio)).check(matches(withText("A very good programmer")));
        onView(withId(R.id.occupation)).check(matches(withText("Software Engineer")));
        onView(withId(R.id.username)).check(matches(withText("Illusion")));
        onView(withId(R.id.email)).check(matches(withText("theillusionofthegift@gmail.com")));


    }

    @Test
    public void checkValidEmail() {
        onView(withId(R.id.email)).perform(typeText("theillusionofthegift"));

        onView(withId(R.id.btPickDate)).perform(scrollTo(),(click()));

        onView(withClassName(Matchers.equalTo(android.widget.DatePicker.class.getName()))).perform(PickerActions.setDate(1999 , 4, 21));
        onView(withText("OK")).perform(click());

        onView(withId(R.id.button2)).perform(scrollTo(), (click()));

        onView(allOf(withId(R.id.email), hasErrorText("Email not valid!")));

    }

    @Test
    public void checkEmailNotBlank() {
        onView(withId(R.id.email)).perform(typeText(""));

        onView(withId(R.id.btPickDate)).perform(scrollTo(),(click()));

        onView(withClassName(Matchers.equalTo(android.widget.DatePicker.class.getName()))).perform(PickerActions.setDate(1999 , 4, 21));
        onView(withText("OK")).perform(click());

        onView(withId(R.id.button2)).perform(scrollTo(), (click()));

        onView(allOf(withId(R.id.email), hasErrorText("Email not valid!")));

    }

    @Test
    public void checkUsernameNotBlank() {
        onView(withId(R.id.name)).perform(typeText("Dylan"));
        onView(withId(R.id.email)).perform(typeText("theillusionofthegift@gmail.com"));
        onView(withId(R.id.username)).perform(typeText(""));

        onView(withId(R.id.btPickDate)).perform(scrollTo(),(click()));

        onView(withClassName(Matchers.equalTo(android.widget.DatePicker.class.getName()))).perform(PickerActions.setDate(1999 , 4, 21));
        onView(withText("OK")).perform(click());

        onView(withId(R.id.button2)).perform(scrollTo(), (click()));

        onView(allOf(withId(R.id.username), hasErrorText("Cannot Be Blank!")));

    }

    @Test
    public void checkUnderEighteen() {

        onView(withId(R.id.btPickDate)).perform(scrollTo(),(click()));

        onView(withClassName(Matchers.equalTo(android.widget.DatePicker.class.getName()))).perform(PickerActions.setDate(2004 , 4, 21));
        onView(withText("OK")).perform(click());

        onView(allOf(withId(R.id.tvDate), hasErrorText("Must Be Older Than 18!")));

    }
}
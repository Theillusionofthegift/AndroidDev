package com.example.androiddev;


import android.view.Gravity;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.contrib.DrawerActions;
import androidx.test.espresso.contrib.NavigationViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.DrawerMatchers.isClosed;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.example.androiddev.TestUtils.waitFor;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

@RunWith(AndroidJUnit4.class)
public class SecondActivityTest {

    @Rule
    public ActivityScenarioRule rule = new ActivityScenarioRule<>(SecondActivity.class);



    @Test
    public void checkNavDrawerSettings() {
        // Open Drawer to click on navigation.
        onView(withId(R.id.drawer_layout))
                .check(matches(isClosed(Gravity.LEFT))) // Left Drawer should be closed.
                .perform(DrawerActions.open()); // Open Drawer

        onView(withId(R.id.nav_view))
                .perform(NavigationViewActions.navigateTo(R.id.nav_settings));
        Espresso.pressBack();
        onView(isRoot()).perform(waitFor(1000));

        onView(withId(R.id.time_spinner)).perform(click());
        onView(withText("1:00")).perform(click());
        onView(withId(R.id.max_dist)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("10")))
                .perform(click());
        onView(withId(R.id.gender)).perform(click());
        onView(withText("Female")).perform(click());
        onView(withId(R.id.checkbox1)).perform(click());
        onView(withId(R.id.minAge)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("20")))
                .perform(click());
        onView(withId(R.id.maxAge)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("25")))
                .perform(click());

        onView(withId(R.id.save_settings)).perform(click());
        onView(withText("Settings Saved")).inRoot(new TestUtils.ToastMatcher())
                .check(matches(isDisplayed()));
    }

    @Test
    public void checkNavDrawerProfile() {
        // Open Drawer to click on navigation.
        onView(withId(R.id.drawer_layout))
                .check(matches(isClosed(Gravity.LEFT))) // Left Drawer should be closed.
                .perform(DrawerActions.open()); // Open Drawer

        onView(withId(R.id.nav_view))
                .perform(NavigationViewActions.navigateTo(R.id.nav_profile));

        onView(allOf(withId(R.id.profile))).check((matches(withText("Your Profile!"))));
    }

    @Test
    public void dataResistOrientationChange() throws InterruptedException {

        onView(withId(R.id.drawer_layout))
                .check(matches(isClosed(Gravity.LEFT))) // Left Drawer should be closed.
                .perform(DrawerActions.open()); // Open Drawer

        onView(withId(R.id.nav_view))
                .perform(NavigationViewActions.navigateTo(R.id.nav_profile));

        onView(withId(R.id.drawer_layout))
                .perform(DrawerActions.close()); // Close Drawer

        TestUtils.rotateScreen(TestUtils.getActivity(rule));

        Thread.sleep(1500);

        onView(allOf(withId(R.id.profile))).check((matches(withText("Your Profile!"))));

    }


}
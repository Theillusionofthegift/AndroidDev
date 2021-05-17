package com.example.androiddev;


import android.os.Bundle;
import android.view.Gravity;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.contrib.DrawerActions;
import androidx.test.espresso.contrib.NavigationViewActions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

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

@RunWith(AndroidJUnit4.class)
public class SecondActivityTest {
    Bundle b = new Bundle();

    @Rule
    public ActivityScenarioRule rule = new ActivityScenarioRule<>(SecondActivity.class);


    @Test
    public void testMatchesLikeToast() {
        onView(isRoot()).perform(waitFor(1000));
        // Open Drawer to click on navigation.
        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open()); // Open Drawer
        onView(isRoot()).perform(waitFor(1000));
        onView(withText(R.string.matches))
                .perform(click()); // Select nav button in nav drawer
        onView(isRoot()).perform(waitFor(1000));
        Espresso.pressBack();
        onView(isRoot()).perform(waitFor(1000));
        onView(withId(R.id.recycler_view)).perform(RecyclerViewActions.scrollToPosition(1));
        onView(withId(R.id.recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(1, new TestUtils.ClickOnLikeButton()));
        onView(withId(R.id.recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(1, new TestUtils.ClickOnLikeButton()));

        onView(withText(R.string.mssage)).inRoot(new TestUtils.ToastMatcher())
                .check(matches(isDisplayed()));
    }

//    @Test
//    public void testRecyclerViewScroll() {
//        onView(isRoot()).perform(waitFor(1000));
//        // Open Drawer to click on navigation.
//        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open()); // Open Drawer
//        onView(isRoot()).perform(waitFor(1000));
//        onView(withText(R.string.matches))
//                .perform(click()); // Select nav button in nav drawer
//        onView(isRoot()).perform(waitFor(1000));
//        onView(withId(R.id.drawer_layout)).perform(DrawerActions.close()); // Open Drawer
//
//        onView(allOf(withId(R.id.recycler_view))).perform(RecyclerViewActions.actionOnItemAtPosition(4, new TestUtils.ClickOnLikeButton()));
//        onView(allOf(withId(R.id.recycler_view))).perform(RecyclerViewActions.actionOnItemAtPosition(4, new TestUtils.ClickOnLikeButton()));
//
//        onView(withText(R.string.mssage2)).inRoot(new TestUtils.ToastMatcher())
//                .check(matches(isDisplayed()));
//    }


    @Test
    public void checkNavDrawerSettings() {
        // Open Drawer to click on navigation.
        onView(withId(R.id.drawer_layout))
                .check(matches(isClosed(Gravity.LEFT))) // Left Drawer should be closed.
                .perform(DrawerActions.open()); // Open Drawer

        onView(withId(R.id.nav_view))
                .perform(NavigationViewActions.navigateTo(R.id.nav_settings));

        onView(allOf(withId(R.id.settings_txt))).check((matches(withText("This is where the settings will go"))));
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
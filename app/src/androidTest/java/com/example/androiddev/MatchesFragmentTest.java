package com.example.androiddev;


import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.SystemClock;

import androidx.test.InstrumentationRegistry;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.contrib.DrawerActions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;


import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;

@RunWith(AndroidJUnit4.class)
public class MatchesFragmentTest {

    @Rule
    public ActivityScenarioRule rule = new ActivityScenarioRule<>(SecondActivity.class);

    @Before
    public void init(){

        getInstrumentation().getUiAutomation().executeShellCommand("appops set " +
                InstrumentationRegistry.getInstrumentation().getTargetContext().getPackageName() +
                " android:mock_location allow");
        LocationManager lm = (LocationManager) TestUtils.getActivity(rule).getSystemService(Context.LOCATION_SERVICE);

        String mocLocationProvider = "mock";
        if(lm.isProviderEnabled(mocLocationProvider)){
            lm.removeTestProvider(mocLocationProvider);
        }

        lm.addTestProvider(mocLocationProvider, false, false,false,false,true,
                true, true, 0 ,5);
        lm.setTestProviderEnabled(mocLocationProvider,true);

        Location loc = new Location(mocLocationProvider);
        Location mockLocation = new Location(mocLocationProvider); // a string
        mockLocation.setLatitude(47.694021);  // double
        mockLocation.setLongitude(-122.341440);
        mockLocation.setAltitude(loc.getAltitude());
        mockLocation.setTime(System.currentTimeMillis());
        mockLocation.setAccuracy(1);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            mockLocation.setElapsedRealtimeNanos(SystemClock.elapsedRealtimeNanos());
        }
        lm.setTestProviderLocation( mocLocationProvider, mockLocation);
    }


    @Test
    public void testMatchesLikeToast() throws InterruptedException {

        // Open Drawer to click on navigation.
        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open()); // Open Drawer

        onView(withText(R.string.matches))
                .perform(click()); // Select nav button in nav drawer

        Espresso.pressBack();
        Thread.sleep(2000);
        onView(withId(R.id.recycler_view)).perform(RecyclerViewActions.scrollToPosition(1));
        onView(withId(R.id.recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(1, new TestUtils.ClickOnLikeButton()));
        onView(withId(R.id.recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(1, new TestUtils.ClickOnLikeButton()));

        onView(withText(R.string.mssage)).inRoot(new TestUtils.ToastMatcher())
                .check(matches(isDisplayed()));
    }

}
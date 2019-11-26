package com.siadous.thomas.mynews;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.intent.Intents;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;


import com.siadous.thomas.mynews.activities.MainActivity;
import com.siadous.thomas.mynews.activities.NotificationActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.Espresso.openContextualActionModeOverflowMenu;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertEquals;

import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class UITest {

    @Rule
    public ActivityTestRule mActivityRule = new ActivityTestRule<>(
            MainActivity.class);


    @Test
    public void displayWebView() {

        onView(withId(R.id.rv_list))
                .perform(RecyclerViewActions.actionOnItemAtPosition(3, click()));

    }


    @Test
    public void displayNotificationActivity() {

        onView(withId(R.id.menu_activity_main_params))
                .perform(click());
        onView(withId(R.id.switch1)).check(matches(isDisplayed()));

    }

    @Test
    public void displaySearchActivity() {

        onView(withId(R.id.menu_activity_main_search))
                .perform(click());
        onView(withId(R.id.search_button)).check(matches(isDisplayed()));

    }

    @Test
    public void editTextOfSearchActivityEmptyNoResultActivityLaunched() {
        onView(withId(R.id.menu_activity_main_search))
                .perform(click());
        onView(withId(R.id.edit_text)).check(matches(withText("")));
        onView(withId(R.id.search_button)).perform(click());
        onView(withId(R.id.search_button)).check(matches(isDisplayed()));
    }

}




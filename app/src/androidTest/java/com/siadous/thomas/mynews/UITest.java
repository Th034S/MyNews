package com.siadous.thomas.mynews;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.siadous.thomas.mynews.activities.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

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

    @Test
    public void displayWebView() {

        onView(allOf(isDisplayed(), withId(R.id.rv_list)))
                .perform(RecyclerViewActions.actionOnItemAtPosition(2, click()));
        onView(withId(R.id.web_view_details)).check(matches(isDisplayed()));

    }

    @Test
    public void editTextOfSearchActivityFilledResultActivityLaunched() {
        onView(withId(R.id.menu_activity_main_search))
                .perform(click());
        onView(withId(R.id.edit_text)).perform(typeText("Donald Trump"), closeSoftKeyboard());
        onView(withId(R.id.search_button)).perform(click());
        onView(withId(R.id.rv_result_list)).check(matches(isDisplayed()));
    }

    @Test
    public void prefNotEmptyWhenEditTextFilledWithSwitchEnabledInNotificationActivity() {
        onView(withId(R.id.menu_activity_main_params))
                .perform(click());
        onView(withId(R.id.edit_text)).perform(typeText("Paris"), closeSoftKeyboard());
        onView(withId(R.id.switch1)).perform(click());
        Espresso.pressBackUnconditionally();
        onView(withId(R.id.menu_activity_main_params))
                .perform(click());
        onView(withId(R.id.edit_text)).check(matches(withText("Paris")));
    }

    @Test
    public void changeFragmentFromViewPager() {
        onView(withId(R.id.activity_main_viewpager)).perform(swipeLeft());

        //onView(withId(R.id.activity_main_tabs)).check(matches(withText("Most Popular")));

    }



}




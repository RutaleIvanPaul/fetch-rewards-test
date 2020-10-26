package com.testapp.fetchrewards

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.testapp.fetchrewards.ui.MainActivity
import junit.framework.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class LoadUIInstrumentedTest {
    @Rule
    @JvmField
    var mainActivity: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    @Test
    fun checkDisplay() {
        onView(withId(R.id.recycler_view)).check(matches(isDisplayed()))
    }

    @Test
    fun checkCount(){
        onView(withId(R.id.recycler_view)).perform(ViewActions.click())
        Thread.sleep(10000)
        assertTrue(
            "Count should be greater than 1.",
            mainActivity.activity.findViewById<RecyclerView>(R.id.recycler_view).adapter?.itemCount!! > 1
        )
    }
}
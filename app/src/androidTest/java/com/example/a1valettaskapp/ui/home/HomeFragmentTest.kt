package com.example.a1valettaskapp.ui.home

import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.a1valettaskapp.MainActivity
import com.example.a1valettaskapp.R
import com.example.a1valettaskapp.adapter.DeviceAdapter
import com.example.a1valettaskapp.presentation.DeviceViewModel
import kotlinx.coroutines.*
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith



@RunWith(AndroidJUnit4::class)
class HomeFragmentTest{


    @get:Rule
    var activityRule = ActivityScenarioRule(MainActivity::class.java)


    val LISTITEM = 5


    @Test
    fun testingtoConfirmViews() {

        onView(withId(R.id.welcome_text_marquee)).check(matches(isDisplayed()))

        onView(withId(R.id.rv_devices)).check(matches(isDisplayed()))

        onView(withId(R.id.search_device)).check(matches(isDisplayed()))

    }

    @Test
    fun test_onDeviceClicked_isDeviceDetailFragmentVisible() {
        onView(withId(R.id.rv_devices)).perform(
                RecyclerViewActions.actionOnItemAtPosition<DeviceAdapter.ImageViewHolder>(
                    LISTITEM,
                    ViewActions.click()
                )
            )

        onView(withId(R.id.device_os)).check(matches(isDisplayed()))
        onView(withId(R.id.device_size)).check(matches(isDisplayed()))
        onView(withId(R.id.device_price)).check(matches(isDisplayed()))
    }


    @Test
    fun test_backNavigation_toDeviceListFragment() {
        onView(withId(R.id.rv_devices))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<DeviceAdapter.ImageViewHolder>(
                    LISTITEM,
                    ViewActions.click()
                )
            )

        // if this line passes, it tells us we are now in Details Fragment
        onView(withId(R.id.device_price)).check(matches(isDisplayed()))

        //Now we use expresso to press and navigate back to Device List Fragment
        Espresso.pressBack()

        //Once we see the previous recylerview screen we know we are back and the test worked
        onView(withId(R.id.rv_devices)).check(matches(isDisplayed()))
    }


}

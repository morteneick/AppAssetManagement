package com.example.asset_management.mainHub

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack

import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId

import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.asset_management.R
import org.junit.Test
import org.junit.runner.RunWith
import java.util.regex.Pattern.matches

@RunWith(AndroidJUnit4ClassRunner::class)
class MainHubActivityTest {

//    @get: Rule
//    val activityRule = ActivityScenarioRule.launch(mainHubActivity::class.java)

    @Test
    fun testIsActivityInView() {
        val activityScenario = ActivityScenario.launch(MainHubActivity::class.java)

        onView(withId(R.id.mainhub)).check(ViewAssertions.matches(isDisplayed()))
    }

    @Test
    fun testIsFragmentInView() {
        val activityScenario = ActivityScenario.launch(MainHubActivity::class.java)

        onView(withId(R.id.fragmentMainhub)).check(ViewAssertions.matches(isDisplayed()))
    }

    @Test
    fun testButtonInView() {
        val activityScenario = ActivityScenario.launch(MainHubActivity::class.java)

        onView(withId(R.id.btnAdd)).check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.btnHistory)).check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.btnInventory)).check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.btnScan)).check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.btnSearch)).check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.btnSettings)).check(ViewAssertions.matches(isDisplayed()))
    }

    @Test
    fun testNavigationAddDevice() {

        val activityScenario = ActivityScenario.launch(MainHubActivity::class.java)

        onView(withId(R.id.btnAdd)).perform(click())
        onView(withId(R.id.addDevice)).check(ViewAssertions.matches(isDisplayed()))
    }
    @Test
    fun testNavigationAddDeviceBack() {

        val activityScenario = ActivityScenario.launch(MainHubActivity::class.java)

        onView(withId(R.id.btnAdd)).perform(click())
        onView(withId(R.id.addDevice)).check(ViewAssertions.matches(isDisplayed()))
        pressBack()
        onView(withId(R.id.mainhub)).check(ViewAssertions.matches(isDisplayed()))
    }
    @Test
    fun testNavigationShowDevice() {

        val activityScenario = ActivityScenario.launch(MainHubActivity::class.java)

        onView(withId(R.id.btnInventory)).perform(click())
        onView(withId(R.id.deviceList)).check(ViewAssertions.matches(isDisplayed()))

    }
    @Test
    fun testNavigationShowSettings() {

        val activityScenario = ActivityScenario.launch(MainHubActivity::class.java)

        onView(withId(R.id.btnSettings)).perform(click())
        onView(withId(R.id.activity_settings)).check(ViewAssertions.matches(isDisplayed()))

    }

    @Test
    fun testNavigationShowDeviceBack() {

        val activityScenario = ActivityScenario.launch(MainHubActivity::class.java)

        onView(withId(R.id.btnInventory)).perform(click())
        onView(withId(R.id.deviceList)).check(ViewAssertions.matches(isDisplayed()))
        pressBack()
        onView(withId(R.id.mainhub)).check(ViewAssertions.matches(isDisplayed()))

    }

}


package com.example.asset_management.deviceCard.ui.reservation

import android.service.autofill.Validators.not
import android.widget.DatePicker
import android.widget.TextView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.PickerActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.asset_management.R
import org.hamcrest.Matchers
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith



@RunWith(AndroidJUnit4ClassRunner::class)
class ReservationActivityTest {
    @Before
    @Throws(Exception::class)
    fun setUp() {
    }

    @Test
    fun testIsActivityInView() {
        val activityScenario = ActivityScenario
                .launch(ReservationActivity::class.java)
        Espresso.onView(ViewMatchers.withId(R.id.activity_reservation))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun testButtonInView() {
        val activityScenario = ActivityScenario
                .launch(ReservationActivity::class.java)

        Espresso.onView(ViewMatchers.withId(R.id.btnStartDate)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.btnEndDate)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.btnReserve)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun testDatePickerButton() {
        val activityScenario = ActivityScenario
                .launch(ReservationActivity::class.java)

        Espresso.onView(ViewMatchers.withId(R.id.btnStartDate)).perform(ViewActions.click())
        onView(withClassName(Matchers.equalTo(DatePicker::class.java.name))).perform(PickerActions
                .setDate(2020, 8, 20))
        onView(withId(android.R.id.button1)).perform(click());
        Espresso.onView(ViewMatchers.withId(R.id.btnEndDate)).perform(ViewActions.click())
        onView(withClassName(Matchers.equalTo(DatePicker::class.java.name))).perform(PickerActions
                .setDate(2020, 8, 20))
        onView(withId(android.R.id.button1)).perform(click());
    }

    @Test
    fun testReserveSuccess() {
        val activityScenario = ActivityScenario
                .launch(ReservationActivity::class.java)

        Espresso.onView(ViewMatchers.withId(R.id.btnStartDate)).perform(ViewActions.click())
        onView(withClassName(Matchers.equalTo(DatePicker::class.java.name))).perform(PickerActions
                .setDate(2020, 8, 20))
        onView(withId(android.R.id.button1)).perform(click());
        Espresso.onView(ViewMatchers.withId(R.id.btnEndDate)).perform(ViewActions.click())
        onView(withClassName(Matchers.equalTo(DatePicker::class.java.name))).perform(PickerActions
                .setDate(2020, 8, 21))
        onView(withId(android.R.id.button1)).perform(click());
    }

    @Test
    fun testReserveFailed() {
        val activityScenario = ActivityScenario
                .launch(ReservationActivity::class.java)


        Espresso.onView(ViewMatchers.withId(R.id.btnStartDate)).perform(ViewActions.click())
        onView(withClassName(Matchers.equalTo(DatePicker::class.java.name))).perform(PickerActions
                .setDate(2020, 8, 20))
        onView(withId(android.R.id.button1)).perform(click());
        Espresso.onView(ViewMatchers.withId(R.id.btnEndDate)).perform(ViewActions.click())
        onView(withClassName(Matchers.equalTo(DatePicker::class.java.name))).perform(PickerActions
                .setDate(2020, 8, 19))
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.textReservationStart)).check(matches(withText("")));
        onView(withId(R.id.textReservationEnd)).check(matches(withText("")));
    }



}
package com.example.asset_management.deviceCard.ui.card

import android.widget.DatePicker
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.PickerActions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import com.example.asset_management.R
import com.example.asset_management.deviceCard.ui.reservation.ReservationActivity
import com.example.asset_management.login.LoginActivity
import com.example.asset_management.recycleViewDeviceList.Device
import com.example.asset_management.recycleViewDeviceList.DeviceAdapter
import org.hamcrest.Matchers
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class CardFragmentTestAndroid {

    @Test
    fun testOnDateSetGuarantee() {
        val activityScenario =
                ActivityScenario.launch(LoginActivity::class.java)
        Espresso.onView(ViewMatchers.withId(R.id.editEmailLogin)).perform(ViewActions.clearText(),
                ViewActions.typeText("d.dziersan@dallmann-bau.de"));
        Espresso.onView(ViewMatchers.withId(R.id.editPasswordLogin)).perform(ViewActions.clearText(),
                ViewActions.typeText("easy"));
        Espresso.onView(ViewMatchers.withText("Einloggen")).perform(ViewActions.click());
        Thread.sleep(1500);
        Espresso.onView(ViewMatchers.withId(R.id.mainhub)).check(ViewAssertions
                .matches(ViewMatchers.isDisplayed()))

        Espresso.onView(ViewMatchers.withId(R.id.btnInventory)).perform(ViewActions.click())

        Espresso.onView(ViewMatchers.withId(R.id.devices)).perform(RecyclerViewActions
                .actionOnItemAtPosition<DeviceAdapter.ViewHolder>(0, ViewActions.click()))

        Espresso.openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getInstrumentation()
                .getTargetContext());
        Espresso.onView(ViewMatchers.withText("Bearbeiten")).perform(ViewActions.click());

        Espresso.onView(ViewMatchers.withId(R.id.btnGuarantee)).perform(scrollTo(), click());
        Espresso.onView(ViewMatchers.withClassName(Matchers.equalTo(DatePicker::class.java.name)))
                .perform(PickerActions
                .setDate(2020, 8, 20))
        Espresso.onView(ViewMatchers.withId(android.R.id.button1)).perform(click());
        Espresso.onView(ViewMatchers.withId(R.id.editGuarantee))
                .check(ViewAssertions.matches(ViewMatchers.withText("2020-08-20")));
    }

    @Test
    fun testOnDateSetTuev() {
        val activityScenario =
                ActivityScenario.launch(LoginActivity::class.java)
        Espresso.onView(ViewMatchers.withId(R.id.editEmailLogin)).perform(ViewActions.clearText(),
                ViewActions.typeText("d.dziersan@dallmann-bau.de"));
        Espresso.onView(ViewMatchers.withId(R.id.editPasswordLogin)).perform(ViewActions.clearText(),
                ViewActions.typeText("easy"));
        Espresso.onView(ViewMatchers.withText("Einloggen")).perform(ViewActions.click());
        Thread.sleep(1500);
        Espresso.onView(ViewMatchers.withId(R.id.mainhub)).check(ViewAssertions
                .matches(ViewMatchers.isDisplayed()))

        Espresso.onView(ViewMatchers.withId(R.id.btnInventory)).perform(ViewActions.click())

        Espresso.onView(ViewMatchers.withId(R.id.devices)).perform(RecyclerViewActions
                .actionOnItemAtPosition<DeviceAdapter.ViewHolder>(0, ViewActions.click()))

        Espresso.openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getInstrumentation()
                .getTargetContext());
        Espresso.onView(ViewMatchers.withText("Bearbeiten")).perform(ViewActions.click());

        Espresso.onView(ViewMatchers.withId(R.id.btnTuev)).perform(scrollTo(), click());
        Espresso.onView(ViewMatchers.withClassName(Matchers.equalTo(DatePicker::class.java.name)))
                .perform(PickerActions
                        .setDate(2020, 8, 20))
        Espresso.onView(ViewMatchers.withId(android.R.id.button1)).perform(click());
        Espresso.onView(ViewMatchers.withId(R.id.editTuev))
                .check(ViewAssertions.matches(ViewMatchers.withText("2020-08-20")));
    }

    @Test
    fun testOnDateSetUvv() {
        val activityScenario =
                ActivityScenario.launch(LoginActivity::class.java)
        Espresso.onView(ViewMatchers.withId(R.id.editEmailLogin)).perform(ViewActions.clearText(),
                ViewActions.typeText("d.dziersan@dallmann-bau.de"));
        Espresso.onView(ViewMatchers.withId(R.id.editPasswordLogin)).perform(ViewActions.clearText(),
                ViewActions.typeText("easy"));
        Espresso.onView(ViewMatchers.withText("Einloggen")).perform(ViewActions.click());
        Thread.sleep(1500);
        Espresso.onView(ViewMatchers.withId(R.id.mainhub)).check(ViewAssertions
                .matches(ViewMatchers.isDisplayed()))

        Espresso.onView(ViewMatchers.withId(R.id.btnInventory)).perform(ViewActions.click())

        Espresso.onView(ViewMatchers.withId(R.id.devices)).perform(RecyclerViewActions
                .actionOnItemAtPosition<DeviceAdapter.ViewHolder>(0, ViewActions.click()))

        Espresso.openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getInstrumentation()
                .getTargetContext());
        Espresso.onView(ViewMatchers.withText("Bearbeiten")).perform(ViewActions.click());

        Espresso.onView(ViewMatchers.withId(R.id.btnUvv)).perform(scrollTo(), click());
        Espresso.onView(ViewMatchers.withClassName(Matchers.equalTo(DatePicker::class.java.name)))
                .perform(PickerActions
                        .setDate(2020, 8, 20))
        Espresso.onView(ViewMatchers.withId(android.R.id.button1)).perform(click());
        Espresso.onView(ViewMatchers.withId(R.id.editUvv))
                .check(ViewAssertions.matches(ViewMatchers.withText("2020-08-20")));
    }

    @Test
    fun testOnDateSetLastRepair() {
        val activityScenario =
                ActivityScenario.launch(LoginActivity::class.java)
        Espresso.onView(ViewMatchers.withId(R.id.editEmailLogin)).perform(ViewActions.clearText(),
                ViewActions.typeText("d.dziersan@dallmann-bau.de"));
        Espresso.onView(ViewMatchers.withId(R.id.editPasswordLogin)).perform(ViewActions.clearText(),
                ViewActions.typeText("easy"));
        Espresso.onView(ViewMatchers.withText("Einloggen")).perform(ViewActions.click());
        Thread.sleep(1500);
        Espresso.onView(ViewMatchers.withId(R.id.mainhub)).check(ViewAssertions
                .matches(ViewMatchers.isDisplayed()))

        Espresso.onView(ViewMatchers.withId(R.id.btnInventory)).perform(ViewActions.click())

        Espresso.onView(ViewMatchers.withId(R.id.devices)).perform(RecyclerViewActions
                .actionOnItemAtPosition<DeviceAdapter.ViewHolder>(0, ViewActions.click()))

        Espresso.openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getInstrumentation()
                .getTargetContext());
        Espresso.onView(ViewMatchers.withText("Bearbeiten")).perform(ViewActions.click());

        Espresso.onView(ViewMatchers.withId(R.id.btnRepair)).perform(scrollTo(), click());
        Espresso.onView(ViewMatchers.withClassName(Matchers.equalTo(DatePicker::class.java.name)))
                .perform(PickerActions
                        .setDate(2020, 8, 20))
        Espresso.onView(ViewMatchers.withId(android.R.id.button1)).perform(click());
        Espresso.onView(ViewMatchers.withId(R.id.editRepair))
                .check(ViewAssertions.matches(ViewMatchers.withText("2020-08-20")));
    }

}
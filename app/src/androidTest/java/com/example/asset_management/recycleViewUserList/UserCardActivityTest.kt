package com.example.asset_management.recycleViewUserList

import android.os.SystemClock
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import com.example.asset_management.R
import com.example.asset_management.deviceCard.SwitchEditable
import com.example.asset_management.login.LoginActivity
import com.example.asset_management.recycleViewDeviceList.DeviceAdapter
import org.hamcrest.CoreMatchers.anything
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class UserCardActivityTest{

    @Test
    fun testIsActivityInView() {

        val activityScenario =
                ActivityScenario.launch(LoginActivity::class.java)
        Espresso.onView(ViewMatchers.withId(R.id.editEmailLogin)).perform(ViewActions.clearText(),
                ViewActions.typeText("d.dziersan@dallmann-bau.de"));
        Espresso.onView(ViewMatchers.withId(R.id.editPasswordLogin)).perform(ViewActions.clearText(),
                ViewActions.typeText("easy"));
        Espresso.onView(ViewMatchers.withText("Einloggen")).perform(ViewActions.click());
        SystemClock.sleep(1500);
        Espresso.onView(ViewMatchers.withId(R.id.btnSettings)).perform(ViewActions.click())

        onData(anything()).inAdapterView(withId(R.id.listOptions)).atPosition(0)
                .perform(click());
        Espresso.onView(ViewMatchers.withId(R.id.users)).perform(RecyclerViewActions
                .actionOnItemAtPosition<DeviceAdapter.ViewHolder>(0, ViewActions.click()))
        Espresso.onView(ViewMatchers.withId(R.id.activity_user_card)).check(ViewAssertions
                .matches(ViewMatchers.isDisplayed()))
    }


    @Test
    fun testToolbar() {
        val activityScenario =
                ActivityScenario.launch(LoginActivity::class.java)
        Espresso.onView(ViewMatchers.withId(R.id.editEmailLogin)).perform(ViewActions.clearText(),
                ViewActions.typeText("d.dziersan@dallmann-bau.de"));
        Espresso.onView(ViewMatchers.withId(R.id.editPasswordLogin)).perform(ViewActions.clearText(),
                ViewActions.typeText("easy"));
        Espresso.onView(ViewMatchers.withText("Einloggen")).perform(ViewActions.click());
        SystemClock.sleep(1500);
        Espresso.onView(ViewMatchers.withId(R.id.btnSettings)).perform(ViewActions.click())

        onData(anything()).inAdapterView(withId(R.id.listOptions)).atPosition(0)
                .perform(click());

        Espresso.onView(ViewMatchers.withId(R.id.users)).perform(RecyclerViewActions
                .actionOnItemAtPosition<DeviceAdapter.ViewHolder>(0, ViewActions.click()))

        Espresso.openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getInstrumentation()
                .getTargetContext());


    }
    @Test
    fun testToolbarSettings() {

        val activityScenario =
                ActivityScenario.launch(LoginActivity::class.java)
        Espresso.onView(ViewMatchers.withId(R.id.editEmailLogin)).perform(ViewActions.clearText(),
                ViewActions.typeText("d.dziersan@dallmann-bau.de"));
        Espresso.onView(ViewMatchers.withId(R.id.editPasswordLogin)).perform(ViewActions.clearText(),
                ViewActions.typeText("easy"));
        Espresso.onView(ViewMatchers.withText("Einloggen")).perform(ViewActions.click());
        SystemClock.sleep(1500);
        Espresso.onView(ViewMatchers.withId(R.id.btnSettings)).perform(ViewActions.click())

        onData(anything()).inAdapterView(withId(R.id.listOptions)).atPosition(0)
                .perform(click());
        Espresso.onView(ViewMatchers.withId(R.id.users)).perform(RecyclerViewActions
                .actionOnItemAtPosition<DeviceAdapter.ViewHolder>(0, ViewActions.click()))

        Espresso.openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getInstrumentation()
                .getTargetContext());
        Espresso.onView(ViewMatchers.withText("Bearbeiten")).perform(click());
        Assert.assertTrue(SwitchEditable.isClicked(InstrumentationRegistry.getInstrumentation()
                .getTargetContext()))

    }
    @Test
    fun testToolbarDelete() {

        val activityScenario =
                ActivityScenario.launch(LoginActivity::class.java)
        Espresso.onView(ViewMatchers.withId(R.id.editEmailLogin)).perform(ViewActions.clearText(),
                ViewActions.typeText("d.dziersan@dallmann-bau.de"));
        Espresso.onView(ViewMatchers.withId(R.id.editPasswordLogin)).perform(ViewActions.clearText(),
                ViewActions.typeText("easy"));
        Espresso.onView(ViewMatchers.withText("Einloggen")).perform(ViewActions.click());
        SystemClock.sleep(1500);
        Espresso.onView(ViewMatchers.withId(R.id.btnSettings)).perform(ViewActions.click())

        onData(anything()).inAdapterView(withId(R.id.listOptions)).atPosition(0)
                .perform(click());
        Espresso.onView(ViewMatchers.withId(R.id.users)).perform(RecyclerViewActions
                .actionOnItemAtPosition<DeviceAdapter.ViewHolder>(0, ViewActions.click()))

        Espresso.openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getInstrumentation()
                .getTargetContext());
        Espresso.onView(ViewMatchers.withText("Löschen")).perform(click());

        Espresso.onView(ViewMatchers.withText("OK")).check(ViewAssertions.matches(ViewMatchers
                .isDisplayed()));
    }
}
package com.example.asset_management.recycleViewDeviceList

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import com.example.asset_management.R
import com.example.asset_management.login.LoginActivity
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class DeviceRecycleActivityTest{


    @Test
    fun testRecycleViewActivityInView() {

        val activityScenario =
                ActivityScenario.launch(LoginActivity::class.java)
        Espresso.onView(ViewMatchers.withId(R.id.editEmailLogin)).perform(ViewActions.clearText(),
                ViewActions.typeText("d.dziersan@dallmann-bau.de"));
        Espresso.onView(ViewMatchers.withId(R.id.editPasswordLogin)).perform(ViewActions.clearText(),
                ViewActions.typeText("easy"));
        Espresso.onView(ViewMatchers.withText("Einloggen")).perform(ViewActions.click());
        Thread.sleep(1500);

        Espresso.onView(ViewMatchers.withId(R.id.btnInventory)).perform(ViewActions.click())
        onView(withId(R.id.devices))
                .check(matches(isDisplayed()));

    }

    @Test
    fun testRecycleViewToolbarInView() {

        val activityScenario =
                ActivityScenario.launch(LoginActivity::class.java)
        Espresso.onView(ViewMatchers.withId(R.id.editEmailLogin)).perform(ViewActions.clearText(),
                ViewActions.typeText("d.dziersan@dallmann-bau.de"));
        Espresso.onView(ViewMatchers.withId(R.id.editPasswordLogin)).perform(ViewActions.clearText(),
                ViewActions.typeText("easy"));
        Espresso.onView(ViewMatchers.withText("Einloggen")).perform(ViewActions.click());
        Thread.sleep(1500);

        Espresso.onView(ViewMatchers.withId(R.id.btnInventory)).perform(ViewActions.click())
        onView(withId(R.id.devices))
                .check(matches(isDisplayed()));

        Espresso.openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getInstrumentation()
                .getTargetContext());
        onView(withText("Filtern")).check(ViewAssertions.matches(isDisplayed()))
        onView(withText("Liste neu laden")).check(ViewAssertions.matches(isDisplayed()))
    }


}
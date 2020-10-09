package com.example.asset_management.recycleViewDeviceList

import android.os.SystemClock
import android.widget.DatePicker
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.PickerActions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import com.example.asset_management.R
import com.example.asset_management.deviceCard.SwitchEditable
import com.example.asset_management.login.LoginActivity
import org.hamcrest.CoreMatchers
import org.hamcrest.Matchers
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4ClassRunner::class)
class FilterDeviceListActivityTest{

    @Test
    fun testActivityStart() {

        val activityScenario =
                ActivityScenario.launch(LoginActivity::class.java)
        Espresso.onView(ViewMatchers.withId(R.id.editEmailLogin)).perform(ViewActions.clearText(),
                ViewActions.typeText("d.dziersan@dallmann-bau.de"));
        Espresso.onView(ViewMatchers.withId(R.id.editPasswordLogin)).perform(ViewActions.clearText(),
                ViewActions.typeText("easy"));
        Espresso.onView(ViewMatchers.withText("Einloggen")).perform(ViewActions.click());
        SystemClock.sleep(1500);
        Espresso.onView(ViewMatchers.withId(R.id.btnInventory)).perform(ViewActions.click())

        Espresso.openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getInstrumentation()
                .getTargetContext());
        Espresso.onView(ViewMatchers.withText("Filtern")).perform(ViewActions.click());

        Espresso.onView(ViewMatchers.withId(R.id.activity_filter_deviceList))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
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
        Espresso.onView(ViewMatchers.withId(R.id.btnInventory)).perform(ViewActions.click())

        Espresso.openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getInstrumentation()
                .getTargetContext());
        Espresso.onView(ViewMatchers.withText("Filtern")).perform(ViewActions.click());

        Espresso.onView(ViewMatchers.withId(R.id.btnStartDateTuev)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withClassName(Matchers.equalTo(DatePicker::class.java.name)))
                .perform(PickerActions
                .setDate(2020, 8, 20))
        Espresso.onView(ViewMatchers.withId(android.R.id.button1)).perform(ViewActions.click());
        Espresso.onView(ViewMatchers.withId(R.id.btnEndDateTuev)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withClassName(Matchers.equalTo(DatePicker::class.java.name)))
                .perform(PickerActions
                .setDate(2020, 8, 21))
        Espresso.onView(ViewMatchers.withId(android.R.id.button1)).perform(ViewActions.click());

        Espresso.onView(ViewMatchers.withId(R.id.textTuevStart))
                .check(ViewAssertions.matches(ViewMatchers
                        .withText("Thursday, August 20, 2020")));

        Espresso.onView(ViewMatchers.withId(R.id.textTuevEnd))
                .check(ViewAssertions.matches(ViewMatchers
                        .withText("Friday, August 21, 2020")));

    }


}
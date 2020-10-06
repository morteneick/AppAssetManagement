package com.example.asset_management.addDevice

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.asset_management.R
import net.bytebuddy.matcher.ElementMatchers.`is`
import org.hamcrest.CoreMatchers.containsString
import org.hamcrest.CoreMatchers.instanceOf
import org.junit.Test
import org.junit.runner.RunWith
import java.util.EnumSet.allOf
import java.util.regex.Pattern.matches


@RunWith(AndroidJUnit4ClassRunner::class)
class AddDeviceFragmentTest{


    @Test
    fun testInsertIntoEditText() {
        val activityScenario = ActivityScenario.launch(AddDeviceActivity::class.java)

        onView(withId(R.id.editInventoryNumber)).perform(scrollTo(), clearText(),
                typeText("1"));
        onView(withId(R.id.editSerialnumber)).perform(scrollTo(), clearText(),
                typeText("2"));
        onView(withId(R.id.editManufacturer)).perform(scrollTo(), clearText(),
                typeText("3"));
        onView(withId(R.id.editModel)).perform(scrollTo(), clearText(),
                typeText("4"));
        onView(withId(R.id.editCategory)).perform(scrollTo(), clearText(),
                typeText("5"));
        onView(withId(R.id.editName)).perform(scrollTo(), clearText(),
                typeText("6"));
        onView(withId(R.id.editStreet)).perform(scrollTo(), clearText(),
                typeText("7"));
        onView(withId(R.id.editPostcode)).perform(scrollTo(), clearText(),
                typeText("8"))
        onView(withId(R.id.editCity)).perform(scrollTo(), clearText(),
                typeText("9"));
        onView(withId(R.id.editRepairNotes)).perform(scrollTo(), clearText(),
                typeText("10"));
        onView(withId(R.id.editNotes)).perform(scrollTo(), clearText(),
                typeText("11"));

        onView(withId(R.id.editStatus)).perform(scrollTo(), click())
        onView(withText("Verfügbar")).perform(click())

    }


    @Test
    fun testInsertIntoEditTextAndSave() {
        val activityScenario = ActivityScenario.launch(AddDeviceActivity::class.java)

        onView(withId(R.id.editInventoryNumber)).perform(scrollTo(), clearText(),
                typeText("1"));
        onView(withId(R.id.editSerialnumber)).perform(scrollTo(), clearText(),
                typeText("2"));
        onView(withId(R.id.editManufacturer)).perform(scrollTo(), clearText(),
                typeText("3"));
        onView(withId(R.id.editModel)).perform(scrollTo(), clearText(),
                typeText("4"));
        onView(withId(R.id.editCategory)).perform(scrollTo(), clearText(),
                typeText("5"));
        onView(withId(R.id.editName)).perform(scrollTo(), clearText(),
                typeText("6"));
        onView(withId(R.id.editStreet)).perform(scrollTo(), clearText(),
                typeText("7"));
        onView(withId(R.id.editPostcode)).perform(scrollTo(), clearText(),
                typeText("8"))
        onView(withId(R.id.editCity)).perform(scrollTo(), clearText(),
                typeText("9"));
        onView(withId(R.id.editRepairNotes)).perform(scrollTo(), clearText(),
                typeText("10"));
        onView(withId(R.id.editNotes)).perform(scrollTo(), clearText(),
                typeText("11"));

        onView(withId(R.id.editStatus)).perform(scrollTo(), click())
        onView(withText("Verfügbar")).perform(click())

        onView(withId(R.id.btnSave)).perform(scrollTo(), click())

    }
}
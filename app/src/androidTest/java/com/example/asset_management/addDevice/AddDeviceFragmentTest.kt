package com.example.asset_management.addDevice
import androidx.fragment.app.Fragment
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.asset_management.R
import org.junit.Test
import org.junit.runner.RunWith



@RunWith(AndroidJUnit4ClassRunner::class)
class AddDeviceFragmentTest{


    @Test
    fun testInsertIntoEditText() {
        val activityScenario = ActivityScenario.launch(AddDeviceActivity::class.java)

        onView(withId(R.id.editInventoryNumber)).perform(clearText(),
                typeText("1"));
//        onView(withId(R.id.editSerialNumber)).perform(clearText(),
//                typeText("2"));
        onView(withId(R.id.editManufacturer)).perform(clearText(),
                typeText("3"));
        onView(withId(R.id.editModel)).perform(clearText(),
                typeText("4"));
//        onView(withId(R.id.editCategorie)).perform(clearText(),
//                typeText("5"));
        onView(withId(R.id.editStatus)).perform(clearText(),
                typeText("6"));
    }
    @Test
    fun testInsertIntoEditTextAndSave() {
        val activityScenario = ActivityScenario.launch(AddDeviceActivity::class.java)

        onView(withId(R.id.editInventoryNumber)).perform(clearText(),
                typeText("1"));
//        onView(withId(R.id.editSerialNumber)).perform(clearText(),
//                typeText("2"));
        onView(withId(R.id.editManufacturer)).perform(clearText(),
                typeText("3"));
        onView(withId(R.id.editModel)).perform(clearText(),
                typeText("4"));
//        onView(withId(R.id.editCategorie)).perform(clearText(),
//                typeText("5"));
        onView(withId(R.id.editStatus)).perform(clearText(),
                typeText("6"), closeSoftKeyboard());
//        onView(withId(R.id.button4)).perform(click())
    }

//    @Test
//    fun testNewInstance() {
//
//        fun fragment() = AddDeviceFragment.newInstance("test", "test1").apply{};
//
//    }

}
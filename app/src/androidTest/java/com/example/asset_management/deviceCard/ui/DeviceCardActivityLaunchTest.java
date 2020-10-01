package com.example.asset_management.deviceCard.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.SmallTest;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import com.example.asset_management.R;
import com.example.asset_management.deviceCard.DeviceCardActivity;
import com.example.asset_management.recycleViewDeviceList.Device;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;

@RunWith(AndroidJUnit4.class)
@SmallTest
public class DeviceCardActivityLaunchTest {

    @Rule
    public ActivityTestRule<DeviceCardActivity> mActivityRule =
            new ActivityTestRule<DeviceCardActivity>(DeviceCardActivity.class) {
                @Override
                protected Intent getActivityIntent() {
                    Context targetContext = getInstrumentation().getTargetContext();
                    Intent result = new Intent(targetContext, DeviceCardActivity.class);
                    Device device = new Device();
                    result.putExtra("Device", device);
                    result.putExtra("isOldVersion", false);
                    return result;
                }
            };


    @Test
    public void shouldShowHelloEarth() {
        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
        onView(withText("Bearbeiten")).perform(click());
        Activity activity = mActivityRule.getActivity();

    }
}






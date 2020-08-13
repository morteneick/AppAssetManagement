package com.example.asset_management.deviceCard;

public class SwitchEditable {

    private boolean isEnabled;

    public SwitchEditable(boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }
}

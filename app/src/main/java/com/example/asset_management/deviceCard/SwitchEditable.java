package com.example.asset_management.deviceCard;
/**
 * SwitchEditable
 * <p>
 *     Version 1.0
 * </p>
 * 20.09.2020
 * AUTHOR: Dominik Dziersan
 */
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

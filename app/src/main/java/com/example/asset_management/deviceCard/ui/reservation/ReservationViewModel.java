package com.example.asset_management.deviceCard.ui.reservation;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
/**
 * ReservationViewModel
 * <p>
 *     Version 1.0
 * </p>
 * 30.08.2020
 * AUTHOR: Dominik Dziersan
 */
public class ReservationViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ReservationViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is notifications TEST");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
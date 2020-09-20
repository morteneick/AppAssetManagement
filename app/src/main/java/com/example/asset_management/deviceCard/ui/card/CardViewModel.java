package com.example.asset_management.deviceCard.ui.card;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
/**
 * CardViewModel
 * <p>
 *     Version 1.0
 * </p>
 * 17.08.2020
 * AUTHOR: Dominik Dziersan
 */
public class CardViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public CardViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
package com.example.themechanger;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class UserTextViewModel extends ViewModel {
    private MutableLiveData<UserText> uiState = new MutableLiveData<>();

    public MutableLiveData<UserText> getUiState() {
        return uiState;
    }

    public void updateState(String value) {
        uiState.setValue(new UserText(value));
    }
}

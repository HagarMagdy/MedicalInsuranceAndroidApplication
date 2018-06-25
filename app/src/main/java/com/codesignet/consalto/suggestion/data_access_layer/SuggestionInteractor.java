package com.codesignet.consalto.suggestion.data_access_layer;

import android.util.Log;

import com.codesignet.consalto.suggestion.pojo.Suggestion;
import com.codesignet.consalto.suggestion.view.SuggestionApiManagerInterface;
import com.codesignet.consalto.suggestion.view.SuggestionInteractorInterface;
import com.codesignet.consalto.suggestion.view.SuggestionPresenterInterface;

/**
 * Created by Mayada Saleh on 6/1/2018.
 */

public class SuggestionInteractor implements SuggestionInteractorInterface {


    private SuggestionPresenterInterface myPresenter;
    private SuggestionApiManagerInterface myApi;

    public SuggestionInteractor(SuggestionPresenterInterface presenter) {
        this.myPresenter = presenter;
        initInteractor();
    }


    @Override
    public void initInteractor() {
        myApi=new SuggestionApiManager(this);
    }

    @Override
    public void sendSuggestionInteractor(Suggestion suggestion) {
        Log.i("suggggg","addressInteracttor"+suggestion.getAddress());
        myApi.receiveSuggestion(suggestion);
    }

    @Override
    public void recieveSuggestionResponseInteractor(Boolean status) {
        myPresenter.recieveSuggestionResponsePresenter(status);
    }


}

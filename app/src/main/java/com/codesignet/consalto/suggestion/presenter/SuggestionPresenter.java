package com.codesignet.consalto.suggestion.presenter;

import android.util.Log;

import com.codesignet.consalto.suggestion.data_access_layer.SuggestionInteractor;
import com.codesignet.consalto.suggestion.pojo.Suggestion;
import com.codesignet.consalto.suggestion.view.SuggestionInteractorInterface;
import com.codesignet.consalto.suggestion.view.SuggestionPresenterInterface;
import com.codesignet.consalto.suggestion.view.SuggestionViewInterface;

/**
 * Created by Mayada Saleh on 6/1/2018.
 */

public class SuggestionPresenter implements SuggestionPresenterInterface {


   private SuggestionViewInterface SuggestionActivity;
   private SuggestionInteractorInterface interactorObject;


    public SuggestionPresenter(SuggestionViewInterface myView) {
        this.SuggestionActivity = myView;
        initPresenter();
    }
    void initPresenter()
    {
        interactorObject=new SuggestionInteractor(this);
        SuggestionActivity.initView();
    }

    @Override
    public void sendSuggestionPresenter(Suggestion suggestion) {
        Log.i("suggggg","addresspresenter"+suggestion.getAddress());

        interactorObject.sendSuggestionInteractor(suggestion);

    }

    @Override
    public void recieveSuggestionResponsePresenter(Boolean status) {
        SuggestionActivity.recieveSuggestionResponseView(status);
        Log.i("status","status is "+status);
    }
}

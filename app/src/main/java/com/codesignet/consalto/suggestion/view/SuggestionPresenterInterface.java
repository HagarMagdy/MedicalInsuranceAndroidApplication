package com.codesignet.consalto.suggestion.view;

import com.codesignet.consalto.suggestion.pojo.Suggestion;

/**
 * Created by Mayada Saleh on 6/1/2018.
 */

public interface SuggestionPresenterInterface {
     void sendSuggestionPresenter(Suggestion suggestion);
     void recieveSuggestionResponsePresenter (Boolean status);
}

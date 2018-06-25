package com.codesignet.consalto.animateFragment.fragmentsView;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.codesignet.consalto.Profile.ui.ProfileActivity;
import com.codesignet.consalto.R;
import com.codesignet.consalto.Settings.ui.SettingsActivity;
import com.codesignet.consalto.contactus.ui.activity.ContactUsActivity;
import com.codesignet.consalto.home.Home.activity.HomeActivity;
import com.codesignet.consalto.suggestion.ui.activity.SuggestionActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class animatedFragmentdown extends Fragment {

   @BindView(R.id.profile) Button profile;
@BindView(R.id.suggestion)Button suggestion;
@BindView(R.id.setting) Button setting;
@BindView(R.id.submitPriscription)Button submitPriscription;
@BindView(R.id.contactus)Button contactus;
@BindView(R.id.home)Button home;


    @OnClick(R.id.contactus)
    public void contactUs(){
        Intent contactUsIntent = new Intent(getView().getContext(), ContactUsActivity.class);
        contactUsIntent.putExtra("typeMail","question");
        startActivity(contactUsIntent);
    }


    @OnClick(R.id.submitPriscription)
    public void submitPrisciption(){
        Intent contactUsIntent = new Intent(getView().getContext(), ContactUsActivity.class);
        contactUsIntent.putExtra("typeMail","accept");
        startActivity(contactUsIntent);
    }

    @OnClick(R.id.profile)
    public void openProfileEmployee(){
        Intent profileIntent = new Intent(getView().getContext(), ProfileActivity.class);
//        profileIntent.putExtra("id",);
        startActivity(profileIntent);
    }

    @OnClick(R.id.home)
    public void openHome(){
        Intent homeIntent = new Intent(getView().getContext(), HomeActivity.class);
       // homeIntent.putExtra("id",1);
        startActivity(homeIntent);
    }


    @OnClick(R.id.setting)
    public void openSettings(){
        Intent settingsIntent = new Intent(getView().getContext(), SettingsActivity.class);
        startActivity(settingsIntent);
    }

    @OnClick(R.id.suggestion)
    public void sendSuggestion(){
        Intent suggestionIntent = new Intent(getView().getContext(),SuggestionActivity.class);
        Bundle suggestionData = new Bundle();
        suggestionData.putInt("employeeId",1);
        suggestionIntent.putExtra("suggestionData",suggestionData);
        startActivity(suggestionIntent);
    }



public void animatedFragmentdown(){

}

public void removeFragmentDown(){
    FragmentManager fm = getActivity()
            .getSupportFragmentManager();
    fm.popBackStack ("downFrag", FragmentManager.POP_BACK_STACK_INCLUSIVE);
}



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_animated_down, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

}

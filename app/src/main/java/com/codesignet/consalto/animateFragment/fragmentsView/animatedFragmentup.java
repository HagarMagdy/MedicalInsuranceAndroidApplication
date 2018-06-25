package com.codesignet.consalto.animateFragment.fragmentsView;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codesignet.consalto.R;



public class animatedFragmentup extends Fragment {

public void animatedFragmentup(){

}
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public void removeFragmentUp(){
        FragmentManager fm = getActivity()
                .getSupportFragmentManager();
        fm.popBackStack ("upFrag", FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_animated_up, container, false);
    }


}

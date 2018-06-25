package com.codesignet.consalto.animateFragment.fragmentsView;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.codesignet.consalto.R;
import com.codesignet.consalto.home.Home.activity.HomeActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class mainFragment extends Fragment {
    boolean appearFlag=false;
   @BindView(R.id.layoutFragDown) LinearLayout layoutFragDown;
   @BindView(R.id.layoutFragUp)LinearLayout layoutFragUp;
  @BindView(R.id.mainFragBtn) Button mainBtn;

  @OnClick(R.id.mainFragBtn)
  public void appearFrags(){
      if(appearFlag==false) {
          animatedFragmentup upFragmentObj = new animatedFragmentup();
          FragmentManager fragmentManager = getFragmentManager();
          android.support.v4.app.FragmentTransaction transaction = fragmentManager.beginTransaction();
          transaction.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_left);
          transaction.add(R.id.layoutFragUp, upFragmentObj, "upFragmentLoad");
          transaction.commit();
          animatedFragmentdown downFragmentObj = new animatedFragmentdown();
          FragmentManager fragmentManager2 = getFragmentManager();
          android.support.v4.app.FragmentTransaction transaction2 = fragmentManager2.beginTransaction();
          transaction2.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_right);
          transaction2.add(R.id.layoutFragDown, downFragmentObj, "downFragmentLoad");
          transaction2.commit();
          appearFlag=true;
      }
      else{
          animatedFragmentup upFragmentObj=new animatedFragmentup();
        FragmentManager fragmentManager= getFragmentManager();
        android.support.v4.app.FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setCustomAnimations(R.anim.slide_in_left,R.anim.slide_out_left);
        transaction.remove(getFragmentManager().findFragmentByTag("upFragmentLoad"));
        transaction.commit();
        animatedFragmentdown downFragmentObj=new animatedFragmentdown();
        FragmentManager fragmentManager2= getFragmentManager();
        android.support.v4.app.FragmentTransaction transaction2 = fragmentManager2.beginTransaction();
        transaction2.setCustomAnimations(R.anim.slide_in_right,R.anim.slide_out_right);
        transaction2.remove(getFragmentManager().findFragmentByTag("downFragmentLoad"));
        transaction2.commit();
        appearFlag=false;
      }
  }

  public void mainFragment(){

  }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this,v);
        return v;
    }


}

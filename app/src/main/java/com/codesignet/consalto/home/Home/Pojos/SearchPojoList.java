package com.codesignet.consalto.home.Home.Pojos;

import com.codesignet.consalto.home.Home.Pojos.SearchPojo;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by hoda.CO on 02/06/2018.
 */

public class SearchPojoList {
    @SerializedName("results")
    ArrayList<SearchPojo> result =new ArrayList<>();

    public ArrayList<SearchPojo> getResult() {
        return result;
    }

    public void setResult(ArrayList<SearchPojo> result) {
        this.result = result;
    }
}

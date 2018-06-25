package com.codesignet.consalto.contactus.data_access_layer;

import android.content.Context;

import com.codesignet.consalto.contactus.pojo.ContactUs;
import com.codesignet.consalto.contactus.view.ContactUsInteractorInterface;
import com.codesignet.consalto.contactus.view.ContactUsLogicDALInterface;
import com.codesignet.consalto.contactus.view.ContactUsPresenterInterface;

/**
 * Created by Aya on 02/06/2018.
 */

public class ContactUsInteractor implements ContactUsInteractorInterface {


    ContactUsPresenterInterface presenterObject;
    ContactUsLogicDALInterface DALObject;
    Context context;
    public ContactUsInteractor(ContactUsPresenterInterface presenterObject,Context context) {
        this.presenterObject= presenterObject;
        this.context=context;
        this.DALObject=new ContactUsLogicDAL(context,this);
    }

    @Override
    public void sendMailInteractor(ContactUs contactUsObject) {
        DALObject.sendMailDAL(contactUsObject);
    }
}

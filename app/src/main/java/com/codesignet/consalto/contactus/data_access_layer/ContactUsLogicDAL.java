package com.codesignet.consalto.contactus.data_access_layer;

import android.content.Context;
import android.os.AsyncTask;

import com.codesignet.consalto.contactus.pojo.ContactUs;
import com.codesignet.consalto.contactus.view.ContactUsInteractorInterface;
import com.codesignet.consalto.contactus.view.ContactUsLogicDALInterface;

/**
 * Created by Aya on 02/06/2018.
 */

public class ContactUsLogicDAL implements ContactUsLogicDALInterface{
    Context context;
    ContactUsInteractorInterface interactor;
    public ContactUsLogicDAL(Context context,ContactUsInteractorInterface interactor) {
        this.interactor = interactor;
        this.context=context;
    }

    @Override
    public void sendMailDAL(ContactUs contactUsObject) {
        new AsyncTask<ContactUs, Void, Void>(){

            @Override
            protected Void doInBackground(ContactUs... contactUsObject) {
                MailService serviceObject = new MailService();
                serviceObject.sendmail(contactUsObject[0]);
                return null;
            }
        }.execute(contactUsObject);
    }
}


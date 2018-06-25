package com.codesignet.consalto.forget_password.data_access_layer;

import android.util.Log;

import com.codesignet.consalto.forget_password.control.ApiRetrofitUtils;
import com.codesignet.consalto.forget_password.pojo.EmailPojo;
import com.codesignet.consalto.forget_password.pojo.ResponseMessage;
import com.codesignet.consalto.forget_password.view.ForgetPasswordApiManagerInterface;
import com.codesignet.consalto.forget_password.view.ForgetPasswordInteractorInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Mayada Saleh on 6/2/2018.
 */

public class ForgetPasswordApiManager implements ForgetPasswordApiManagerInterface {


    private ForgetPasswordInteractorInterface myInteractor;
    Boolean returnResponseStatus=false;

    public ForgetPasswordApiManager(ForgetPasswordInteractorInterface interactor) {
        this.myInteractor = interactor;
    }

    @Override
    public void receiveEmail(EmailPojo emailPojo) {

        Log.i("mayadaaa","emailApi"+emailPojo.getMail());

        Call<ResponseMessage> responseCall = ApiRetrofitUtils.getService().resetPassword(emailPojo);
        responseCall.enqueue(new Callback<ResponseMessage>() {
            @Override
            public void onResponse(Call<ResponseMessage> call, Response<ResponseMessage> response) {
                ResponseMessage responseReturn = response.body();

                //Null Pointer Exception
                Log.i("resss",responseReturn.getMessage().toString());

                returnResponseStatus=responseReturn.getStatus();
                myInteractor.recieveResponseInteractor(returnResponseStatus);
            }
            @Override
            public void onFailure(Call<ResponseMessage> call, Throwable t) {
                Log.i("failure",t.getMessage());
                returnResponseStatus=false;
                myInteractor.recieveResponseInteractor(returnResponseStatus);
            }
        });
    }
}

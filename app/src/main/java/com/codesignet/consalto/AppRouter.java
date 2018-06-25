package com.codesignet.consalto;

import android.content.Context;
import android.content.Intent;

import com.codesignet.consalto.login.ui.activity.LoginActivity;


public class AppRouter {
    public static void NavigateToHomeScreen(Context context) {
        context.startActivity(new Intent(context, LoginActivity.class));
    }
}

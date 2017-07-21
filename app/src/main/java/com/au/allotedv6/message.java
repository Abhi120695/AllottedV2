package com.au.allotedv6;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;

import com.au.allotedv6.core.Chat;
import com.quickblox.auth.QBAuth;
import com.quickblox.auth.model.QBSession;
import com.quickblox.chat.QBChatService;
import com.quickblox.chat.model.QBChatMessage;
import com.quickblox.chat.model.QBDialog;
import com.quickblox.chat.model.QBDialogType;
import com.quickblox.core.QBEntityCallback;
import com.quickblox.core.exception.QBResponseException;
import com.quickblox.core.request.QBRequestGetBuilder;
import com.quickblox.users.QBUsers;
import com.quickblox.users.model.QBUser;

import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPException;

import java.util.ArrayList;
import java.util.Date;


/**
 * Created by Abhishek-Pc on 23-03-2016.
 */
public class message {
    private Chat mChat;
    public void newsession(final View view1, final View view, final DialogInterface builder, final String regno, final String pass) {

        QBChatService chatService = QBChatService.getInstance();
        QBAuth.createSession(new QBEntityCallback<QBSession>() {
            @Override
            public void onSuccess(QBSession session, Bundle params) {
                final QBUser user = new QBUser();
                user.setLogin(regno);
                user.setPassword(pass);
                user.getPassword();
                Log.v("passtest", user.getPassword());

                QBUsers.signUp(user, new QBEntityCallback<QBUser>() {
                    @Override
                    public void onSuccess(QBUser user, Bundle args) {
                        user.setPassword(pass);

                        Snackbar sb = Snackbar.make(view1, "Sign up successful", Snackbar.LENGTH_LONG);
                        sb.show();
                        builder.dismiss();
                        // success
                    }

                    @Override
                    public void onError(QBResponseException error) {
                        Log.v("notdone", error.getMessage());
                        // error
                        Snackbar sb = Snackbar.make(view, error.getMessage(), Snackbar.LENGTH_LONG);
                        sb.show();
                    }
                });
            }

            @Override
            public void onError(QBResponseException error) {
                // errors
                Log.v("notdone", error.getMessage());
                Snackbar sb = Snackbar.make(view, error.getMessage(), Snackbar.LENGTH_LONG);
                sb.show();
            }

        });








    }
    public void login(final FragmentTransaction ft, final loginsighnup loginsighnup, final View view1, final View view, final DialogInterface builder, final String regno, final String pass) {
        final QBChatService chatService = QBChatService.getInstance();
        final QBUser user = new QBUser(regno, pass);
        QBAuth.createSession(user, new QBEntityCallback<QBSession>() {
            @Override
            public void onSuccess(QBSession session, Bundle params) {
                user.setId(session.getUserId());


                // user.setLogin(regno);
                // user.setPassword(pass);

                Log.v("TEST11", session.getUserId().toString());
                chatService.login(user, new QBEntityCallback() {

                    @Override
                    public void onSuccess(Object o, Bundle bundle) {
                        Log.v("done", bundle.toString());
                        /*
                        loginsighnup.Login1.setVisibility(View.INVISIBLE);
                        loginsighnup.Login.setVisibility(View.VISIBLE);
                        loginsighnup.Signup.setVisibility(View.VISIBLE);
                        loginsighnup.username1.setVisibility(View.INVISIBLE);
                        loginsighnup.password1.setVisibility(View.INVISIBLE);*/
                        Snackbar sb = Snackbar.make(view1, "Login successful", Snackbar.LENGTH_LONG);
                        sb.show();
                        builder.dismiss();
                        ft.detach(loginsighnup).attach(loginsighnup).commit();

                    }

                    @Override
                    public void onError(QBResponseException error) {
                        Log.v("notdone1", error.getMessage());
                        // errror
                        Snackbar sb = Snackbar.make(view, "notdone1" + error.getMessage(), Snackbar.LENGTH_LONG);
                        sb.show();
                    }
                });
            }

            @Override
            public void onError(QBResponseException e) {
                Log.v("notdone2", e.getMessage());
                Snackbar sb = Snackbar.make(view, "notdone2" + e.getMessage(), Snackbar.LENGTH_LONG);
                sb.show();
            }

        });
    }




}


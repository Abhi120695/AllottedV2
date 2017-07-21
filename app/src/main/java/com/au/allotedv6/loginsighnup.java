package com.au.allotedv6;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.au.allotedv6.core.Chat;
import com.quickblox.chat.QBChatService;
import com.quickblox.chat.QBRoster;
import com.quickblox.chat.listeners.QBRosterListener;
import com.quickblox.chat.listeners.QBSubscriptionListener;
import com.quickblox.chat.model.QBChatMessage;
import com.quickblox.chat.model.QBDialog;
import com.quickblox.chat.model.QBDialogType;
import com.quickblox.chat.model.QBPresence;
import com.quickblox.core.QBEntityCallback;
import com.quickblox.core.exception.QBResponseException;
import com.quickblox.core.request.QBRequestGetBuilder;
import com.quickblox.users.QBUsers;
import com.quickblox.users.model.QBUser;

import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Created by Abhishek-Pc on 24-03-2016.
 */
public class loginsighnup extends Fragment {
    public EditText username;
    public EditText password;
    public EditText username1;
    public EditText password1;
    public EditText Conpassword;
    public Button Login1;
    public Button Login;
    public Button contact;
    public Button Logout;
    public Button Signup;
    public Button Signup1;
    public SharedPreferences settings;
    SharedPreferences.Editor editor;
    public String savedregno;
    message mMessage;
    public String regno;
    public String pass;
    private ListView Chatlist;
    private Dilogueadapter chatadapter;
    private Chat mChat;
    private List<QBDialog> chathistory;

    public static loginsighnup newInstance() {
        loginsighnup fragment = new loginsighnup();
        return fragment;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final QBChatService chatService = QBChatService.getInstance();

        final View rootView = inflater.inflate(R.layout.loginsignup, container, false);
        //username= (EditText) rootView.findViewById(R.id.editText7);
        //password= (EditText) rootView.findViewById(R.id.editText8);


        Login = (Button) rootView.findViewById(R.id.button14);
        contact = (Button) rootView.findViewById(R.id.button15);
        Logout = (Button) rootView.findViewById(R.id.button12);
        Signup = (Button) rootView.findViewById(R.id.button13);
        Chatlist = (ListView) rootView.findViewById(R.id.listView6);

        boolean isLoggedIn = chatService.isLoggedIn();
        final FragmentTransaction ft = getFragmentManager().beginTransaction();
        if (!isLoggedIn) {
            Login.setVisibility(View.VISIBLE);
            Signup.setVisibility(View.VISIBLE);
            Chatlist.setVisibility(View.INVISIBLE);
            Logout.setVisibility(View.INVISIBLE);

        } else {
            Login.setVisibility(View.INVISIBLE);
            Signup.setVisibility(View.INVISIBLE);
            Chatlist.setVisibility(View.VISIBLE);
            Logout.setVisibility(View.VISIBLE);
            getchats(getActivity());
            adduser();


        }

        settings = getActivity().getSharedPreferences("Register", Context.MODE_PRIVATE);
        editor = settings.edit();
        savedregno = settings.getString("Register", "noreg");
        mMessage = new message();

        Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                if (!savedregno.equals("noreg")) {

                   /* username.setVisibility(View.VISIBLE);
                    username.setText(savedregno);
                    password.setVisibility(View.VISIBLE);
                    Conpassword.setVisibility(View.VISIBLE);
                    Signup1.setVisibility(View.VISIBLE);
                    Login.setVisibility(View.INVISIBLE);
                    Signup.setVisibility(View.INVISIBLE);*/
                    final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    // Get the layout inflater

                    LayoutInflater inflater = getActivity().getLayoutInflater();
                    final View v_iew = inflater.inflate(R.layout.customalert, null);
                    builder.setView(v_iew)
                            //builder.setView(inflater.inflate(R.layout.customalert, null))

                            // Add action buttons
                            .setPositiveButton("Signup", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int id) {

                                }
                            })
                            .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });
                    // builder.show();
                    final AlertDialog dialog = builder.create();
                    dialog.show();
                    dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Boolean wantToCloseDialog = false;
                            //Do stuff, possibly set wantToCloseDialog to true then...
                            username = (EditText) v_iew.findViewById(R.id.username);
                            password = (EditText) v_iew.findViewById(R.id.password);
                            Conpassword = (EditText) v_iew.findViewById(R.id.cpassword);
                            regno = username.getText().toString();
                            pass = password.getText().toString();
                            String pass2 = Conpassword.getText().toString();
                            if (pass.equals(pass2) || !pass.equals("")) {

                                mMessage.newsession(view, v_iew, dialog, regno, pass);


                            }
                            // if (wantToCloseDialog)
                            //  dialog.dismiss();
                            //else dialog stays open. Make sure you have an obvious way to close the dialog especially if you set cancellable to false.
                        }
                    });


                } else {
                    Snackbar sb = Snackbar.make(view, "Please update you Details in database first", Snackbar.LENGTH_LONG);
                    sb.show();
                }

            }
        });
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                if (!savedregno.equals("noreg")) {
                    /*
                    username1.setVisibility(View.VISIBLE);
                    password1.setVisibility(View.VISIBLE);

                    Login1.setVisibility(View.VISIBLE);
                    Login.setVisibility(View.INVISIBLE);
                    Signup.setVisibility(View.INVISIBLE);*/
                    final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    // Get the layout inflater

                    LayoutInflater inflater = getActivity().getLayoutInflater();
                    final View v_iew = inflater.inflate(R.layout.customlogin, null);
                    builder.setView(v_iew)
                            //builder.setView(inflater.inflate(R.layout.customalert, null))

                            // Add action buttons
                            .setPositiveButton("Login", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int id) {

                                }
                            })
                            .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });
                    // builder.show();
                    final AlertDialog dialog = builder.create();
                    dialog.show();
                    dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Boolean wantToCloseDialog = false;
                            //Do stuff, possibly set wantToCloseDialog to true then...
                            username = (EditText) v_iew.findViewById(R.id.username1);
                            password = (EditText) v_iew.findViewById(R.id.password1);

                            regno = username.getText().toString();
                            pass = password.getText().toString();


                            mMessage.login(ft, loginsighnup.this, view, v_iew, dialog, regno, pass);


                            // if (wantToCloseDialog)
                            //  dialog.dismiss();
                            //else dialog stays open. Make sure you have an obvious way to close the dialog especially if you set cancellable to false.
                        }
                    });

                } else {
                    Snackbar sb = Snackbar.make(view, "Please update you Details in database first", Snackbar.LENGTH_LONG);
                    sb.show();
                }


            }
        });
        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chatService.logout(new QBEntityCallback() {

                    @Override
                    public void onSuccess(Object o, Bundle bundle) {
                        chatService.destroy();
                    }


                    @Override
                    public void onError(QBResponseException errors) {

                    }
                });
                ft.detach(loginsighnup.this).attach(loginsighnup.this).commit();
            }
        });
    contact.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            ft.detach(loginsighnup.this).attach(contactfrafment.newInstance()).commit();

        }
    });

        return rootView;
    }

    public void getchats(final FragmentActivity activity) {

        QBRequestGetBuilder requestBuilder = new QBRequestGetBuilder();
        requestBuilder.setLimit(100);

        QBChatService.getChatDialogs(null, requestBuilder, new QBEntityCallback<ArrayList<QBDialog>>() {
            @Override
            public void onSuccess(ArrayList<QBDialog> dialogs, Bundle args) {
                Log.v("intialise", "ready");
                int totalEntries = args.getInt("total_entries");
                chathistory = new ArrayList<>();
                chathistory.addAll(dialogs);
                /*
                QBDialog qbDialog = new QBDialog();
                qbDialog.setId(1);
                qbDialog.setDialogId("2");
                qbDialog.setLastMessage("hello");
                qbDialog.setName("don");
                qbDialog.setType(QBDialogType.PRIVATE);
                chathistory.add(qbDialog);
                */
                chatadapter = new Dilogueadapter(chathistory, activity);
                if (!chatadapter.isEmpty()) {
                    Chatlist.setAdapter(chatadapter);
                }

            }

            @Override
            public void onError(QBResponseException errors) {
                Log.v("intialise", errors.getMessage());
            }
        });
    }

    public void adduser() {

        QBRosterListener rosterListener = new QBRosterListener() {
            @Override
            public void entriesDeleted(Collection<Integer> userIds) {

            }

            @Override
            public void entriesAdded(Collection<Integer> userIds) {

            }

            @Override
            public void entriesUpdated(Collection<Integer> userIds) {

            }

            @Override
            public void presenceChanged(QBPresence presence) {

            }
        };

        QBSubscriptionListener subscriptionListener = new QBSubscriptionListener() {
            @Override
            public void subscriptionRequested(int userId) {

            }
        };


// Do this after success Chat login
        final QBRoster chatRoster = QBChatService.getInstance().getRoster(QBRoster.SubscriptionMode.mutual, subscriptionListener);
        chatRoster.addRosterListener(rosterListener);



        QBUsers.getUserByLogin("14bce0701", new QBEntityCallback<QBUser>() {
            @Override
            public void onSuccess(QBUser user, Bundle args) {
                int userID = user.getId();
                Log.v("id", user.toString());
                if (chatRoster.contains(userID))

                {
                    try {
                        chatRoster.subscribe(userID);
                    } catch (SmackException.NotConnectedException e) {

                    }
                } else

                {
                    try {
                        chatRoster.createEntry(userID, null);
                    } catch (XMPPException e) {

                    } catch (SmackException.NotLoggedInException e) {

                    } catch (SmackException.NotConnectedException e) {

                    } catch (SmackException.NoResponseException e) {

                    }
                }
            }

            @Override
            public void onError(QBResponseException errors) {

            }
        });

}
    public void sendmessage(){
        QBChatMessage chatMessage = new QBChatMessage();
        chatMessage.setBody("hello");
        chatMessage.setProperty("PROPERTY_SAVE_TO_HISTORY", "1");
        chatMessage.setDateSent(new Date().getTime() / 1000);

        try {
            mChat.sendMessage(chatMessage);
        } catch (XMPPException e) {
            Log.v("TAG", "failed to send a message", e);
        } catch (SmackException sme) {
            Log.v("TAG", "failed to send a message", sme);
        }

        // messageEditText.setText("");


    }

}

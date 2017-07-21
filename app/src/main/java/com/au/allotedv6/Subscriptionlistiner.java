package com.au.allotedv6;

import com.quickblox.chat.QBChatService;
import com.quickblox.chat.QBRoster;
import com.quickblox.chat.listeners.QBRosterListener;
import com.quickblox.chat.listeners.QBSubscriptionListener;
import com.quickblox.chat.model.QBPresence;

import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPException;

import java.util.Collection;

/**
 * Created by Abhishek-Pc on 25-03-2016.
 */
public class Subscriptionlistiner {
    QBRoster chatRoster ;
    QBSubscriptionListener subscriptionListener;
    public void listen(){

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

         subscriptionListener = new QBSubscriptionListener() {
            @Override
            public void subscriptionRequested(int userId) {


            }
        };

        chatRoster = QBChatService.getInstance().getRoster(QBRoster.SubscriptionMode.mutual, subscriptionListener);
        chatRoster.addRosterListener(rosterListener);
// Do this after success Chat login









    }

    private void adduser(int userId) {
        try {

            chatRoster.confirmSubscription(userId);

        } catch (SmackException.NotConnectedException e) {

        } catch (SmackException.NotLoggedInException e) {

        } catch (XMPPException e) {

        } catch (SmackException.NoResponseException e) {

        }

    }

}

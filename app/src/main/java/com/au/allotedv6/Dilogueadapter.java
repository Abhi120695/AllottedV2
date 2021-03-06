package com.au.allotedv6;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.au.allotedv6.core.ChatService;
import com.quickblox.chat.QBChatService;
import com.quickblox.chat.model.QBDialog;
import com.quickblox.chat.model.QBDialogType;
import com.quickblox.users.model.QBUser;

import java.util.List;


/**
 * Created by Abhishek-Pc on 25-03-2016.
 */
public class Dilogueadapter extends BaseAdapter {
        private List<QBDialog> dataSource;
        private LayoutInflater inflater;


        public Dilogueadapter(List<QBDialog> dataSource, Context ctx) {
            this.dataSource = dataSource;
            this.inflater = LayoutInflater.from(ctx);

        }

        public List<QBDialog> getDataSource() {
            return dataSource;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public Object getItem(int position) {
            return dataSource.get(position);
        }

        @Override
        public int getCount() {
            return dataSource.size();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;

            // initIfNeed view
            //
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.list_item_room, null);
                holder = new ViewHolder();
                holder.name = (TextView)convertView.findViewById(R.id.roomName);
                holder.lastMessage = (TextView)convertView.findViewById(R.id.lastMessage);
                holder.groupType = (TextView)convertView.findViewById(R.id.textViewGroupType);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            // set data
            //
            QBDialog dialog = dataSource.get(position);
            if(dialog.getType().equals(QBDialogType.GROUP)){
                holder.name.setText(dialog.getName());
            }else{
                // get opponent name for private dialog
                //
                Integer opponentID = ChatService.getInstance().getOpponentIDForPrivateDialog(dialog);
                QBUser user = ChatService.getInstance().getDialogsUsers().get(opponentID);
                if(user != null){
                    holder.name.setText(user.getLogin() == null ? user.getFullName() : user.getLogin());
                }
            }
            if (dialog.getLastMessage() != null) {
               // holder.lastMessage.setText("Sticker");
            } else {
                holder.lastMessage.setText(dialog.getLastMessage());
            }
            holder.groupType.setText(dialog.getType().toString());

            return convertView;
        }

        private static class ViewHolder{
            TextView name;
            TextView lastMessage;
            TextView groupType;
        }
    }


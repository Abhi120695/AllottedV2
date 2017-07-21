package com.au.allotedv6;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.BackendlessDataQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Abhishek-Pc on 13-03-2016.
 */
public class Retrivedobject {
    Datasource mDatasource;
    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    public String Club;
    private List<Studentobject> mRetrivedobjectList;
    List<Retriveddata> mRetriveddatas;

    public Retrivedobject(Context context) {
        prefs = context.getSharedPreferences("Clubname", Context.MODE_PRIVATE);
        editor = prefs.edit();
        Club = prefs.getString("Clubname", "no Name");
        mDatasource=new Datasource(context);
        mDatasource.open();
        mRetriveddatas=new ArrayList<>();
    }

    //private String whereClause = "Club = "+Club;

    List<Retriveddata> Retrive() {
       // BackendlessDataQuery dataQuery = new BackendlessDataQuery();
        //dataQuery.setWhereClause(whereClause);

       // BackendlessCollection<Studentobject> result = Backendless.Persistence.of(Studentobject.class).find();
        Backendless.Persistence.of( Studentobject.class).find( new AsyncCallback<BackendlessCollection<Studentobject>>(){
            @Override
            public void handleResponse(BackendlessCollection<Studentobject> result)
            {
               Log.v( "Downloaded",result.getData().get(0).getNAME());
                mRetrivedobjectList = result.getData();
                Log.v( "Downloaded",mRetrivedobjectList.get(0).getNAME());
                for(int i=0;i<mRetrivedobjectList.size(); i++) {
                    Log.v( "Downloadedtest2",mRetrivedobjectList.get(i).getNAME()+i);
                    if(mRetrivedobjectList.get(i).getClub().contains(Club)) {

                        Log.v( "Downloaded",mRetrivedobjectList.get(i).getNAME()+i);
                        mDatasource.create(mRetrivedobjectList.get(i));
                        Retriveddata mRetrived = new Retriveddata();
                        mRetrived.setNAME(mRetrivedobjectList.get(i).getNAME());
                        mRetrived.setRegistration_Number(mRetrivedobjectList.get(i).getKEY());
                        mRetrived.setTaken(mRetrivedobjectList.get(i).getTaken());
                        mRetrived.setClub(mRetrivedobjectList.get(i).getClub());
                        mRetrived.setMonday(mRetrivedobjectList.get(i).getMONDAY());
                        mRetrived.setTuesday(mRetrivedobjectList.get(i).getNAME());
                        mRetrived.setWenesday(mRetrivedobjectList.get(i).getWEDNESDAY());
                        mRetrived.setThursday(mRetrivedobjectList.get(i).getTHURSDAY());
                        mRetrived.setFriday(mRetrivedobjectList.get(i).getFRIDAY());
                        mRetriveddatas.add(mRetrived);
                    }


                }

                // all Contact instances have been found
            }

            @Override
            public void handleFault(BackendlessFault backendlessFault) {

            }


        });

            return mRetriveddatas;

    }
}

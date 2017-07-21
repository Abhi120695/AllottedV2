package com.au.allotedv6;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import com.opencsv.CSVReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.sql.DataSource;

/**
 * Created by Abhishek-Pc on 28-02-2016.
 */
public class Import  {
    final Context mContext;
    Datasource mDataSource;



    private String path;

    public Import(Context context) {
        this.mContext = context;
        mDataSource = new Datasource(mContext);
        mDataSource.open();


    }
    private boolean checkflag=false;

    SQLiteOpenHelper dbhelper1;

    public void impcsv(File path) {
       // File exportDir = new File(Environment.getExternalStorageDirectory(), "");

       /* if (!exportDir.exists())

        {
            exportDir.mkdirs();
        }
        */

       // File file = new File(path,"");
        Log.v("working1", "tiilhere");
        try {
            CSVReader reader = new CSVReader(new FileReader(path));
            String[] nextLine;
            Log.v("working2", "tiilhere");


            try {
                while ((nextLine = reader.readNext()) != null) {

                    // nextLine[] is an array of values from the line

                    Sortstrings sortstrings = new Sortstrings();

                    sortstrings.setNAME(nextLine[0]);
                    sortstrings.setTaken(nextLine[1]);
                    sortstrings.setClub(nextLine[2]);
                    sortstrings.setKEY(nextLine[3]);
                    sortstrings.setMONDAY(nextLine[4]);
                    sortstrings.setTUESDAY(nextLine[5]);
                    sortstrings.setWEDNESDAY(nextLine[6]);
                    sortstrings.setTHURSDAY(nextLine[7]);
                    sortstrings.setFRIDAY(nextLine[8]);
                    Log.v("working", sortstrings.getNAME());

                    if (nextLine[2].equals("sample Reg NO") || nextLine[2].equals("Registrationnum")) {

                    } else {
                        mDataSource.create(sortstrings);
                        checkflag=true;
                    }
                        /*
                        if (value == 1) {
                         /*
                            Toast.makeText(getApplicationContext(), "Data inerted into table", Toast.LENGTH_LONG).show();
                        }
                */


                }
                if(checkflag){
                    Toast.makeText(mContext,"Data has been imported",Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(mContext,"Import failed",Toast.LENGTH_LONG).show();
                }

            } catch (IOException e) {
                Log.v("notworking1", e.toString());
                e.printStackTrace();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Log.v("notworking2", "here");
        } catch (IOException e) {
            e.printStackTrace();
            Log.v("notworking1", "here");
        }
    }




}



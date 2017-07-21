package com.au.allotedv6;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessException;
import com.backendless.exceptions.BackendlessFault;

import java.util.List;

/**
 *
 * Created by Abhishek-Pc on 12-03-2016.
 */
public class Studentobject  {
    private List<Retriveddata> mlist;

   private String MONDAY =new String();
   private String TUESDAY =new String();
   private String WEDNESDAY =new String();
    private String THURSDAY =new String();
    private String FRIDAY =new String();
    private  String KEY=new String();
    private String Taken =new String();

    public String getClub() {
        return Club;
    }

    public void setClub(String club) {
        Club = club;
    }

    private String Club = new String();
    public Studentobject(){

    }



    public String getMONDAY() {
        return MONDAY;
    }

    public void setMONDAY(String MONDAY) {
        this.MONDAY = MONDAY;
    }

    public String getTUESDAY() {
        return TUESDAY;
    }

    public void setTUESDAY(String TUESDAY) {
        this.TUESDAY = TUESDAY;
    }

    public String getWEDNESDAY() {
        return WEDNESDAY;
    }

    public void setWEDNESDAY(String WEDNESDAY) {
        this.WEDNESDAY = WEDNESDAY;
    }

    public String getTHURSDAY() {
        return THURSDAY;
    }

    public void setTHURSDAY(String THURSDAY) {
        this.THURSDAY = THURSDAY;
    }

    public String getFRIDAY() {
        return FRIDAY;
    }

    public void setFRIDAY(String FRIDAY) {
        this.FRIDAY = FRIDAY;
    }

    public String getKEY() {
        return KEY;
    }

    public void setKEY(String KEY) {
        this.KEY = KEY;
    }

    public String getTaken() {
        return Taken;
    }

    public void setTaken(String taken) {
        Taken = taken;
    }
    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    String NAME=new String();

    public String getNAME() {
        return NAME;
    }


    public Studentobject(Sortstrings sortstrings){
        this.setMONDAY(sortstrings.getMONDAY());
        this.setTUESDAY(sortstrings.getTUESDAY());
        this.setWEDNESDAY(sortstrings.getWEDNESDAY());
        this.setTHURSDAY(sortstrings.getTHURSDAY());
        this.setFRIDAY(sortstrings.getFRIDAY());
        this.setTaken(sortstrings.getTaken());
        this.setKEY(sortstrings.getKEY());
        this.setNAME(sortstrings.getNAME());
        this.setClub(sortstrings.getClub());



    }

    public Studentobject(Retriveddata sortstrings){
        this.setMONDAY(sortstrings.getMonday());
        this.setTUESDAY(sortstrings.getTuesday());
        this.setWEDNESDAY(sortstrings.getWenesday());
        this.setTHURSDAY(sortstrings.getThursday());
        this.setFRIDAY(sortstrings.getFriday());
        this.setTaken(sortstrings.getTaken());
        this.setKEY(sortstrings.getRegistration_Number());
        this.setNAME(sortstrings.getNAME());
        this.setClub(sortstrings.getClub());



    }

    public void saveStudentobject(Sortstrings sortstrings){
        String applicationID = "CA422B34-F29D-105D-FF75-AADF25000500";
        String secretKey = "737E46FA-05A1-D547-FFAA-17D97421CF00";
        String version = "v1";
//        Backendless.initApp( applicationID, secretKey, version );

        Studentobject newStudent =new Studentobject(sortstrings);

        // save object synchronously
      // Studentobject savedStudent = Backendless.Persistence.save( newStudent );

// save object asynchronously
        Backendless.Persistence.save(newStudent, new AsyncCallback<Studentobject>() {
            public void handleResponse(Studentobject response) {
                // new Contact instance has been saved
                Log.v("done", "uploded");
            }


            public void handleFault(BackendlessFault fault) {
                // an error has occurred, the error code can be retrieved with fault.getCode()
                Log.v("notdone", fault.getCode());
            }
        });
    }
    public void saveStudentobject(Retriveddata retriveddata){
        String applicationID = "CA422B34-F29D-105D-FF75-AADF25000500";
        String secretKey = "737E46FA-05A1-D547-FFAA-17D97421CF00";
        String version = "v1";
//        Backendless.initApp( applicationID, secretKey, version );

        final Studentobject newStudent =new Studentobject(retriveddata);

        // save object synchronously

       /* try {
            Studentobject savedStudent = Backendless.Persistence.save(newStudent);
            Log.v("done","uploded"+newStudent.getNAME());
        }catch (BackendlessException e){
            Log.v("notdone",e.getCode()+e.getMessage()+newStudent.getNAME());

        }
        */

// save object asynchronously

        Backendless.Persistence.save( newStudent, new AsyncCallback<Studentobject>() {
            public void handleResponse( Studentobject response )
            {
                // new Contact instance has been saved
                Log.v("done","uploded"+newStudent.getNAME());
            }



            public void handleFault( BackendlessFault fault )
            {
                // an error has occurred, the error code can be retrieved with fault.getCode()
                Log.v("notdone",fault.getCode()+fault.getMessage()+newStudent.getNAME());
            }
        });

    }
    public void saveStudentobject(List<Retriveddata> retriveddata){
        String applicationID = "CA422B34-F29D-105D-FF75-AADF25000500";
        String secretKey = "737E46FA-05A1-D547-FFAA-17D97421CF00";
        String version = "v1";
//        Backendless.initApp( applicationID, secretKey, version );
        Retriveddata mretriveddata;
        for (int i=0; i<retriveddata.size();i++) {
            mretriveddata = retriveddata.get(i);


            final Studentobject newStudent = new Studentobject(mretriveddata);


            // save object synchronously

       /* try {
            Studentobject savedStudent = Backendless.Persistence.save(newStudent);
            Log.v("done","uploded"+newStudent.getNAME());
        }catch (BackendlessException e){
            Log.v("notdone",e.getCode()+e.getMessage()+newStudent.getNAME());

        }
        */

// save object asynchronously

            Backendless.Persistence.save(newStudent, new AsyncCallback<Studentobject>() {
                public void handleResponse(Studentobject response) {
                    // new Contact instance has been saved
                    Log.v("done", "uploded" + newStudent.getNAME());
                }


                public void handleFault(BackendlessFault fault) {
                    // an error has occurred, the error code can be retrieved with fault.getCode()
                    Log.v("notdone", fault.getCode() + fault.getMessage() + newStudent.getNAME());
                }
            });
        }

    }


}


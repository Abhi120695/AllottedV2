package com.au.allotedv6;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Abhishek-Pc on 04-03-2016.
 */
public class sortretriveddata2 {
    private List<Retriveddata> Data1;
    //private List<String> Regnum =new ArrayList<>();
    StringBuilder Regnum = new StringBuilder();
    private String Regnum1 = new String();
    private String Monday = new String();
    private String Tuesday = new String();
    private String Wednesday = new String();
    private String Thursday = new String();
    private String Friday = new String();
    private String NAME = new String();
    private List<String> Reg ;
    private  List<String> name;

    public List<String> getReg() {
        return Reg;
    }

    public void setReg(List<String> reg) {
        Reg = reg;
    }

    protected Context mContext;
    Getstudent mGetstudent;


    public sortretriveddata2(Context context) {

        Data1 = new Datasource(context).getall();
        Reg=new ArrayList<>();
        name=new ArrayList<>();
        mGetstudent=new Getstudent();
        mContext=context;

    }

    public String Sortdata(String Day, String[] Time, View view) {


// In your method:

        boolean Found = true;
        boolean Found2;

        int i = 0;
        int j;
         int k = Time.length;
        int l=0;
        if (Day.equals("Monday")) {
            for (i = 0; i < Data1.size(); i++) {
                Found = true;
                Found2=true;
                Monday = Data1.get(i).getMonday();
                Regnum1 = Data1.get(i).getRegistration_Number();
                NAME = Data1.get(i).getNAME();

                    for (j = 0; j < k; j++) {
                      Found2= checkday(Monday, Time[j]);
                        Found=Found&&Found2;
                        Log.v("check21",Time[j]+Boolean.toString(Found2)+Boolean.toString(Found));
                        //Log.v("check31",Boolean.toString(Found));

                    }

                if (Found) {
                    //Regnum.append(Regnum1 + "-" + NAME + "\n");
                    Reg.add(Regnum1+"-"+NAME);
                    Regnum.append("true");
                    //name.add(NAME);
                }

            }
        }

            if (Day.equals("Tuesday")) {
                for (i = 0; i < Data1.size(); i++) {
                    Found = true;
                    Found2=true;

                    Tuesday = Data1.get(i).getTuesday();
                    Regnum1 = Data1.get(i).getRegistration_Number();
                    NAME = Data1.get(i).getNAME();
                    for (j = 0; j < k; j++) {
                        Found2= checkday(Tuesday,Time[j]);
                        Found=Found&&Found2;
                        Log.v("check21",Time[j]+Boolean.toString(Found2)+Boolean.toString(Found));
                        //Log.v("check31",Boolean.toString(Found));

                    }
                    if (Found) {
                       // Regnum.append(Regnum1 + "-" + NAME + "\n");
                        Reg.add(Regnum1+"-"+NAME);
                        Regnum.append("true");
                       // name.add(NAME);
                    }

                }
            }
            if (Day.equals("Wednesday")) {
                for (i = 0; i < Data1.size(); i++) {
                    Found = true;
                    Found2=true;
                    Wednesday = Data1.get(i).getWenesday();
                    Regnum1 = Data1.get(i).getRegistration_Number();
                    NAME = Data1.get(i).getNAME();
                    for (j = 0; j < k; j++) {
                        Found2= checkday(Wednesday,Time[j]);
                        Found=Found&&Found2;
                        Log.v("check21",Time[j]+Boolean.toString(Found2)+Boolean.toString(Found));
                        //Log.v("check31",Boolean.toString(Found));

                    }
                    if (Found) {
                       // Regnum.append(Regnum1 + "-" + NAME + "\n");
                        Reg.add(Regnum1+"-"+NAME);
                        Regnum.append("true");
                        //name.add(NAME);
                    }

                }
            }
            if (Day.equals("Thursday")) {
                for (i = 0; i < Data1.size(); i++) {
                    Found = true;
                    Found2=true;

                    Thursday = Data1.get(i).getThursday();
                    Regnum1 = Data1.get(i).getRegistration_Number();
                    NAME = Data1.get(i).getNAME();
                    for (j = 0; j < k; j++) {
                        Found2= checkday(Thursday,Time[j]);
                        Found=Found&&Found2;
                        Log.v("check21",Time[j]+Boolean.toString(Found2)+Boolean.toString(Found));
                        //Log.v("check31",Boolean.toString(Found));

                    }
                    if (Found) {
                      //  Regnum.append(Regnum1 + "-" + NAME + "\n");
                        Regnum.append("true");
                        Reg.add(Regnum1+"-"+NAME);
                        //name.add(NAME);
                    }

                }
            }
            if (Day.equals("Friday")) {
                for (i = 0; i < Data1.size(); i++) {
                    Found = true;
                    Found2=true;

                    Friday = Data1.get(i).getFriday();
                    Regnum1 = Data1.get(i).getRegistration_Number();
                    NAME = Data1.get(i).getNAME();
                    for (j = 0; j < k; j++) {
                        Found2= checkday(Friday,Time[j]);
                        Found=Found&&Found2;
                        Log.v("check21",Time[j]+Boolean.toString(Found2)+Boolean.toString(Found));
                        //Log.v("check31",Boolean.toString(Found));

                    }

                    if (Found) {
                       // Regnum.append(Regnum1 + "-" + NAME + "\n");
                        Regnum.append("true");
                        Reg.add(Regnum1+"-"+NAME+",");
                        //name.add(NAME);
                    }

                }
            }



        if (Regnum.toString().equals("")) {
            Snackbar sb = Snackbar.make(view, "No members found", Snackbar.LENGTH_LONG);
            sb.show();
            return "false";


        }
        else {

          return Reg.toString().replaceAll("[\\[\\]]","");



        }








    }

    private Boolean checkday(String day, String s) {
        Boolean Found=false;
        if (day.contains(s)) {
            Found = true;
            Log.v("check11",s+Found.toString());
            //Log.v("check221",Found.toString());

        }
        return Found;
    }
}




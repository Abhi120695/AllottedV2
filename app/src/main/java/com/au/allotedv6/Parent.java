package com.au.allotedv6;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.backendless.Backendless;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class Parent extends Fragment {
    private EditText Regno;
    private EditText dob;

    private EditText mobileno;
    private String campus;
    private String dateOfBirth;
    private String registrationNumber;
    private String mobileNumber;
    private String choice;
    private SharedPreferences settings2;
    private SharedPreferences settings;
    SharedPreferences.Editor editor;
    Login mLogin;
    Donloadeddata mDownloadedata;
    private String Taken;
    Datasource mDatasource;
    Database mDatabase;
    Freecalc F=new Freecalc();
    private String name;
    private String Free;
    private Button Done;
    private String savedregno;
    public static Parent newInstance() {
        Parent fragment = new Parent();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View rootView = inflater.inflate(R.layout.content_parent, container, false);

        Toolbar toolbar = (Toolbar) rootView.findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        Regno= (EditText) rootView.findViewById(R.id.editText);
        dob= (EditText) rootView.findViewById(R.id.editText2);
        mobileno= (EditText) rootView.findViewById(R.id.editText3);
        Done= (Button) rootView.findViewById(R.id.button);
        mDatasource=new Datasource(getActivity());
        mDatabase=new Database(getActivity());
        mDatasource.open();
        settings2 = getActivity().getSharedPreferences("Choice", Context.MODE_PRIVATE);
        choice = settings2.getString("Choice", "no Choice");
        settings = getActivity().getSharedPreferences("Register", Context.MODE_PRIVATE);
        editor = settings.edit();
        savedregno=settings.getString("Register","noreg");
        Backendless.initApp(getActivity(), "CA422B34-F29D-105D-FF75-AADF25000500", "737E46FA-05A1-D547-FFAA-17D97421CF00", "v1");

      /*  FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        */
        Done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Start(view);
            }
        });
        return rootView;
    }

/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; getActivity() adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    */

    public void Start(View view) {
campus="vellore";
        registrationNumber=Regno.getText().toString();

        dateOfBirth=dob.getText().toString();
        mobileNumber=mobileno.getText().toString();

        requestData(null);


    }
    private void requestData(String uri) {

        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(constant.API_BASE_URL)
                .build();

        vitApi api = adapter.create(vitApi.class);

        api.login(campus, registrationNumber, dateOfBirth, mobileNumber, new Callback<Login>() {


            @Override
            public void success(Login login, Response response) {
                mLogin = login;
                Toast.makeText(getActivity(), mLogin.toString(), Toast.LENGTH_LONG).show();
                requestData2(null);
                name=mLogin.toString();
            }

            @Override
            public void failure(RetrofitError arg0) {
                // TODO Auto-generated method stub

            }
        });

    }

    private void requestData2(String uri2) {
        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(constant.API_BASE_URL)
                .build();

        vitapi2 api = adapter.create(vitapi2.class);

        api.share(campus, registrationNumber, dateOfBirth, mobileNumber,registrationNumber, new Callback<Donloadeddata>() {



            @Override
            public void success(Donloadeddata donloadeddata, Response response) {
                mDownloadedata=donloadeddata;
                Toast.makeText(getActivity(), mDownloadedata.toString(), Toast.LENGTH_LONG).show();
                Taken=mDownloadedata.toString();
             Free=  F.calc1(name, registrationNumber, Taken, mDatasource);
                editor.putString("Register",registrationNumber);
                editor.commit();
                Toast.makeText(getActivity(), Free, Toast.LENGTH_LONG).show();
                if(choice.equals("Allotent")){

                }
            }

            @Override
            public void failure(RetrofitError arg0) {
                // TODO Auto-generated method stub

            }
        });
    }
}

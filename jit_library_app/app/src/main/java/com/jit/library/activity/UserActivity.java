package com.jit.library.activity;


import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.jit.library.R;
import com.jit.library.adapter.CustomListView;
import com.jit.library.dataModel.DataModel;
import com.jit.library.manager.SessionManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class UserActivity extends AppCompatActivity {

    private static final String TAG = "UserActivity";
    private SessionManager sessionManager;
    private Context c = this;
    private ProgressDialog progressDialog;
    private ListView mListView;
    private Spinner spinner_blood_group, spinner_city;
    private Button btnSubmit;
    private String addUrl = "http://192.168.43.125/jit_library_api/select_all.php";
    private String filterUrl = "http://192.168.43.125/jit_library_api/filter_data.php";
    private static ArrayList<DataModel> mArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        sessionManager = new SessionManager(getApplicationContext());
        progressDialog = new ProgressDialog(this);
        mListView = (ListView) findViewById(R.id.listView_);
        spinner_blood_group = (Spinner) findViewById(R.id.spinner_branch_group);
        spinner_city = (Spinner) findViewById(R.id.spinner_year);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        mArrayList = new ArrayList<>();
        getDataList(addUrl);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {
                final Dialog mdialog = new Dialog(c);
                mdialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                mdialog.setContentView(R.layout.daiog_box_layout);
                mdialog.setCancelable(true);
                TextView txtName = mdialog.findViewById(R.id.textView_Name);
                TextView txtBloodGroup = mdialog.findViewById(R.id.textView_Blood_Group);
                TextView txtGender = mdialog.findViewById(R.id.textView_Gender);
                TextView txtCity = mdialog.findViewById(R.id.textView_City);
                TextView txtContact = mdialog.findViewById(R.id.textView_Contact);
                txtName.setText(mArrayList.get(i).getbookName());
                txtBloodGroup.setText(mArrayList.get(i).getbookAuthor());
                txtGender.setText(mArrayList.get(i).getbookPub());
                txtCity.setText(mArrayList.get(i).getbookBranch());
                txtContact.setText(mArrayList.get(i).getbookYear());

                mdialog.show();
            }
        });
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mArrayList.clear();
                getDataList(filterUrl+"?branch="+spinner_blood_group.getSelectedItem()+"&year="+spinner_city.getSelectedItem());
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {
            sessionManager.logoutUser();
            Intent i = new Intent(getApplicationContext(), LoginActivity.class);
            // Closing all the Activities
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            // Add new Flag to start new Activity
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            // Staring Login Activity
            startActivity(i);

            // Close
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void getDataList(String addInfoUrl) {
        Log.d("Tag", addInfoUrl);
        progressDialog.setMessage("Searching...");
        progressDialog.show();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(addInfoUrl, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray jsonArray) {
                progressDialog.dismiss();
                Log.d("Tag", "" + jsonArray);
                displayList(jsonArray);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Error", String.valueOf(error));
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(jsonArrayRequest);
    }

    private void displayList(JSONArray jsonArray) {
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                mArrayList.add(new DataModel(jsonObject.getString("book_id"),jsonObject.getString("book_name"),jsonObject.getString("author"),jsonObject.getString("publication"),jsonObject.getString("branch"),jsonObject.getString("year")));
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
            Log.d("Tag", String.valueOf(mArrayList.isEmpty()));
            CustomListView customAdapter = new CustomListView(getApplicationContext(), mArrayList);
            mListView.setAdapter(customAdapter);
            mListView.setEmptyView(findViewById(R.id.empty_View));

    }
}



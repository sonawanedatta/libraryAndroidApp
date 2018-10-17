package com.jit.library.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.jit.library.R;
import com.jit.library.validation.MyValidator;

import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;


public class RegisterActivity extends AppCompatActivity {

    private static final String TAG = "RegisterActivity";
    private static final String URL_FOR_REGISTRATION = "http://192.168.43.125/jit_library_api/register.php";
    ProgressDialog progressDialog;

    private EditText signupInputName, signupInputEmail, signupInputPassword,singupInputMobile;
    private Button btnSignUp;
    private Spinner spinner_blood_group, spinner_city;
    private Button btnLinkLogin;
    private RadioGroup genderRadioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Progress dialog
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);

        signupInputName = (EditText) findViewById(R.id.signup_input_name);
        signupInputEmail = (EditText) findViewById(R.id.signup_input_email);
        signupInputPassword = (EditText) findViewById(R.id.signup_input_password);
        spinner_blood_group = (Spinner) findViewById(R.id.signup_spinner_blood_group);
        spinner_city = (Spinner) findViewById(R.id.signup_spinner_city);
        singupInputMobile = (EditText) findViewById(R.id.signup_input_mobile);

        btnSignUp = (Button) findViewById(R.id.btn_signup);
        btnLinkLogin = (Button) findViewById(R.id.btn_link_login);

        genderRadioGroup = (RadioGroup) findViewById(R.id.gender_radio_group);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitForm();
            }
        });
        btnLinkLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(i);
            }
        });
    }

    private void submitForm() {

        int selectedId = genderRadioGroup.getCheckedRadioButtonId();
        String gender;
        if(selectedId == R.id.female_radio_btn)
            gender = "Female";
        else
            gender = "Male";
        boolean flag = true;
        if (!MyValidator.isValidRequired(signupInputName)){
            flag = false;
        }
        if (!MyValidator.isValidEmail(signupInputEmail)){
            flag = false;
        }
        if (!MyValidator.isValidRequired(signupInputPassword)){
            flag = false;
        }
        if (!MyValidator.isValidMobile(singupInputMobile)){
            flag = false;
        }
        if(spinner_blood_group.getSelectedItem().equals("Choose")){
            Toast.makeText(this, "Choose Branch", Toast.LENGTH_SHORT).show();
        }
        if(spinner_city.getSelectedItem().equals("Choose")){
            Toast.makeText(this, "Choose Year", Toast.LENGTH_SHORT).show();
        }
        else {
            if (flag) {
                registerUser(signupInputName.getText().toString(),
                        signupInputEmail.getText().toString(),
                        signupInputPassword.getText().toString(),
                        gender,
                        spinner_blood_group.getSelectedItem().toString(),
                        spinner_city.getSelectedItem().toString(),
                        singupInputMobile.getText().toString());
            }
        }
    }

    private void registerUser(final String name, final String email, final String password,
                              final String gender, final String branch, final String year, final String mobile) {
        // Tag used to cancel the request
        String cancel_req_tag = "register";


        progressDialog.setMessage("Adding you ...");
        showDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST,
                URL_FOR_REGISTRATION, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Register Response: " + response.toString());
                hideDialog();

                if (response.trim().toString().contains("Registeration Successfully done!")) {

                    // Launch login activity
                    Intent intent = new Intent(
                            RegisterActivity.this,
                            LoginActivity.class);
                    startActivity(intent);
                    finish();
                } else {

                    Toast.makeText(getApplicationContext(),
                            response.trim().toString(), Toast.LENGTH_LONG).show();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Registration Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
                hideDialog();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // Posting params to register url
                Map<String, String> params = new HashMap<String, String>();
                params.put("username", name);
                params.put("email", email);
                params.put("password", password);
                params.put("gender", gender);
                params.put("year", year);
                params.put("branch", branch);
                params.put("mobile", mobile);
                return params;
            }
        };
        // Adding request to request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(strReq);
    }

    private void showDialog() {
        if (!progressDialog.isShowing())
            progressDialog.show();
    }

    private void hideDialog() {
        if (progressDialog.isShowing())
            progressDialog.dismiss();
    }

}
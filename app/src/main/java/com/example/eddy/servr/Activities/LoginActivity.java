package com.example.eddy.servr.Activities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.example.eddy.servr.R;
import com.example.eddy.servr.ServerConnection;
import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;

/** October 25, 2017
 *  Eddy Yao
 *
 *      Log-in activity - Allows user to login or register an account using the text boxes provided
 *  This activity will communicate with the server and pass the necessary information, and eventually
 *  launch the main activity of the application when required.
*/

public class LoginActivity extends AppCompatActivity implements LoaderCallbacks<Cursor> {

    // Initialize layout elements
    private Context mContext;
    private Button registerButton;
    private LinearLayout mLinearLayout;
    private PopupWindow mPopupWindow;

    private static final int REQUEST_READ_CONTACTS = 0;

    // Keep track of the login task to ensure we can cancel it if requested.
    private UserLoginTask mAuthTask = null;

    // Further UI references
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;

    private TextInputEditText user;
    private TextInputEditText pin;
    private TextInputEditText email;
    private TextInputEditText phone;
    private TextInputEditText city;
    private TextInputEditText country;

    // Instances activity and creates UI
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Set up the login form.
        mEmailView = findViewById(R.id.email);
        populateAutoComplete();

        // Initializes necessary text fields - email and password
        mPasswordView = findViewById(R.id.password);
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        Button mEmailSignInButton = findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

        // Assigns UI components
        mContext = getApplicationContext();
        mLinearLayout = findViewById(R.id.login_layout);
        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);

        // Set up for registration button
        registerButton = findViewById(R.id.register_button);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                // Inflates the registration popup menu
                LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);

                assert inflater != null;
                @SuppressLint("InflateParams") final View customView = inflater.inflate(R.layout.popup_register, null);

                if(customView == null){
                    System.out.println("LOGIN ACTIVITY: customView is null");
                }else{
                    // Initializes the popup window
                    mPopupWindow = new PopupWindow(customView, 650, 1200);
                    mPopupWindow.setElevation(5.0f);

                    // Creates the close button
                    ImageButton closeButton = customView.findViewById(R.id.ib_close);
                    closeButton.setOnClickListener(new View.OnClickListener(){
                        public void onClick(View view){
                            mPopupWindow.dismiss();
                        }
                    });

                    // Allows the text fields to be edited
                    mPopupWindow.setFocusable(true);
                    mPopupWindow.update();

                    //Saving the users submissions
                    Button submit = customView.findViewById(R.id.sumbit_reg_button);

                    if(submit == null){
                        System.out.println("LOGIN ACTIVITY: Submit button null");
                    }else{
                        submit.setOnClickListener(new View.OnClickListener(){
                            @Override
                            public void onClick(View view) {
                                // Error trapping of inputted values
                                Boolean validRegistration = true;

                                TextInputEditText user = customView.findViewById(R.id.user_reg_edit);
                                TextInputEditText pin = customView.findViewById(R.id.pin_reg_edit);
                                TextInputEditText email = customView.findViewById(R.id.email_reg_edit);
                                TextInputEditText phone = customView.findViewById(R.id.phone_reg_edit);
                                TextInputEditText city = customView.findViewById(R.id.city_reg_edit);
                                TextInputEditText country = customView.findViewById(R.id.country_reg_edit);

                                String userEmail = email.getText().toString();
                                String userPin = pin.getText().toString();
                                String userPhone = phone.getText().toString();

                                if (!isEmailValid(userEmail)){
                                    email.setError(getString(R.string.error_invalid_email));
                                    validRegistration = false;
                                }
                                if (!isPasswordValid(userPin)){
                                    pin.setError(getString(R.string.error_invalid_password));
                                    validRegistration = false;
                                }
                                if (!isPhoneValid(userPhone)){
                                    phone.setError(getString(R.string.error_invalid_phone));
                                    validRegistration = false;
                                }

                                if (validRegistration){
                                    try{
                                        // Registers user to server if the input passes error checking
                                        BufferingActivity.servr.register(String.format("%s,%s,%s,%s,%s,%s",
                                                user.getText().toString(), pin.getText().toString(),
                                                email.getText().toString(), phone.getText().toString(),
                                                city.getText().toString(), country.getText().toString()));

                                        // Logs user in as soon as registration is finished
                                        mEmailView.setText(email.getText().toString());
                                        mPasswordView.setText(pin.getText().toString());
                                        attemptLogin();
                                    } catch (Exception e){
                                        System.out.println(e.getMessage());
                                    }
                                }
                            }
                        });

                        // Stops the keyboard from opening on startup
                        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

                        // Show the pop up window at the center of the layout
                        mLinearLayout.post(new Runnable() {
                            public void run() {
                                mPopupWindow.showAtLocation(mLinearLayout, Gravity.CENTER, 0, 0);
                            }
                        });
                    }

                }
            }
        });
    }

    // Reads contacts for any relevant information
    private void populateAutoComplete() {
        if (!mayRequestContacts()) {
            return;
        }

        getLoaderManager().initLoader(0, null, this);
    }

    // Checks if app has permission to read contacts as per Android OS
    private boolean mayRequestContacts() {
        if (checkSelfPermission(READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        if (shouldShowRequestPermissionRationale(READ_CONTACTS)) {
            // Requests permission to read contacts if it has not been granted yet
            Snackbar.make(mEmailView, R.string.permission_rationale, Snackbar.LENGTH_INDEFINITE)
                    .setAction(android.R.string.ok, new View.OnClickListener() {
                        @Override
                        @TargetApi(Build.VERSION_CODES.M)
                        public void onClick(View v) {
                            requestPermissions(new String[]{READ_CONTACTS}, REQUEST_READ_CONTACTS);
                        }
                    });
        } else {
            requestPermissions(new String[]{READ_CONTACTS}, REQUEST_READ_CONTACTS);
        }
        return false;
    }

    // Callback received when a permissions request has been completed.
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == REQUEST_READ_CONTACTS) {
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                populateAutoComplete();
            }
        }
    }

    // Attempts to sign in the account specified by the login form. If there are form errors
    // (invalid email, missing fields, etc.), the errors are presented and no login attempt is made.
    private void attemptLogin() {
        if (mAuthTask != null) {
            return;
        }

        // Reset errors.
        mEmailView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            showProgress(true);

            // Sets up a new login task with the provide login form
            mAuthTask = new UserLoginTask(email, password);
            try {
                // Executes login task
                mAuthTask.execute();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // Verifies if email is valid
    private boolean isEmailValid(String email) {
        return email.contains("@");
    }

    // Verifies if password (4-digit PIN) is valid
    private boolean isPasswordValid(String pin) {
        if(pin.length()==4) {
            try {
                Integer.parseInt(pin);
                return true;
            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }

    // Verifies if phone number is valid
    private boolean isPhoneValid(String phone){
        try{
            Long.parseLong(phone.replace("-", "").replace("(", "")
                    .replace(")", ""));
            return true;
        } catch (Exception e){
            return false;
        }
    }

    // Shows the progress UI and hides the login form.
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // Honeycomb APIs allow for easier animations. Used to fade the progress spinner.
        int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

        mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

        mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
        mProgressView.animate().setDuration(shortAnimTime).alpha(
                show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
    }

    // Initiates loading process in case there exists saved information
    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new CursorLoader(this,
                // Retrieve data rows for the device user's 'profile' contact.
                Uri.withAppendedPath(ContactsContract.Profile.CONTENT_URI,
                        ContactsContract.Contacts.Data.CONTENT_DIRECTORY), ProfileQuery.PROJECTION,

                // Select only email addresses.
                ContactsContract.Contacts.Data.MIMETYPE +
                        " = ?", new String[]{ContactsContract.CommonDataKinds.Email
                .CONTENT_ITEM_TYPE},

                // Show primary email addresses first. Note that there won't be
                // a primary email address if the user hasn't specified one.
                ContactsContract.Contacts.Data.IS_PRIMARY + " DESC");
    }

    // Retrieves result of loading process and stores any returned information
    @Override
    public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) {
        List<String> emails = new ArrayList<>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            emails.add(cursor.getString(ProfileQuery.ADDRESS));
            cursor.moveToNext();
        }

        addEmailsToAutoComplete(emails);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> cursorLoader) {

    }

    // Uses results from loading process to autocomplete email if possible
    private void addEmailsToAutoComplete(List<String> emailAddressCollection) {
        //Create adapter to tell the AutoCompleteTextView what to show in its dropdown list.
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(LoginActivity.this,
                        android.R.layout.simple_dropdown_item_1line, emailAddressCollection);

        mEmailView.setAdapter(adapter);
    }

    // Query used to retrieve saved emails in loading process
    private interface ProfileQuery {
        String[] PROJECTION = {
                ContactsContract.CommonDataKinds.Email.ADDRESS,
                ContactsContract.CommonDataKinds.Email.IS_PRIMARY,
        };

        int ADDRESS = 0;
        int IS_PRIMARY = 1;
    }

    // Launches main activity once login is finished
    protected void startMainActivity() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    // Represents an asynchronous task which attempts the login and runs in a background thread
    @SuppressLint("StaticFieldLeak")
    private class UserLoginTask extends AsyncTask<Void, Void, Boolean> {

        // Login parameters
        private final String mEmail;
        private final String mPassword;

        // Constructor which declares login parameters as per the login form
        UserLoginTask(String email, String password) {
            mEmail = email;
            mPassword = password;
        }

        // Runs login process in a background thread so as to not interfere with the UI
        @Override
        protected Boolean doInBackground(Void... voids) {
            try {
                // Attempts to login using public server object from BufferingActivity
                BufferingActivity.servr.login(mEmail + ":" + mPassword);
            } catch (Exception e) {
                Log.e("Log-in Error", e.getMessage() + "\n" + e.getCause());
            }

            return true;
        }

        // Runs once the login attempt is finished
        @Override
        protected void onPostExecute(final Boolean success) {
            mAuthTask = null;
            showProgress(false);

            // Successfully logs in and launches main activity if the server object successfully
            // executed the login attempt, as indicated by the public static user of the class
            if (ServerConnection.user != null) {
                startMainActivity();
                finish();
            } else {
                // Sends error message if otherwise
                mPasswordView.setError(getString(R.string.error_incorrect_credentials));
                mPasswordView.requestFocus();
            }
        }

        // Cancels login
        @Override
        protected void onCancelled() {
            mAuthTask = null;
            showProgress(false);
        }
    }
}


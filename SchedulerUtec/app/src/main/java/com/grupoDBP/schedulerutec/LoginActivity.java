package com.grupoDBP.schedulerutec;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;

public class LoginActivity extends AppCompatActivity {

    // CONSTANTS
    public static final String EXTRA_CALLING_MENU = "EXTRA_CALLING_MENU";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void onClickLoginSubmit(View view){
        EditText idInput = findViewById(R.id.login_id);
        EditText passwordInput = findViewById(R.id.login_password);
        String id_input = idInput.getText().toString();
        String password_input = passwordInput.getText().toString();

        try {
            boolean loggedIn = RequestHandeler.loginRequest(id_input, password_input);
            if (loggedIn) {
                // Success
                Toast.makeText(this, getString(R.string.generic_successful_login), Toast.LENGTH_SHORT).show();
                finish();
            } else {
                // Handle error
                Toast.makeText(this, getString(R.string.generic_invalid_credentials), Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            // Handle error
            Toast.makeText(this, getString(R.string.generic_connection_error), Toast.LENGTH_SHORT).show();
            Log.w(this.getClass().getName(), "Error processing API response in login request");
            e.printStackTrace();
        }
    }


    public void crearcuenta(View view) {
        Intent crearcuenta = new Intent(Intent.ACTION_VIEW, Uri.parse(ConnectionUtils.getRegisterUrl()));
        startActivity(crearcuenta);

    }
}
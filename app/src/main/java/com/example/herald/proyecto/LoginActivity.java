package com.example.herald.proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;


public class LoginActivity extends AppCompatActivity {

    private static final int RC_SIGN_IN = 0;
    //    int RC_SIGN_IN = 0;
    SignInButton signin;
    GoogleSignInClient mGoogleSignInClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button btnLog = findViewById(R.id.btn_login);
        btnLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Snackbar.make(v, "Iniciando Sesion", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                sendLogin();
            }



        });

        signin = findViewById(R.id.sign_in_button);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.sign_in_button:
                        signIn();
                        break;
                }
            }
        });

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {

            Intent main = new Intent(this, MainActivity.class);
            startActivity(main);
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("error", "signInResult:failed code=" + e.getStatusCode());
        }
    }


    private void sendLogin() {
        final EditText email = findViewById(R.id.txt_email);
        EditText password = findViewById(R.id.txt_password);
//        final TextView welcome = findViewById(R.id.welcome);
//        welcome.setVisibility(View.GONE);

        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.add("email", email.getText().toString());
        params.add("password", password.getText().toString());

        String mail = email.getText().toString();
        String pass = password.getText().toString();

        if(mail.length() == 0){
            Toast.makeText(LoginActivity.this, "Debes de ingresar un Email", Toast.LENGTH_SHORT).show();
        }

        if(pass.length() == 0){
            Toast.makeText(LoginActivity.this, "Debes de ingresar una Contraseña", Toast.LENGTH_SHORT).show();
        }

        if(mail.length() != 0 && pass.length() != 0){
            client.post(Utils.LOGIN_SERVICE, params, new JsonHttpResponseHandler(){
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    if(response.has("token")){
                        try {
                            Utils.TOKEN = response.getString("token");
                            Toast.makeText(LoginActivity.this, "Sesion Iniciada", Toast.LENGTH_SHORT).show();
//                        welcome.setVisibility(View.VISIBLE);
//                        welcome.setText("Bienvenido" + email.getText());
                            Intent main = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(main);
                        } catch (JSONException e) {
                            e.printStackTrace();

                        }
                    }
                    else{
                        Toast.makeText(LoginActivity.this, "Error de TOKEN", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }


    }


    public void onClick(View v){
        int id = v.getId();
        switch (id){
            case R.id.btn_register:{
                Intent register = new Intent(this, RegisterActivity.class);
                startActivity(register);
            }
//            case R.id.btn_login:{
//                Intent login = new Intent(this, ProductosActivity.class);
//                startActivity(login);
//            }
        }
    }

}
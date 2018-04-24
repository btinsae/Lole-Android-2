package fanos.com.lole.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;


import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.ErrorCodes;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Arrays;

import fanos.com.lole.R;

public class SplashActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    private static final int RC_SIGN_IN = 123;
    private static final int RC_ERROR = 321;
    private static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mAuth = FirebaseAuth.getInstance();
        splashScreeDelay();

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // RC_SIGN_IN is the request code you passed into startActivityForResult(...) when starting the sign in flow.
        if (requestCode == RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);

            // Successfully signed in
            if (resultCode == RESULT_OK) {
                //startActivity(MainActivity.createIntent(this, response));
                startActivity(new Intent(this, MainActivity.class));

                finish();
                return;
            } else {
                // Sign in failed
                if (response == null) {
                    // User pressed back button
                    Toast.makeText(this,"user pressed back button",Toast.LENGTH_LONG).show();
                    Log.d("LOGIN", "login null");
                    return;
                }

                if (response.getError().getErrorCode() == ErrorCodes.NO_NETWORK) {
                    Log.e("LOGIN", "Network error");
                    return;
                }

                if (response.getError().getErrorCode() == ErrorCodes.UNKNOWN_ERROR) {
                    Log.e("LOGIN", "Unknown error");
                    return;
                }
            }

            // showSnackbar(R.string.unknown_sign_in_response);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

//        if (!isLoggedIn()) {
//            login();
//        }
    }

    private boolean isLoggedIn() {
        return mAuth.getCurrentUser() != null;

    }

    private void login() {
        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(Arrays.asList(
                                new AuthUI.IdpConfig.EmailBuilder().build(),
                                new AuthUI.IdpConfig.PhoneBuilder().build(),
                                new AuthUI.IdpConfig.GoogleBuilder().build()
//                                new AuthUI.IdpConfig.FacebookBuilder().build(),
//                                new AuthUI.IdpConfig.TwitterBuilder().build()))
                        ))
                        .build(),
                RC_SIGN_IN);
    }

    @Override
    protected void onResume() {
        super.onResume();

        GoogleApiAvailability availability = GoogleApiAvailability.getInstance();
        final int PLAY_SERVICES_RESOLUTION_REQUEST = 123;
        int status = availability.isGooglePlayServicesAvailable(this);
        if (status != ConnectionResult.SUCCESS) {
            availability.getErrorDialog(this, status, PLAY_SERVICES_RESOLUTION_REQUEST, new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialogInterface) {
                    Log.i("GPS", "Google Play Service error dialog closed");
                }
            });
        }
        splashScreeDelay();
    }

    public void splashScreeDelay() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
               // if (isLoggedIn()) {
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
//                } else {
//                    login();
//                }
            }
        }, SPLASH_TIME_OUT);
    }
}

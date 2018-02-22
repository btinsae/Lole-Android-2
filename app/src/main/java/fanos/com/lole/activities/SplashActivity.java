package fanos.com.lole.activities;

import android.content.Intent;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
//        mAuth = FirebaseAuth.getInstance();
//        GoogleApiAvailability googleApiAvailability = GoogleApiAvailability.getInstance();
//        int status = googleApiAvailability.isGooglePlayServicesAvailable(this);
//        if (status == ConnectionResult.SERVICE_VERSION_UPDATE_REQUIRED) {
//            if (googleApiAvailability.isUserResolvableError(status)) {
//                googleApiAvailability.getErrorDialog(this, status, RC_ERROR, null);
//            }
//        }
//        if (mAuth == null) {
//            startActivityForResult(
//                    AuthUI.getInstance()
//                            .createSignInIntentBuilder()
//                            .setAvailableProviders(Arrays.asList(
//                                    new AuthUI.IdpConfig.EmailBuilder().build(),
//                                    new AuthUI.IdpConfig.PhoneBuilder().build()
//                            ))
//                            .setTosUrl("https://superapp.example.com/terms-of-service.html")
//                            .setPrivacyPolicyUrl("https://superapp.example.com/privacy-policy.html")
//                            .build(),
//                    RC_SIGN_IN);
//        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // RC_SIGN_IN is the request code you passed into startActivityForResult(...) when starting the sign in flow.
        if (requestCode == RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);

            // Successfully signed in
            if (resultCode == RESULT_OK) {
                IdpResponse idpResponse = IdpResponse.fromResultIntent(data);
                startActivity(new Intent(this, MainActivity.class)
                        .putExtra("my_token", idpResponse.getIdpToken()));
                //  startActivity(SignedInActivity.createIntent(this, response));
                finish();
                return;
            } else {
                // Sign in failed
                if (response == null) {
                    // User pressed back button
                    showSnackbar(R.string.sign_in_cancelled);
                    return;
                }

                if (response.getErrorCode() == ErrorCodes.NO_NETWORK) {
                    showSnackbar(R.string.no_internet_connection);
                    return;
                }

                if (response.getErrorCode() == ErrorCodes.UNKNOWN_ERROR) {
                    showSnackbar(R.string.unknown_error);
                    return;
                }
            }
        }
    }

    private void showSnackbar(int msg) {

        Snackbar.make(null, msg, Snackbar.LENGTH_SHORT).show();
    }
}

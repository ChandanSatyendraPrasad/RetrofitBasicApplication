package chandan.prasad.retrofitbasicapplication.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

import chandan.prasad.retrofitbasicapplication.R;
import chandan.prasad.retrofitbasicapplication.apiclient.ApiClient;
import chandan.prasad.retrofitbasicapplication.requestbyapp.UserRequest;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    EditText sname, semail, sphone, spassword;
    public String u_name, u_email, u_phone, u_password;
    Button button;
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sname = findViewById(R.id.name);
        semail = findViewById(R.id.email);
        sphone = findViewById(R.id.phone);
        spassword = findViewById(R.id.password);
        button = findViewById(R.id.button);
        linearLayout = findViewById(R.id.linlat);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                u_name = sname.getText().toString().trim();
                u_email = semail.getText().toString().trim();
                u_phone = sphone.getText().toString().trim();
                u_password = spassword.getText().toString().trim();
                if (validinput()) {
                    final String user_name = u_name;
                    final String user_email = u_email;
                    final String user_phone = u_phone;
                    final String user_password = u_password;

                    Call<UserRequest> userRequestCall = ApiClient.getUserService().saveUsers(user_name, user_email, user_phone, user_password);
                    userRequestCall.enqueue(new Callback<UserRequest>() {
                        @Override
                        public void onResponse(@NonNull Call<UserRequest> call, @NonNull Response<UserRequest> response) {
                            if (response.isSuccessful()) {
                                String stat= null;
                                if (response.body() != null) {
                                    stat = response.body().getStatus().toUpperCase();
                                }
                                String msg= null;
                                if (response.body() != null) {
                                    msg = response.body().getMessage().toUpperCase();
                                }
                                showMsg(stat, msg);
                            }
                        }

                        @Override
                        public void onFailure(@NonNull Call<UserRequest> call, @NonNull Throwable t) {
                            showMsg("Fail", t.getMessage());
                            System.out.println("Fail" + t.getMessage());
                        }
                    });

                }


            }
        });
    }


    private boolean validinput() {
        if (u_name.isEmpty() || u_email.isEmpty() || u_phone.isEmpty() || u_password.isEmpty()||u_password.length()>6) {
            showMsg("Error", "Kindly Fill Details Properly");
            return false;
        } else {
            return true;
        }
    }

    private void showMsg(String titel, String message) {
        Snackbar snackbar = Snackbar.make(linearLayout, titel + "\n" + message, Snackbar.LENGTH_SHORT);
        snackbar.setAction("Re-Try", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearText();
            }
        });
        snackbar.setTextColor(Color.RED);
        snackbar.setBackgroundTint(Color.TRANSPARENT);
        snackbar.setActionTextColor(Color.BLUE);
        snackbar.show();
    }

    private void clearText() {
        sname.setText("");
        semail.setText("");
        sphone.setText("");
        spassword.setText("");
    }
}
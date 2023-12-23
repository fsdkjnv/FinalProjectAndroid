package com.example.drawer.fragment.FragmentLogin_Logout.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.drawer.Data.MyDataSingleton;
import com.example.drawer.MainActivity;
import com.example.drawer.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class FragmentLogin extends Fragment {

    private EditText loginEmail, loginPassword;
    private Button loginButton;
    private FirebaseAuth auth;
    private SharedPreferences sharedPreferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        loginEmail = view.findViewById(R.id.login_email);
        loginPassword = view.findViewById(R.id.login_password);
        loginButton = view.findViewById(R.id.login_button);

        auth = FirebaseAuth.getInstance();
        sharedPreferences = getActivity().getSharedPreferences("loginPrefs", Context.MODE_PRIVATE);

        // Check if the user is already logged in
        if (sharedPreferences.getBoolean("loggedIn", false)) {
            String savedEmail = sharedPreferences.getString("userEmail", "");
            MyDataSingleton.getInstance().setUserEmail(savedEmail);

            // Auto-fill the email field
            loginEmail.setText(savedEmail);

            startActivity(new Intent(getActivity(), MainActivity.class));
            getActivity().finish();
        }

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = loginEmail.getText().toString();
                String pass = loginPassword.getText().toString();
                MyDataSingleton.getInstance().setUserEmail(email);

                if (!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    if (!pass.isEmpty()) {
                        auth.signInWithEmailAndPassword(email, pass)
                                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                    @Override
                                    public void onSuccess(AuthResult authResult) {
                                        // Save login status and user email
                                        SharedPreferences.Editor editor = sharedPreferences.edit();
                                        editor.putBoolean("loggedIn", true);
                                        editor.putString("userEmail", email);
                                        editor.apply();
                                        startActivity(new Intent(getActivity(), MainActivity.class));
                                        getActivity().finish();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(getActivity(), "Login Failed", Toast.LENGTH_SHORT).show();
                                    }
                                });
                    } else {
                        loginPassword.setError("Empty fields are not allowed");
                    }
                } else if (email.isEmpty()) {
                    loginEmail.setError("Empty fields are not allowed");
                } else {
                    loginEmail.setError("Please enter correct email");
                }
            }
        });

        return view;
    }
}

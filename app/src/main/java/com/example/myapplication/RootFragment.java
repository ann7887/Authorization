package com.example.myapplication;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.text.Editable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class RootFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "SUCCESS";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public RootFragment() {
        // Required empty public constructor
    }

    public static RootFragment newInstance(String param1, String param2) {
        RootFragment fragment = new RootFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_root, container, false);
        final EditText passwordEditText = view.findViewById(R.id.editTextTextPassword2);
        final EditText usernameEditText = view.findViewById(R.id.editTextUsername);
        final TextInputLayout usernameTextInput = view.findViewById(R.id.username_text_input);
        final TextInputLayout passwordTextInput = view.findViewById(R.id.password_text_input);
        Button btn = view.findViewById(R.id.next_button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isUsernameValid(usernameEditText.getText())) {
                    usernameTextInput.setError(getString(R.string.shr_error_username));
                }
                else if (!isPasswordValid(passwordEditText.getText())) {
                    passwordTextInput.setError(getString(R.string.shr_error_password));
                }
                else {
                    passwordTextInput.setError(null);
                    usernameTextInput.setError(null);
                    Bundle bundle = new Bundle();
                    bundle.putString(ARG_PARAM1, "correct username and password");
                    Navigation.findNavController(view).navigate(R.id.action_rootFragment_to_openedFragment, bundle);
                }
            }
        });
        usernameEditText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int j, KeyEvent keyEvent1) {
                if (isUsernameValid(usernameEditText.getText())) {
                    usernameTextInput.setError(null);
                }
                return false;
            }
        });
        passwordEditText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent2) {
                if (isPasswordValid(passwordEditText.getText())) {
                    passwordTextInput.setError(null);
                }
                return false;
            }
        });
        return view;

    }

    private boolean isPasswordValid(@Nullable Editable text) {
        return text != null && text.length() >= 8;
    }
    private boolean isUsernameValid(@Nullable Editable textt) {
        return textt != null && textt.length() >= 1;
    }
}
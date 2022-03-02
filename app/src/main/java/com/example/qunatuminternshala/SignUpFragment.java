package com.example.qunatuminternshala;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpFragment extends Fragment {

      EditText name , emailmain , phoneon, password;
      CheckBox checkBox;
      TextView alreadyaccountsignin;
      RelativeLayout registeruser;

    private FirebaseAuth firebaseAuth;

    @Override
    public void onViewCreated(@NonNull  View view, @Nullable  Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        alreadyaccountsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SignInFragment signInFragment = new SignInFragment();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fliout , signInFragment);
                transaction.commit();
            }
        });


        registeruser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String mail=emailmain.getText().toString().trim();
                String pword=password.getText().toString().trim();
                String pno = phoneon.getText().toString();
                String naam = name.getText().toString().trim();

                if(mail.isEmpty() || pword.isEmpty() || pno.isEmpty()|| naam.isEmpty()|| !Patterns.EMAIL_ADDRESS.matcher(mail).matches())
                {
                    Toast.makeText(getActivity(),"All Fields are Required",Toast.LENGTH_SHORT).show();
                }
                else if(pword.length()<7)
                {
                    Toast.makeText(getActivity(),"Password Should Greater than 7 Digits",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    /// registered the user to firebase


                    firebaseAuth.createUserWithEmailAndPassword(mail,pword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if(task.isSuccessful())
                            {
                                Toast.makeText(getActivity(),"Registration Successful",Toast.LENGTH_SHORT).show();
                                sendEmailVerification();
                            }
                            else
                            {
                                Toast.makeText(getActivity(),"Failed To Register",Toast.LENGTH_SHORT).show();
                            }


                        }
                    });

                }

            }
        });

    }


    private void sendEmailVerification()
    {
        FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();
        if(firebaseUser!=null)
        {
            firebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    Toast.makeText(getActivity(),"Verification Email is Sent,Verify and Log In Again",Toast.LENGTH_SHORT).show();
                    firebaseAuth.signOut();
                    getActivity().finish();
                    SignInFragment signInFragment = new SignInFragment();
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.mainlayout ,signInFragment);
                    transaction.commit();
                }
            });
        }

        else
        {
            Toast.makeText(getActivity(),"Failed To Send Verification Email",Toast.LENGTH_SHORT).show();
        }
    }

    public SignUpFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_sign_up, container, false);
        name = view.findViewById(R.id.name);
        emailmain = view.findViewById(R.id.emailmain);
        password = view.findViewById(R.id.password);
        phoneon = view.findViewById(R.id.phoneno);
        checkBox = view.findViewById(R.id.checkBox);
        alreadyaccountsignin = view.findViewById(R.id.alreadyaccountsignin);
        registeruser = view.findViewById(R.id.registeruser);
        ((AppCompatActivity)getActivity()).getSupportActionBar();
        firebaseAuth= FirebaseAuth.getInstance();
        return  view;
    }
}
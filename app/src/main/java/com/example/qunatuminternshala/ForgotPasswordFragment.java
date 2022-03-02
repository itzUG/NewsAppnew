package com.example.qunatuminternshala;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;


public class ForgotPasswordFragment extends Fragment {
     EditText forgotpassword;
     Button passwordrecoverbutton;
      RelativeLayout gobacktologin;
      FirebaseAuth firebaseAuth;


    @Override
    public void onViewCreated(@NonNull View view, @Nullable  Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        gobacktologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SignUpFragment signUpFragment = new SignUpFragment();
                FragmentTransaction transaction =getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fliout , signUpFragment);
                transaction.commit();
            }
        });


        passwordrecoverbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mail=forgotpassword.getText().toString().trim();
                if(mail.isEmpty())
                {
                    Toast.makeText(getActivity(),"Enter your mail first",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    //we have to send password recover email

                    firebaseAuth.sendPasswordResetEmail(mail).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if (task.isSuccessful())
                            {
                                Toast.makeText(getActivity(),"Mail Sent , You can recover your password using mail",Toast.LENGTH_SHORT).show();
                                SignUpFragment signUpFragment = new SignUpFragment();
                                FragmentTransaction transaction =getActivity().getSupportFragmentManager().beginTransaction();
                                transaction.replace(R.id.fliout , signUpFragment);
                                transaction.commit();
                            }
                            else
                            {
                                Toast.makeText(getActivity(),"Email is Wrong or Account Not Exist",Toast.LENGTH_SHORT).show();
                            }


                        }
                    });

                }
            }
        });




    }

    public ForgotPasswordFragment() {}



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_forgot_password, container, false);
        forgotpassword=view.findViewById(R.id.forgotpassword);
        passwordrecoverbutton=view.findViewById(R.id.passwordrecoverbutton);
        gobacktologin=view.findViewById(R.id.gobacktologin);
        firebaseAuth= FirebaseAuth.getInstance();
        return view;
      }
}
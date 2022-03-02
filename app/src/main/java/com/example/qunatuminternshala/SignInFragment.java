package com.example.qunatuminternshala;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;

public class SignInFragment extends Fragment {

EditText signinemail ,signinpassword;
TextView forgotpassword , registeringuser;
ImageView fblogin , googlelogin;
RelativeLayout loginuser , fbgoogle;
Button signinpage;
private FirebaseAuth firebaseAuth;


    @Override
    public void onViewCreated(@NonNull View view, @Nullable  Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        forgotpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ForgotPasswordFragment forgotPasswordFragment = new ForgotPasswordFragment();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fliout , forgotPasswordFragment);
                transaction.commit();
            }
        });


        registeringuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SignUpFragment signUpFragment = new SignUpFragment();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fliout , signUpFragment);
                transaction.commit();
            }
        });

        fblogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getActivity(), " WASNT ABEL TO IMPELEMT DUE TO TIME ISSUE", Toast.LENGTH_SHORT).show();
            }
        });

        googlelogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), " WASNT ABEL TO IMPELEMT DUE TO TIME ISSUE", Toast.LENGTH_SHORT).show();
            }
        });

   //////////////USER LOGIN IF ALREADY IF ACCOUNT EXIXTS//////////////


        loginuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mail=signinemail.getText().toString().trim();
                String password=signinpassword.getText().toString().trim();

                if(mail.isEmpty()|| password.isEmpty())
                {
                    Toast.makeText(getActivity(),"All Field Are Required",Toast.LENGTH_SHORT).show();

                }
                else
                {


                    firebaseAuth.signInWithEmailAndPassword(mail,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {


                            if(task.isSuccessful())
                            {
                                NewsFragment newsActivity = new NewsFragment();
                                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                                transaction.replace(R.id.fliout , newsActivity);
                                transaction.commit();
                            }
                            else
                            {
                                Toast.makeText(getActivity(),"Account Doesn't Exist",Toast.LENGTH_SHORT).show();
                            }


                        }
                    });






                }
            }
        });




    }




    public SignInFragment() {}

   @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_sign_in, container, false);
        signinemail = view.findViewById(R.id.signinemail);
        signinpassword = view.findViewById(R.id.signinpassword);
        forgotpassword = view.findViewById(R.id.forgotpassword);
        fblogin = view.findViewById(R.id.fblogin);
        googlelogin = view.findViewById(R.id.googlelogin);
        loginuser = view.findViewById(R.id.loginuser);
       signinpage = view.findViewById(R.id.signinpage);
        registeringuser = view.findViewById(R.id.registeringuser);
        firebaseAuth=FirebaseAuth.getInstance();
        FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();

        return  view;
  }
}
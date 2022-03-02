package com.example.qunatuminternshala;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;

import com.google.firebase.auth.FirebaseAuth;

public class WebView extends AppCompatActivity {

    Toolbar toolbar;
    android.webkit.WebView webView;
    TextView logout;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        logout = findViewById(R.id.logout);
        toolbar = findViewById(R.id.toolbar);
        webView = findViewById(R.id.webView);
        firebaseAuth = FirebaseAuth.getInstance();
        setSupportActionBar(toolbar);


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseAuth.signOut();
                Toast.makeText(WebView.this, "Signed Out Succesfully", Toast.LENGTH_SHORT).show();

                SignUpFragment signUpFragment = new SignUpFragment();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fliout , signUpFragment);
                transaction.commit();
            }
        });


        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(url);

    }
}
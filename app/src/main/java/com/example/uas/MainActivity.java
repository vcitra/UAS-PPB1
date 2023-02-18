package com.example.uas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.uas.Menu.MenuActivity;
import com.example.uas.HelperClasses.SliderAdapter;
import com.example.uas.Profile.SignupActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class MainActivity extends AppCompatActivity {

    //Variables
    ViewPager viewPager;
    LinearLayout dotsLayout;
    SliderAdapter sliderAdapter;
    TextView[] dots;
    TextView txtSignUp;
    Button imgNext;
    CardView login;
    private EditText emailTextView, passwordTextView;
    private Button Btn;
    private ProgressBar progressBar;
    private FirebaseAuth mAuth;
    int currentPos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        // taking instance of FirebaseAuth
        mAuth = FirebaseAuth.getInstance();

        //Hooks
        viewPager = findViewById(R.id.slider);
        dotsLayout = findViewById(R.id.dots);
        // initialising all views through id defined above
        emailTextView = findViewById(R.id.edit_email);
        passwordTextView = findViewById(R.id.edit_password);
        Btn = findViewById(R.id.next_btn);
        progressBar = findViewById(R.id.progressBar);
        txtSignUp = findViewById(R.id.txt_signup);
        login = findViewById(R.id.cardLogin);

        //Call adapter
        sliderAdapter = new SliderAdapter(this);
        viewPager.setAdapter(sliderAdapter);

        //Dots
        addDots(0);
        viewPager.addOnPageChangeListener(changeListener);

        // Set on Click Listener on Sign-in button
        Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                loginUserAccount();
            }
        });
    }

    private void loginUserAccount()
    {

        // show the visibility of progress bar to show loading
        progressBar.setVisibility(View.VISIBLE);

        // Take the value of two edit texts in Strings
        String email, password;
        email = emailTextView.getText().toString();
        password = passwordTextView.getText().toString();

        // validations for input email and password
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(),
                            "Please enter email!!",
                            Toast.LENGTH_LONG)
                    .show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(),
                            "Please enter password!!",
                            Toast.LENGTH_LONG)
                    .show();
            return;
        }

        // signin existing user
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(
                        new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(
                                    @NonNull Task<AuthResult> task)
                            {
                                if (task.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(),
                                                    "Login successful!!",
                                                    Toast.LENGTH_LONG)
                                            .show();

                                    // hide the progress bar
                                    progressBar.setVisibility(View.GONE);

                                    // if sign-in is successful
                                    // intent to home activity
                                    Intent intent
                                            = new Intent(MainActivity.this,
                                            MenuActivity.class);
                                    startActivity(intent);
                                }

                                else {

                                    // sign-in failed
                                    Toast.makeText(getApplicationContext(),
                                                    "Login failed!!",
                                                    Toast.LENGTH_LONG)
                                            .show();

                                    // hide the progress bar
                                    progressBar.setVisibility(View.GONE);
                                }
                            }
                        });
    }

    public void skip(View view) {
        startActivity(new Intent(this, MenuActivity.class));
        finish();
    }

    public void next(View view) {
        viewPager.setCurrentItem(currentPos + 1);
    }

    private void addDots(int position) {

        dots = new TextView[3];
        dotsLayout.removeAllViews();

        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);

            dotsLayout.addView(dots[i]);
        }

        if (dots.length > 0) {
            dots[position].setTextColor(getResources().getColor(R.color.black));
        }

    }

    //mengatur visibility View disetiap slide layout
    ViewPager.OnPageChangeListener changeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }
        @Override
        public void onPageSelected(int position) {
            addDots(position);
            currentPos = position;

            if (position == 0) {
                emailTextView.setVisibility(View.INVISIBLE);
                passwordTextView.setVisibility(View.INVISIBLE);
                Btn.setVisibility(View.INVISIBLE);
                txtSignUp.setVisibility(View.INVISIBLE);
                login.setVisibility(View.INVISIBLE);
            }else if (position == 1) {
                emailTextView.setVisibility(View.INVISIBLE);
                passwordTextView.setVisibility(View.INVISIBLE);
                Btn.setVisibility(View.INVISIBLE);
                txtSignUp.setVisibility(View.INVISIBLE);
                login.setVisibility(View.INVISIBLE);
            }else {
                emailTextView.setVisibility(View.VISIBLE);
                passwordTextView.setVisibility(View.VISIBLE);
                Btn.setVisibility(View.VISIBLE);
                txtSignUp.setVisibility(View.VISIBLE);
                login.setVisibility(View.VISIBLE);
                //doLogin(imgNext);
                txtSignUp.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent signup = new Intent(getBaseContext(), SignupActivity.class);
                        startActivity(signup);
                    }
                });



            }
        }


        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };




}



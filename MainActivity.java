package com.android.simoniel.journalapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements View.onClickListener,GoogleApiClient.onConnectionFailedListener{
    private LinearLayout prof_section;
    private Button logout,post;
    private TextView username,email;
    private SignInButton signin;
    private GoogleApiClient googleApiClient;
    private ImageView image;
    private static final REQ_CODE=9001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        prof_section=(LinearLayout)findViewById(R.id.prof_section);
        logout=(Button)findViewById(R.id.signup);
        post=(Button)findViewById(R.id.post);
        username=(TextView)findViewById(R.id.user);
        email=(TextView)findViewById(R.id.email);
        signin=(SignInButton)findViewById(R.id.signin);
        GoogleSignInOptions sign=new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        googleApiClient = new GoogleApiClient.Builder().enableAutoManage(this, this).addApi(AUTH.GOOGLE_SIGN_IN_API,sign).build();

        signup.setOnClickListener(this);
        post.setOnClickListener(this);
        logout.setOnClickListener(this);

        prof_section.setVisibility(View.GONE);


    }
    @Override
    protected void onClick(View v){
       switch (v.getId()){
           case R.id.signin:
               signIn();
               break;
           case R.id.signout:
               signOut();
               break;
       }
    }
    @Override
    protected void onConnectionFailed(@signNull ConnectionResult connectionResult){

    }
    protected void signIn(){
        Intent intent=Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
        startActivityForResult(intent,REQ_CODE)
    }
    protected void signOut(){
        Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback(new ResultCallback<status>) ;
        @Override
         public void onResult(@signNull status status){
            updateUI(false);
        }
    }
    protected void post(){

    }
    private void handleResult(GoogleSignInResult result){
      if (result.isSuccess()){
          GoogleSignInAccount account = result.getSignInAccount();
          String name = account.getDisplayName();
          String email=account.getEmail();
          String img_url=account.getPhotoUrl().toString();
          Name.setText(name);
          Email.setText(email);
          Glide.with(this).load(img_url).into(image);
          updateUI(true);
      }
      else
          updateUI(false);
    }
    protected void updateUI(boolean isLogin){
         if(isLogin){
             prof_section.setVisibility(View.VISIBLE);
             signin.setVisibilityt(View.GONE);
         }else {
             prof_section.setVisibility(View.GONE);
             signin.setVisibilityt(View.VISIBLE);
         }
    }
    @Override
    protected void onActivityResult(int requestcode, int resultCode, Intent data)
        super onCreate(requestcode, resultCode, data){
        if (requestcode==REQ_CODE){
            GoogleSignInResult result=Auth.GoogleSignInApi.getResultFromIntent(data);
            handleResult(result);
        }
    }
}

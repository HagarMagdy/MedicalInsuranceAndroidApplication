package com.codesignet.consalto.contactus.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.codesignet.consalto.R;
import com.codesignet.consalto.animateFragment.fragmentsView.animatedFragmentdown;
import com.codesignet.consalto.animateFragment.fragmentsView.animatedFragmentup;
import com.codesignet.consalto.contactus.data_access_layer.MailService;
import com.codesignet.consalto.contactus.pojo.ContactUs;
import com.codesignet.consalto.contactus.presenter.ContactUsPresenter;
import com.codesignet.consalto.contactus.view.ContactUsPresenterInterface;
import com.codesignet.consalto.contactus.view.ContactUsViewInterface;
import com.codesignet.consalto.home.Home.activity.HomeActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ContactUsActivity extends AppCompatActivity implements ContactUsViewInterface{
    boolean appearFlag=false;
    String Name,msg;
//    String regexPhone = "^[0-9]{5,15}$";
    String mailType;
    boolean validMsg=true;
    @BindView(R.id.NameTxt) EditText NameTxt ;
//    @BindView(R.id.emailTxt) EditText emailTxt ;
//    @BindView(R.id.phoneTxt) EditText phoneTxt ;
    @BindView(R.id.msgTxt) EditText msgTxt ;
    @BindView(R.id.counterTxt) TextView counterTxt;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)  {
        if (Integer.parseInt(android.os.Build.VERSION.SDK) > 5
                && keyCode == KeyEvent.KEYCODE_BACK
                && event.getRepeatCount() == 0) {
            Log.d("CDA", "onKeyDown Called");
            onBackPressed();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    public void onBackPressed() {
        finish();
        Intent setIntent = new Intent(this, HomeActivity.class);
        startActivity(setIntent);
    }


    @OnClick(R.id.submitMsg)
    public void submitMsg(){

        Name = NameTxt.getText().toString();
     //   email = emailTxt.getText().toString();
     //   phone = phoneTxt.getText().toString();
        msg=msgTxt.getText().toString();
        if (TextUtils.isEmpty(Name) || TextUtils.isEmpty(msg)) {

            emptyFields();
        } else
        {
//            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
//                invalidMail();
//            }
//            if(!phone.matches(regexPhone))
//            {
//                invalidPhone();
//            }

            msgTxt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    if (msgTxt.getText().length() < 20 || msgTxt.getText().length() > 200) {
                        invalidMsg();
                        validMsg=false;
                }

            }
        }


        });
            if(validMsg==true) {
           // ContactUs contactUsObject = new ContactUs(firstName,lastName,phone,email,msg);
           // presenterObject.sendMailPresenter(contactUsObject);
                if(mailType.equals("question")) {
                    Intent emailIntent = new Intent(Intent.ACTION_SEND);
                    emailIntent.setType("text/plain");
                    emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"medicalinsurance.mobile@gmail.com"});
                    emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Question To Medical Insurance");
                    emailIntent.putExtra(Intent.EXTRA_TEXT, "Dear Medical Insurance Company" + "\n" + msg + "\n" + Name );
                    try {
                        startActivity(Intent.createChooser(emailIntent, "Send mail..."));
                    } catch (android.content.ActivityNotFoundException ex) {
                        Toast.makeText(ContactUsActivity.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                    }
                }else if (mailType.equals("accept")){
                    Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                    photoPickerIntent.setType("image/*");
                    startActivityForResult(photoPickerIntent, 1);
                }

       }
     }
    }

    private final TextWatcher mTextEditorWatcher = new TextWatcher() {
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {
            //This sets a textview to the current length
            counterTxt.setText(String.valueOf(s.length()));
        }

        public void afterTextChanged(Editable s) {
        }
    };

    ContactUsPresenterInterface presenterObject;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
        ButterKnife.bind(this);
        counterTxt.setText("0");
        msgTxt.setFilters( new InputFilter[]{new InputFilter.LengthFilter(500)});
        NameTxt.setFilters( new InputFilter[]{new InputFilter.LengthFilter(50)});
        Intent mailIntent = getIntent();
        mailType = mailIntent.getStringExtra("typeMail");
        presenterObject = new ContactUsPresenter(this,getApplication().getApplicationContext());
        msgTxt.addTextChangedListener(mTextEditorWatcher);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            Uri photoUri = data.getData();
            if(photoUri!=null)
            {
                Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
                emailIntent.setType("application/image");
                emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{"medical.insurance.acceptance@gmail.com"});
                emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,"Image Acceptance");
                emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, "priscription content is \n"+ msg + "\n" + Name );
                emailIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse(String.valueOf(photoUri)));
                try {
                    startActivity(Intent.createChooser(emailIntent, "Send mail..."));

                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(ContactUsActivity.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }


    @Override
    public void emptyFields() {
        Toast.makeText(this,getResources().getString(R.string.empty_fields),Toast.LENGTH_LONG).show();
    }

    @Override
    public void invalidMail() {
        Toast.makeText(this,getResources().getString(R.string.invalid_mail),Toast.LENGTH_LONG).show();

    }

    @Override
    public void invalidPhone() {
        Toast.makeText(this,getResources().getString(R.string.invalid_phone),Toast.LENGTH_LONG).show();
    }
    @Override
    public void invalidMsg(){
        Toast.makeText(this,getResources().getString(R.string.invalid_msg),Toast.LENGTH_LONG).show();
    }


}

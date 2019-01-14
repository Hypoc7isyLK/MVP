package com.bwie.likuo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.likuo.bean.Result;
import com.bwie.likuo.core.DataCall;
import com.bwie.likuo.presenter.RegisterPresent;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends AppCompatActivity {

    @BindView(R.id.registereditorPhone)
    EditText registereditorPhone;
    @BindView(R.id.registereditorWord)
    EditText registereditorWord;
    @BindView(R.id.textregister)
    TextView textregister;
    @BindView(R.id.register)
    Button register;
    private RegisterPresent mRegisterPresent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.textregister, R.id.register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.textregister:
                startActivity(new Intent(this,MainActivity.class));
                finish();
                break;
            case R.id.register:
                String registerphone = registereditorPhone.getText().toString().trim();
                String registerpwd = registereditorWord.getText().toString().trim();
                boolean mobileNO = isMobileNO(registerphone);
                if (mobileNO == true){
                    mRegisterPresent = new RegisterPresent(new registerCall());
                    mRegisterPresent.request(registerphone,registerpwd);
                }else {
                    Toast.makeText(this, "请输入正确的手机号！", Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }


    private class registerCall implements DataCall<Result> {
        @Override
        public void success(Result data) {
            String status = data.getStatus();
            if (status.equals("0000")){
                Toast.makeText(RegisterActivity.this, data.getMessage(), Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(RegisterActivity.this, data.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void fail(Exception e) {

        }
    }


    public static boolean isMobileNO(String mobileNums) {

        String telRegex = "^((13[0-9])|(14[5,7,9])|(15[^4])|(18[0-9])|(17[0,1,3,5,6,7,8]))\\d{8}$";
        if (TextUtils.isEmpty(mobileNums)){
            return false;
        }else{
            return mobileNums.matches(telRegex);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mRegisterPresent = null;
    }
}

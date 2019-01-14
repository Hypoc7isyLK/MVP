package com.bwie.likuo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.likuo.bean.LoginBean;
import com.bwie.likuo.bean.Result;
import com.bwie.likuo.core.DataCall;
import com.bwie.likuo.presenter.LoginPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.editorPhone)
    EditText editorPhone;
    @BindView(R.id.editorWord)
    EditText editorWord;
    @BindView(R.id.textregister)
    TextView textregister;
    @BindView(R.id.login)
    Button login;
    private LoginPresenter mLoginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }

    @OnClick({R.id.textregister, R.id.login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.textregister:
                //点击跳转
                startActivity(new Intent(this,RegisterActivity.class));
                finish();
                break;
            case R.id.login:
                String phone = editorPhone.getText().toString().trim();
                String pwd = editorWord.getText().toString().trim();
                //正则验证
                boolean mobileNO = isMobileNO(phone);
                if (mobileNO == true){
                    mLoginPresenter = new LoginPresenter(new LoginCall());
                    mLoginPresenter.request(phone,pwd);
                }else {
                    Toast.makeText(this, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
                }


                break;
        }
    }
    //正则验证
    public static boolean isMobileNO(String mobileNums) {

        String telRegex = "^((13[0-9])|(14[5,7,9])|(15[^4])|(18[0-9])|(17[0,1,3,5,6,7,8]))\\d{8}$";
        if (TextUtils.isEmpty(mobileNums)){
            return false;
        }else{
                return mobileNums.matches(telRegex);
        }
    }


    //获取数据
    private class LoginCall implements DataCall<Result<LoginBean>> {
        @Override
        public void success(Result<LoginBean> data) {
            if (data.getStatus().equals("0000")) {
                Toast.makeText(MainActivity.this, data.getMessage(), Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this,HomeActivity.class));
                finish();
            }else {
                Toast.makeText(MainActivity.this, data.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void fail(Exception e) {

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLoginPresenter = null;
    }
}

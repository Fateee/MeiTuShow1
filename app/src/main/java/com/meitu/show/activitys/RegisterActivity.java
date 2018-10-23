package com.meitu.show.activitys;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.meitu.show.BaseActivity;
import com.meitu.show.R;
import com.meitu.show.activitys.home.activity.HomeActivity;
import com.meitu.show.model.RegisterModel;
import com.meitu.show.presenter.RegisterPresenter;
import com.meitu.show.utils.SharePreUtil;
import com.meitu.show.viewinf.RegisterIV;

import butterknife.BindView;
import butterknife.OnClick;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

public class RegisterActivity extends BaseActivity<RegisterPresenter> implements RegisterIV {

    @BindView(R.id.iv_register_bg)
    ImageView mRegisterBgIV;
    private String phone_number;
    private String cord_number;
    EventHandler eventHandler;
    private boolean flag = true;
    /**
     * 使用Handler来分发Message对象到主线程中，处理事件
     */
    Handler handler = new RegisterHandler();

    @BindView(R.id.user_name)
    EditText mEdtPhoneNumber;

    @BindView(R.id.user_name_tip)
    TextView mTvSendCaptcha;

    //验证码模块
    @BindView(R.id.codes)
    EditText mEdtCaptcha;

    @BindView(R.id.codes_tip)
    TextView mTvCaptchaNotReceived;

    @BindView(R.id.login)
    View mBtnLogin;

    @BindView(R.id.txt_main_title)
    TextView txtMainTitle;

    /**
     * 记录当前的CountDownTimer, 避免出现多个timer导致倒计时错乱的问题。
     */
    private CountDownTimer mCurrentCountDown;
    private RegisterPresenter mRegisterPresenter;
    private ProgressDialog mProgressDialog;

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context,RegisterActivity.class));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initBundle() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SMSSDK.unregisterEventHandler(eventHandler);
        handler.removeCallbacksAndMessages(null);
    }

    private void initEditText() {
        mEdtPhoneNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                checkLoginEnable();
                checkVerifyEnable();
            }
        });
        mEdtCaptcha.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                checkLoginEnable();
            }
        });
    }

    private void getVerificationCode() {
        if (judPhone(phone_number))//去掉左右空格获取字符串
        {
            SMSSDK.getVerificationCode("86", phone_number);
//            edit_cord.requestFocus();
        }
    }

    private void checkLoginEnable() {
        String account = getTextViewString(mEdtPhoneNumber);
        String verifyCode = getTextViewString(mEdtCaptcha);

//        boolean enable = ValidUtil.checkPhoneNum(account) && ValidUtil.checkCaptcha(verifyCode);
        boolean enable = judPhone(account) && judCord(verifyCode);
        mBtnLogin.setEnabled(enable);
        mBtnLogin.setAlpha(enable ? 0.9f : 0.7f);
    }

    private void checkVerifyEnable() {
        String account = getTextViewString(mEdtPhoneNumber);
        boolean enable = judPhone(account);
        mTvSendCaptcha.setEnabled(enable);
        mTvSendCaptcha.setAlpha(enable ? 0.9f : 0.7f);
    }

    public String getTextViewString(TextView view) {
        String result = "";
        try {
            return view.getText().toString();
        } catch (Exception e) {
            return result;
        }
    }

    private boolean judPhone(String phone) {
        if (TextUtils.isEmpty(phone)) {
//            Toast.makeText(RegisterActivity.this, "请输入您的电话号码", Toast.LENGTH_LONG).show();
//            mEdtPhoneNumber.requestFocus();
            return false;
        } else if (phone.length() != 11) {
//            Toast.makeText(RegisterActivity.this, "您的电话号码位数不正确", Toast.LENGTH_LONG).show();
//            mEdtPhoneNumber.requestFocus();
            return false;
        } else {
            phone_number = phone;
            String num = "[1][3456789]\\d{9}";
            if (phone_number.matches(num))
                return true;
            else {
                Toast.makeText(RegisterActivity.this, "请输入正确的手机号码", Toast.LENGTH_LONG).show();
                return false;
            }
        }
    }

    private boolean judCord(String verifyCode) {
        judPhone(phone_number);
        if (TextUtils.isEmpty(verifyCode)) {
//            Toast.makeText(RegisterActivity.this, "请输入您的验证码", Toast.LENGTH_LONG).show();
//            mEdtCaptcha.requestFocus();
            return false;
        } else if (verifyCode.trim().length() != 4) {
//            Toast.makeText(RegisterActivity.this, "您的验证码位数不正确", Toast.LENGTH_LONG).show();
//            mEdtCaptcha.requestFocus();

            return false;
        } else {
            cord_number = verifyCode.trim();
            return true;
        }

    }

    @Override
    protected int getContentView() {
        return R.layout.lg_fragment_login_with_phone;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected RegisterPresenter getPresenter() {
        mRegisterPresenter = new RegisterPresenter();
        return mRegisterPresenter;
    }

    @Override
    protected void initDatas() {
        //加载背景，
//        Glide.with(this)
//                .load(R.drawable.bg_register_a)
//                // 设置高斯模糊
//                .bitmapTransform(new BlurTransformation(this, 5))
//                .into(mRegisterBgIV);

//        getId();
        txtMainTitle.setVisibility(View.GONE);
        initEditText();
        eventHandler = new EventHandler() {
            public void afterEvent(int event, int result, Object data) {
                Message msg = new Message();
                msg.arg1 = event;
                msg.arg2 = result;
                msg.obj = data;
                handler.sendMessage(msg);
            }
        };
        SMSSDK.registerEventHandler(eventHandler);
    }

    @OnClick({R.id.codes_tip})
    void onNotReceiveClicked() {
//        if (mCaptchaPresenter != null) {
//            ParamsBuilder.create("notReceiveLoginRegisterVerifyCode").postEvent(getContext());
//            mCaptchaPresenter.onCaptchaNotReceived(getCurrentMobileNumber());
//        }
    }

    @OnClick({R.id.user_name_tip})
    void onSendCaptchaClicked() {
        getVerificationCode();
        onCaptchaSentSuccess(mEdtPhoneNumber, mTvSendCaptcha,
                true, 1);
    }

    @OnClick(R.id.login)
    void loginClicked() {
        String phoneNum = getTextViewString(mEdtPhoneNumber);
        String code = getTextViewString(mEdtCaptcha);

        if (judCord(code))
            SMSSDK.submitVerificationCode("86", phoneNum, code);
        flag = false;
    }

    @OnClick(R.id.txt_left_title)
    public void onViewClick(View v) {
        switch (v.getId()) {
            case R.id.txt_left_title:
                finish();
                break;
        }
    }

    private static final int CAPTCHA_MAX_WAIT_IN_MILLIS = 60 * 1000;

    public void onCaptchaSentSuccess(
            final View mEdtPhoneNumber, final TextView mBtnSendCaptcha,
            boolean showCountDown, int countDownTime) {


        //验证码发送成功后,立即将手机号码锁定, 60秒后才允许修改。
        //如果服务器返回了显示按钮的时间,则在该时间后显示出未收到验证码的帮助按钮。
        if (mEdtPhoneNumber != null) {
            mEdtPhoneNumber.setEnabled(false);
        }
        mBtnSendCaptcha.setEnabled(false);

        //当且仅当需要倒计时时才显示, 倒计时为0时不处理。
        if (showCountDown && countDownTime > 0) {
            if (mCurrentCountDown != null) {
                mCurrentCountDown.cancel();
            }
            //在60秒内需要显示倒计时
            mCurrentCountDown = new CountDownTimer(CAPTCHA_MAX_WAIT_IN_MILLIS, 1000) {

                @Override
                public void onFinish() {
                    mCurrentCountDown = null;
                    if (mEdtPhoneNumber != null) {
                        mEdtPhoneNumber.setEnabled(true);
                    }
                    mBtnSendCaptcha.setEnabled(true);
                    mBtnSendCaptcha.setText(R.string.uc_lib_bind_phone_btn_send_captcha_again);
//                    //60秒倒计时完成后"未收到"按钮将隐藏, 诱导用户点击重新获取。
//                    if (mTvCaptchaNotReceived != null) {
//                        mTvCaptchaNotReceived.setVisibility(View.GONE);
//                    }
                }

                @Override
                public void onTick(long millisUntilFinished) {
                    mBtnSendCaptcha.setText(getString(
                            R.string.uc_lib_bind_phone_btn_countdown,
                            millisUntilFinished / 1000));
                }
            }.start();
        }

//        if (showNotReceiveBtn) {
//            mTvCaptchaNotReceived.postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    mTvCaptchaNotReceived.setVisibility(View.VISIBLE);
//                }
//            }, showTime * 1000);
//        }
    }

    @Override
    public void notifyRegisterResult(RegisterModel.DataBean data) {
        SharePreUtil mSharePreUtil = SharePreUtil.getInstance(getApplicationContext());
        mSharePreUtil.saveUserInfo(data);
        HomeActivity.startActivity(this);
        finish();
    }

    @Override
    public void showLoading() {
        mProgressDialog = ProgressDialog.show(this, "", "", false, true);
        mProgressDialog.show();
    }

    @Override
    public void dismissLoading() {
        mProgressDialog.dismiss();
    }


    private class RegisterHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int event = msg.arg1;
            int result = msg.arg2;
            Object data = msg.obj;
            if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                if (result == SMSSDK.RESULT_COMPLETE) {
                    // 请注意，此时只是完成了发送验证码的请求，验证码短信还需要几秒钟之后才送达
                    boolean smart = (Boolean) data;
                    if (smart) {
                        Toast.makeText(getApplicationContext(), "该手机号已经注册过，请重新输入",
                                Toast.LENGTH_LONG).show();
                        mEdtPhoneNumber.requestFocus();
                        return;
                    }
                } else {
                    ((Throwable) data).printStackTrace();
                }
            } else if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                if (result == SMSSDK.RESULT_COMPLETE) {
//                    Toast.makeText(getApplicationContext(), "验证码输入正确",
//                            Toast.LENGTH_LONG).show();
                    //todo api
                    //todo ui 跳转
                    //todo token保存
                    String phoneNum = getTextViewString(mEdtPhoneNumber);
                    String code = getTextViewString(mEdtCaptcha);
                    mRegisterPresenter.postRegisterUser(phoneNum,code);
                } else {
                    Toast.makeText(getApplicationContext(), "验证码输入错误", Toast.LENGTH_LONG).show();
                }
            } else {
                if (flag) {
                    Toast.makeText(getApplicationContext(), "验证码获取失败请重新获取", Toast.LENGTH_LONG).show();
                    mEdtPhoneNumber.requestFocus();
                } else {
                    Toast.makeText(getApplicationContext(), "验证码输入错误", Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}

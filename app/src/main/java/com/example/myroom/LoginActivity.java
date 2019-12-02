package com.example.myroom;

import android.os.Bundle;
import android.content.Intent;

import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import com.kakao.auth.ISessionCallback;
import com.kakao.auth.Session;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.MeV2ResponseCallback;
import com.kakao.usermgmt.response.MeV2Response;
import com.kakao.util.exception.KakaoException;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "";
    SessionCallback callback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // getHashKey();

        setContentView(R.layout.activity_main);
        callback = new SessionCallback();
        Session.getCurrentSession().addCallback(callback);
        requestMe();

    }

    class SessionCallback implements ISessionCallback {
        @Override
        public void onSessionOpened() {
            UserManagement.getInstance().me(new MeV2ResponseCallback() {
                @Override
                public void onSessionClosed(ErrorResult errorResult) {

                }

                @Override
                public void onSuccess(MeV2Response result) {
                    //로그인에 성공하면 로그인한 사용자의 일련번호, 닉네임, 이미지url등을 리턴합니다.
                    //사용자 ID는 보안상의 문제로 제공하지 않고 일련번호는 제공합니다.

                    // Log.e("UserProfile", userProfile.toString());
                    // Log.e("UserProfile", userProfile.getId() + "");
                    Intent intent = new Intent(LoginActivity.this, SearchActivity.class);
                    startActivity(intent);

                }
            });
        }

        @Override
        public void onSessionOpenFailed(KakaoException exception) {
            Log.e("UserProfile", "Failed");
        }


    }
    public void requestMe() {
        //유저의 정보를 받아오는 함수
        UserManagement.getInstance().me(new MeV2ResponseCallback() {
            @Override
            public void onFailure(ErrorResult errorResult) {
                Log.e(TAG, "error message=" + errorResult);
//                super.onFailure(errorResult);
            }

            @Override
            public void onSuccess(MeV2Response result) {
                Log.e("UserProfile", result.toString());
                Log.e("UserProfile", result.getId() + "");
            }

            @Override
            public void onSessionClosed(ErrorResult errorResult) {
                Log.d(TAG, "onSessionClosed1 =" + errorResult);
            }

        });
    }

}

package com.example.administrator.myapp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.administrator.myapp.R;
import com.example.administrator.myapp.bean.Examinfo;
import com.example.administrator.myapp.utils.OkHttpUtils;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void test(View view) {
        OkHttpUtils<Examinfo> utils=new OkHttpUtils<>(getApplicationContext());
        String url="http://101.251.196.90:8080/JztkServer/examInfo";
        utils.url(url)
        .targetClass(Examinfo.class)
        .execute(new OkHttpUtils.OnCompleteListener<Examinfo>(){
            @Override
            public void onSuccess(Examinfo result) {
                Log.e("main","result="+result);
            }
            @Override
            public void onError(String error) {
                Log.e("main", "error= "+error );
            }
        });
        startActivity(new Intent(MainActivity.this,ExamActiviity.class));
    }

    public void exit(View view) {
        finish();
    }
}

package com.example.administrator.myapp;

import android.app.Application;
import android.util.Log;

import com.example.administrator.myapp.bean.Exam;
import com.example.administrator.myapp.bean.Examinfo;
import com.example.administrator.myapp.utils.OkHttpUtils;

import java.util.List;

/**
 * Created by Administrator on 2017/7/3.
 */

public class ExamApplication extends Application{
    Examinfo mExaminfo;
    List<Exam> eExamlist;
    static ExamApplication instance;



    @Override
    public void onCreate() {
        super.onCreate();
        instance=this;
        initData();

    }
    public static ExamApplication getInstance() {
        return instance;
    }

    public Examinfo getmExaminfo() {
        return mExaminfo;
    }

    public void setmExaminfo(Examinfo mExaminfo) {
        this.mExaminfo = mExaminfo;
    }

    public List<Exam> geteExamlist() {
        return eExamlist;
    }

    public void seteExamlist(List<Exam> eExamlist) {
        this.eExamlist = eExamlist;
    }

    private void initData(){
        OkHttpUtils<Examinfo> utils=new OkHttpUtils<>(instance);
        String url="http://101.251.196.90:8080/JztkServer/examInfo";
        utils.url(url)
                .targetClass(Examinfo.class)
                .execute(new OkHttpUtils.OnCompleteListener<Examinfo>(){
                    @Override
                    public void onSuccess(Examinfo result) {
                        Log.e("main","result="+result);
                        mExaminfo=result;
                    }
                    @Override
                    public void onError(String error) {
                        Log.e("main", "error= "+error );
    }
});

    }
}
package com.example.administrator.myapp;

import android.app.Application;
import android.util.Log;

import com.example.administrator.myapp.bean.Exam;
import com.example.administrator.myapp.bean.Examinfo;
import com.example.administrator.myapp.bean.Result;
import com.example.administrator.myapp.utils.OkHttpUtils;
import com.example.administrator.myapp.utils.ResultUtils;

import java.util.List;

/**
 * Created by Administrator on 2017/7/3.
 */

public class ExamApplication extends Application{
    Examinfo mExaminfo;
    List<Exam> mExamList;
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

    public List<Exam> getmExamlist() {
        return mExamList;
    }

    public void setmExamlist(List<Exam> mExamlist) {
        this.mExamList = mExamlist;
    }

    private void initData(){
        new Thread(new Runnable() {
            @Override
            public void run() {
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
                OkHttpUtils<String> utils1=new OkHttpUtils<String>(instance);
                String url2="http://101.251.196.90:8080/JztkServer/getQuestions?testType=rand";
                utils1.url(url2)
                        .targetClass(String.class)
                        .execute(new OkHttpUtils.OnCompleteListener<String>(){
                            @Override
                            public void onSuccess(String jsonStr) {
                            Result result= ResultUtils.getListResultFromJson(jsonStr);
                     if (result!=null && result.getError_code()==0){
                         List<Exam> list=result.getResult();
                         if (list!=null&&list.size()>0){//已下载到数据
                             mExamList=list;
                         }
                     }
                            }

                            @Override
                            public void onError(String error) {
                            Log.e("'main","error"+error);
                            }
                        });
            }
        }).start();


    }
}

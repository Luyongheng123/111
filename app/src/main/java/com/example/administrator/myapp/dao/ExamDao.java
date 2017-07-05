package com.example.administrator.myapp.dao;

import android.content.Intent;
import android.util.Log;

import com.example.administrator.myapp.ExamApplication;
import com.example.administrator.myapp.bean.Exam;
import com.example.administrator.myapp.bean.ExamInfo;
import com.example.administrator.myapp.bean.Result;
import com.example.administrator.myapp.utils.OkHttpUtils;
import com.example.administrator.myapp.utils.ResultUtils;

import java.util.List;

/**
 * Created by Administrator on 2017/7/5.
 */

public class ExamDao implements IExamDao{
public void loadExamInfo(){
    OkHttpUtils<ExamInfo> utils=new OkHttpUtils<>(ExamApplication.getInstance());
    String url="http://101.251.196.90:8080/JztkServer/examInfo";
    utils.url(url)
            .targetClass(ExamInfo.class)
            .execute(new OkHttpUtils.OnCompleteListener<ExamInfo>(){
                @Override
                public void onSuccess(ExamInfo result) {
                    Log.e("main","result="+result);
                    ExamApplication.getInstance().setExamInfo(result);
                    ExamApplication.getInstance()
                            .sendBroadcast(new Intent(ExamApplication.LOAD_EXAM_INFO)
                            );
                }
                @Override
                public void onError(String error) {Log.e("main", "error= "+error );
                }
            });
}
public void loadQuestionLists(){
    OkHttpUtils<String> utils1=new OkHttpUtils<String>(ExamApplication.getInstance());
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
                            ExamApplication.getInstance().setExamList(list);;

                        }
                    }
                }

                @Override
                public void onError(String error) {
                    Log.e("'main","error"+error);
                }
            });
}
}


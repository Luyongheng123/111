package com.example.administrator.myapp;

import android.app.Application;

import com.example.administrator.myapp.bean.Exam;
import com.example.administrator.myapp.bean.ExamInfo;
import com.example.administrator.myapp.biz.IExamBiz;

import java.util.List;

/**
 * Created by Administrator on 2017/7/3.
 */

public class ExamApplication extends Application{
    public static  String LOAD_EXAM_INFO="load_exam_info";
    public static  String LOAD_EXAM_QUESTION="load_exam_question";
    public static String LOAD_DATA_SUCCESS="load_exam_success";
    ExamInfo mExamInfo;
    List<Exam> mExamList;
    static ExamApplication instance;
IExamBiz biz;

    @Override
    public void onCreate() {
        super.onCreate();
        instance=this;

    }

    public static ExamApplication getInstance() {return instance;}

    public ExamInfo getExamInfo() {return mExamInfo;}

    public void setExamInfo(ExamInfo examInfo) {
        this.mExamInfo = examInfo;
    }

    public List<Exam> getExamList() {
        return mExamList;
    }

    public void setExamList(List<Exam> examList) {
        this.mExamList = examList;
    }
}

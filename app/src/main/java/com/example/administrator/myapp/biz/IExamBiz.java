package com.example.administrator.myapp.biz;

/**
 * Created by Administrator on 2017/7/5.
 */

public interface IExamBiz {
    void beginExam();
    void nextQuestion();
    void preQuestion();
    void commitExam();
}

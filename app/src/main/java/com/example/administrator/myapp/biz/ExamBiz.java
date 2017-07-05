package com.example.administrator.myapp.biz;

import com.example.administrator.myapp.dao.IExamDao;
import com.example.administrator.myapp.dao.ExamDao;
/**
 * Created by Administrator on 2017/7/5.
 */

public class ExamBiz implements  IExamBiz{

    IExamDao dao;
    public ExamBiz() {
        this.dao = new ExamDao();
    }
    @Override
    public void beginExam() {
        dao.loadExamInfo();
        dao.loadQuestionLists();
    }

    @Override
    public void nextQuestion() {

    }

    @Override
    public void preQuestion() {

    }

    @Override
    public void commitExam() {

    }
}

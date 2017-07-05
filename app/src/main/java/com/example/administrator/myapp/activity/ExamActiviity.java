package com.example.administrator.myapp.activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.myapp.ExamApplication;
import com.example.administrator.myapp.R;
import com.example.administrator.myapp.bean.Exam;
import com.example.administrator.myapp.bean.ExamInfo;
import com.example.administrator.myapp.biz.ExamBiz;
import com.example.administrator.myapp.biz.IExamBiz;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Administrator on 2017/7/2.
 */

public class ExamActiviity extends AppCompatActivity {
    TextView tvExamInfo, tvExmTitle, tv0p1, tv0p2, tv0p3, tv0p4;
    ImageView mImageView;
    IExamBiz biz;
    boolean isLoatExamInfo = false;
    boolean isLoatQuestion = false;
    LoadExamBroadcast mLoadExamBroadcast;
    LoadQuestionBroadcast mLoadQuestionBroadcast;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);
        mLoadExamBroadcast=new LoadExamBroadcast();
        mLoadQuestionBroadcast=new LoadQuestionBroadcast();
        setListener();
        initView();
        loadData();
    }
private  void setListener(){
    registerReceiver(mLoadExamBroadcast,new IntentFilter(ExamApplication.LOAD_EXAM_INFO));
    registerReceiver(mLoadQuestionBroadcast,new IntentFilter(ExamApplication.LOAD_EXAM_QUESTION));
}
    private void loadData() {
        biz = new ExamBiz();
        new Thread(new Runnable() {
            @Override
            public void run() {
                biz.beginExam();
            }
        }).start();
    }

    //    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
//        super.onCreate(savedInstanceState, persistentState);
//    }
    private void initView() {

        tvExamInfo = (TextView) findViewById(R.id.tv_examinfo);
        tvExmTitle = (TextView) findViewById(R.id.tv_exam_title);
        tv0p1 = (TextView) findViewById(R.id.tv_op1);
        tv0p2 = (TextView) findViewById(R.id.tv_op2);
        tv0p3 = (TextView) findViewById(R.id.tv_op3);
        tv0p4 = (TextView) findViewById(R.id.tv_op4);
        mImageView = (ImageView) findViewById(R.id.im_exam_image);

    }

    private void initData() {
        if (isLoatExamInfo && isLoatQuestion) {

            ExamInfo examinfo = ExamApplication.getInstance().getExamInfo();
            if (examinfo != null) {
                showData(examinfo);
            }
            List<Exam> examList = ExamApplication.getInstance().getExamList();
            if (examList != null) {
                showExam(examList);
            }
        }
    }

    private void showExam(List<Exam> examList) {
        Exam exam = examList.get(0);
        if (exam != null) {
            tvExamInfo.setText(exam.getQuestion());
            tv0p1.setText(exam.getItem1());
            tv0p2.setText(exam.getItem2());
            tv0p3.setText(exam.getItem3());
            tv0p4.setText(exam.getItem4());
            Picasso.with(ExamActiviity.this)
                    .load(exam.getUrl())
                    .into(mImageView);
        }
    }

    private void showData(ExamInfo examinfo) {
        tvExamInfo.setText(examinfo.toString());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mLoadExamBroadcast!=null){
            unregisterReceiver(mLoadExamBroadcast);
        }
        if (mLoadQuestionBroadcast!=null){
            unregisterReceiver(mLoadQuestionBroadcast);
        }
    }

    class LoadExamBroadcast extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            boolean isSuccess = intent.getBooleanExtra(ExamApplication.LOAD_DATA_SUCCESS, false);
            Log.e("LoadExamBroadcast","LoadExamBroadcast.isSuccess"+isSuccess);
            if (isSuccess) {
                isLoatExamInfo = true;
            }
initData();
        }
    }

    class LoadQuestionBroadcast extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            boolean isSuccess = intent.getBooleanExtra(ExamApplication.LOAD_DATA_SUCCESS, false);
            Log.e("LoadQuestionBroadcast","LoadQuestionBroadcast.isSuccess"+isSuccess);
            if (isSuccess) {
                isLoatQuestion = true;
            }

        }
    }
}
package com.example.administrator.myapp.activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.myapp.ExamApplication;
import com.example.administrator.myapp.R;
import com.example.administrator.myapp.bean.Exam;
import com.example.administrator.myapp.bean.Examinfo;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Administrator on 2017/7/2.
 */

public class ExamActiviity extends AppCompatActivity {
    TextView tvExamInfo,tvExmTitle,tv0p1,tv0p2,tv0p3,tv0p4;
    ImageView mImageView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);
       initView();
        initData();
    }

//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
//        super.onCreate(savedInstanceState, persistentState);
//    }
    private void initView(){

        tvExamInfo= (TextView) findViewById(R.id.tv_examinfo);
        tvExmTitle= (TextView) findViewById(R.id.tv_exam_title);
        tv0p1= (TextView) findViewById(R.id.tv_op1);
        tv0p2= (TextView) findViewById(R.id.tv_op2);
        tv0p3= (TextView) findViewById(R.id.tv_op3);
        tv0p4= (TextView) findViewById(R.id.tv_op4);
         mImageView=(ImageView) findViewById(R.id.im_exam_image);

    }



    private void initData() {
        Examinfo examinfo = ExamApplication.getInstance().getmExaminfo();
        if (examinfo != null) {
           showData(examinfo);
            }
          List<Exam>examList=ExamApplication.getInstance().getmExamlist();
        if (examList!=null){
            showExam(examList);
        }
        }
        private  void showExam(List<Exam> examList){
           Exam exam=examList.get(0);
            if (exam!=null){
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
    private void showData (Examinfo examinfo){
        tvExamInfo.setText(examinfo.toString());
    }
    }
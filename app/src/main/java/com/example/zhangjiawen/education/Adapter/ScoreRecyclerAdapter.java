package com.example.zhangjiawen.education.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.zhangjiawen.education.Info.CourseInfo;
import com.example.zhangjiawen.education.R;
import com.example.zhangjiawen.education.Holder.ScoreItemHolder;

import java.util.ArrayList;

/**
 * Created by zhangjiawen on 2017/1/23.
 */
public class ScoreRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    /**
     * 及格线
     */
    private static final int PASS_SCORE = 60;
    private Context context;
    private LayoutInflater layoutInflater;

    /**
     * 存放课程成绩信息实体的集合
     */
    private ArrayList<CourseInfo> courseInfoArrayList;

    public ScoreRecyclerAdapter(Context context, ArrayList<CourseInfo> courseInfoArrayList) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.courseInfoArrayList = courseInfoArrayList;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ScoreItemHolder scoreItemHolder = new ScoreItemHolder(layoutInflater.inflate(R.layout.score_item_layout, parent, false));
//        ScoreItemHolder scoreItemHolder = new ScoreItemHolder(layoutInflater.from(parent.getContext()).inflate(R.layout.score_item_layout, parent, false));
        return scoreItemHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
//        //从list集合中取出数据绑定到view
//        ((ScoreItemHolder) holder).getTextView_scoreName().setText(courseInfoArrayList.get(position).getCourse_name());
//        ((ScoreItemHolder) holder).getTextView_score().setText(courseInfoArrayList.get(position).getCourse_score());
//        //如果分数没有达到及格线，则显示红色，否则显示绿色
//        ((ScoreItemHolder) holder).getTextView_score().setTextColor(Integer.parseInt(((ScoreItemHolder) holder).getTextView_score().getText().toString()) < PASS_SCORE ? context.getResources().getColor(R.color.score_failed) : context.getResources().getColor(R.color.score_pass));
//        ((ScoreItemHolder) holder).getTextView_scoreNumber().setText(courseInfoArrayList.get(position).getCourse_code());
//        ((ScoreItemHolder) holder).getTextView_scoreAveragePoint().setText(courseInfoArrayList.get(position).getCourse_average_point());
//        ((ScoreItemHolder) holder).getTextView_scoreCredit().setText(courseInfoArrayList.get(position).getCourse_credit());
//        ((ScoreItemHolder) holder).getTextView_scoreNature().setText(courseInfoArrayList.get(position).getCourse_nature());

        //从list集合中取出数据绑定到view
        ((ScoreItemHolder) holder).getTextView_scoreName();
        ((ScoreItemHolder) holder).getTextView_score();
        ((ScoreItemHolder) holder).getTextView_score();
        ((ScoreItemHolder) holder).getTextView_scoreNumber();
        ((ScoreItemHolder) holder).getTextView_scoreAveragePoint();
        ((ScoreItemHolder) holder).getTextView_scoreCredit();
        ((ScoreItemHolder) holder).getTextView_scoreNature();

    }

    @Override
    public int getItemCount() {
//        return courseInfoArrayList.size();
        return 5;
    }
}
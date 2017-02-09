package com.example.zhangjiawen.education.Holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.zhangjiawen.education.R;

/**
 * Created by zhangjiawen on 2017/2/8.
 */
public class TrainItemHolder extends RecyclerView.ViewHolder {
    /**
     * 课程名字
     */
    private TextView textView_scoreName_train;
    /**
     * 课程成绩
     */
    private TextView textView_score;
    /**
     * 课程代码
     */
    private TextView textView_scoreNumber_train;

    /**
     * 课程性质
     */
    private TextView textView_scoreNature_train;
    /**
     * 课程学分
     */
    private TextView textView_scoreCredit_train;
    /**
     * 课程绩点
     */
    private TextView textView_scoreAveragePoint;

    public TrainItemHolder(View itemView) {
        super(itemView);
        this.textView_scoreName_train = (TextView) itemView.findViewById(R.id.textView_scoreName_train);
//        this.textView_score = (TextView) itemView.findViewById(R.id.textView_score);
        this.textView_scoreNumber_train = (TextView) itemView.findViewById(R.id.textView_scoreNumber_train);
        this.textView_scoreNature_train = (TextView) itemView.findViewById(R.id.textView_scoreNature_train);
        this.textView_scoreCredit_train = (TextView) itemView.findViewById(R.id.textView_scoreCredit_train);
//        this.textView_scoreAveragePoint = (TextView) itemView.findViewById(R.id.textView_scoreAveragePoint);

    }

    public TextView getTextView_scoreName_train() {
        return textView_scoreName_train;
    }

//    public TextView getTextView_score() {
//        return textView_score;
//    }

    public TextView getTextView_scoreNumber_train() {
        return textView_scoreNumber_train;
    }

    public TextView getTextView_scoreNature_train() {
        return textView_scoreNature_train;
    }

    public TextView getTextView_scoreCredit_train() {
        return textView_scoreCredit_train;
    }

//    public TextView getTextView_scoreAveragePoint() {
//        return textView_scoreAveragePoint;
//    }


}

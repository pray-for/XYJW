package com.example.zhangjiawen.education.Holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.zhangjiawen.education.R;

/**
 * Created by zhangjiawen on 2017/1/23.
 */
public class ScoreItemHolder extends RecyclerView.ViewHolder {
    /**
     * 课程名字
     */
    private TextView textView_scoreName;
    /**
     * 课程成绩
     */
    private TextView textView_score;
    /**
     * 课程代码
     */
    private TextView textView_scoreNumber;

    /**
     * 课程性质
     */
    private TextView textView_scoreNature;
    /**
     * 课程学分
     */
    private TextView textView_scoreCredit;
    /**
     * 课程绩点
     */
    private TextView textView_scoreAveragePoint;

    public ScoreItemHolder(View itemView) {
        super(itemView);
        this.textView_scoreName = (TextView) itemView.findViewById(R.id.textView_scoreName);
        this.textView_score = (TextView) itemView.findViewById(R.id.textView_score);
        this.textView_scoreNumber = (TextView) itemView.findViewById(R.id.textView_scoreNumber);
        this.textView_scoreNature = (TextView) itemView.findViewById(R.id.textView_scoreNature);
        this.textView_scoreCredit = (TextView) itemView.findViewById(R.id.textView_scoreCredit);
        this.textView_scoreAveragePoint = (TextView) itemView.findViewById(R.id.textView_scoreAveragePoint);

        textView_scoreName.setText("1");
        textView_score.setText("1");
        textView_scoreNumber.setText("1");
        textView_scoreNature.setText("1");
        textView_scoreCredit.setText("1");
        textView_scoreAveragePoint.setText("1");

    }

    public TextView getTextView_scoreName() {
        return textView_scoreName;
    }

    public TextView getTextView_score() {
        return textView_score;
    }

    public TextView getTextView_scoreNumber() {
        return textView_scoreNumber;
    }

    public TextView getTextView_scoreNature() {
        return textView_scoreNature;
    }

    public TextView getTextView_scoreCredit() {
        return textView_scoreCredit;
    }

    public TextView getTextView_scoreAveragePoint() {
        return textView_scoreAveragePoint;
    }


}

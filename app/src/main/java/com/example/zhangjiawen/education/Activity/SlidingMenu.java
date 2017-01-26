package com.example.zhangjiawen.education.Activity;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import com.example.zhangjiawen.education.R;

/**
 * Created by zhangjiawen on 2017/1/11.
 */
public class SlidingMenu extends HorizontalScrollView {

    private LinearLayout mWapper;//固定
    private ViewGroup mMenu;
    private ViewGroup mContent;//内容区域
    private int mScreenWidth;//屏幕宽度
    private int mMenuRightpadding = 50;
    private boolean once;//默认初始值为false
    private int mMenuWidth;//内容区域的宽度
    private boolean isOpen;//标识当前的状态


    //未使用自定义属性时调用
    public SlidingMenu(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    //当使用了自定义属性时，调用
    public SlidingMenu(Context context , AttributeSet attrs , int defStyle){
        super(context , attrs , defStyle);

        //获取自定义的属性
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs ,
                R.styleable.SlidingMenu , defStyle , 0);
        int n = a.getIndexCount();
        for (int i = 0; i < n; i++){
            int attr = a.getIndex(i);
            switch (attr){
                case R.styleable.SlidingMenu_rightPadding:
                    mMenuRightpadding = a.getDimensionPixelOffset(attr , (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50,
                            context.getResources().getDisplayMetrics()));
                    break;
                default:
                    break;
            }
        }
        a.recycle();//回收

        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        mScreenWidth = outMetrics.widthPixels;//宽度

//        mMenuRightpadding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50,
//                context.getResources().getDisplayMetrics());//将50dp转化为像素值px

    }

    public SlidingMenu(Context context){
        this(context , null);
    }


    //设置子view的宽和高
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        if (!once){
            mWapper = (LinearLayout) getChildAt(0);
            mMenu = (ViewGroup) mWapper.getChildAt(0);
            mContent = (ViewGroup) mWapper.getChildAt(1);
            mMenuWidth = mMenu.getLayoutParams().width = mScreenWidth - mMenuRightpadding;
            mContent.getLayoutParams().width = mScreenWidth;
            once = true;
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    //通过设置偏移量，将menu隐藏
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (changed){
            this.scrollTo(mMenuWidth , 0);
        }
    }

    //实现手动的控制
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        switch (action){
            case MotionEvent.ACTION_UP:
                int scrollX = getScrollX();//隐藏在左边的宽度
                if (scrollX >= mMenuWidth / 2){
                    this.smoothScrollTo(mMenuWidth , 0);
                    isOpen = false;

                }else{
                    this.smoothScrollTo(0 , 0);
                    isOpen = true;
                }
                return true;
        }
        return super.onTouchEvent(ev);
    }

    //打开菜单
    public void openMenu(){
        if (isOpen){
            return;
        }
        this.smoothScrollTo(0 , 0);
        isOpen = true;
    }

    //关闭菜单
    public void closeMenu(){
        if (!isOpen){
            return;
        }
        this.smoothScrollTo(mMenuWidth , 0);
        isOpen = false;
    }

    //切换菜单的开关
    public void toggle(){
        if (isOpen){
            closeMenu();
        }else{
            openMenu();
        }
    }
}


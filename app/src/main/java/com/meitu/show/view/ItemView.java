package com.meitu.show.view;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.meitu.show.R;

public class ItemView extends ViewGroup {

    private final ImageView leftIcon;
    private final TextView leftTitle;
    private final View bottomLine;
    private final ImageView rightArrow;
    private boolean isShowRightArrow;
    private boolean isShowLeftIcon;
    private boolean isShowBottomLine;

    public ItemView(Context context) {
        super(context);
    }

    public ItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View v = View.inflate(context, R.layout.activity_usercenter, this);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.ItemView);
        isShowBottomLine = ta.getBoolean(R.styleable.ItemView_show_bottom_line, true);//得到是否显示底部下划线属性
        isShowLeftIcon = ta.getBoolean(R.styleable.ItemView_show_left_icon, true);//得到是否显示左侧图标属性标识
        isShowRightArrow = ta.getBoolean(R.styleable.ItemView_show_right_arrow, true);//得到是否显示右侧图标属性标识
        leftIcon.setBackground(ta.getDrawable(R.styleable.ItemView_left_icon));//设置左侧图标
        leftTitle.setText(ta.getString(R.styleable.ItemView_left_text));//设置左侧标题文字
        leftIcon.setVisibility(isShowLeftIcon ? View.VISIBLE : View.INVISIBLE);//设置左侧箭头图标是否显示 rightDesc.setText(ta.getString(R.styleable.ItemView_right_text));//设置右侧文字描述
        bottomLine.setVisibility(isShowBottomLine ? View.VISIBLE : View.INVISIBLE);//设置底部图标是否显示
        rightArrow.setVisibility(isShowRightArrow ? View.VISIBLE : View.INVISIBLE);//设置右侧箭头图标是否显示
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }

    //设置左侧图标
    public void setLeftIcon(int value) {
        Drawable drawable = getResources().getDrawable(value);
        leftIcon.setBackground(drawable);
    }

    //设置左侧标题文字
    public void setLeftTitle(String value) {
        leftTitle.setText(value);
    }

    //设置右侧描述文字
    public void setRightDesc(String value) {
        rightDesc.setText(value);
    }

    //设置右侧箭头
    public void setShowRightArrow(boolean value) {
        rightArrow.setVisibility(value ? View.VISIBLE : View.INVISIBLE);//设置右侧箭头图标是否显示
    }

    //设置是否显示下画线
    public void setShowBottomLine(boolean value) {
        bottomLine.setVisibility(value ? View.VISIBLE : View.INVISIBLE);//设置右侧箭头图标是否显示
    }

}

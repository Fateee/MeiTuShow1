package com.meitu.show.others;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v4.view.ViewPropertyAnimatorListenerAdapter;
import android.support.v4.view.animation.FastOutLinearInInterpolator;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;

import com.meitu.show.R;

public class UserCenterBehavior extends FloatingActionButton.Behavior {
//    //我们还可以加一个加速器实现弹射效果
//    private FastOutLinearInInterpolator folistener = new FastOutLinearInInterpolator();
//
//    public UserCenterBehavior(Context context, AttributeSet attr) {
//        super();
//    }
//
//    @Override
//    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child, View directTargetChild, View target, int nestedScrollAxes) {
//        //开始滑监听---当观察recyclerview开始发生滑动的时候回调
//        //nestedScrollAxes滑动关联的方向
//        return nestedScrollAxes == ViewGroup.SCROLL_AXIS_VERTICAL || super.onStartNestedScroll(coordinatorLayout, child, directTargetChild, target, nestedScrollAxes);
//    }
//
//    //正在滑出来
//    boolean isAnimatingOut = false;
//
//    @Override
//    public void onNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
//        //不断的调用
//        //判断滑动的方向 dyConsumed 某个方向的增量
//        if (dyConsumed > 0 && !isAnimatingOut && child.getVisibility() == View.VISIBLE) {
//            //fab划出去
//            animateOut(child);
//        } else if (dyConsumed < 0 && child.getVisibility() != View.VISIBLE) {
//            //fab划进来
//            animateIn(child);
//        }
//        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
//    }
//
//    //滑进来
//    private void animateIn(FloatingActionButton child) {
//        child.setVisibility(View.VISIBLE);
//        //属性动画
//        ViewCompat.animate(child).translationX(0).setInterpolator(folistener).setListener(null).start();
//    }
//
//    //滑出去
//    private void animateOut(FloatingActionButton child) {
//        //属性动画
//        //设置监听判断状态
//        ViewCompat.animate(child).translationY(child.getHeight()).setInterpolator(folistener).setListener(new ViewPropertyAnimatorListenerAdapter() {
//            @Override
//            public void onAnimationStart(View view) {
//                isAnimatingOut = true;
//                super.onAnimationStart(view);
//            }
//
//            @Override
//            public void onAnimationCancel(View view) {
//                isAnimatingOut = false;
//                super.onAnimationCancel(view);
//            }
//
//            @Override
//            public void onAnimationEnd(View view) {
//                view.setVisibility(View.INVISIBLE);
//                isAnimatingOut = false;
//                super.onAnimationEnd(view);
//            }
//        }).start();
//    }
//
//    @Override
//    public void onStopNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child, View target) {
//        super.onStopNestedScroll(coordinatorLayout, child, target);
//    }

//    private static final Interpolator INTERPOLATOR = new FastOutLinearInInterpolator();
    private static final Interpolator INTERPOLATOR = new FastOutSlowInInterpolator();
//    private static final Interpolator INTERPOLATOR = new LinearOutSlowInInterpolator();
    private boolean mIsAnimatingOut = false;

    public UserCenterBehavior(Context context, AttributeSet attrs) {
        super();
    }

    @Override
    public boolean onStartNestedScroll(final CoordinatorLayout coordinatorLayout, final FloatingActionButton child,
                                       final View directTargetChild, final View target, final int nestedScrollAxes) {
        // Ensure we react to vertical scrolling
        return nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL
                || super.onStartNestedScroll(coordinatorLayout, child, directTargetChild, target, nestedScrollAxes);
    }

    @Override
    public void onNestedScroll(final CoordinatorLayout coordinatorLayout, final FloatingActionButton child,
                               final View target, final int dxConsumed, final int dyConsumed,
                               final int dxUnconsumed, final int dyUnconsumed) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
        if (dyConsumed > 0 && !this.mIsAnimatingOut && child.getVisibility() == View.VISIBLE) {
            // User scrolled down and the FAB is currently visible -> hide the FAB
            animateOut(child);
        } else if (dyConsumed < 0 && child.getVisibility() != View.VISIBLE) {
            // User scrolled up and the FAB is currently not visible -> show the FAB
            animateIn(child);
        }
    }

    // Same animation that FloatingActionButton.Behavior uses to hide the FAB when the AppBarLayout exits
    private void animateOut(final FloatingActionButton button) {
        if (Build.VERSION.SDK_INT >= 14) {
            ViewCompat.animate(button).translationY(button.getHeight() + getMarginBottom(button)).setInterpolator(INTERPOLATOR).withLayer()
                    .setListener(new ViewPropertyAnimatorListener() {
                        public void onAnimationStart(View view) {
                            UserCenterBehavior.this.mIsAnimatingOut = true;
                        }

                        public void onAnimationCancel(View view) {
                            UserCenterBehavior.this.mIsAnimatingOut = false;
                        }

                        public void onAnimationEnd(View view) {
                            UserCenterBehavior.this.mIsAnimatingOut = false;
                            view.setVisibility(View.INVISIBLE);
                        }
                    }).start();
        } else {

        }
    }

    // Same animation that FloatingActionButton.Behavior uses to show the FAB when the AppBarLayout enters
    private void animateIn(FloatingActionButton button) {
        button.setVisibility(View.VISIBLE);
        if (Build.VERSION.SDK_INT >= 14) {
            ViewCompat.animate(button).translationY(0)
                    .setInterpolator(INTERPOLATOR).withLayer().setListener(null)
                    .start();
        } else {

        }
    }

    private int getMarginBottom(View v) {
        int marginBottom = 0;
        final ViewGroup.LayoutParams layoutParams = v.getLayoutParams();
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            marginBottom = ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin;
        }
        return marginBottom;
    }
}

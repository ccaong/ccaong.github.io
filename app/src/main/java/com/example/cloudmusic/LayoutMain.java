package com.example.cloudmusic;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

/**
 * Created by lsadmin on 2017/6/12.
 */

public class LayoutMain extends RelativeLayout {
    private ViewPager child_viewpager;
    private float startX;
    public LayoutMain(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    //返回TRUE在当前layout下进行操作
    public boolean onInterceptTouchEvent(MotionEvent event)
    {
        //获取点击事件
        int action = event.getAction();
        switch (action)
        {
            //按下
            case MotionEvent.ACTION_DOWN:
                //得到按下的坐标
                startX = event.getX();
                //阻止父层的View截获touch事件
                getParent().requestDisallowInterceptTouchEvent(true);

                break;
            //滑动，在此对里层viewpager的第一页和最后一页滑动做处理
            case MotionEvent.ACTION_MOVE:
                //若没动，如果是第一页或者最后一页，请求父类拦截touch事件
                if (startX == event.getX()){
                    if (0 == child_viewpager.getCurrentItem()
                            || child_viewpager.getCurrentItem() == child_viewpager.getAdapter().getCount() - 1)
                    {
                        getParent().requestDisallowInterceptTouchEvent(false);
                    }
                }
                //当里层viewpager已经是最后一页，此时继续向右滑（手指从右往左滑），请求父类拦截
                else if (startX > event.getX()){
                    if (child_viewpager.getCurrentItem() == child_viewpager
                            .getAdapter().getCount() - 1)
                    {
                        getParent().requestDisallowInterceptTouchEvent(false);
                    }
                }
                //里层viewpager已经是第一页，此时继续向左滑（手指从左往右滑），请求父类拦截
                else if (startX < event.getX()){
                    if (child_viewpager.getCurrentItem() == 0){
                        getParent().requestDisallowInterceptTouchEvent(false);
                    }
                } else{
                    getParent().requestDisallowInterceptTouchEvent(true);
                }
                break;
            case MotionEvent.ACTION_UP://抬起
            case MotionEvent.ACTION_CANCEL:
                getParent().requestDisallowInterceptTouchEvent(false);
                break;
        }
        return false;
    }
    //注入里层viewpager
    public void setChild_viewpager(ViewPager child_viewpager){
        this.child_viewpager = child_viewpager;
    }
}

package com.show.element;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Outline;
import android.graphics.Path;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewOutlineProvider;

import androidx.appcompat.widget.AppCompatImageView;

import com.google.android.material.imageview.ShapeableImageView;

public class RoundImageView extends ShapeableImageView {
    private float width, height;
    private int defaultRadius = 0;
    private int radius = 0;
    private int leftTopRadius;
    private int rightTopRadius;
    private int rightBottomRadius;
    private int leftBottomRadius;


    public RoundImageView(Context context) {
        this(context, null);
        init(context, null);
    }

    public RoundImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        init(context, attrs);
    }

    public RoundImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }


    private void init(Context context, AttributeSet attrs) {
        if (Build.VERSION.SDK_INT < 18) {
            setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }
        // 读取配置
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.RoundCornerImageView);
        radius = array.getDimensionPixelOffset(R.styleable.RoundCornerImageView_radius, defaultRadius);
        leftTopRadius = array.getDimensionPixelOffset(R.styleable.RoundCornerImageView_left_top_radius, defaultRadius);
        rightTopRadius = array.getDimensionPixelOffset(R.styleable.RoundCornerImageView_right_top_radius, defaultRadius);
        rightBottomRadius = array.getDimensionPixelOffset(R.styleable.RoundCornerImageView_right_bottom_radius, defaultRadius);
        leftBottomRadius = array.getDimensionPixelOffset(R.styleable.RoundCornerImageView_left_bottom_radius, defaultRadius);

        Log.w("radius", "init---" + radius);
        //如果四个角的值没有设置，那么就使用通用的radius的值。
        if (defaultRadius == leftTopRadius) {
            leftTopRadius = radius;
        }
        if (defaultRadius == rightTopRadius) {
            rightTopRadius = radius;
        }
        if (defaultRadius == rightBottomRadius) {
            rightBottomRadius = radius;
        }
        if (defaultRadius == leftBottomRadius) {
            leftBottomRadius = radius;
        }
        array.recycle();
    }


    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        width = getWidth();
        height = getHeight();
    }

    public void setRadius(int radius) {
        this.radius = radius;
        Log.w("radius", "====" + radius);
        leftTopRadius = radius;
        rightTopRadius = radius;
        rightBottomRadius = radius;
        leftBottomRadius = radius;

        ViewOutlineProvider provider = new ViewOutlineProvider() {

            @Override
            public void getOutline(View view, Outline outline) {
                outline.setRoundRect(0, 0, view.getWidth(), view.getHeight(), radius);
            }
        };

        setOutlineProvider(provider);

        setClipToOutline(true);

        invalidate();
    }

    public int getRadius() {
        return radius;
    }

}



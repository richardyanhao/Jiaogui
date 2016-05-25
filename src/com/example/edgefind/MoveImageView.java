package com.example.edgefind;

import android.app.ActionBar.LayoutParams;
import android.content.Context;
import android.util.*;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

public class MoveImageView extends ImageView { 
	private int getx,gety;
    public MoveImageView(Context context) { 
        super(context); 
    } 
    
 
    public MoveImageView(Context context, AttributeSet attrs) { 
        super(context, attrs, 0); 
    } 
 
    public MoveImageView(Context context, AttributeSet attrs, int defStyle) { 
        super(context, attrs, defStyle); 
    } 
 
    public void setLocation(int x, int y) { 
        this.setFrame(x, y+ this.getHeight() , x + this.getWidth(), y); //左侧顶部右侧底部
    } 
    public void setLoc(int x, int y) { 
        this.setFrame(x, y+ this.getHeight() , x + this.getWidth(), y); //左侧顶部右侧底部
    } 
    // 移动 
    public boolean autoMouse(MotionEvent event) { 
        boolean rb = false; 
        switch (event.getAction()) { 
        case MotionEvent.ACTION_MOVE: 
            this.setLocation((int) event.getX(), (int) event.getY()); 
            this.getx=(int) event.getX();
            this.gety=(int) event.getY();
            rb = true; 
            break; 
        } 
        return rb; 
    } 
    public int getpositX(){
    	return getx;
    }
    public int getpositY(){
    	return gety;
    }
} 

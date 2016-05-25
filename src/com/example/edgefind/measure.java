package com.example.edgefind;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.CameraBridgeViewBase;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.android.CameraBridgeViewBase.CvCameraViewFrame;
import org.opencv.android.CameraBridgeViewBase.CvCameraViewListener2;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class measure extends Activity implements CvCameraViewListener2{
	private static final String TAG = "OCVSample::Activity";

    private CameraBridgeViewBase mOpenCvCameraView;
    private boolean              mIsJavaCamera = false;
    private MenuItem             mItemSwitchCamera = null;
    private MenuItem             mItemSave = null;
    SharedPreferences store= null;
    private Toast mToast;
    //double x1,y1,x2,y2;

    private BaseLoaderCallback mLoaderCallback = new BaseLoaderCallback(this) {
        @Override
        public void onManagerConnected(int status) {
            switch (status) {
                case LoaderCallbackInterface.SUCCESS:
                {
                    Log.i(TAG, "OpenCV loaded successfully");
                    mOpenCvCameraView.enableView();
                } break;
                default:
                {
                    super.onManagerConnected(status);
                } break;
            }
        }
    };

    public measure() {
        Log.i(TAG, "Instantiated new " + this.getClass());
    }

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "called onCreate");
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        setContentView(R.layout.activity_main);

        if (mIsJavaCamera)
            mOpenCvCameraView = (CameraBridgeViewBase) findViewById(R.id.tutorial1_activity_java_surface_view);
        else
            mOpenCvCameraView = (CameraBridgeViewBase) findViewById(R.id.tutorial1_activity_native_surface_view);

        mOpenCvCameraView.setVisibility(SurfaceView.VISIBLE);

        mOpenCvCameraView.setCvCameraViewListener(this);
    }

    @Override
    public void onPause()
    {
        super.onPause();
        if (mOpenCvCameraView != null)
            mOpenCvCameraView.disableView();
    }

    @Override
    public void onResume()
    {
        super.onResume();
        OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_2_4_3, this, mLoaderCallback);
    }

    public void onDestroy() {
        super.onDestroy();
        if (mOpenCvCameraView != null)
            mOpenCvCameraView.disableView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.i(TAG, "called onCreateOptionsMenu");
        mItemSave = menu.add("保存");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String toastMesage = new String();
        Log.i(TAG, "called onOptionsItemSelected; selected item: " + item);
        
        

        return true;
    }

    public void onCameraViewStarted(int width, int height) {
    }

    public void onCameraViewStopped() {
    }

    public Mat onCameraFrame(CvCameraViewFrame inputFrame) {
    	Mat mRgba = inputFrame.rgba();
    	Size sizeRgba = mRgba.size();
    	int rows = (int) sizeRgba.height; int cols = (int) sizeRgba.width;
    	SharedPreferences SharedPreferencesSave= getSharedPreferences("edge", 
    			Context.MODE_WORLD_READABLE);  
    	double x1 = SharedPreferencesSave.getFloat("x1", 0.0f);
    	double x2 = SharedPreferencesSave.getFloat("x2", 0.0f);
    	double y1 = SharedPreferencesSave.getFloat("y1", 0.0f);
    	double y2 = SharedPreferencesSave.getFloat("y2", 0.0f);
    	double x3=cols/2+(x1-x2)/10;
    	double x4=cols/2-(x1-x2)/10;
    	double middleV=rows/2;
    	//Core.line(mRgba, new Point(x1,y1), new Point(x2,y2), new Scalar(255,0,0),3);
    	//Core.line(mRgba, new Point(x3,rows/2), new Point(x4,rows/2), new Scalar(255,0,0),3);
    	Core.line(mRgba, new Point(x3,rows/2+(x1-x2)/3), new Point(x3,rows/2-(x1-x2)/3), new Scalar(255,0,0),1);
    	Core.line(mRgba, new Point(x4,rows/2+(x1-x2)/3), new Point(x4,rows/2-(x1-x2)/3), new Scalar(255,0,0),1);
    	return mRgba;
    }
}
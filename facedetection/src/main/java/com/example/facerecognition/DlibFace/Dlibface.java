package com.example.facerecognition.DlibFace;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Environment;
import android.util.Log;

import com.example.facedetection.R;
import com.tzutalin.dlib.FaceDet;
import com.tzutalin.dlib.VisionDetRet;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Dlibface {

    public  FaceDet mFaceDet = null;
    public Dlibface(Context context){

        if (mFaceDet == null) {
            if (!new File(Dlibface.GetModelPath()).exists()) {
                FileUtils.copyFileFromRawToOthers(context,R.raw.face, Dlibface.GetModelPath());
            }
            if (new File(GetModelPath()).exists()) {
                mFaceDet = new FaceDet(GetModelPath());
                Log.e("GetModelPath()::",GetModelPath());
            }
        }
    }
    public  ArrayList<Point> RunFaceDet(Bitmap bmp,double rx,double ry){
        ArrayList<Point> Points=new ArrayList();
        if (mFaceDet == null||bmp==null) {   Log.e("InputImage==null()::",GetModelPath()); return null;}
        Bitmap Image = Bitmap.createScaledBitmap(bmp, (int)(bmp.getWidth()*rx), (int)(bmp.getHeight()*ry), true);
        List<VisionDetRet>  results=mFaceDet.detect(Image);
        for (final VisionDetRet ret : results) {
            ArrayList<Point> landmarks = ret.getFaceLandmarks();
            for(int i=0;i<landmarks.size();i++){
                    Points.add(landmarks.get(i));
            }
           return Points;
        }
        return Points;
    }
    public  static String GetModelPath() {
        File sdcard = Environment.getExternalStorageDirectory();
        String targetPath = sdcard.getAbsolutePath() + File.separator + "face.dat";
        return targetPath;
    }



}

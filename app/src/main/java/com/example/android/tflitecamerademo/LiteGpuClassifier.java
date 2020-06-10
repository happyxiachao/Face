package com.example.android.tflitecamerademo;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;

import org.tensorflow.lite.Interpreter;
import org.tensorflow.lite.Tensor;
import org.tensorflow.lite.gpu.GpuDelegate;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public abstract class LiteGpuClassifier {
    public final Interpreter.Options tfliteOptions = new Interpreter.Options();
    /** The loaded TensorFlow Lite model. */
    public MappedByteBuffer tfliteModel;
    /** An instance of the driver class to run model inference with Tensorflow Lite. */
    public Interpreter tflite;
    /** holds a gpu delegate */
    GpuDelegate gpuDelegate = null;

    /** Initializes an {@code ImageClassifier}. */
    public LiteGpuClassifier(Activity activity, String ModelPath, boolean ISGPU) throws IOException {
        tfliteModel = loadModelFile(activity, ModelPath);
        if(ISGPU){this.useGpu();}else{this.useCPU();}
    }

    /** Memory-map the model file in Assets. */
    public MappedByteBuffer loadModelFile(Activity activity, String ModelPath) throws IOException {
        AssetFileDescriptor fileDescriptor = activity.getAssets().openFd(ModelPath);
        FileInputStream inputStream = new FileInputStream(fileDescriptor.getFileDescriptor());
        FileChannel fileChannel = inputStream.getChannel();
        long startOffset = fileDescriptor.getStartOffset();
        long declaredLength = fileDescriptor.getDeclaredLength();
        return fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffset, declaredLength);
    }

    public void useGpu() {
        if (gpuDelegate == null) {
            gpuDelegate = new GpuDelegate();
            tfliteOptions.addDelegate(gpuDelegate);
            tfliteOptions.setAllowFp16PrecisionForFp32(true);
            tflite = new Interpreter(tfliteModel, tfliteOptions);
        }
    }
    public void run(Object input, Object output) {
        Tensor out= tflite.getOutputTensor(0);
        tflite.run(input,out);
    }
    public void useCPU() {
        tflite = new Interpreter(tfliteModel, tfliteOptions);
    }

    public void setNumThreads(int numThreads) {
        tfliteOptions.setNumThreads(numThreads);
    }

    /** Closes tflite to release resources. */
    public void close() {
        tflite.close();
        tflite = null;
        if (gpuDelegate != null) {
            gpuDelegate.close();
            gpuDelegate = null;
        }
        tfliteModel = null;
    }

}

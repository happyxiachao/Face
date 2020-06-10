<div align="center">
<img style="float:left;" width="250" height="auto" src="https://github.com/azemZejnil/ObjectDetection-Android/blob/master/docs/imgs/Screenshot_20180725-101017.png">
<img style="float:left;" width="250" height="auto" src="https://github.com/azemZejnil/ObjectDetection-Android/blob/master/docs/imgs/Screenshot_20180725-101057.png">
<img style="float:left;" width="250" height="auto" src="https://github.com/azemZejnil/ObjectDetection-Android/blob/master/docs/imgs/Screenshot_20180725-101258.png">
</div>
<br>

# Tensorflow's object detection model.

<br>
Tensorflow model trained to detect two kinds of object: Car (Mercedes benz, A class 2000 and not to be confused, it is only type of car that can be recognized by this model.) and banana.

<br>

### About Tensorflow

TensorFlow is a multipurpose machine learning framework. Object detection is just one of many amazing possibilities from this framework.
<br>
Read more here:
<br>
https://www.tensorflow.org/

<br>

### Versioning

For some reason, back when I trained this model, Tensorflow 1.8 version has had some difficulties with 
successful training of object detection models and it was advised to use 1.7
<br>
I used 3.6 python and Pillow library for images manipulation.
<br>
This is important to note because as it seems, using different Tensorflow version in Android Studio can lead to errors.
You must use same version of Tensorflow both in training and in Android Studio
### Formating and usage in Android app

There is Google's official repository on Tensorflow:
<br>
https://github.com/tensorflow
<br>
There you can find directions for usage of any of the Tensorflow trained models.
<br>
In Android application, we can use both .pb and .tflite files. 
After the training is finished their size is usually about 50mb, but both formats size can be 
significantly lowered by running the provided scripts for that matter. 

<br>

### Training

For the training purpose, you need to provide images and labels.
Now, obviously, the more images you provide, the better detection will be.
I provided images of the same car but in different colors and from different angles and that is why
on the upper images you can see successful detection of my car from both frot and back, and in different color.
<br>
You want to take care of as many as possible variations, such as different light scenarios, different 
states of object (for example, banana can be dark or green and won't be recognized if such images weren't provided).
<br>
If I wanted to be able to detect another fruit, say apple, I would provide additional apple images and labels, ofcourse.
<br>
Training of the models is very demanding on the computer and you need to have very strong machine to complete it
in reasonable amount of time.
<br>
I trained my model on AWS server machine via remote control by ssh



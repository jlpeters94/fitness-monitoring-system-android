package com.hehe.trumpcard;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.android.Utils;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

public class Start33 extends AppCompatActivity {

    private ImageView im;
    private Mat eto;
    private Button b, b2;
    private int absoluteFaceSize;
    private CascadeClassifier cascadeClassifier;
    private TextView rpw, rph, rw, rh, rb;
    private String num1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start33);

        im = (ImageView) findViewById(R.id.imageid); //Your image View
        b = (Button) findViewById(R.id.buttonid); // your Button

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent img = new Intent(); //Your Intent
                img.setAction(MediaStore.ACTION_IMAGE_CAPTURE); //the intents action to capture the image
                startActivityForResult(img, 1);//start the activity adding any code .1 in this example
            }

        });
    }

    private BaseLoaderCallback mLoaderCallback = new BaseLoaderCallback(this) {
        @Override
        public void onManagerConnected(int status) {
            switch (status){
                case LoaderCallbackInterface.SUCCESS:
                    initializeOpenCVDependencies();
                    break;
                default:
                    super.onManagerConnected(status);
                    break;
            }
        }
    };

    @Override
    public void onResume() {
        super.onResume();
        OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_3_0_0, this, mLoaderCallback);
    }

    private void initializeOpenCVDependencies(){
        try{
            // Copy the resource into a temp file so OpenCV can load it
            InputStream is = getResources().openRawResource(R.raw.haarcascade_fullbody);
            File cascadeDir = getDir("cascade", Context.MODE_APPEND);
            File mCascadeFile = new File(cascadeDir, "haarcascade_fullbody.xml");
            FileOutputStream os = new FileOutputStream(mCascadeFile);

            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = is.read(buffer)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            is.close();
            os.close();

            // Load the cascade classifier
            cascadeClassifier = new CascadeClassifier(mCascadeFile.getAbsolutePath());
        } catch (Exception e) {
            Log.e("OpenCVActivity", "Error loading cascade", e);
        }
    }

    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        super.onActivityResult(requestCode,resultCode,data);//method retrieves the requestCode , its result and the data containing the pic from system
        if(requestCode==1&&resultCode==RESULT_OK){
            Bitmap bmp = (Bitmap)data.getExtras().get("data"); //get data and casts it into Bitmap photo


            int width, height;
            width = bmp.getWidth();
            height = bmp.getHeight();
            eto = new Mat(width, height, CvType.CV_8U);
            absoluteFaceSize = (int) (height * 0.2);
            Bitmap bmp32 = bmp.copy(Bitmap.Config.ARGB_8888, true);
            Utils.bitmapToMat(bmp32, eto);

            Imgproc.cvtColor(eto, eto, Imgproc.COLOR_RGBA2RGB);

            MatOfRect faces = new MatOfRect();

            // Use the classifier to detect faces
            if (cascadeClassifier != null) {
                cascadeClassifier.detectMultiScale(eto, faces, 1.1, 1, 2,
                        new Size(50, 100), new Size());
            }

            // If there are any faces found, draw a rectangle around it
            Rect[] facesArray = faces.toArray();
            for (int i = 0; i <facesArray.length; i++)
                Imgproc.rectangle(eto, facesArray[i].tl(), facesArray[i].br(),new Scalar(0, 255, 0, 255), 3);

            int rheight = Rect.height;

            Intent intent = getIntent();
            num1 = intent.getStringExtra("num1");

            int h = 70 * rheight / 120;
            int w = Integer.parseInt(num1);

            final int bmi = 703 * w / (h * h);

            rw = (TextView) findViewById(R.id.textView);
            rh = (TextView) findViewById(R.id.textView2);

            rb = (TextView) findViewById(R.id.textView9);

            rw.setText(String.valueOf(w));
            rh.setText(String.valueOf(h));

            rb.setText(String.valueOf(bmi));

            Utils.matToBitmap(eto, bmp32);
            im.setImageBitmap(bmp32);// set photo to imageView

            b2 = (Button) findViewById(R.id.button11);

            b2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(bmi >= 1.0 && bmi <= 18.4){
                        Intent intent = new Intent(Start33.this, Underweight.class);
                        startActivity(intent);
                    }
                    else if(bmi >= 18.5 && bmi <= 24.9){
                        Intent intent2 = new Intent(Start33.this, Normal.class);
                        startActivity(intent2);
                    }
                    else if(bmi >= 25.0 && bmi <= 50.0){
                        Intent intent3 = new Intent(Start33.this, Overweight.class);
                        startActivity(intent3);
                    }

                }
            });

        }
    }

}

package com.hehe.trumpcard;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.android.Utils;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static android.widget.Toast.LENGTH_LONG;

public class WeightLossCheck2 extends AppCompatActivity {

    private ImageView im;
    private Mat eto;
    private Button b, b2, b3;
    private int absoluteFaceSize;
    private CascadeClassifier cascadeClassifier;
    private TextView rpw, rph, rw, rh, rb, dateView;
    public static boolean IsButtonClickable32 = false;
    public static float alpha6 = .5f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight_loss_check2);

        im = (ImageView) findViewById(R.id.imageid); //Your image View
        b = (Button) findViewById(R.id.buttonid); // your Button
        b3= (Button) findViewById(R.id.button53);

        dateView = (TextView)findViewById(R.id.dateNow);
        setDate(dateView);

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WeightLossCheck2.this, WeightLoss.class);
                startActivity(intent);
            }
        });

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
            if(facesArray.length > 0) {
                for (int i = 0; i < facesArray.length; i++)
                    Imgproc.rectangle(eto, facesArray[i].tl(), facesArray[i].br(), new Scalar(85, 175, 85, 255), 3);
                Toast.makeText(WeightLossCheck2.this, "Person Detected!", LENGTH_LONG).show();
            }
            else {
                Toast.makeText(WeightLossCheck2.this, "No Person Detected! Try Again.", LENGTH_LONG).show();
            }

            int rwidth = Rect.width;
            int rheight = Rect.height;

            int w = 125 * rwidth / 50;
            int h = 65 * rheight / 135;

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

                    Bitmap bitmap = takeScreenshot();
                    saveBitmap(bitmap);

                    if(bmi >= 25.0 && bmi <= 50.0){

                        IsButtonClickable32 = false;
                        WeightLoss.button8.setClickable(IsButtonClickable32);
                        Toast.makeText(WeightLossCheck2.this, "Need at least 24 BMI!", LENGTH_LONG).show();

                        Intent intent = new Intent(WeightLossCheck2.this, WeightLoss.class);
                        startActivity(intent);
                    }
                    else if(bmi >= 1.0 && bmi <= 24.9){

                        IsButtonClickable32 = true;
                        alpha6 = 1;
                        WeightLoss.button8.setClickable(IsButtonClickable32);
                        Toast.makeText(WeightLossCheck2.this, "Diet Plan Unlocked!", LENGTH_LONG).show();

                        Intent intent2 = new Intent(WeightLossCheck2.this, WeightLoss.class);
                        startActivity(intent2);
                    }

                }
            });

        }
    }

    public void setDate (TextView view){
        Date today = Calendar.getInstance().getTime();//getting date
        SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM dd yyy hh:mm:ss");//formating according to my need
        String date = formatter.format(today);
        view.setText(date);
    }

    public Bitmap takeScreenshot() {
        dateView.setVisibility(View.VISIBLE);
        b.setVisibility(View.GONE);
        b2.setVisibility(View.GONE);
        b3.setVisibility(View.GONE);
        View rootView = findViewById(android.R.id.content).getRootView();
        rootView.setDrawingCacheEnabled(true);
        return rootView.getDrawingCache();
    }

    public void saveBitmap(Bitmap bitmap) {
        String time = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File imagePath = new File(Environment.getExternalStorageDirectory() + "/classic/");
        File imageName = new File(Environment.getExternalStorageDirectory() + "/classic/" + time + ".jpg");
        if (!imagePath.exists()) {
            imagePath.mkdirs();
        }
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(imageName);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            Log.e("GREC", e.getMessage(), e);
        } catch (IOException e) {
            Log.e("GREC", e.getMessage(), e);
        }
        sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(imageName)));
    }

}

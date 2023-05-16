package com.hehe.trumpcard;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class GridViewAdapter extends BaseAdapter {

    private AppCompatActivity activity;
    private String[]                filepath;
    private static LayoutInflater inflater    = null;
    Bitmap bmp         = null;

    GridViewAdapter (AppCompatActivity a, String[] fpath) {
        activity = a;
        filepath = fpath;
        inflater = (LayoutInflater)activity
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public int getCount ()
    {
        return filepath.length;
    }

    public Object getItem (int position)
    {
        return position;
    }

    public long getItemId (int position)
    {
        return position;
    }

    public View getView (int position, View convertView, ViewGroup parent)
    {
        View vi = convertView;
        if (convertView == null)
            vi = inflater.inflate(R.layout.activity_grid_view_adapter, null);
        ImageView image = (ImageView)vi.findViewById(R.id.image);
        int targetWidth = 100;
        int targetHeight = 100;
        BitmapFactory.Options bmpOptions = new BitmapFactory.Options();
        bmpOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filepath[position], bmpOptions);
        int currHeight = bmpOptions.outHeight;
        int currWidth = bmpOptions.outWidth;
        int sampleSize = 1;
        if (currHeight > targetHeight || currWidth > targetWidth)
        {
            if (currWidth > currHeight)
                sampleSize = Math.round((float)currHeight
                        / (float)targetHeight);
            else
                sampleSize = Math.round((float)currWidth
                        / (float)targetWidth);
        }
        bmpOptions.inSampleSize = sampleSize;
        bmpOptions.inJustDecodeBounds = false;
        bmp = BitmapFactory.decodeFile(filepath[position], bmpOptions);
        image.setImageBitmap(bmp);
        image.setScaleType(ImageView.ScaleType.FIT_XY);
        bmp = null;
        return vi;
    }
}

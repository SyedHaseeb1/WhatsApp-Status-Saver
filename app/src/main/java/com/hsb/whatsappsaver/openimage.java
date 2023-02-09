package com.hsb.whatsappsaver;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.github.chrisbanes.photoview.PhotoView;

public class openimage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_openimage);
      try {
          getWindow().setFlags(
                  WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                  WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
          );
      } catch (Exception e) {
          e.printStackTrace();
      }


        PhotoView photoView = (PhotoView) findViewById(R.id.fullscreen_content);
        Intent intent=getIntent();
        String path=intent.getStringExtra("uri");
        Log.e("Intent path",path);
        if (path.isEmpty()){

        }else {
            photoView.setImageURI(Uri.parse(path));
//        imageView.setImageURI(Uri.parse(path));

    }
    }


}
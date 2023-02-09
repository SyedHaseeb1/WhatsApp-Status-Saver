package com.hsb.whatsappsaver;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Environment;
import android.transition.Slide;
import android.transition.TransitionInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.hsb.whatsappsaver.ui.main.SectionsPagerAdapter;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    String path = Environment.getExternalStorageDirectory().toString() + "/Status Saver";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Kabootar", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();

                setupWindowAnimations();

            }
        });
    }
    public void openfolder() {
        File WhatsApp_Folder = new File(Environment.getExternalStorageDirectory().toString() + "/Status Saver/WhatsApp Saver/");
        File Instagram_Folder = new File(Environment.getExternalStorageDirectory().toString() + "/Status Saver/Instagram Saver/");
        if (!WhatsApp_Folder.exists()){
            WhatsApp_Folder.mkdir();
        }



    }

    private void setupWindowAnimations() {
      Intent intent=new Intent(getApplicationContext(),testanim.class);
      startActivity(intent);
    }
}
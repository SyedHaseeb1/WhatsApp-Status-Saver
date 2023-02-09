package com.hsb.whatsappsaver;

import android.content.Intent;
import android.provider.SyncStateContract;

import com.daimajia.androidanimations.library.Techniques;
import com.viksaa.sssplash.lib.activity.AwesomeSplash;
import com.viksaa.sssplash.lib.cnst.Flags;
import com.viksaa.sssplash.lib.model.ConfigSplash;

//extends AwesomeSplash!
public class AnimationSplash extends AwesomeSplash {

    //DO NOT OVERRIDE onCreate()!
    //if you need to start some services do it in initSplash()!


    @Override
    public void initSplash(ConfigSplash configSplash) {

        /* you don't have to override every property */

        //Customize Circular Reveal
        configSplash.setBackgroundColor(R.color.dark); //any color you want form colors.xml
        configSplash.setAnimCircularRevealDuration(600); //int ms
        configSplash.setRevealFlagX(Flags.REVEAL_RIGHT);  //or Flags.REVEAL_LEFT
        configSplash.setRevealFlagY(Flags.REVEAL_BOTTOM); //or Flags.REVEAL_TOP

        //Choose LOGO OR PATH; if you don't provide String value for path it's logo by default

        configSplash.setLogoSplash(R.drawable.whatsapp); //or any other drawable
        configSplash.setAnimLogoSplashDuration(600); //int ms
        configSplash.setAnimLogoSplashTechnique(Techniques.BounceIn); //choose one form Techniques (ref: https://github.com/daimajia/AndroidViewAnimations)


        	//Customize Path
      //  configSplash.setPathSplash(SyncStateContract.Constants.class); //set path String
        configSplash.setOriginalHeight(400); //in relation to your svg (path) resource
        configSplash.setOriginalWidth(400); //in relation to your svg (path) resource
        configSplash.setAnimPathStrokeDrawingDuration(400);
        configSplash.setPathSplashStrokeSize(3); //I advise value be <5
        configSplash.setPathSplashStrokeColor(R.color.white); //any color you want form colors.xml
        configSplash.setAnimPathFillingDuration(400);
        configSplash.setPathSplashFillColor(R.color.white); //path object filling color


        //Customize Title
        configSplash.setTitleSplash("WhatsApp Saver Offered by Haseeb");
        configSplash.setTitleTextColor(R.color.white);
        configSplash.setTitleTextSize(18f); //float value
        configSplash.setAnimTitleDuration(400);
        configSplash.setPathSplashStrokeColor(R.color.white); //any color you want form colors.xml
        configSplash.setAnimTitleTechnique(Techniques.FlipInX);
       // configSplash.setTitleFont("fonts/myfont.ttf"); //provide string to your font located in assets/fonts/

    }

    @Override
    public void animationsFinished() {
        Intent intent=new Intent(AnimationSplash.this,Start.class);
        startActivity(intent);
        finish();
        //transit to another activity here
        //or do whatever you want
    }
}
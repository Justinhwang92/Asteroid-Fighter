//
//package com.example.myfirstapp;
//
//import android.content.res.Resources;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//
//public class Background {
//
//    int x = 0, y = 0;
//    Bitmap background;
//    Bitmap background2;
//    Bitmap background3;
//    Bitmap background4;
//    Bitmap background5;
//
//    Background (int screenX, int screenY, Resources res) {
////        background = BitmapFactory.decodeResource(res, R.drawable.background1);
////        background = Bitmap.createScaledBitmap(background, screenX, screenY, false);
//
//        background = BitmapFactory.decodeResource(res, R.drawable.b1);
//        background = Bitmap.createScaledBitmap(background, screenX, screenY, false);
//
//        background2 = BitmapFactory.decodeResource(res, R.drawable.b2);
//        background2 = Bitmap.createScaledBitmap(background2, screenX, screenY, false);
//
//        background3 = BitmapFactory.decodeResource(res, R.drawable.b3);
//        background3 = Bitmap.createScaledBitmap(background3, screenX, screenY, false);
//
//        background4 = BitmapFactory.decodeResource(res, R.drawable.b4);
//        background4 = Bitmap.createScaledBitmap(background4, screenX, screenY, false);
//
//        background5 = BitmapFactory.decodeResource(res, R.drawable.b5);
//        background5 = Bitmap.createScaledBitmap(background5, screenX, screenY, false);
//
//
//    }
//
////    void getBackground1 (int screenX, int screenY, Resources res) {
////        background2 = BitmapFactory.decodeResource(res, R.drawable.b1);
////        background2 = Bitmap.createScaledBitmap(background, screenX, screenY, false);
////    }
////
////    void getBackground2 (int screenX, int screenY, Resources res) {
////        background2 = BitmapFactory.decodeResource(res, R.drawable.b2);
////        background2 = Bitmap.createScaledBitmap(background, screenX, screenY, false);
////    }
////
////    void getBackground3 (int screenX, int screenY, Resources res) {
////        background3 = BitmapFactory.decodeResource(res, R.drawable.b3);
////        background3 = Bitmap.createScaledBitmap(background, screenX, screenY, false);
////    }
////
////    void getBackground4 (int screenX, int screenY, Resources res) {
////        background4 = BitmapFactory.decodeResource(res, R.drawable.b4);
////        background4 = Bitmap.createScaledBitmap(background, screenX, screenY, false);
////    }
////
////    void getBackground5 (int screenX, int screenY, Resources res) {
////        background5 = BitmapFactory.decodeResource(res, R.drawable.b5);
////        background5 = Bitmap.createScaledBitmap(background, screenX, screenY, false);
////    }
//}

package com.example.myfirstapp;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Background {

    int x = 0, y = 0;
    Bitmap background;

    Background (int screenX, int screenY, Resources res) {
        //background = BitmapFactory.decodeResource(res, R.drawable.background1);
        background = BitmapFactory.decodeResource(res, R.drawable.b2);
        background = Bitmap.createScaledBitmap(background, screenX, screenY, false);
    }
}
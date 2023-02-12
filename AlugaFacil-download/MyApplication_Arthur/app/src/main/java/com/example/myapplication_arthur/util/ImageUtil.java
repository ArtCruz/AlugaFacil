package com.example.myapplication_arthur.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;
import java.util.Base64;

public class ImageUtil {

    public static Bitmap convertStringToBitMap(String strBase64){
        byte[] imgBytes = new byte[0];
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            imgBytes = Base64.getDecoder().decode(strBase64);
        }
        return BitmapFactory.decodeByteArray(imgBytes, 0, imgBytes.length);
    }

    public static String convertBitMapToString(Bitmap imgBitmap){
        byte[] imgByte;
        ByteArrayOutputStream streamImgByte = new ByteArrayOutputStream();
        String result = "";

        imgBitmap.compress(Bitmap.CompressFormat.PNG, 70, streamImgByte);
        imgByte = streamImgByte.toByteArray();
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            result = Base64.getEncoder().encodeToString(imgByte);
        }

        return result;
    }

}

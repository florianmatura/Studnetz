package hirtz.florian.matura.ksa.studnetz.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import hirtz.florian.matura.ksa.studnetz.R;
import hirtz.florian.matura.ksa.studnetz.models.ProfilePictureModel;

/**
 * Created by ingli on 10.07.2018.
 */

public class TempFileGenerator {

    public String getTempFilePath(Context mContext, String blob) {
        if(!blob.equals("0") && !blob.equals("") && blob != null) {
            try {
                //System.out.println("blob: " + blob);
                ProfilePictureModel picture = new ProfilePictureModel(mContext, blob);
                return picture.getPath();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        Bitmap PBDummy = drawableToBitmap(mContext.getResources().getDrawable(R.mipmap.profilepicture_placeholder_round));
        //Bitmap PBDummy = BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.profilepicture_placeholder);
        System.out.println("BITMAP: " + PBDummy);
        ProfilePictureModel picture = new ProfilePictureModel(mContext, PBDummy);

        return picture.getPath();
    }

    //https://stackoverflow.com/questions/3035692/how-to-convert-a-drawable-to-a-bitmap
    private static Bitmap drawableToBitmap (Drawable drawable) {
        Bitmap bitmap = null;

        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            if(bitmapDrawable.getBitmap() != null) {
                return bitmapDrawable.getBitmap();
            }
        }

        if(drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0) {
            bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888); // Single color bitmap will be created of 1x1 pixel
        } else {
            bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }
}

package com.comli.dawnbreaksthrough.personalintro;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.util.TypedValue;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by SparklingGT on 9/13/2016.
 */
public class PictureUtils
{
    /**
     * Get a scaled squared bitmap, the size is up to you.
     *<p><i>Note : Calling this method would produce a thumbnail in the cache folder.</i></p>
     *
     * @param person the person that is currently being displayed
     * @param size    in DIP
     * @param thumbnailSize Either <strong>Elements.Thumbnail.Size.LARGE</strong>
     *                      or <strong>Elements.Thumbnail.Size.SMALL</strong>
     * @return Scaled bitmap
     */

    public static Bitmap getScaleBitmap(Person person, int size, int thumbnailSize, Context context) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        int thumbSizeInPixel = dipToPixel(size, context);
        String path = PersonLab.get(context).getPhotoFile(person).getPath();
        float srcWidth;
        float srcHeight;

        for (int i = 0; true; i++) {
            options.inJustDecodeBounds = true;
            options.inSampleSize = (int) Math.pow(2, i);
            BitmapFactory.decodeFile(path, options);
            srcWidth = options.outWidth;
            srcHeight = options.outHeight;
            if (srcWidth < thumbSizeInPixel || srcHeight < thumbSizeInPixel) {
                options = new BitmapFactory.Options();
                options.inSampleSize = (int) Math.pow(2, i - 1);
                break;
            }
            options = new BitmapFactory.Options();
        }

        Bitmap thumbnail = ThumbnailUtils.extractThumbnail(BitmapFactory.decodeFile(path, options),
                thumbSizeInPixel, thumbSizeInPixel);

        // caching starting here
        File cachePhoto = PersonLab.get(context).getThumbnailFile(person, thumbnailSize);
        try (FileOutputStream out = new FileOutputStream(cachePhoto)) {
            thumbnail.compress(Bitmap.CompressFormat.JPEG, 80, out);
            //// TODO: 9/15/2016  Maybe let user choose the quality
        } catch (IOException e) {
            e.printStackTrace();
        }

        return thumbnail;
    }


    public static int dipToPixel(int dip, Context context) {
        return (int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dip, context.getResources().getDisplayMetrics()));
    }
}

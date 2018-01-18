package com.jv.benedict.utils;

import android.app.Activity;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.util.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * 备注：上传服务器的图片一般是bitmap转成base64加密的字符串
 * Created by wj on 2017/8/4 15:06
 */
public class BitmapUtil {

    /**
     * bitmap转base64加密string（上传到服务器base64）
     *
     * @param bitmap bitmap资源
     * @return base64加密字符串
     */
    public static String bitmapToString(Bitmap bitmap) {
        // 将Bitmap转换成字符串
        String string;
        ByteArrayOutputStream bStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, bStream);
        byte[] bytes = bStream.toByteArray();//bitmap转bytes
        string = Base64.encodeToString(bytes, Base64.NO_WRAP);
        return string;
    }

    /**
     * base64加密string转bitmap
     *
     * @param string base64加密的字符串
     * @return 解密好的bitmap资源
     */
    public static Bitmap stringToBitmap(String string) {
        // 将字符串转换成Bitmap类型
        Bitmap bitmap = null;
        try {
            byte[] bitmapArray;
            bitmapArray = Base64.decode(string, Base64.DEFAULT);
            bitmap = BitmapFactory.decodeByteArray(bitmapArray, 0,
                    bitmapArray.length);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    /**
     * bitmap转byte
     *
     * @param bitmap 原bitmap
     * @return 生成的byte
     */
    public static byte[] bitmapToByte(Bitmap bitmap) {
        byte[] bytes;
        ByteArrayOutputStream bStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, bStream);
        bytes = bStream.toByteArray();//bitmap转bytes
        return bytes;
    }

    /**
     * 适配7.0 通过文件转uri
     *
     * @param photoFile 文件地址
     * @return 文件对应的uri
     */
    public static Uri fileToUri(Context context, String fileProvider, File photoFile) {
        Uri photoURI;//适配7.0获得的图片uri
        if (Build.VERSION.SDK_INT >= 24) {
            //如果是7.0及以上的系统使用FileProvider的方式创建一个Uri
            photoURI = FileProvider.getUriForFile(context, fileProvider, photoFile);
        } else {
            //7.0以下使用这种方式创建一个Uri
            photoURI = Uri.fromFile(photoFile);
        }
        return photoURI;
    }

    /**
     * 系统相册回调  uri转file
     *
     * @param context 上下文
     * @param uri     uri
     * @return file
     */
    public static File uriToFile(Context context, Uri uri) {
        String imagePath = null;
        if (Build.VERSION.SDK_INT >= 19) {// 4.4及以上系统使用这个方法处理图片
            if (DocumentsContract.isDocumentUri(context, uri)) {
                // 如果是document类型的uri，则通过document id处理
                String docId = DocumentsContract.getDocumentId(uri);
                if ("com.android.providers.media.documents".equals(uri.getAuthority())) {
                    String id = docId.split(":")[1];// 解析出数字格式的id
                    String selection = MediaStore.Images.Media._ID + "=" + id;
                    imagePath = getImagePath(context, MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
                } else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())) {
                    Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(docId));
                    imagePath = getImagePath(context, contentUri, null);
                }
            } else if ("content".equalsIgnoreCase(uri.getScheme())) {
                // 如果是content类型的uri，则使用普通方式处理
                imagePath = getImagePath(context, uri, null);
            } else if ("file".equalsIgnoreCase(uri.getScheme())) {
                // 如果是file类型的uri，直接获取图片路径即可
                imagePath = uri.getPath();
            }
        } else {// 4.4一下系统使用这个方法处理图片
            imagePath = getImagePath(context, uri, null);
        }
        return new File(imagePath);
    }

    private static String getImagePath(Context context, Uri uri, String selection) {
        String path = null;
        // 通过Uri和selection 来获取真实的图片路径
        Cursor cursor = context.getContentResolver().query(uri, null, selection, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }

//    /**
//     * 系统相册回调  uri转file
//     *
//     * @param context 上下文
//     * @param uri     uri
//     * @return file
//     */
//    public static File uriToFile(Context context, Uri uri) {
//        String picturePath;
//        if (!TextUtils.isEmpty(uri.getAuthority())) {
//            String[] filePathColumn = {MediaStore.Images.Media.DATA};
//            Cursor cursor = context.getContentResolver().query(uri, filePathColumn, null, null, null);
//            if (cursor == null) {
//                ToastUtils.showShort("图片路径获取为空");
//                return null;
//            } else {
//                cursor.moveToFirst();
//                int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
//                picturePath = cursor.getString(column_index);
//                cursor.close();
//            }
//        } else {
//            picturePath = uri.getPath();
//        }
//        return new File(picturePath);
//    }

    /**
     * 系统图片裁剪
     *
     * @param uri 图片uri
     * @return 返回一个意图intent
     */
    public static Intent cropImage(Uri uri, File photoFile) {
        Uri photoURI;//适配7.0获得的图片uri
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//            //如果是7.0及以上的系统使用FileProvider的方式创建一个Uri
//            photoURI = FileProvider.getUriForFile(context, fileProvider, photoFile);
//            //这两个貌似可以不用
////            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
////            intent.setFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
//        } else {
        //7.0以下使用这种方式创建一个Uri
        photoURI = Uri.fromFile(photoFile);
//        }
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        // aspectX aspectY 是宽高的比例
        // 设置x,y的比例，截图方框就按照这个比例来截 若设置为0,0，或者不设置 则自由比例截图
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        // 裁剪区的宽和高 其实就是裁剪后的显示区域 若裁剪的比例不是显示的比例，
        // 则自动压缩图片填满显示区域。若设置为0,0 就不显示。若不设置，则按原始大小显示
        intent.putExtra("outputX", 200);
        intent.putExtra("outputY", 200);
        intent.putExtra("return-data", false);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.PNG.toString());
        intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
        return intent;
    }

    /**
     * 通过uri获取图片并进行压缩
     *
     * @param uri
     */
    public static Bitmap getBitmapFormUri(Activity ac, Uri uri) {
        InputStream input = null;
        try {
            input = ac.getContentResolver().openInputStream(uri);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BitmapFactory.Options onlyBoundsOptions = new BitmapFactory.Options();
        onlyBoundsOptions.inJustDecodeBounds = true;
        onlyBoundsOptions.inDither = true;//optional
        onlyBoundsOptions.inPreferredConfig = Bitmap.Config.ARGB_8888;//optional
        BitmapFactory.decodeStream(input, null, onlyBoundsOptions);
        try {
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        int originalWidth = onlyBoundsOptions.outWidth;
        int originalHeight = onlyBoundsOptions.outHeight;
        if ((originalWidth == -1) || (originalHeight == -1))
            return null;
        //图片分辨率以480x800为标准
        float hh = 800f;//这里设置高度为800f
        float ww = 480f;//这里设置宽度为480f
        //缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
        int be = 1;//be=1表示不缩放
        if (originalWidth > originalHeight && originalWidth > ww) {//如果宽度大的话根据宽度固定大小缩放
            be = (int) (originalWidth / ww);
        } else if (originalWidth < originalHeight && originalHeight > hh) {//如果高度高的话根据宽度固定大小缩放
            be = (int) (originalHeight / hh);
        }
        if (be <= 0)
            be = 1;
        //比例压缩
        BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
        bitmapOptions.inSampleSize = be;//设置缩放比例
        bitmapOptions.inDither = true;//optional
        bitmapOptions.inPreferredConfig = Bitmap.Config.ARGB_8888;//optional
        try {
            input = ac.getContentResolver().openInputStream(uri);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Bitmap bitmap = BitmapFactory.decodeStream(input, null, bitmapOptions);
        try {
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return compressImage(bitmap);//再进行质量压缩
    }

    /**
     * 质量压缩方法
     *
     * @param image
     * @return
     */
    public static Bitmap compressImage(Bitmap image) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);//质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 100;
        while (baos.toByteArray().length / 1024 > 100) {  //循环判断如果压缩后图片是否大于100kb,大于继续压缩
            baos.reset();//重置baos即清空baos
            //第一个参数 ：图片格式 ，第二个参数： 图片质量，100为最高，0为最差  ，第三个参数：保存压缩后的数据的流
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);//这里压缩options%，把压缩后的数据存放到baos中
            options -= 10;//每次都减少10
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());//把压缩后的数据baos存放到ByteArrayInputStream中
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);//把ByteArrayInputStream数据生成图片
        return bitmap;
    }

    /**
     * 读取图片的旋转的角度
     *
     * @param path 图片绝对路径
     * @return 图片的旋转角度
     */
    public static int getBitmapDegree(String path) {
        int degree = 0;
        try {
            // 从指定路径下读取图片，并获取其EXIF信息
            ExifInterface exifInterface = new ExifInterface(path);
            // 获取图片的旋转信息
            int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return degree;
    }

    /**
     * 将图片按照某个角度进行旋转
     *
     * @param bm     需要旋转的图片
     * @param degree 旋转角度
     * @return 旋转后的图片
     */
    public static Bitmap rotateBitmapByDegree(Bitmap bm, int degree) {
        Bitmap returnBm = null;

        // 根据旋转角度，生成旋转矩阵
        Matrix matrix = new Matrix();
        matrix.postRotate(degree);
        try {
            // 将原始图片按照旋转矩阵进行旋转，并得到新的图片
            returnBm = Bitmap.createBitmap(bm, 0, 0, bm.getWidth(), bm.getHeight(), matrix, true);
        } catch (OutOfMemoryError e) {
        }
        if (returnBm == null) {
            returnBm = bm;
        }
        if (bm != returnBm) {
            bm.recycle();
        }
        return returnBm;
    }
}

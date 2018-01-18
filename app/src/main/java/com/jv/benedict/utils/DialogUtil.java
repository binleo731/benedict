package com.jv.benedict.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

/**
 * 对话框
 * Created by wj on 2017/7/28 15:04
 */

public class DialogUtil {
    /**
     * 回调确定和取消
     *
     * @param context  上下文
     * @param title    标题
     * @param message  内容
     * @param callBack 回调
     */
    public static void showDialog(Context context, String title, String message, final DialogCallBack callBack) {
        if (context != null) {
            new AlertDialog.Builder(context)
                    .setTitle(title)
                    .setMessage(message)
                    .setPositiveButton("确定", callBack::onPositive)
                    .setNegativeButton("取消", (dialog, which) -> {
                        callBack.onNegative(dialog, which);
                        dialog.dismiss();
                    })
                    .setCancelable(false)
                    .create().show();
        }
    }

    /**
     * 回调确定和取消
     *
     * @param context  上下文
     * @param message  内容
     * @param callBack 回调
     */
    public static void showDialog(Context context, String message, final DialogCallBack callBack) {
        if (context != null) {
            new AlertDialog.Builder(context)
                    .setTitle("提示")
                    .setMessage(message)
                    .setPositiveButton("确定", callBack::onPositive)
                    .setNegativeButton("取消", callBack::onNegative)
                    .setCancelable(false)
                    .create().show();
        }
    }

    /**
     * 只回调确定
     *
     * @param context  上下文
     * @param message  内容
     * @param callBack 回调
     */
    public static void showPbtnDialog(Context context, String message, final PositiveCallBack callBack) {
        if (context != null) {
            new AlertDialog.Builder(context)
                    .setTitle("提示")
                    .setMessage(message)
                    .setPositiveButton("确定", callBack::onPositive)
                    .setCancelable(false)
                    .create().show();
        }
    }

    /**
     * 不回调（只展示信息）
     *
     * @param context 上下文
     * @param message 内容
     */
    public static void showDialog(Context context, String message) {
        if (context != null) {
//            SnackbarUtils.topSnackbar((Activity) context, message);// 哈哈哈，二次封装的好处，一行代码换掉所有样式[捂脸]
//            new AlertDialog.Builder(context)
//                    .setTitle("提示")
//                    .setMessage(message)
//                    .setPositiveButton("确定", null)
//                    .setCancelable(false)
//                      .create().show();
        }
    }

    /**
     * 单选框
     *
     * @param context     上下文
     * @param items       单选框内容数组
     * @param checkedItem 默认选中的item
     * @param callBack    回调
     */
    public static void showDialog(Context context, String[] items, int checkedItem, final DialogCallBack callBack) {
        new AlertDialog.Builder(context)
                .setSingleChoiceItems(items, checkedItem, (dialog, which) -> {
//                        callBack.onSingleChoiceItems(dialog, which);
                    dialog.dismiss();
                })
                .create().show();
    }

    /**
     * 回调对话框选项
     */
    public interface DialogCallBack {
        /**
         * 确定
         */
        void onPositive(DialogInterface dialog, int which);

        /**
         * 取消
         */
        void onNegative(DialogInterface dialog, int which);

//        /**
//         * 单选框
//         */
//        void onSingleChoiceItems(DialogInterface dialog, int which);
    }

    /**
     * 只回调对话框确定选项
     */
    public interface PositiveCallBack {
        /**
         * 确定
         */
        void onPositive(DialogInterface dialog, int which);

    }
}

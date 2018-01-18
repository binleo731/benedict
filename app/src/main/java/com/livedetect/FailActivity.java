package com.livedetect;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jv.benedict.R;
import com.livedetect.data.ConstantValues;
import com.livedetect.utils.FileUtils;
import com.livedetect.utils.StringUtils;
import com.orhanobut.logger.Logger;

public class FailActivity extends Activity {
    private final String TAG = FailActivity.class.getSimpleName();
    private ImageView mReturnImg;
    private ImageView mAgainImg;

    private TextView mRezionTv = null;
    private TextView mAction_tv = null;
    private TextView mTimeConsuming_tv = null;
    private ImageView returnImg;
    private ImageView mFailImg = null;
    private ImageView mFailTopImg = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FileUtils.init(this);
        setContentView(FileUtils.getResIdByTypeAndName(ConstantValues.RES_TYPE_LAYOUT, "htjc_activity_fail"));
        initView();
        Intent mIntent = getIntent();
        Bundle result = mIntent.getBundleExtra("result");
        /*
         * 失败的动作
         */
        String mMove = result.getString("mMove");
        /*
         * 失败的原因
         */
        String mRezion = result.getString("mRezion");
        /*
         * 总耗时
         */
        long timeConsuming = result.getLong("timeConsuming");
        /*
         * 失败的图片
         */
        byte[] pic_result = result.getByteArray("pic_result");
        /*
         * 如果需要用的话这个肯定是false
         */
        boolean check_pass = result.getBoolean("check_pass");

        if (pic_result != null) {
            //writeFile(pic_result,dirPicSave+System.currentTimeMillis()+"best_Fail.jpg");
            mFailTopImg.setVisibility(View.GONE);
            mFailImg.setVisibility(View.VISIBLE);
            Bitmap bitmap = FileUtils.getBitmapByBytesAndScale(pic_result, 1);
            if (null != bitmap) {
                 mFailImg.setImageBitmap(bitmap);
            }
        } else {
            mFailTopImg.setVisibility(View.VISIBLE);
            mFailImg.setVisibility(View.GONE);
        }


        if (StringUtils.isStrEqual(mRezion, ConstantValues.BAD_REASON.CHACK_FAIL)) {
            mRezionTv.setText(FileUtils.getResIdByTypeAndName(ConstantValues.RES_TYPE_STRING, "htjc_fail_remind_default"));
        } else if (StringUtils.isStrEqual(mRezion, ConstantValues.BAD_REASON.NO_FACE)) {
            mRezionTv.setText(FileUtils.getResIdByTypeAndName(ConstantValues.RES_TYPE_STRING, "htjc_fail_remind_noface"));
        } else if (StringUtils.isStrEqual(mRezion, ConstantValues.BAD_REASON.MORE_FACE)) {
            mRezionTv.setText(FileUtils.getResIdByTypeAndName(ConstantValues.RES_TYPE_STRING, "htjc_fail_remind_moreface"));
        } else if (StringUtils.isStrEqual(mRezion, ConstantValues.BAD_REASON.NOT_LIVE)) {//非活体
            mRezionTv.setText(FileUtils.getResIdByTypeAndName(ConstantValues.RES_TYPE_STRING, "htjc_fail_remind_notlive"));
        } else if (StringUtils.isStrEqual(mRezion, ConstantValues.BAD_REASON.BAD_MOVEMENT_TYPE)) {//互斥
            mRezionTv.setText(FileUtils.getResIdByTypeAndName(ConstantValues.RES_TYPE_STRING, "htjc_fail_remind_badmovementtype"));
        } else if (StringUtils.isStrEqual(mRezion, ConstantValues.BAD_REASON.TIME_OUT)) {//超时
            mRezionTv.setText(FileUtils.getResIdByTypeAndName(ConstantValues.RES_TYPE_STRING, "htjc_fail_remind_timeout"));
        } else if (StringUtils.isStrEqual(mRezion, ConstantValues.BAD_REASON.GET_PGP_FAILED)) {
            mRezionTv.setText(FileUtils.getResIdByTypeAndName(ConstantValues.RES_TYPE_STRING, "htjc_fail_remind_pgp_fail"));
        } else if (StringUtils.isStrEqual(mRezion, ConstantValues.BAD_REASON.CHECK_3D_FAILED)) {//3d
            mRezionTv.setText(FileUtils.getResIdByTypeAndName(ConstantValues.RES_TYPE_STRING, "htjc_fail_remind_3d"));
        } else if (StringUtils.isStrEqual(mRezion, ConstantValues.BAD_REASON.CHECK_SKIN_COLOR_FAILED)) {//肤色
            mRezionTv.setText(FileUtils.getResIdByTypeAndName(ConstantValues.RES_TYPE_STRING, "htjc_fail_remind_badcolor"));
        } else if (StringUtils.isStrEqual(mRezion, ConstantValues.BAD_REASON.CHECK_CONTINUITY_COLOR_FAILED)) {//连续性
            mRezionTv.setText(FileUtils.getResIdByTypeAndName(ConstantValues.RES_TYPE_STRING, "htjc_fail_remind_badcontinuity"));
        } else if (StringUtils.isStrEqual(mRezion, ConstantValues.BAD_REASON.CHECK_ABNORMALITY_FAILED)) {//
            mRezionTv.setText(FileUtils.getResIdByTypeAndName(ConstantValues.RES_TYPE_STRING, "htjc_fail_remind_abnormality"));
        } else if (StringUtils.isStrEqual(mRezion, ConstantValues.BAD_REASON.GUIDE_TIME_OUT)) {//超时
            mRezionTv.setText(FileUtils.getResIdByTypeAndName(ConstantValues.RES_TYPE_STRING, "htjc_guide_time_out"));
        }

        if (mMove != null) {
            mAction_tv.setText("失败动作是:" + mMove);
        }
        mTimeConsuming_tv.setText("总耗时:" + timeConsuming + "毫秒");
    }

    private void initView() {
        mFailTopImg = (ImageView) findViewById(FileUtils.getResIdByTypeAndName(ConstantValues.RES_TYPE_ID, "fail_top_iv"));
        mFailImg = (ImageView) findViewById(FileUtils.getResIdByTypeAndName(ConstantValues.RES_TYPE_ID, "fail_img"));
        mTimeConsuming_tv = (TextView) findViewById(FileUtils.getResIdByTypeAndName(ConstantValues.RES_TYPE_ID, "timeConsuming_tv"));
        mAction_tv = (TextView) findViewById(FileUtils.getResIdByTypeAndName(ConstantValues.RES_TYPE_ID, "action_tv"));
        mAgainImg = (ImageView) findViewById(FileUtils.getResIdByTypeAndName(ConstantValues.RES_TYPE_ID, "btn_again"));
        mReturnImg = (ImageView) findViewById(FileUtils.getResIdByTypeAndName(ConstantValues.RES_TYPE_ID, "btn_return"));
        mRezionTv = (TextView) findViewById(FileUtils.getResIdByTypeAndName(ConstantValues.RES_TYPE_ID, "rezion_tv"));
        mAgainImg.setOnClickListener(v -> {
            Intent intent = new Intent(FailActivity.this, LiveDetectActivity.class);
            Bundle bundle = new Bundle();
            bundle.putBoolean(getString(R.string.face_isRandomable), true);
            bundle.putString(getString(R.string.face_actions), "01279");
            bundle.putString(getString(R.string.face_selectActionsNum), "3");
            bundle.putString(getString(R.string.face_singleActionDectTime), "8");
            bundle.putBoolean(getString(R.string.isWaterable), false);
            bundle.putBoolean(getString(R.string.face_openSound), true);
            intent.putExtra(getString(R.string.face_comprehensive_set), bundle);
            startActivity(intent);
            FailActivity.this.finish();
            Logger.e("重新扫描——finish");
        });
        mReturnImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FailActivity.this.finish();
                Logger.e("退出——finish");
            }
        });
        returnImg = (ImageView) findViewById(FileUtils.getResIdByTypeAndName(ConstantValues.RES_TYPE_ID, "iv_return"));
        returnImg.setVisibility(View.INVISIBLE);
    }
}

package com.sr.superhelperx.imagepicker.activity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import androidx.core.content.FileProvider;
import androidx.viewpager.widget.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sr.superhelperx.R;
import com.sr.superhelperx.imagepicker.adapter.ImagePreViewAdapter;
import com.sr.superhelperx.imagepicker.data.MediaFile;
import com.sr.superhelperx.imagepicker.manager.SelectionManager;
import com.sr.superhelperx.imagepicker.provider.ImagePickerProvider;
import com.sr.superhelperx.imagepicker.utils.DataUtil;
import com.sr.superhelperx.imagepicker.view.HackyViewPager;
import com.sr.superhelperx.util.UtilToast;

import java.io.File;
import java.util.List;

/**
 * 大图预览界面
 * Create by: chenWei.li
 * Date: 2018/10/3
 * Time: 下午11:32
 * Email: lichenwei.me@foxmail.com
 */
public class ImagePreActivity extends BaseActivity {

    public static final String IMAGE_POSITION = "imagePosition";
    private List<MediaFile> mMediaFileList;
    private int mPosition = 0;

    private TextView mTvTitle;
    private TextView mTvCommit;
    private ImageView mIvPlay;
    private HackyViewPager mViewPager;
    private LinearLayout mLlPreSelect;
    private ImageView mIvPreCheck;
    private ImagePreViewAdapter mImagePreViewAdapter;

    @Override
    protected int bindLayout() {
        return R.layout.activity_pre_image;
    }

    @Override
    protected void initView() {
        mTvTitle = findViewById(R.id.tv_actionBar_title);
        mTvCommit = findViewById(R.id.tv_actionBar_commit);
        mIvPlay = findViewById(R.id.iv_main_play);
        mViewPager = findViewById(R.id.vp_main_preImage);
        mLlPreSelect = findViewById(R.id.ll_pre_select);
        mIvPreCheck = findViewById(R.id.iv_item_check);
    }

    @Override
    protected void initListener() {

        findViewById(R.id.iv_actionBar_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mTvTitle.setText(String.format("%d/%d", position + 1, mMediaFileList.size()));

                if (mMediaFileList.get(position).getDuration() > 0) {
                    mIvPlay.setVisibility(View.VISIBLE);
                } else {
                    mIvPlay.setVisibility(View.GONE);
                }

                updateSelectButton(mMediaFileList.get(position).getPath());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mLlPreSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean addSuccess = SelectionManager.getInstance().addImageToSelectList(mMediaFileList.get(mViewPager.getCurrentItem()).getPath());
                if (addSuccess) {
                    updateSelectButton(mMediaFileList.get(mViewPager.getCurrentItem()).getPath());
                    updateCommitButton();
                } else {
                    showNoChooseToast();
                }
            }
        });

        mTvCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_OK, new Intent());
                finish();
            }
        });

        mIvPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //实现播放视频的跳转逻辑(调用原生视频播放器)
                Intent intent = new Intent(Intent.ACTION_VIEW);
                Uri uri = FileProvider.getUriForFile(ImagePreActivity.this, ImagePickerProvider.getFileProviderName(ImagePreActivity.this), new File(mMediaFileList.get(mViewPager.getCurrentItem()).getPath()));
                intent.setDataAndType(uri, "video/*");
                //给所有符合跳转条件的应用授权
                List<ResolveInfo> resInfoList = getPackageManager()
                        .queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
                for (ResolveInfo resolveInfo : resInfoList) {
                    String packageName = resolveInfo.activityInfo.packageName;
                    grantUriPermission(packageName, uri, Intent.FLAG_GRANT_READ_URI_PERMISSION
                            | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                }
                startActivity(intent);
            }
        });

    }

    @Override
    protected void getData() {
        mMediaFileList = DataUtil.getInstance().getMediaData();
        mPosition = getIntent().getIntExtra(IMAGE_POSITION, 0);
        mImagePreViewAdapter = new ImagePreViewAdapter(this, mMediaFileList);
        mViewPager.setAdapter(mImagePreViewAdapter);
        mViewPager.setCurrentItem(mPosition);
        mTvTitle.setText(String.format("%d/%d", mPosition + 1, mMediaFileList.size()));

        //更新当前页面状态
        updateSelectButton(mMediaFileList.get(mPosition).getPath());
        updateCommitButton();
    }

    /**
     * 更新确认按钮状态
     */
    private void updateCommitButton() {

        int maxCount = SelectionManager.getInstance().getImageMaxCount() + SelectionManager.getInstance().getVideoMaxCount();

        //改变确定按钮UI
        int selectCount = SelectionManager.getInstance().getSelectPaths().size();
        if (selectCount == 0) {
            mTvCommit.setEnabled(false);
            mTvCommit.setText(getString(R.string.confirm));
            return;
        }
        if (selectCount < maxCount) {
            mTvCommit.setEnabled(true);
            mTvCommit.setText(String.format(getString(R.string.confirm_msg), selectCount, maxCount));
            return;
        }
        if (selectCount == maxCount) {
            mTvCommit.setEnabled(true);
            mTvCommit.setText(String.format(getString(R.string.confirm_msg), selectCount, maxCount));
            return;
        }
    }

    /**
     * 更新选择按钮状态
     */
    private void updateSelectButton(String imagePath) {
        boolean isSelect = SelectionManager.getInstance().isImageSelect(imagePath);
        if (isSelect) {
            mIvPreCheck.setImageDrawable(getResources().getDrawable(R.mipmap.icon_image_checked));
        } else {
            mIvPreCheck.setImageDrawable(getResources().getDrawable(R.mipmap.icon_image_check));
        }
    }

    private void showNoChooseToast(){
        if (SelectionManager.getInstance().getVideoMaxCount() == 0){
            UtilToast.show("最多选择" + SelectionManager.getInstance().getImageMaxCount() + "张图片");
        }else {
            UtilToast.show("最多选择" + SelectionManager.getInstance().getImageMaxCount() + "张图片," +  SelectionManager.getInstance().getVideoMaxCount() + "个视频");
        }
    }
}

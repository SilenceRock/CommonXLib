package com.sr.superhelperx.imagepicker.manager;

import android.webkit.MimeTypeMap;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 媒体选择集合管理类
 * Create by: chenWei.li
 * Date: 2018/8/23
 * Time: 上午1:19
 * Email: lichenwei.me@foxmail.com
 */
public class SelectionManager {

    private static volatile SelectionManager mSelectionManager;

    private Set<String> mSelectImagePaths = new HashSet<>();

    private Set<String> mSelectVideoPaths = new HashSet<>();

    private int mImageMaxCount = 1;

    private int mVideoMaxCount = 0;

    private SelectionManager() {
    }

    public static SelectionManager getInstance() {
        if (mSelectionManager == null) {
            synchronized (SelectionManager.class) {
                if (mSelectionManager == null) {
                    mSelectionManager = new SelectionManager();
                }
            }
        }
        return mSelectionManager;
    }

    /**
     * 设置最大选择数
     *
     * @param maxCount
     */
    public void setImageMaxCount(int maxCount) {
        this.mImageMaxCount = maxCount;
    }

    /**
     * 获取当前设置最大选择数
     *
     * @return
     */
    public int getImageMaxCount() {
        return this.mImageMaxCount;
    }

    /**
     * 设置视频最大选择数
     *
     * @param maxCount
     */
    public void setVideoMaxCount(int maxCount) {
        this.mVideoMaxCount = maxCount;
    }

    /**
     * 获取当前设置视频最大选择数
     *
     * @return
     */
    public int getVideoMaxCount() {
        return this.mVideoMaxCount;
    }

    /**
     * 获取当前所选图片集合path
     *
     * @return
     */
    public Set<String> getSelectPaths() {
        return mSelectImagePaths;
    }

    /**
     * 添加/移除图片到选择集合
     *
     * @param imagePath
     * @return
     */
    public boolean addImageToSelectList(String imagePath) {
        if (mSelectImagePaths.contains(imagePath)) {
            mSelectVideoPaths.remove(imagePath);
            return mSelectImagePaths.remove(imagePath);
        } else {
            String mimeType = MimeTypeMap.getSingleton().getMimeTypeFromExtension(MimeTypeMap.getFileExtensionFromUrl(imagePath));
            if (mimeType != null){
                if (mimeType.contains("video")){
                    //选的是视频
                    if (mSelectVideoPaths.size() < mVideoMaxCount){
                        mSelectVideoPaths.add(imagePath);
                        return mSelectImagePaths.add(imagePath);
                    }else {
                        return false;
                    }
                }else {
                    //选的是图片
                    if (mSelectImagePaths.size() < mImageMaxCount) {
                        return mSelectImagePaths.add(imagePath);
                    } else {
                        return false;
                    }
                }
            }
            return false;
        }
    }

    /**
     * 添加图片到选择集合
     *
     * @param imagePaths
     */
    public void addImagePathsToSelectList(List<String> imagePaths) {
        if (imagePaths != null) {
            for (int i = 0; i < imagePaths.size(); i++) {
                String imagePath = imagePaths.get(i);
                if (!mSelectImagePaths.contains(imagePath) && mSelectImagePaths.size() < (mImageMaxCount + mVideoMaxCount)) {
                    mSelectImagePaths.add(imagePath);
                }
            }
        }
    }

    /**
     * 判断当前图片是否被选择
     *
     * @param imagePath
     * @return
     */
    public boolean isImageSelect(String imagePath) {
        if (mSelectImagePaths.contains(imagePath)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 是否还可以继续选择图片
     *
     * @return
     */
    public boolean isCanChoose() {
        if (getSelectPaths().size() < mImageMaxCount) {
            return true;
        }
        return false;
    }

    /**
     * 清除已选图片
     */
    public void removeAll() {
        mSelectVideoPaths.clear();
        mSelectImagePaths.clear();
    }

}

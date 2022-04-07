package com.sr.superhelperx.util;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;

import java.io.IOException;

/**
 * Created by Hang.Yang on 2018/8/17 16:27.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */

/**
 * 播放音频
 */
public final class UtilVoice {
    private static UtilVoice INSTANCE;
    private MediaPlayer m;

    private UtilVoice() {
        this.init();
    }

    private void init() {
        this.m = null;
        this.m = new MediaPlayer();
        this.m.setAudioStreamType(3);
        this.m.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            public void onPrepared(MediaPlayer mediaPlayer) {
                UtilVoice.this.start();
            }
        });
    }

    public void start(Context context, int resourceId, boolean isLooping) {
        this.start(context, (Uri)null, resourceId, (String)null, isLooping);
    }

    public void start(Context context, Uri uri, boolean isLooping) {
        this.start(context, uri, 0, (String)null, isLooping);
    }

    public void start(String path, boolean isLooping) {
        this.start((Context)null, (Uri)null, 0, path, isLooping);
    }

    private void start(Context context, Uri uri, int resourceId, String path, boolean isLooping) {
        try {
            this.stop();
            this.m.release();
            if(context != null) {
                if(uri != null) {
                    this.m.setDataSource(context, uri);
                } else if(resourceId != 0) {
                    this.m.setDataSource(context.getResources().openRawResourceFd(resourceId).getFileDescriptor());
                }
            } else if(path != null) {
                this.m.setDataSource(path);
            }

            this.m.prepareAsync();
        } catch (IllegalArgumentException var7) {
            var7.printStackTrace();
        } catch (SecurityException var8) {
            var8.printStackTrace();
        } catch (IllegalStateException var9) {
            var9.printStackTrace();
        } catch (IOException var10) {
            var10.printStackTrace();
        }

    }

    public void pause() {
        try {
            this.m.pause();
        } catch (Exception var2) {
            var2.printStackTrace();
        }

    }

    public void reset() {
        try {
            this.m.reset();
            this.start();
        } catch (Exception var2) {
            var2.printStackTrace();
        }

    }

    public void start() {
        try {
            this.m.start();
        } catch (Exception var2) {
            var2.printStackTrace();
        }

    }

    public void stop() {
        try {
            this.m.stop();
        } catch (Exception var2) {
            var2.printStackTrace();
            this.init();
        }

    }

    public static UtilVoice getInstance() {
        return INSTANCE == null?(INSTANCE = new UtilVoice()):INSTANCE;
    }
}

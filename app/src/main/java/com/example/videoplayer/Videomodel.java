package com.example.videoplayer;

import android.graphics.Bitmap;

public class Videomodel {
    private String VideoName;
    private String VideoPath;
    private static Bitmap ThumbNail;

    public String getVideoName() {
        return VideoName;
    }

    public void setVideoName(String videoName) {
        VideoName = videoName;
    }

    public String getVideoPath() {
        return VideoPath;
    }

    public void setVideoPath(String videoPath) {
        VideoPath = videoPath;
    }

    public static Bitmap getThumbNail() {
        return ThumbNail;
    }

    public void setThumbNail(Bitmap thumbNail) {
        ThumbNail = thumbNail;
    }

    public Videomodel(String videoName, String videoPath, Bitmap thumbNail) {
        VideoName = videoName;
        VideoPath = videoPath;
        ThumbNail = thumbNail;
    }
}

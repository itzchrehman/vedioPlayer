package com.example.videoplayer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContentResolverCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.jar.Attributes;

public class MainActivity extends AppCompatActivity implements VideoRVAdapter.ViewHolder.VideoClickInterface {
    private RecyclerView videoRV;
    private ArrayList<Videomodel>videomodelArrayList;
    private VideoRVAdapter videoRVAdapter;
    private static final int STORAGE_PERMISSION=101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        videoRV = findViewById(R.id.idRVVideos);
        videomodelArrayList = new ArrayList<>();
        videoRVAdapter = new VideoRVAdapter(videomodelArrayList, this, this::onVideoClick);
        videoRV.setLayoutManager(new GridLayoutManager(this, 2));
        videoRV.setAdapter(videoRVAdapter);
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED){
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION);
    }else{
            getVedios();
        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==STORAGE_PERMISSION){
            if (grantResults.length > 0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
                getVedios();
            }else{
                Toast.makeText(this, "The App will not work without Permission..", Toast.LENGTH_SHORT).show();
                Toast.makeText(this, "apply permissions", Toast.LENGTH_SHORT).show();
                finish();
            }

        }
    }

    private void getVedios() {
        ContentResolver contentResolver=getContentResolver();
        Uri uri= MediaStore.Video.Media.EXTERNAL_CONTENT_URI;

        Cursor cursor=contentResolver.query(uri,null,null,null,null);
        if(cursor!=null && cursor.moveToFirst()){
            do{
                @SuppressLint("Range") String VideoTitle = cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.TITLE));
                @SuppressLint("Range") String Videopath = cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.DATA));
                Bitmap VideoThumbnail = ThumbnailUtils.createVideoThumbnail(Videopath,MediaStore.Images.Thumbnails.MINI_KIND);

                videomodelArrayList.add(new Videomodel(VideoTitle,Videopath,VideoThumbnail));




            }while (cursor.moveToNext());
        }
        videoRVAdapter.notifyDataSetChanged();
    }

    @Override
    public void onVideoClick(int position) {
        Intent i= new Intent(MainActivity.this,VideoPlayerActivity.class);
        i.putExtra("videoName",videomodelArrayList.get(position).getVideoName());
        i.putExtra("videoPath",videomodelArrayList.get(position).getVideoPath());
        startActivity(i);


    }
}
package com.example.videoplayer;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class VideoRVAdapter extends RecyclerView.Adapter<VideoRVAdapter.ViewHolder> {

    private ArrayList<Videomodel>videomodelArrayList;
    private Context context;
    private ViewHolder.VideoClickInterface videoClickInterface;

    public VideoRVAdapter(ArrayList<Videomodel> videomodelArrayList, Context context, ViewHolder.VideoClickInterface videoClickInterface) {
        this.videomodelArrayList = videomodelArrayList;
        this.context = context;
        this.videoClickInterface = videoClickInterface;
    }

    @NonNull
    @Override
    public VideoRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.video_rv_item,parent,false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull VideoRVAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Videomodel Videomodel=videomodelArrayList.get(position);
        holder.thumbnailIV.setImageBitmap(com.example.videoplayer.Videomodel.getThumbNail());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                videoClickInterface.onVideoClick(position);
            }
        });


    }

    @Override
    public int getItemCount() {
        return videomodelArrayList.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView thumbnailIV;
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            thumbnailIV=itemView.findViewById(R.id.idIVThumNail);
        }
        public interface VideoClickInterface{
            void onVideoClick(int position);

        }
    }
}

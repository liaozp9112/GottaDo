package cn.com.gottado.main.play.config;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import cn.com.gottado.R;
import cn.com.gottado.tool.util.StringTools;

/**
 * Created by Administrator on 2017/6/8.
 */

public class PlayLViewAdapter extends RecyclerView.Adapter<PlayLViewHolder>{

    private OnItemClickListener mOnItemClickListener = null;

    private List<PlayItem> mPlayItems;

    public PlayLViewAdapter(List<PlayItem> playItems) {
        if (playItems == null) {
            throw new IllegalArgumentException("playItems must not be null");
        }
        mPlayItems = playItems;
    }


    @Override
    public PlayLViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemViews = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_play_list_item, parent, false);
        final PlayLViewHolder  viewHolder = new PlayLViewHolder(itemViews);
        itemViews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    //注意这里使用getTag方法获取position
                    mOnItemClickListener.onItemClick(v,(int)v.getTag(),viewHolder);
                }
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(PlayLViewHolder viewHolder, int position) {
        PlayItem dataModel = mPlayItems.get(position);

        viewHolder.getTvTitle().setText(dataModel.getTitle());
        viewHolder.getmImageView().setImageResource(dataModel.getImageViewRes());
        viewHolder.getTvTitle().setTransitionName("R.id.main_toolbar");
        if(StringTools.strNotEmpty(dataModel.getTip())){
            viewHolder.getTip().setVisibility(View.VISIBLE);
            viewHolder.getTip().setText(dataModel.getTip());
        }
        viewHolder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return mPlayItems.size();
    }

    public void addData(PlayItem dataModel,int position) {
        mPlayItems.add(position, dataModel);
        notifyItemInserted(position);
    }

    public void removeData(int position) {
        mPlayItems.remove(position);
        notifyItemRemoved(position);
    }


    //define interface
    public static interface OnItemClickListener {
        void onItemClick(View view , int position, RecyclerView.ViewHolder holder);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }
}

package cn.com.gottado.main.play.config;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import cn.com.gottado.R;

/**
 * Created by Administrator on 2017/6/8.
 */

public class PlayRViewAdapter extends RecyclerView.Adapter<PlayRViewHolder> {


    private List<PlayItem> mPlayItems;

    public PlayRViewAdapter(List<PlayItem> playItems) {
        if (playItems == null) {
            throw new IllegalArgumentException("playItems must not be null");
        }
        mPlayItems = playItems;
    }


    @Override
    public PlayRViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_play_rv_item, parent, false);
        return new PlayRViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PlayRViewHolder holder, int position) {
        PlayItem dataModel = mPlayItems.get(position);
        ViewGroup.LayoutParams lp = holder.getTvTitle().getLayoutParams();
        lp.height = dataModel.getHeight();
        holder.itemView.setLayoutParams(lp);
      //  holder.getTvTitle().setLayoutParams(lp);

        holder.getTvTitle().setText(dataModel.getTitle());
        holder.getTvTip().setText(dataModel.getTip());
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
}

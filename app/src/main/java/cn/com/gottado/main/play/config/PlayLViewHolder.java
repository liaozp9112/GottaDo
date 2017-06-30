package cn.com.gottado.main.play.config;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import cn.com.gottado.R;

/**
 * Created by Administrator on 2017/6/8.
 * 玩一玩二级viewholder
 */

public class PlayLViewHolder extends RecyclerView.ViewHolder {

    private ImageView mImageView; // 标题图片
    private TextView mTvTitle; // 名称
    private TextView mTip; // 名称
    public PlayLViewHolder( View itemView) {
        super(itemView);
        mTvTitle = (TextView) itemView.findViewById(R.id.play_item_name);
        mImageView= (ImageView) itemView.findViewById(R.id.play_item_picture);
        mTip=(TextView)itemView.findViewById(R.id.play_item_tip);
    }

    public TextView getTvTitle() {
        return mTvTitle;
    }

    public ImageView getmImageView() {
        return mImageView;
    }

    public TextView getTip() {
        return mTip;
    }
}

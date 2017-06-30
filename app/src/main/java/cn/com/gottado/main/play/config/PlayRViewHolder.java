package cn.com.gottado.main.play.config;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import cn.com.gottado.R;

/**
 * Created by Administrator on 2017/6/8.
 */

public class PlayRViewHolder extends RecyclerView.ViewHolder {

    private TextView mTvTitle; // 标题
    private TextView mTvTip; // 提示
    public PlayRViewHolder( View itemView) {
        super(itemView);
        mTvTitle = (TextView) itemView.findViewById(R.id.play_item_title);
        mTvTip = (TextView) itemView.findViewById(R.id.play_item_tip);
    }

    public TextView getTvTitle() {
        return mTvTitle;
    }

    public TextView getTvTip() {
        return mTvTip;
    }

}

package cn.com.gottado.main.play.config;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.xutils.common.util.DensityUtil;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.List;

import cn.com.gottado.R;
import cn.com.gottado.tool.db.table.MToolsItem;
import cn.com.gottado.tool.util.StringTools;

/**
 * Created by Administrator on 2017/6/8.
 */

public class PlayLViewAdapter extends RecyclerView.Adapter<PlayLViewHolder>{

    private OnItemClickListener mOnItemClickListener = null;

    private List<MToolsItem> mToolsItems;
    private ImageOptions mImageOptions;
    public PlayLViewAdapter(List<MToolsItem> data) {
        if (data == null) {
            throw new IllegalArgumentException("playItems must not be null");
        }
        mToolsItems = data;
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
        MToolsItem dataModel = mToolsItems.get(position);

        viewHolder.getTvTitle().setText(dataModel.getTitle());

        viewHolder.getTvTitle().setTransitionName("R.id.main_toolbar");
         mImageOptions=new ImageOptions.Builder()
        //         .setSize(DensityUtil.dip2px(100),DensityUtil.dip2px(100))//设置图片大小
                //       .setRadius(DensityUtil.dip2px(5))//圆角半径
                .setCrop(true)//是否对图片进行裁剪,如果ImageView的大小不是定义为wrap_content, 不要crop
                .setFadeIn(true)//设置淡入效果
                .setLoadingDrawableId(R.drawable.ic_broken_image_black)//设置加载过程中显示的图片
                .setFailureDrawableId(R.drawable.ic_broken_image_black)//设置加载失败显示的图片
                .setUseMemCache(true)//设置使用缓存
                .setCircular(true)//设置图片显示为圆形
                .setIgnoreGif(true)//设置支持gif
                .build();
        x.image().bind( viewHolder.getmImageView(),dataModel.getBgPicUrl());
     //   viewHolder.getmImageView().setImageResource(dataModel.getImageViewRes());
      /*  if(StringTools.strNotEmpty(dataModel.getTip())){
            viewHolder.getTip().setVisibility(View.VISIBLE);
            viewHolder.getTip().setText(dataModel.getTip());
        }*/
        viewHolder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return mToolsItems.size();
    }

    public void addData(MToolsItem dataModel,int position) {
        mToolsItems.add(position, dataModel);
        notifyItemInserted(position);
    }

    public void removeData(int position) {
        mToolsItems.remove(position);
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

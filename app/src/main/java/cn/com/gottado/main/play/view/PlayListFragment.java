package cn.com.gottado.main.play.view;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONObject;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;

import cn.com.gottado.R;
import cn.com.gottado.main.play.config.PlayItem;
import cn.com.gottado.main.play.config.PlayItemDecoration;
import cn.com.gottado.main.play.config.PlayLViewAdapter;
import cn.com.gottado.main.play.config.PlayLViewHolder;
import cn.com.gottado.tool.base.BaseFragment;
import cn.com.gottado.tool.base.FragmentActivity;
import cn.com.gottado.tool.util.JsonTools;

/**
 * Created by Administrator on 2017/6/8.
 */
@ContentView(R.layout.fragment_play)
public class PlayListFragment extends BaseFragment {


    private ArrayList<PlayItem> data=null;
    @ViewInject(R.id.play_recyclerview)
    private RecyclerView mRecyclerView =null;
    private  PlayLViewAdapter mAdapter =null;
    public static PlayListFragment newInstance(String param1) {
        PlayListFragment fragment = new PlayListFragment();
        Bundle args = new Bundle();
        args.putString("title", param1);
        fragment.setArguments(args);
        return fragment;
    }

    public PlayListFragment() {

    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //     mView=inflater.inflate(R.layout.fragment_play,container,false);
       mView = x.view().inject(this,inflater,container);
        return mView;
    }

    @Override
    public void setupUI() {
        Bundle bundle = getArguments();
        String title = bundle.getString("title");
        mToolbar.setTitle(title);
        mActivity.setSupportActionBar(mToolbar);
        initRecyclerView();
    }

    private void initRecyclerView(){
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.addItemDecoration(new PlayItemDecoration(mContext));
        mAdapter=new PlayLViewAdapter(getData());
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new PlayLViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, RecyclerView.ViewHolder viewHolder) {

                Intent intent =new Intent(mActivity,FragmentActivity.class);
                intent.putExtra(FragmentActivity.CLASSNAME,data.get(position).getClassName());
                intent.putExtra(FragmentActivity.PARAMS,data.get(position).getParams());
                mActivity.startActivityShareEle(intent,
                        ((PlayLViewHolder)viewHolder).getTvTitle(),
                        getString(R.string.toolbar_transition_name));

            }
        });

    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.play_base_menu,menu);

    }
    /**
     * 数据
     *
     * @return 数据
     */
    private ArrayList<PlayItem> getData() {
        int count = 10;
        data = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            PlayItem model = new PlayItem();
            model.setTitle("百度搜索恶搞");
            model.setTip("古怪 搞笑 恶搞"+i);
            model.setHeight((int) (150 + (i%3+1) * 150));
            model.setImageViewRes(R.drawable.baidu_bg);
            model.setClassName("cn.com.gottado.main.play.function.BDEditFragment");
            JSONObject parmasJson=new JSONObject();
            JsonTools.put(parmasJson,"title",model.getTitle());
            model.setParams(parmasJson.toString());
            data.add(model);
        }

        return data;
    }


}

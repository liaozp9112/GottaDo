package cn.com.gottado.main.play.view;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.ypy.eventbus.EventBus;

import org.json.JSONArray;
import org.json.JSONObject;
import org.xutils.DbManager;
import org.xutils.db.Selector;
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
import cn.com.gottado.tool.db.DbUtil;
import cn.com.gottado.tool.db.XDaoConfig;
import cn.com.gottado.tool.db.table.MToolsItem;
import cn.com.gottado.tool.eventbus.model.ArrayListEvent;
import cn.com.gottado.tool.eventbus.model.ObjectEvent;
import cn.com.gottado.tool.http.HttpAction;
import cn.com.gottado.tool.http.HttpParams;
import cn.com.gottado.tool.http.HttpUtil;
import cn.com.gottado.tool.http.JsonCallBack;
import cn.com.gottado.tool.util.JsonTools;
import cn.com.gottado.tool.util.LogUtil;
import cn.com.gottado.tool.util.StringTools;

/**
 * Created by Administrator on 2017/6/8.
 */
@ContentView(R.layout.fragment_play)
public class PlayListFragment extends BaseFragment {


    private ArrayList<MToolsItem> data=new ArrayList<>();
    @ViewInject(R.id.play_recyclerview)
    private RecyclerView mRecyclerView =null;
    private  PlayLViewAdapter mAdapter =null;

    private SearchView mSearchView=null;
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
        EventBus.getDefault().register(this);
        return mView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.play_base_menu,menu);
        initSearchView(menu);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private void initSearchView(Menu menu){
        MenuItem item = menu.findItem(R.id.action_search);
        mSearchView = (SearchView) MenuItemCompat.getActionView(item);
        // 设置搜索文本监听
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            // 当点击搜索按钮时触发该方法
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            // 当搜索内容改变时触发该方法
            @Override
            public boolean onQueryTextChange(String newText) {
                data.clear();
                if(StringTools.strNotEmpty(newText)){
                    data.addAll(MToolsItem.findByTitleKey(newText));
                }else{
                    data.addAll(MToolsItem.findBySort());
                }
                mAdapter.notifyDataSetChanged();
                return false;
            }
        });
    }

    @Override
    public void setupUI() {
        Bundle bundle = getArguments();
        String title = bundle.getString("title");
        mToolbar.setTitle(title);
        mActivity.setSupportActionBar(mToolbar);
        getLocalData();

    }

    private void initRecyclerView(){
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.addItemDecoration(new PlayItemDecoration(mContext));
        mAdapter=new PlayLViewAdapter(data);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new PlayLViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, RecyclerView.ViewHolder viewHolder) {

                Intent intent =new Intent(mActivity,FragmentActivity.class);
                JSONObject content =JsonTools.generateJsonObj(data.get(position).getContent());
                intent.putExtra(FragmentActivity.CLASSNAME,JsonTools.getJsonString(content,"android_path"));
                intent.putExtra(FragmentActivity.PARAMS,JsonTools.getJsonString(content,"param"));
                mActivity.startActivityShareEle(intent,
                        ((PlayLViewHolder)viewHolder).getTvTitle(),
                        getString(R.string.toolbar_transition_name));

            }
        });
        mRecyclerView.setVisibility(View.VISIBLE);
        findById(R.id.progress_layout).setVisibility(View.GONE);
    }



    /**
     * 数据
     *
     * @return 数据
     */
    private void getLocalData() {
        ArrayList dataResult = MToolsItem.findBySort();
        if(dataResult!=null&&dataResult.size()>0){
            EventBus.getDefault().post(new ArrayListEvent(dataResult));
        }

    }


    public void onEventMainThread(ObjectEvent objectEvent){
        ArrayListEvent<MToolsItem> listEvent=(ArrayListEvent<MToolsItem>)objectEvent;
        data=listEvent.getArrayList();
        initRecyclerView();

    }



}

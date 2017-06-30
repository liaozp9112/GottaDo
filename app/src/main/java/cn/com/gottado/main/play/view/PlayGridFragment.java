package cn.com.gottado.main.play.view;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import cn.com.gottado.R;
import cn.com.gottado.main.play.config.PlayItem;
import cn.com.gottado.main.play.config.PlayItemDecoration;
import cn.com.gottado.main.play.config.PlayRViewAdapter;
import cn.com.gottado.tool.base.BaseFragment;

/**
 * Created by Administrator on 2017/6/8.
 */

public class PlayGridFragment extends BaseFragment {


    private RecyclerView mRecyclerView =null;

    public static PlayGridFragment newInstance(String param1) {
        PlayGridFragment fragment = new PlayGridFragment();
        Bundle args = new Bundle();
        args.putString("title", param1);
        fragment.setArguments(args);
        return fragment;
    }

    public PlayGridFragment() {

    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_play, container, false);
        setupUI();
        return mView;
    }

    @Override
    public void setupUI() {
        Bundle bundle = getArguments();
        String title = bundle.getString("title");
        mToolbar=(Toolbar)mView.findViewById(R.id.main_toolbar);
        mToolbar.setTitle(title);
        mActivity.setSupportActionBar(mToolbar);
        mRecyclerView=(RecyclerView) mView.findViewById(R.id.play_recyclerview);
        initRecyclerView();
    }

    private void initRecyclerView(){
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));
        mRecyclerView.addItemDecoration(new PlayItemDecoration(mContext));
        mRecyclerView.setAdapter(new PlayRViewAdapter(getData()));
    }


    /**
     * 数据
     *
     * @return 数据
     */
    private ArrayList<PlayItem> getData() {
        int count = 10;
        ArrayList<PlayItem> data = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            PlayItem model = new PlayItem();
            model.setTitle("光棍节特别活动之小小小泥邱"+i);
            model.setTip("tip"+i);
            model.setHeight((int) (150 + (i%3+1) * 150));
            data.add(model);
        }

        return data;
    }
}

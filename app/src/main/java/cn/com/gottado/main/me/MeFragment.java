package cn.com.gottado.main.me;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.xutils.view.annotation.ContentView;
import org.xutils.x;

import cn.com.gottado.R;
import cn.com.gottado.tool.base.BaseFragment;

/**
 * Created by Administrator on 2017/6/8.
 */
@ContentView(R.layout.fragment_me)
public class MeFragment extends BaseFragment {

    public static MeFragment newInstance(String param1) {
        MeFragment fragment = new MeFragment();
        Bundle args = new Bundle();
        args.putString("title", param1);
        fragment.setArguments(args);
        return fragment;
    }

    public MeFragment() {

    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
     mView= x.view().inject(this,inflater,container);
        //    mView=inflater.inflate(R.layout.fragment_me,container,false);
        return mView;
    }

    @Override
    public void setupUI() {
        Bundle bundle = getArguments();
        String title = bundle.getString("title");
        mToolbar.setTitle(title);


    }

}

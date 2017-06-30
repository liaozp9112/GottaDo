package cn.com.gottado.tool.base;

import android.app.ActivityOptions;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.util.Pair;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import org.xutils.view.annotation.ViewInject;

import cn.com.gottado.R;

/**
 * Created by Administrator on 2017/6/8.
 */

public  class BaseFragment extends Fragment {

    @ViewInject(R.id.main_toolbar)
    public Toolbar mToolbar=null;
    @ViewInject(R.id.toolbar_title)
    public TextView toolbarTvt=null;
    public View mView=null;

    public Context mContext;

    private Boolean hasBack=false;

    public BaseActivity mActivity;

    private boolean mShowBarTitle=true;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = (BaseActivity)getActivity();
        mContext = getActivity();
        setHasOptionsMenu(true);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupUI();
        if(mToolbar!=null){
            if(mActivity.getSupportActionBar()==null){
                mActivity.setSupportActionBar(mToolbar);
            }
            if(hasBack){
            //关键下面两句话，设置了回退按钮，及点击事件的效果
                    mActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                    mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mActivity.onBackPressed();
                    }
                });
            }
            mActivity.getSupportActionBar().setDisplayShowTitleEnabled(mShowBarTitle);

        }
    }

    public  void setupUI(){};

    public void setHasBack(boolean hasBack){
        this.hasBack=hasBack;
    }

    public View findById(int resid){
        return mView.findViewById(resid);
    }

    public View findById(View view,int resid){
        return view.findViewById(resid);
    }
    public void setShowToolbarTitle(boolean show){
        mShowBarTitle=show;
    }



}

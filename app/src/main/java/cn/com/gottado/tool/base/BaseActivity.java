package cn.com.gottado.tool.base;

import android.app.ActivityOptions;
import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;

import com.ypy.eventbus.EventBus;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import cn.com.gottado.R;

/**
 * Created by Administrator on 2017/6/7.
 */

public  class BaseActivity extends android.support.v7.app.AppCompatActivity{
    public AppCompatActivity mActivity=this;

    public Toolbar mToolbar=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BaseApp.verifyStoragePermissions(this);
        x.view().inject(this);

    }



    public void hideKeyBoard(){
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(mActivity.getWindow().getDecorView().getWindowToken(), 0);
   }

    //***只带一个分享元素的跳转*/
    public void startActivityShareEle(Intent intent,View view,String name){
        startActivity(intent,ActivityOptions.makeSceneTransitionAnimation(mActivity,
                        view,name).toBundle());
    }

    public void startTransActivity(Intent intent){
     // startActivity(intent, ActivityOptionsCompat.makeSceneTransitionAnimation(mActivity).toBundle());
        startActivity(intent, ActivityOptionsCompat.makeSceneTransitionAnimation(mActivity).toBundle());
        //   startActivity(intent);
    }

    public void setTransition(){
        // 设置contentFeature,可使用切换动画
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        Transition explode = TransitionInflater.from(this).inflateTransition(android.R.transition.explode);
        getWindow().setEnterTransition(explode);
    }

    public void setupWindowAnimations() {
        // We are not interested in defining a new Enter Transition. Instead we change default transition duration
        getWindow().getEnterTransition().setDuration(getResources().getInteger(R.integer.anim_duration_long));
    }
}

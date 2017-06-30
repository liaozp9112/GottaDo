package cn.com.gottado.main.tab;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import cn.com.gottado.R;
import cn.com.gottado.main.loc.LocFragment;
import cn.com.gottado.main.me.MeFragment;
import cn.com.gottado.main.play.view.PlayListFragment;
import cn.com.gottado.tool.base.BaseActivity;

/**
 * Created by Administrator on 2017/6/8.
 */
@ContentView(R.layout.layout_tab_mian)
public class GdTabActivity extends BaseActivity implements BottomNavigationBar.OnTabSelectedListener{

    private PlayListFragment mPlayFragment;
    private LocFragment mLocFragment;
    private MeFragment mMeFragment;
    @ViewInject(R.id.bottom_navigation_bar)
    private BottomNavigationBar bottomNavigationBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupUI();
        setupWindowAnimations();
    }



    public void setupUI(){
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_SHIFTING);
        bottomNavigationBar
                .addItem(new BottomNavigationItem(R.drawable.tab_play, "玩一玩"))
                .addItem(new BottomNavigationItem(R.drawable.tab_map, "找ta"))
                .addItem(new BottomNavigationItem(R.drawable.tab_me, "我的"))
                .initialise();

        bottomNavigationBar.setTabSelectedListener(this);
        setDefaultFragment();
    }

    /**
     * 设置默认的
     */
    private void setDefaultFragment() {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        mPlayFragment = PlayListFragment.newInstance("玩一玩");
        transaction.replace(R.id.tabs, mPlayFragment);
        transaction.commit();
    }
    @Override
    public void onTabSelected(int position) {
        FragmentManager fm = this.getFragmentManager();
        //开启事务
        FragmentTransaction transaction = fm.beginTransaction();
        switch (position) {
            case 0:
                if (mPlayFragment == null) {
                    mPlayFragment = PlayListFragment.newInstance("玩一玩");
                }
                transaction.replace(R.id.tabs, mPlayFragment);
                break;
            case 1:
                if (mLocFragment == null) {
                    mLocFragment = LocFragment.newInstance("找ta");
                }
                transaction.replace(R.id.tabs, mLocFragment);
                break;
            case 2:
                if (mMeFragment == null) {
                    mMeFragment = MeFragment.newInstance("我");
                }
                transaction.replace(R.id.tabs, mMeFragment);
                break;

            default:
                break;
        }
        // 事务提交
        transaction.commit();
    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }
}

package cn.com.gottado.tool.base;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

import cn.com.gottado.R;

/**
 * Created by Administrator on 2017/6/12.
 */

public class FragmentActivity extends BaseActivity {

    public static final String FRAGMENTACTIVITY="FragmentActivity";
    public static final String PARAMS="PARAMS";
    public static final String CLASSNAME="CLASSNAME";
    private String className="";
    private BaseFragment mBaseFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTransition();
        setContentView(R.layout.activity_fragment);
        getParamsAndInit();
        setupWindowAnimations();
    }
    @SuppressWarnings("ResourceType")
    private void getParamsAndInit(){
        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        Class mClass ;
        Bundle bundleParam=new Bundle();
        try {

            className= getIntent().getStringExtra(CLASSNAME);


            mClass =  Class.forName(className);
            bundleParam.putString(PARAMS,getIntent().getStringExtra(PARAMS));
            mBaseFragment = (BaseFragment)mClass.newInstance();
            mBaseFragment.setArguments(bundleParam);

        } catch (Exception e) {
            e.printStackTrace();
            try {
                mClass =  Class.forName("cn.com.gottado.tool.base.ErrorFragment");
                bundleParam.putString(PARAMS,ErrorFragment.ERROR_PAGE_NOT_FOUND);
                mBaseFragment = (BaseFragment)mClass.newInstance();
                mBaseFragment.setArguments(bundleParam);
            }catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        transaction.replace(R.id.activity_fragment_root, mBaseFragment);
        transaction.commit();
    }


}

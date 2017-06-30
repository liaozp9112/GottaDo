package cn.com.gottado.tool.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import cn.com.gottado.R;

/**
 * Created by Administrator on 2017/6/28.
 */
@ContentView(R.layout.fragment_error)
public class ErrorFragment extends BaseFragment {

    public final static String ERROR_PAGE_NOT_FOUND = "404";

    public final static String ERROR_UNKNOW = "1";

    public static String ERROR_TYPE = ERROR_UNKNOW;

    @ViewInject(R.id.text_error)
    private TextView detailText;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = x.view().inject(this,inflater,container);
        return mView;
    }


    @Override
    public void setupUI() {
        super.setupUI();
        setHasBack(true);
        mToolbar.setTitle("返回");
        mActivity.setSupportActionBar(mToolbar);
        setUpErrorText();

    }

    private void setUpErrorText(){
        try {
            ERROR_TYPE=getArguments().getString(FragmentActivity.PARAMS);
        }catch (Exception e) {
            e.printStackTrace();
            ERROR_TYPE = ERROR_UNKNOW;
        }
        switch (ERROR_TYPE){
            case ERROR_PAGE_NOT_FOUND:
                detailText.setText(getString(R.string.error_page_not_found_text));
                break;
            default:
                detailText.setText(getString(R.string.error_unknow_text));
                break;
        }
    }
}

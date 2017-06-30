package cn.com.gottado.main.play;

import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.xutils.view.annotation.ViewInject;

import cn.com.gottado.R;
import cn.com.gottado.tool.base.BaseFragment;

/**
 * Created by Administrator on 2017/6/20.
 */

public class PlayBasicFragment extends BaseFragment implements View.OnClickListener{
    private View qqShareView=null;
    private View wechatShareView=null;
    private View pyqShareView=null;
    private View weiboShareView=null;
    private Button cancelBtn=null;
    private View dialogRootView=null;
    private BottomSheetDialog dialog=null;

    public void popShareMenu(){
        if(dialog==null){
            dialog  = new BottomSheetDialog(mContext);
            dialogRootView= mActivity.getLayoutInflater().inflate(R.layout.menu_play_share, null);
            //qq
            qqShareView=findById(dialogRootView,R.id.qq_share_view);
            qqShareView.setOnClickListener(this);
            //weixin
            wechatShareView=findById(dialogRootView,R.id.wechat_share_view);
            wechatShareView.setOnClickListener(this);
            //pengyouquan
            pyqShareView=findById(dialogRootView,R.id.wechat_pyq_share_view);
            pyqShareView.setOnClickListener(this);
            //weibo
            weiboShareView=findById(dialogRootView,R.id.weibo_share_view);
            weiboShareView.setOnClickListener(this);
            cancelBtn=(Button)findById(dialogRootView,R.id.cancel_menu_btn);
            cancelBtn.setOnClickListener(this);
            dialog.setContentView(dialogRootView);
        }
        dialog.show();
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.cancel_menu_btn:
                dismissDialog();
                break;
            case R.id.qq_share_view:
                shareImgToQQ();
                break;
            case R.id.wechat_share_view:
                shareImgToWechat();
                break;
            case R.id.wechat_pyq_share_view:
                shareImgToPyq();
                break;
            case R.id.weibo_share_view:
                shareImgToWeibo();
                break;
            default:break;
        }
    }

    private void dismissDialog(){
        if(dialog!=null&&dialog.isShowing()){
            dialog.dismiss();
        }
    }

    public void shareImgToQQ(){
        Toast.makeText(getActivity(),"QQ",Toast.LENGTH_SHORT).show();
    }

    public void shareImgToWechat(){
        Toast.makeText(getActivity(),"wechat",Toast.LENGTH_SHORT).show();
    }

    public void shareImgToPyq(){
        Toast.makeText(getActivity(),"pyq",Toast.LENGTH_SHORT).show();
    }

    public void shareImgToWeibo(){
        Toast.makeText(getActivity(),"weibo",Toast.LENGTH_SHORT).show();
    }
}

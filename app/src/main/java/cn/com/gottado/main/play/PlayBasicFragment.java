package cn.com.gottado.main.play;

import android.content.ComponentName;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.xutils.view.annotation.ViewInject;

import java.io.File;
import java.io.IOException;

import cn.com.gottado.R;
import cn.com.gottado.tool.base.BaseFragment;
import cn.com.gottado.tool.config.Config;
import cn.com.gottado.tool.util.AndroidShare;
import cn.com.gottado.tool.util.FileUtil;
import cn.com.gottado.tool.util.ImageUtil;

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
    protected String IMG_PATH="";
    protected Bitmap showBitmap=null;

    private AndroidShare androidShare=null;

    protected void popShareMenu(){
        dialog.show();
    }

    protected void popSysShareMenu(){
        androidShare.getSysShareIntent(mActivity.getTitle()+"","",
                AndroidShare.DRAWABLE,showBitmap);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.cancel_menu_btn:
                dismissDialog();
                break;
            case R.id.qq_share_view:
                androidShare.shareQQFriend(mActivity.getTitle()+"","",
                        AndroidShare.DRAWABLE,showBitmap);
                break;
            case R.id.wechat_share_view:
                androidShare.shareWeChatFriend(mActivity.getTitle()+"","",
                        AndroidShare.DRAWABLE,showBitmap);
                break;
            case R.id.wechat_pyq_share_view:
                androidShare.shareWeChatFriendCircle(mActivity.getTitle()+"","",showBitmap);
                break;
            case R.id.weibo_share_view:
                androidShare.shareWeibo(mActivity.getTitle()+"","",showBitmap);
                break;
            default:break;
        }
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        androidShare=new AndroidShare(mContext);
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
    }

    private void dismissDialog(){
        if(dialog!=null&&dialog.isShowing()){
            dialog.dismiss();
        }
    }



    protected void saveImage(String filename, Bitmap bitmap){

        String filepath= Config.APP_PATH + FileUtil.PLAY_PATH+filename;
        IMG_PATH=filepath;
        try {
            ImageUtil.saveFile(mContext, bitmap,filename, FileUtil.PLAY_PATH);
        }catch (IOException e){
            e.printStackTrace();
        }

    }
}

package cn.com.gottado.main.play.function;

import android.content.ComponentName;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONObject;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.File;
import java.io.IOException;

import cn.com.gottado.R;
import cn.com.gottado.main.play.PlayBasicFragment;
import cn.com.gottado.tool.base.FragmentActivity;
import cn.com.gottado.tool.config.Config;
import cn.com.gottado.tool.util.FileUtil;
import cn.com.gottado.tool.util.ImageUtil;
import cn.com.gottado.tool.util.JsonTools;
import cn.com.gottado.tool.util.StringTools;
import cn.com.gottado.tool.view.SnackbarUtil;
import cn.com.gottado.tool.view.TouchImageView;

/**
 * Created by Administrator on 2017/6/13.
 */
@ContentView(R.layout.play_baidu_edit)
public class BDEditFragment extends PlayBasicFragment{

  @ViewInject(R.id.search_input)
    private  EditText input1;
   @ViewInject(R.id.find_input)
    private EditText input2;
   @ViewInject(R.id.btn_preview)
    private Button btn_preview=null;
  @ViewInject(R.id.bd_img)
    private TouchImageView mImageView=null;

    private Bitmap sourBitmap=null,showBitmap;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = x.view().inject(this,inflater,container);
        return mView;
    }

    @Override
    public void setupUI() {
        super.setupUI();
        //toolbar
        Bundle bundle = getArguments();
        JSONObject paramsJson= JsonTools.generateJsonObj(bundle.getString(FragmentActivity.PARAMS));
        String title = JsonTools.getJsonString(paramsJson,"title");
        toolbarTvt.setText(title);
        setShowToolbarTitle(false);
        setHasBack(true);
        //view
        mImageView=(TouchImageView)findById(R.id.bd_img) ;
        mImageView.setMaxZoom(4f);
        mImageView.setImageResource(R.drawable.baidu_bg);
        sourBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.baidu_bg);
        showBitmap=sourBitmap;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.play_menu,menu);
    }

    @Event(value=R.id.btn_preview,type = View.OnClickListener.class)
    private void createImg(View v){
        mActivity.hideKeyBoard();
        showBitmap=sourBitmap;
        createFirstBmp();
        createSecondBmp();
        mImageView.setImageBitmap(showBitmap);
    }

    private void createFirstBmp(){
        String text1= StringTools.getEdittxt(input1);

        showBitmap = ImageUtil.drawTextToLeftTop(mContext, showBitmap, text1, 50, Color.BLACK, 310, 36);
    }

    private void createSecondBmp(){
        String text2= StringTools.getEdittxt(input2);
      //  ((TextView)findById(R.id.find_input_text)).setText();
        showBitmap = ImageUtil.drawTextToLeftBottom(mContext, showBitmap, text2, 35, Color.BLUE, 590, 14);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_save:
                saveImage();
                break;
            case R.id.action_share:
              //  saveAndShare();
                popShareMenu();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void saveImage(){

        try {
          ImageUtil.saveFile(mContext, showBitmap, System.currentTimeMillis() + ".jpg", "/play/");
            SnackbarUtil.showNormal(mView,"保存成功");
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    private void saveAndShare(){
        String filename= System.currentTimeMillis() + ".jpg";
        String filepath= Config.APP_PATH + FileUtil.PLAY_PATH+filename;
        try {
            ImageUtil.saveFile(mContext, showBitmap,filename, FileUtil.PLAY_PATH);
            shareWeChat(filepath);
        }catch (IOException e){
            e.printStackTrace();
            SnackbarUtil.showNormal(mView,"请先安装微信");
        }
    }

    private void shareWeChat(String path){
        Uri uriToImage = Uri.fromFile(new File(path));
        Intent shareIntent = new Intent();
        //发送图片到朋友圈
        //ComponentName comp = new ComponentName("com.tencent.mm", "com.tencent.mm.ui.tools.ShareToTimeLineUI");
        //发送图片给好友。
        ComponentName comp = new ComponentName("com.tencent.mm", "com.tencent.mm.ui.tools.ShareImgUI");
        shareIntent.setComponent(comp);
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_STREAM, uriToImage);
        shareIntent.setType("image/jpeg");
        startActivity(Intent.createChooser(shareIntent, "搞起"));
    }
}

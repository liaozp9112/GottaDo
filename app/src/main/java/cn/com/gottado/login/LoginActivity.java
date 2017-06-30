package cn.com.gottado.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import cn.com.gottado.R;
import cn.com.gottado.main.tab.GdTabActivity;
import cn.com.gottado.tool.base.BaseActivity;
import cn.com.gottado.tool.view.SnackbarUtil;

/**
 * Created by Administrator on 2017/6/7.
 */
@ContentView(R.layout.activity_login)
public class LoginActivity extends BaseActivity {

    @ViewInject(R.id.username_input)
    private EditText nameInput;
    @ViewInject(R.id.pwd_input)
    private EditText pwdInput;
    @ViewInject(R.id.login_button)
    private Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupToolbar();

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyBoard();
                Intent intent =new Intent(mActivity,GdTabActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void setupToolbar() {
        mToolbar=(Toolbar) findViewById(R.id.main_toolbar);
         mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close_white);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    //    getMenuInflater().inflate(R.menu.login_menu,menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
            default:break;
        }
        return super.onOptionsItemSelected(item);
    }
}

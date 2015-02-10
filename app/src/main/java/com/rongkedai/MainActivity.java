package com.rongkedai;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.rongkedai.misc.Urls;
import com.rongkedai.ui.ProjectListActivity;
import com.rongkedai.ui.ProjectNoticeListActivity;
import com.yuexiaohome.framework.util.L;

public class MainActivity extends Activity {
    static String[] adPics = {Urls.AD_PIC_1, Urls.AD_PIC_2, Urls.AD_PIC_3, Urls.AD_PIC_4, Urls.AD_PIC_5};

    @InjectView(R.id.ad_iv)
    ImageView adIv;

    @Override
    protected void onStart() {
        super.onStart();

        int picIndex=(int)(Math.random()*5);
        L.d("pic index:"+picIndex);
        DisplayImageOptions options =
                new DisplayImageOptions.Builder().cacheOnDisk(true).cacheInMemory(true).build();
        ImageLoader.getInstance().displayImage(adPics[picIndex], adIv, options);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
    }

    @OnClick({R.id.borrow_tv, R.id.project_notice_tv, R.id.website_notice_view, R.id.signin_tv, R.id.about_tv, R.id.discussion_tv, R.id.account_tv})
    public void launchActivity(TextView tv) {
        Intent intent;
        switch (tv.getId()) {
            case R.id.borrow_tv:
                intent = new Intent(this, ProjectListActivity.class);
                //intent.putExtra("url",Urls.PROJECT_LIST_WEB);
                startActivity(intent);
                break;
            case R.id.website_notice_view:
                intent = new Intent(this, WebViewActivity.class);
                intent.putExtra("url", Urls.WEBSITE_NOTICE_WEB);
                startActivity(intent);
                break;
            case R.id.about_tv:
                intent = new Intent(this, WebViewActivity.class);
                intent.putExtra("url", Urls.ABOUT_US_WEB);
                startActivity(intent);
                break;
            case R.id.project_notice_tv:
                intent = new Intent(this, ProjectNoticeListActivity.class);
                startActivity(intent);
                break;
            case R.id.signin_tv:
                intent = new Intent(this, WebViewActivity.class);
                intent.putExtra("enlargePic",false);
                intent.putExtra("url", Urls.SIGNIN_WEB);
                startActivity(intent);
                break;
            case R.id.discussion_tv:
                intent = new Intent(this, WebViewActivity.class);
                intent.putExtra("url", Urls.DISCUSSION_WEB);
                startActivity(intent);
                break;
            case R.id.account_tv:
                intent = new Intent(this, WebViewActivity.class);
                intent.putExtra("url", Urls.ACCOUNT_WEB);
                startActivity(intent);
                break;

        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_common, menu);

        // It is also possible add items here. Use a generated id from
        // resources (ids.xml) to ensure that all menu ids are distinct.
        MenuItem locationItem = menu.add(0, R.id.action_about, 0, "说明");
        //locationItem.setIcon(R.drawable.ic_action_location);

        // Need to use MenuItemCompat methods to call any action item related methods
        MenuItemCompat.setShowAsAction(locationItem, MenuItem.SHOW_AS_ACTION_NEVER);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_about:
                Intent intent = new Intent(this, WebViewActivity.class);
                intent.putExtra("data", getString(R.string.remark));
                startActivity(intent);
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}

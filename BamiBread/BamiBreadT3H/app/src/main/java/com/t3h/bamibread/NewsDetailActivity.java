package com.t3h.bamibread;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.t3h.bamibread.base.BaseActivity;
import com.t3h.bamibread.model.NewsBamiBread;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Heart Of Dead on 9/15/2017.
 */

public class NewsDetailActivity extends BaseActivity {
    @BindView(R.id.mtoolbar)
    Toolbar mToolbar;
    @BindView(R.id.txt_news)
    TextView txtNews;
    @BindView(R.id.img_news)
    ImageView imgNews;

    @Override
    public int getLayoutMain() {
        return R.layout.news_and_recruit_detail;
    }

    @Override
    public void initComponents() {
        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        Intent intent = getIntent();
        NewsBamiBread newsBamiBread = (NewsBamiBread) intent.getSerializableExtra("NEWBAMI");
        txtNews.setText(newsBamiBread.getMessage());
        Picasso.with(this).load(newsBamiBread.getLinkImage()).into(imgNews);


    }

    @Override
    public void addEvents() {

    }
}

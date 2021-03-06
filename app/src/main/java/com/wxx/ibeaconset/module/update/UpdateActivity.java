package com.wxx.ibeaconset.module.update;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.wxx.ibeaconset.R;
import com.wxx.ibeaconset.Utils.Util;
import com.wxx.ibeaconset.base.BaseActivity;
import com.wxx.ibeaconset.bean.PageList;
import com.yanzhenjie.nohttp.Logger;

import java.io.InputStream;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 作者：Tangren_ on 2017/4/25 0025.
 * 邮箱：wu_tangren@163.com
 * TODO:用一句话概括
 */


public class UpdateActivity extends BaseActivity<UpdateView, UpdatePresenter> implements UpdateView {
    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.title)
    TextInputEditText title;
    @BindView(R.id.input_title)
    TextInputLayout inputTitle;
    @BindView(R.id.des)
    TextInputEditText des;
    @BindView(R.id.input_des)
    TextInputLayout inputDes;
    @BindView(R.id.comment)
    TextInputEditText comment;
    @BindView(R.id.input_comment)
    TextInputLayout inputComment;
    @BindView(R.id.url)
    TextInputEditText url;
    @BindView(R.id.button)
    Button button;
    @BindView(R.id.mtoolbar)
    Toolbar mtoolbar;
    @BindView(R.id.page_id)
    TextInputEditText pageId;
    @BindView(R.id.input_pageid)
    TextInputLayout inputPageid;

    private PageList.DataBean.PagesBean bean;

    private String imagePath = null;

    private Bitmap bitmap = null;

    private static final int DEFAULT_CODE = 0;

    @Override
    protected UpdatePresenter createPresenter() {
        return new UpdatePresenter(this);
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_update_page;
    }

    private String activity = null;

    @Override
    protected void initView() {
        mtoolbar.setTitle("修改页面");
        setSupportActionBar(mtoolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void initData() {
        bean = getIntent().getParcelableExtra("pageList");
        if (bean == null) {
            activity = "main";
            bean = new PageList.DataBean.PagesBean();
            inputPageid.setVisibility(View.GONE);
        } else {
            activity = "manager";
            title.setText(bean.getTitle());
            des.setText(bean.getDescription());
            comment.setText(bean.getComment());
            url.setText(bean.getPage_url());
            pageId.setText(bean.getPage_id() + "");
            Picasso.with(this).load(bean.getIcon_url()).placeholder(R.mipmap.timg).error(R.drawable.ic_ico_load_fail).into(img);
        }
    }

    @Override
    public void onSuccess(int code, int type, String msg) {
        if (code == 1 && type == 4) {
            bean.setIcon_url(msg);
            mPresenter.fetchUpdate(bean, activity);
        } else if (code == 1 && type == 5) {
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
            setResult(DEFAULT_CODE);
            finishActivityFromRight();
        } else {
            finishActivityFromRight();
        }
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFail(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }


    @OnClick({R.id.button, R.id.img})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img:
                Intent intent = new Intent(Intent.ACTION_PICK);
//                if (Build.VERSION.SDK_INT < 19) {
//                    intent.setAction(Intent.ACTION_GET_CONTENT);
//                } else {
//                    intent.setAction(Intent.ACTION_OPEN_DOCUMENT);
//                }
                intent.setType("image/*");
                startActivityForResult(intent, 1);
                break;
            case R.id.button:

                bean.setTitle(title.getText().toString());
                bean.setDescription(des.getText().toString());
                bean.setComment(comment.getText().toString());
                if (activity.equals("manager"))
                    bean.setPage_id(Integer.valueOf(pageId.getText().toString()));
                bean.setPage_url(url.getText().toString());
                if (TextUtils.isEmpty(title.getText().toString()) ||
                        TextUtils.isEmpty(des.getText().toString()) ||
                        TextUtils.isEmpty(url.getText().toString())) {
                    Toast.makeText(this, "必填参数缺省", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (imagePath != null) {
                    InputStream stream = Util.Bitmap2InputStream(bitmap, 100);
                    mPresenter.addImage(stream);
                } else {
                    mPresenter.fetchUpdate(bean, activity);
                }

                break;
            default:
                break;
        }


    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Logger.d("requestCode=" + requestCode + ",resultCode=" + resultCode);
        if (requestCode == 1) {
            if (data != null) {
                Uri uri = data.getData();
                imagePath = Util.getImagePath(getApplicationContext(), uri);
                if (imagePath == null) return;
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inJustDecodeBounds = true;
                BitmapFactory.decodeFile(imagePath, options);

                double radio = Math.max(options.outWidth * 1.0f / 1024f, options.outHeight * 1.0f / 1024f);
                options.inSampleSize = (int) Math.ceil(radio);
                options.inJustDecodeBounds = false;
                bitmap = BitmapFactory.decodeFile(imagePath, options);
                img.setImageBitmap(bitmap);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (bitmap != null)
            bitmap.recycle();

    }

}

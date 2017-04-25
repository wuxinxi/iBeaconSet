package com.wxx.ibeaconset.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;
import com.wxx.ibeaconset.MyApplication;
import com.wxx.ibeaconset.R;
import com.wxx.ibeaconset.bean.PageList;
import com.wxx.ibeaconset.inte.OnIteLongClick;
import com.wxx.ibeaconset.inte.OnItemclick;
import com.wxx.ibeaconset.viewholder.PageHolder;
import com.yanzhenjie.nohttp.Logger;

import java.util.List;

/**
 * 作者：Tangren_ on 2017/4/22 0022.
 * 邮箱：wu_tangren@163.com
 * TODO:用一句话概括
 */


public class PageAdapter extends RecyclerView.Adapter<PageHolder> {

    private List<PageList.DataBean.PagesBean> pagesBeanList;
    private LayoutInflater mInflater;
    private OnItemclick itemclick;
    private OnIteLongClick iteLongClick;

    @Override
    public PageHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mInflater = LayoutInflater.from(parent.getContext());
        return new PageHolder(mInflater.inflate(R.layout.item_page_list, parent, false), itemclick, iteLongClick, pagesBeanList);
    }

    @Override
    public void onBindViewHolder(PageHolder holder, int position) {
        PageList.DataBean.PagesBean pagesBean = pagesBeanList.get(position);
        if (pagesBean == null) return;
        holder.comment.setText("备注：" + pagesBean.getComment());
        holder.title.setText("主标题：" + pagesBean.getTitle());
        holder.description.setText("副标题：" + pagesBean.getDescription());
        holder.page_id.setText("页面ID：" + pagesBean.getPage_id());
        holder.page_url.setText("跳转链接：" + pagesBean.getPage_url());
        Picasso.with(MyApplication.getInstance().getApplicationContext())
                .load(pagesBean.getIcon_url())
                .placeholder(R.mipmap.timg)
                .error(R.drawable.ic_ico_load_fail)
                .into(holder.img);

    }

    @Override
    public int getItemCount() {
        Logger.d("pagesBeanList.size():" + pagesBeanList.size());
        return pagesBeanList == null ? 0 : pagesBeanList.size();
    }

    public void setItemclick(OnItemclick itemclick) {
        this.itemclick = itemclick;
    }

    public void setItemLongClick(OnIteLongClick iteLongClick) {
        this.iteLongClick = iteLongClick;
    }

    public void add(List<PageList.DataBean.PagesBean> pagesBeanList) {
        this.pagesBeanList = pagesBeanList;
        notifyDataSetChanged();
    }

    public void addMore(List<PageList.DataBean.PagesBean> pagesBeanList) {
        if (pagesBeanList == null) {
            add(pagesBeanList);
        } else {
            this.pagesBeanList.addAll(pagesBeanList);
            notifyDataSetChanged();
        }
    }
}

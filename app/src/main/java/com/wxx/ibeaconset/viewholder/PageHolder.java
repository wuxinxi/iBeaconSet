package com.wxx.ibeaconset.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.wxx.ibeaconset.R;
import com.wxx.ibeaconset.bean.PageList;
import com.wxx.ibeaconset.inte.OnIteLongClick;
import com.wxx.ibeaconset.inte.OnItemclick;

import java.util.List;

/**
 * 作者：Tangren_ on 2017/4/22 0022.
 * 邮箱：wu_tangren@163.com
 * TODO:用一句话概括
 */


public class PageHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

    private OnItemclick itemclick;
    private OnIteLongClick iteLongClick;

    public ImageView img;
    public TextView comment;
    public TextView title;
    public TextView description;
    public TextView page_id;
    public TextView page_url;

    private List<PageList.DataBean.PagesBean> pagesBeanList;

    public PageHolder(View itemView, OnItemclick itemclick, OnIteLongClick iteLongClick, List<PageList.DataBean.PagesBean> pagesBeanList) {
        super(itemView);
        img = (ImageView) itemView.findViewById(R.id.img);
        comment = (TextView) itemView.findViewById(R.id.comment);
        title = (TextView) itemView.findViewById(R.id.title);
        description = (TextView) itemView.findViewById(R.id.description);
        page_id = (TextView) itemView.findViewById(R.id.page_id);
        page_url = (TextView) itemView.findViewById(R.id.page_url);
        this.itemclick = itemclick;
        this.iteLongClick = iteLongClick;
        this.pagesBeanList = pagesBeanList;
        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (itemclick != null) {
            itemclick.click(v, getAdapterPosition(), pagesBeanList);
        }
    }

    @Override
    public boolean onLongClick(View v) {
        if (iteLongClick != null) {
            iteLongClick.longClick(v, getAdapterPosition(), pagesBeanList);
        }
        return true;
    }
}

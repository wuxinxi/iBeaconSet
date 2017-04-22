package com.wxx.ibeaconset.inte;

import android.view.View;

import java.util.List;

/**
 * 作者：Tangren_ on 2017/4/22 0022.
 * 邮箱：wu_tangren@163.com
 * TODO:用一句话概括
 */


public interface OnItemclick<T> {
    void click(View view, int postion, List<T> list);
}

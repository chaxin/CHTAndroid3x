package com.damenghai.chahuitong.view.special;

import com.damenghai.chahuitong.model.bean.Category;
import com.damenghai.chahuitong.model.bean.Goods;
import com.damenghai.chahuitong.model.bean.Recommend;
import com.damenghai.chahuitong.view.MvpView;

import java.util.List;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public interface ValuerListMvp extends MvpView {

    void showRecommendList(List<Recommend> list);

    void setCategory(List<Category> list);

    int getCurPage();

    String getCateSort();

    int getPriceSort();

    int getSalesSort();

    int getClickSort();

    void clear();

}

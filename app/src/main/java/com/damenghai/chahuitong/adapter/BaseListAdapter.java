package com.damenghai.chahuitong.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * 通用的ListView适配器，所有的ListView适配器都应该继承该类
 * @author LiaoPeiKun
 *
 * @param <T> 用于存放视图数据的Bean类
 */
public abstract class BaseListAdapter<T> extends BaseAdapter {
	/**
	 * 创建ViewHolder所需要的上下文
	 */
	protected Context mContext;

	/**
	 * 创建ViewHolder所需要的布局文件
	 */
	protected int mResId;

	/**
	 * 所有存放视图数据Bean类的容器
	 */
	protected List<T> mData;

	public BaseListAdapter(Context context, List<T> data, int resId) {
		this.mContext = context;
		this.mData = data;
		this.mResId = resId;
	}

	@Override
	public int getCount() {
		return mData.size();
	}

	@Override
	public T getItem(int position) {
		return mData.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = ViewHolder.get(convertView, mContext, mResId, parent, position);

		convert(holder, getItem(position));

		return holder.getConvertView();
	}

	/**
	 * 暴露给子类的接口，用于设置每个Item的具体数据，每个子类有各自的实现
	 * @param holder 复用视图控件的辅助类，通过它可以获得视图中的各个控件
	 * @param t 存放视图数据Bean类
	 */
	public abstract void convert(ViewHolder holder, T t);

	public void add(int position, T t) {
		if (t != null) mData.add(position, t);
		notifyDataSetChanged();
	}

    public void add(T t) {
        if (t != null) mData.add(t);
        notifyDataSetChanged();
    }

	public void remove(T t) {
		if(mData.contains(t)) {
			mData.remove(t);
			notifyDataSetChanged();
		}
	}

    public void clear() {
        if (mData != null) mData.clear();
    }

    public void addList(List<T> data) {
        if (data != null) {
            for (T t : data) {
                if (!mData.contains(t)) mData.add(t);
            }
            notifyDataSetChanged();
        }
    }

	public List<T> getList() {
		return mData;
	}

}

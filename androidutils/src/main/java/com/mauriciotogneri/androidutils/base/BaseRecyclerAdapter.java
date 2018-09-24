package com.mauriciotogneri.androidutils.base;

import android.content.Context;
import androidx.annotation.DrawableRes;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mauriciotogneri.androidutils.base.BaseRecyclerViewHolder.OnViewHolderClicked;

import java.util.List;

public abstract class BaseRecyclerAdapter<T, V extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<V> implements OnViewHolderClicked
{
    private final int resourceId;
    private final List<T> items;
    private final LayoutInflater inflater;
    private final OnItemSelected<T> onItemSelected;

    public BaseRecyclerAdapter(Context context, int resourceId, List<T> items, OnItemSelected<T> onItemSelected)
    {
        this.resourceId = resourceId;
        this.items = items;
        this.inflater = LayoutInflater.from(context);
        this.onItemSelected = onItemSelected;
    }

    @Override
    public V onCreateViewHolder(ViewGroup parent, int viewType)
    {
        return viewHolder(inflater.inflate(resourceId, parent, false));
    }

    @Override
    public void onBindViewHolder(V viewHolder, int position)
    {
        fillView(viewHolder, items.get(position));
    }

    @Override
    public int getItemCount()
    {
        return items.size();
    }

    protected abstract V viewHolder(View view);

    protected abstract void fillView(V viewHolder, T item);

    public T item(int position)
    {
        return items.get(position);
    }

    public void update()
    {
        notifyDataSetChanged();
    }

    public void update(List<T> list)
    {
        items.clear();
        items.addAll(list);

        update();
    }

    public void add(T element)
    {
        items.add(element);

        update();
    }

    @Override
    public void onViewHolderClick(int position)
    {
        onItemSelected.onItemClick(item(position), position);
    }

    @Override
    public void onViewHolderLongClick(int position)
    {
        onItemSelected.onItemLongClick(item(position), position);
    }

    public interface OnItemSelected<T>
    {
        void onItemClick(T item, int position);

        void onItemLongClick(T item, int position);
    }

    public static class CustomDividerItemDecoration extends DividerItemDecoration
    {
        public CustomDividerItemDecoration(Context context, int orientation, @DrawableRes int resId)
        {
            super(context, orientation);

            setDrawable(ContextCompat.getDrawable(context, resId));
        }
    }
}
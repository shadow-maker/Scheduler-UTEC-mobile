package com.grupoDBP.schedulerutec;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    private List<ListElement> data;
    private LayoutInflater inflater;
    private Context context;

    public ListAdapter(List<ListElement> item, Context context) {
        this.inflater = LayoutInflater.from(context);
        this.context = context;
        this.data = item;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_element, null);
        return new ListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ListAdapter.ViewHolder holder, final int position) {
        holder.bindData(data.get(position));
    }

    public void setItems(List<ListElement> items) {
        data = items;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, createdBy, date;

        ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            createdBy = itemView.findViewById(R.id.createdBy);
            date = itemView.findViewById(R.id.date);
        }

        void bindData(final ListElement item) {
            title.setText(item.getTitle());
            createdBy.setText(item.getCreatedBy());
            date.setText(item.getDate());
        }
    }
}

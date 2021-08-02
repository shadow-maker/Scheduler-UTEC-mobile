package com.grupoDBP.schedulerutec;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.*;

public class ScheduleListAdapter extends RecyclerView.Adapter<ScheduleListAdapter.ViewHolder> {
    private List<ScheduleElement> data;
    private LayoutInflater inflater;
    private Context context;

    public ScheduleListAdapter(List<ScheduleElement> list, Context context) {
        this.inflater = LayoutInflater.from(context);
        this.context = context;
        this.data = list;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public ScheduleListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.schedule_element, null);
        return new ScheduleListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ScheduleListAdapter.ViewHolder holder, final int position) {
        holder.bindData(data.get(position));
    }

    public void setItems(List<ScheduleElement> items) {
        data = items;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView id, title, createdBy;

        ViewHolder(View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.scheduleId);
            title = itemView.findViewById(R.id.title);
            createdBy = itemView.findViewById(R.id.createdBy);
        }

        void bindData(final ScheduleElement item) {
            id.setText(item.getId());
            title.setText(item.getTitle());
            createdBy.setText(item.getCreatedBy());
        }
    }
}

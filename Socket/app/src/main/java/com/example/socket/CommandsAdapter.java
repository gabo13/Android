package com.example.socket;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class CommandsAdapter extends BaseAdapter {

    private final Context mContext;
    private final List<Command> commands;

    public CommandsAdapter(Context context, List<Command> commands) {
        this.mContext = context;
        this.commands = commands;
    }
    @Override
    public int getCount() {
        return this.commands.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            convertView = layoutInflater.inflate(R.layout.grid_item, null);
        }
        final TextView btnTextView = (TextView)convertView.findViewById(R.id.btnName);

        btnTextView.setText(this.commands.get(position).getName());
        return convertView;
    }
}

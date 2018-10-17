package com.jit.library.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.jit.library.R;

import com.jit.library.dataModel.DataModel;

import java.util.ArrayList;


public class CustomListView extends BaseAdapter {

    private static LayoutInflater inflater=null;
    public ArrayList<DataModel> dataModel;
    Context mContext;
    public CustomListView(Context context, ArrayList<DataModel> dataModel) {
        this.dataModel = dataModel;
        mContext = context;
    }

    @Override
    public int getCount() {
        return dataModel.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public class ViewHolder
    {
        TextView txtName,txtBloodGroup;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        DataModel model = dataModel.get(i);
        ViewHolder holder=new ViewHolder();
        View rowView;
        inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        rowView = inflater.inflate(R.layout.listview, null);
        holder.txtName = rowView.findViewById(R.id.textView_name);
        holder.txtBloodGroup = rowView.findViewById(R.id.textView_blood_group);
        holder.txtName.setText(model.getbookName());
        holder.txtBloodGroup.setText(model.getbookBranch());
        return rowView;
    }
}

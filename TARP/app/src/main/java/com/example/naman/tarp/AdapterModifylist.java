package com.example.naman.tarp;

/**
 * Created by naman on 10-Feb-18.
 */
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class AdapterModifylist extends BaseAdapter {

    Activity activity;
    List<UserModel> users;
    LayoutInflater inflater;
    int type=0;

    public AdapterModifylist(Activity activity) {
        this.activity = activity;
    }

    public AdapterModifylist(Activity activity, List<UserModel> users) {
        this.activity   = activity;
        this.users      = users;

        inflater        = activity.getLayoutInflater();
    }


    @Override
    public int getCount() {
        return users.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder holder = null;

        if (view == null){

            view = inflater.inflate(R.layout.list_view_item, viewGroup, false);

            holder = new ViewHolder();

            holder.tvUserName = (TextView)view.findViewById(R.id.tv_user_name);
            holder.ivCheckBox = (ImageView) view.findViewById(R.id.iv_check_box);

            view.setTag(holder);
        }else
            holder = (ViewHolder)view.getTag();

        UserModel model = users.get(i);

        holder.tvUserName.setText(model.getUserName());

        if (model.isSelected() && type==0)
            holder.ivCheckBox.setBackgroundResource(R.drawable.checked);
        else if (!model.isSelected() && type==0)
            holder.ivCheckBox.setBackgroundResource(R.drawable.check);
        else if (model.isSelected() && type==1)
            holder.ivCheckBox.setBackgroundResource(R.drawable.radio_checked);
        else if (!model.isSelected() && type==1)
            holder.ivCheckBox.setBackgroundResource(R.drawable.radio_check);

        return view;

    }

    public void updateRecords(List<UserModel> users){
        this.users = users;

        notifyDataSetChanged();
    }

    class ViewHolder{

        TextView tvUserName;
        ImageView ivCheckBox;

    }
}

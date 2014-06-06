/**
 *
 * copyright(C)2014- 
 *
 */
package com.microjixl.demo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jixiaolong<microjixl@gmail.com>
 */
public class DataAdapter extends BaseAdapter{
    private List<String> mList;
    private LayoutInflater inflater;
    private int markPosition = 0;

    public DataAdapter(Context context) {
        inflater = LayoutInflater.from(context);
        mList = new ArrayList<String>();
    }

    public void setData(List<String> mList){
        if(mList == null){
            this.mList.clear();
        }else{
            this.mList = mList;
        }
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int i) {
        return mList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView == null){
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_menu, null);
            holder.mark = convertView.findViewById(R.id.v_line_vertical);
            holder.name = (TextView)convertView.findViewById(R.id.tv_name);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder)convertView.getTag();
        }

        String s = mList.get(position);

        holder.name.setText(s);
        if(position == markPosition){
            holder.name.setEnabled(true);
            holder.mark.setVisibility(View.VISIBLE);
        }else{
            holder.name.setEnabled(false);
            holder.mark.setVisibility(View.GONE);
        }

        return convertView;
    }

    class ViewHolder{
        View mark;
        TextView name;
    }

    public void checked(int markPosition){
        this.markPosition = markPosition;
        if(markPosition>=0 && markPosition < mList.size()){
            notifyDataSetChanged();
        }
    }
}

package com.example.nirbhay.blipper;

/**
 * Created by nirbhay on 11/18/16.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class CustomAdapterEmergency extends BaseAdapter{
    String [] result1;
    String [] result2;
    String [] result3;
    String [] result4;
    String [] result5;
    String [] result6;
    String [] result7;


    Context context;

    private static LayoutInflater inflater=null;
    public CustomAdapterEmergency(ViewEmergencies mActivity, String[] phoneNosList, String[] userNamesList, String[] uids, String[] uaddrs, String[] ufname, String[] ufno, String[] time_stamp) {
        // TODO Auto-generated constructor stub
        result1=phoneNosList;
        result2 = userNamesList;
        result3 = uids;
        result4 = uaddrs;
        result5 = ufname;
        result6 = ufno;
        result7 = time_stamp;
        context=mActivity;
        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return result1.length;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public class Holder
    {
        TextView tv1,tv2,tv3,tv4,tv5,tv6,tv7;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder=new Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.list_view_custom_layout_emergency, null);
        holder.tv1=(TextView) rowView.findViewById(R.id.textView1);
        holder.tv1.setText(result1[position]);
        holder.tv2=(TextView) rowView.findViewById(R.id.textView2);
        holder.tv2.setText(result2[position]);
        holder.tv3=(TextView) rowView.findViewById(R.id.textView4);
        holder.tv3.setText(result5[position]);
        holder.tv4=(TextView) rowView.findViewById(R.id.textView3);
        holder.tv4.setText(result3[position]);
        holder.tv5=(TextView) rowView.findViewById(R.id.textView6);
        holder.tv5.setText(result4[position]);
        holder.tv6=(TextView) rowView.findViewById(R.id.textView5);
        holder.tv6.setText(result6[position]);
        holder.tv7=(TextView) rowView.findViewById(R.id.textView7);
        holder.tv7.setText(result7[position]+" GMT");
        rowView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Toast.makeText(context, "You Clicked "+result1[position], Toast.LENGTH_LONG).show();
            }
        });
        return rowView;
    }

}
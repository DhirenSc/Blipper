package com.example.nirbhay.blipper;

/**
 * Created by nirbhay on 11/18/16.
 */

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
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
    String [] result8;
    String [] result9;


    Context context;

    private static LayoutInflater inflater=null;
    public CustomAdapterEmergency(ViewEmergencies mActivity, String[] phoneNosList, String[] userNamesList, String[] uids, String[] uaddrs, String[] ufname, String[] ufno, String[] time_stamp, String[] last_lat, String[] last_lng) {
        // TODO Auto-generated constructor stub
        result1=phoneNosList;
        result2 = userNamesList;
        result3 = uids;
        result4 = uaddrs;
        result5 = ufname;
        result6 = ufno;
        result7 = time_stamp;
        result8 = last_lat;
        result9 = last_lng;
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
        TextView tv1,tv2,tv3,tv4,tv5,tv6,tv7,tv8,tv9;
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
        holder.tv7.setText(result7[position]+"+5:30 IST");
        holder.tv8=(TextView) rowView.findViewById(R.id.textView8);
        holder.tv8.setText(result8[position]);
        holder.tv9=(TextView) rowView.findViewById(R.id.textView9);
        holder.tv9.setText(result9[position]);

        Button locationView = (Button) rowView.findViewById(R.id.viewLoc);
        locationView.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                if(result8.equals("LOC_NOT_AVAIL") || result9.equals("LOC_NOT_AVAIL") || result8.equals("") || result9.equals("")  ){
                    Toast.makeText(context,"Location Not Available for this emergency" ,Toast.LENGTH_SHORT).show();

                }
                else {
                    Intent i = new Intent(context, UserMapActivity.class);
                    i.putExtra("LocA", result8[position] + "@@@@" + result9[position]);
                    Log.e("LOC:", result8[position] + "@@@@" + result9[position]);
                    context.startActivity(i);
                }
            }
        });
        return rowView;
    }

}
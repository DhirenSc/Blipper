package com.example.nirbhay.blipper;

/**
 * Created by nirbhay on 11/18/16.
 */

import android.content.Context;
import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

public class CustomAdapter extends BaseAdapter{
    String [] result1;
    String [] result2;
    String [] result3;
    String [] result4;
    String [] result5;
    String [] result6;
    Holder holder;
    int p[];
    Context context;

    private static LayoutInflater inflater=null;
    public CustomAdapter(ViewNeighbours mActivity, String[] phoneNosList, String[] userNamesList, String[] uids, String[] uaddrs, String[] ufname, String[] ufno) {
        // TODO Auto-generated constructor stub
        result1=phoneNosList;
        result2 = userNamesList;
        result3 = uids;
        result4 = uaddrs;
        result5 = ufname;
        result6 = ufno;
        context=mActivity;
        p = new int[getCount()];
        for(int i=0;i<p.length;i++){
            p[i]=0;
        }
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
        TextView tv1,tv2,tv3,tv4,tv5,tv6;
        CardView front,back;
        FloatingActionButton fab;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        holder=new Holder();

        View rowView;
        rowView = inflater.inflate(R.layout.list_view_custom_layout, null);
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
        holder.front = (CardView) rowView.findViewById(R.id.viewDetails) ;
        holder.back = (CardView) rowView.findViewById(R.id.viewDetailsx) ;
        holder.fab = (FloatingActionButton) rowView.findViewById(R.id.remNeighbour);
        final String id_neigh = result3[position];
        final String id_user = ViewNeighbours.uidN;
        final CardView v = holder.back;
        final CardView x = holder.front;
        v.post(new Runnable() {
            @Override
            public void run() {
                int itemHeight = v.getHeight();
                Log.d("Height", "" + itemHeight);
                CardView.LayoutParams layoutParams = (CardView.LayoutParams)
                        x.getLayoutParams();
                layoutParams.height = itemHeight;
            }

        });

        YoYo.with(Techniques.SlideInRight)
                .duration(1200)
                .playOn(holder.fab);
        YoYo.with(Techniques.SlideInRight)
                .duration(1200)
                .playOn(holder.front);
        YoYo.with(Techniques.FadeOut)
                .duration(0)
                .playOn(holder.back);

        holder.fab.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                new RemoveNeighbour(context).execute(id_neigh,id_user);

            }
        });

        rowView.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.d(p[position]+"","<-POS");
                //Toast.makeText(context, "You Clicked "+p[position], Toast.LENGTH_LONG).show();

                if(p[position]==0) {
                    YoYo.with(Techniques.RotateOut)
                            .duration(400)
                            .playOn(v.findViewById(R.id.viewDetails));
                    YoYo.with(Techniques.RotateIn)
                            .duration(400)
                            .playOn(v.findViewById(R.id.viewDetailsx));
                    p[position]=1;
                    return;
                }
                if(p[position]==1){
                    YoYo.with(Techniques.RotateOut)
                            .duration(400)
                            .playOn(v.findViewById(R.id.viewDetailsx));
                    YoYo.with(Techniques.RotateIn)
                            .duration(400)
                            .playOn(v.findViewById(R.id.viewDetails));
                    p[position]=0;
                }
            }
        });


        /*
        holder.back.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                YoYo.with(Techniques.FadeOut)
                        .duration(1)
                        .playOn(v.findViewById(R.id.viewDetailsx));
                YoYo.with(Techniques.RotateInDownRight)
                        .duration(1200)
                        .playOn(v.findViewById(R.id.viewDetails));
            }
        });
        /*
        rowView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Toast.makeText(context, "You Clicked "+result1[position], Toast.LENGTH_LONG).show();
            }
        });
        */
        return rowView;
    }

}
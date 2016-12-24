package com.example.nirbhay.blipper;

/**
 * Created by nirbhay on 11/18/16.
 */

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import static android.R.attr.visible;

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
    int p_height[];
    int p[];


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
        p_height = new int[getCount()];
        for(int i=0;i<p_height.length;i++){
            p_height[i]=0;
        }
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
        TextView tv1,tv2,tv3,tv4,tv5,tv6,tv7,tv8,tv9;
        ImageView iv,iv2;
        CardView front,back_1,back_2;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder=new Holder();
        final View rowView;
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
        holder.iv= (ImageView) rowView.findViewById(R.id.MapImage);
        holder.iv2 = (ImageView) rowView.findViewById(R.id.DummyImage);
        holder.front = (CardView) rowView.findViewById(R.id.holderfront) ;
        holder.back_1 = (CardView) rowView.findViewById(R.id.holderback1) ;
        holder.back_2 = (CardView) rowView.findViewById(R.id.holderback2) ;

        if(result8[position].equals("LOC_NOT_AVAIL") || result9[position].equals("LOC_NOT_AVAIL") || result8[position].equals("") || result9[position].equals("") || result8[position].equals("0") || result9[position].equals("0") ){
            holder.iv2.setVisibility(rowView.VISIBLE);

        }
        else {

            /*Normal Map View Image*/
            Picasso.with(context)
                    .load("https://maps.googleapis.com/maps/api/staticmap?zoom=18&size=600x350&maptype=road&markers=size:large|color:red|label:E|"+result8[position]+","+result9[position]+"&key=%20AIzaSyDLtr3l3y7-XZ32zeLQqZu2sHs8uf-4Cf8&style=feature:all|element:geometry|color:0x242f3e&style=feature:all|element:labels.text.stroke|lightness:-80&style=feature:administrative|element:labels.text.fill|color:0x746855&style=feature:administrative.locality|element:labels.text.fill|color:0xd59563&style=feature:poi|element:labels.text.fill|color:0xd59563&style=feature:poi.park|element:geometry|color:0x263c3f&style=feature:poi.park|element:labels.text.fill|color:0x6b9a76&style=feature:road|element:geometry.fill|color:0x2b3544&style=feature:road|element:labels.text.fill|color:0x9ca5b3&style=feature:road.arterial|element:geometry.fill|color:0x38414e&style=feature:road.highway|element:geometry.fill|color:0x746855&style=feature:road.highway|element:geometry.stroke|color:0x1f2835&style=feature:road.highway|element:labels.text.fill|color:0xf3d19c&style=feature:road.local|element:geometry.fill|color:0x38414e&style=feature:road.local|element:geometry.stroke|color:0x212a37&style=feature:transit|element:geometry|color:0x2f3948&style=feature:transit.station|element:labels.text.fill|color:0xd59563&style=feature:water|element:geometry|color:0x17263c&style=feature:water|element:labels.text.fill|color:0x515c6d&style=feature:water|element:labels.text.stroke|lightness:-20")
                    .into(holder.iv);

            /*Street View Image
            Picasso.with(context)
                    .load("https://maps.googleapis.com/maps/api/streetview?size=600x350&location="+result8[position]+","+result9[position]+"&fov=90&heading=235&pitch=10&key=AIzaSyDriq-eqWotqGAMGymumk37Wzc9bFSpJ9Y")
                    .into(holder.iv);
            */



        }
        final CardView z = holder.back_1;
        final CardView v = holder.back_2;
        final CardView x = holder.front;

        YoYo.with(Techniques.FadeOut)
                .duration(0)
                .playOn(holder.back_1);

        z.post(new Runnable() {
            @Override
            public void run() {
                int itemHeight = z.getHeight();
                Log.d("Height", "" + itemHeight);
                CardView.LayoutParams layoutParams = (CardView.LayoutParams)
                        x.getLayoutParams();
                layoutParams.height = itemHeight;

                p_height[position]=layoutParams.height+200;

            }

        });


        YoYo.with(Techniques.SlideInRight)
                .duration(1600)
                .playOn(rowView.findViewById(R.id.holderfront));
        YoYo.with(Techniques.SlideInLeft)
                .duration(1600)
                .playOn(rowView.findViewById(R.id.holderback2));


        FloatingActionButton locationView = (FloatingActionButton) rowView.findViewById(R.id.viewLoc);
        locationView.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                if(result8[position].equals("LOC_NOT_AVAIL") || result9[position].equals("LOC_NOT_AVAIL") || result8[position].equals("") || result9[position].equals("") || result8[position].equals("0") || result9[position].equals("0")  ){
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

        rowView.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.d(p[position]+"","<-POS");
                //Toast.makeText(context, "You Clicked "+p[position], Toast.LENGTH_LONG).show();

                if(p[position]==0) {


                    YoYo.with(Techniques.SlideOutRight)
                            .duration(1000)
                            .playOn(v.findViewById(R.id.holderfront));
                    YoYo.with(Techniques.SlideInLeft)
                            .duration(1000)
                            .playOn(v.findViewById(R.id.holderback1));
                    p[position]=1;
                    return;
                }
                if(p[position]==1){
                    YoYo.with(Techniques.SlideInLeft)
                            .duration(1000)
                            .playOn(v.findViewById(R.id.holderfront));
                    YoYo.with(Techniques.SlideOutRight)
                            .duration(1000)
                            .playOn(v.findViewById(R.id.holderback1));
                    p[position]=0;
                }
            }
        });

        return rowView;
    }



}
package com.example.manojk.ors.Models;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.manojk.ors.R;

import java.util.ArrayList;

/**
 * Created by manojK on 16/11/2016.
 */
public class orsTripLayoutAdapter extends RecyclerView.Adapter<orsTripLayoutAdapter.MyViewHolder> {
    ArrayList<orsTripLayout> arrayList = new ArrayList<>();

    public orsTripLayoutAdapter(ArrayList<orsTripLayout> arrayList) {
        this.arrayList = arrayList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_trip_layout_row_item, parent, false);
        view.getBackground().setAlpha(220);
        MyViewHolder myViewHolder = new MyViewHolder(view);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        if (arrayList.get(position).isLayout_c1_Online()) {
            if (arrayList.get(position).isLayout_c1_Reserved()) {
                holder.tv_Layout_c1.setBackgroundResource(R.drawable.bs_bkd);
            }else {
                holder.tv_Layout_c1.setBackgroundResource(R.drawable.bs_avl);
            }
        }
        else {
            if (!(arrayList.get(position).getLayout_c1().equals("vc"))) {
                holder.tv_Layout_c1.setBackgroundResource(R.drawable.bs_cnt);
            }
        }

        if (arrayList.get(position).isLayout_c2_Online()) {
            if (arrayList.get(position).isLayout_c2_Reserved()) {
                holder.tv_Layout_c2.setBackgroundResource(R.drawable.bs_bkd);
            }else {
                holder.tv_Layout_c2.setBackgroundResource(R.drawable.bs_avl);
            }
        }
        else {
            if (!(arrayList.get(position).getLayout_c2().equals("vc"))) {
                holder.tv_Layout_c2.setBackgroundResource(R.drawable.bs_cnt);
            }
        }

        if (arrayList.get(position).isLayout_c3_Online()) {
            if (arrayList.get(position).isLayout_c3_Reserved()) {
                holder.tv_Layout_c3.setBackgroundResource(R.drawable.bs_bkd);
            }else {
                holder.tv_Layout_c3.setBackgroundResource(R.drawable.bs_avl);
            }
        }
        else {
            if (!(arrayList.get(position).getLayout_c3().equals("vc")) && !(arrayList.get(position).getLayout_c3().equals("mm"))) {
                holder.tv_Layout_c3.setBackgroundResource(R.drawable.bs_cnt);
            }
        }
        //layout c4
        if (arrayList.get(position).isLayout_c4_Online()) {
            if (arrayList.get(position).isLayout_c4_Reserved()) {
                holder.tv_Layout_c4.setBackgroundResource(R.drawable.bs_bkd);
            }else {
                holder.tv_Layout_c4.setBackgroundResource(R.drawable.bs_avl);
            }
        }
        else {
            if (!(arrayList.get(position).getLayout_c4().equals("vc"))) {
                holder.tv_Layout_c4.setBackgroundResource(R.drawable.bs_cnt);
            }
        }
        //Layout_c5
        if (arrayList.get(position).isLayout_c5_Online()) {
            if (arrayList.get(position).isLayout_c5_Reserved()) {
                holder.tv_Layout_c5.setBackgroundResource(R.drawable.bs_bkd);
            }else {
                holder.tv_Layout_c5.setBackgroundResource(R.drawable.bs_avl);
            }
        }
        else {
            if (!(arrayList.get(position).getLayout_c5().equals("vc"))) {
                holder.tv_Layout_c5.setBackgroundResource(R.drawable.bs_cnt);
            }
        }
        //layout_c6
        if (arrayList.get(position).isLayout_c6_Online()) {
            if (arrayList.get(position).isLayout_c6_Reserved()) {
                holder.tv_Layout_c6.setBackgroundResource(R.drawable.bs_bkd);
            }else {
                holder.tv_Layout_c6.setBackgroundResource(R.drawable.bs_avl);
            }
        }
        else {
            if (!(arrayList.get(position).getLayout_c6().equals("vc"))) {
                holder.tv_Layout_c6.setBackgroundResource(R.drawable.bs_cnt);
            }else
            {
                holder.tv_Layout_c6.setWidth(0);
            }
        }
        //arrayList.get(position).getLayout_c1()=="vc" ||arrayList.get(position).getLayout_c1()=="cnd" ||arrayList.get(position).getLayout_c1()=="mm"
       // Log.d("myApp", "getLayout_c1-response  " + arrayList.get(position).getLayout_c1());
        if (arrayList.get(position).getLayout_c1().equals("vc"))
        {
            holder.tv_Layout_c1.setText("");
        }else
        {
            holder.tv_Layout_c1.setText(arrayList.get(position).getLayout_c1());
        }
        //Layout_c2
        if (arrayList.get(position).getLayout_c2().equals("vc"))
        {
            holder.tv_Layout_c2.setText("");
        }else
        {
            holder.tv_Layout_c2.setText(arrayList.get(position).getLayout_c2());
        }
        //layout_c3
        if (arrayList.get(position).getLayout_c3().equals("mm"))
        {
            holder.tv_Layout_c3.setText("");
        }else
        {
            holder.tv_Layout_c3.setText(arrayList.get(position).getLayout_c3());
        }
        //layout_c4
        if (arrayList.get(position).getLayout_c4().equals("vc"))
        {
            holder.tv_Layout_c4.setText("");
        }else
        {
            holder.tv_Layout_c4.setText(arrayList.get(position).getLayout_c4());
        }
        //layout_c5
        if (arrayList.get(position).getLayout_c5().equals("vc"))
        {
            holder.tv_Layout_c5.setText("");
        }else
        {
            holder.tv_Layout_c5.setText(arrayList.get(position).getLayout_c5());
        }
        //layout_c6
        if (arrayList.get(position).getLayout_c6().equals("vc"))
        {
            holder.tv_Layout_c6.setText("");
        }else
        {
            holder.tv_Layout_c6.setText(arrayList.get(position).getLayout_c6());
        }

        //holder.tv_Layout_c1.setText(arrayList.get(position).getLayout_c1());
        //holder.tv_Layout_c2.setText(arrayList.get(position).getLayout_c2());
        //holder.tv_Layout_c3.setText(arrayList.get(position).getLayout_c3());
        //holder.tv_Layout_c4.setText(arrayList.get(position).getLayout_c4());
        //holder.tv_Layout_c5.setText(arrayList.get(position).getLayout_c5());
        //holder.tv_Layout_c6.setText(arrayList.get(position).getLayout_c6());

        //holder.tv_Layout_c1.setOnTouchListener();

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_Layout_c1, tv_Layout_c2, tv_Layout_c3, tv_Layout_c4, tv_Layout_c5, tv_Layout_c6;
        //ImageView iv_Layout_c1, iv_Layout_c2, iv_Layout_c4, iv_Layout_c5, iv_Layout_c6;

        public MyViewHolder(View itemView) {
            super(itemView);
            //iv_Layout_c1 = (ImageView) itemView.findViewById(R.id.iv_layout_c1);
            //iv_Layout_c2 = (ImageView) itemView.findViewById(R.id.iv_layout_c2);

            tv_Layout_c1 = (TextView) itemView.findViewById(R.id.tv_layout_c1);
            tv_Layout_c2 = (TextView) itemView.findViewById(R.id.layout_c2);
            tv_Layout_c3 = (TextView) itemView.findViewById(R.id.layout_c3);
            tv_Layout_c4 = (TextView) itemView.findViewById(R.id.layout_c4);
            tv_Layout_c5 = (TextView) itemView.findViewById(R.id.layout_c5);
            tv_Layout_c6 = (TextView) itemView.findViewById(R.id.layout_c6);
        }
    }
}
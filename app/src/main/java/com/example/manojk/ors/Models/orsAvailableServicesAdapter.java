package com.example.manojk.ors.Models;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.manojk.ors.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by manojK on 07/11/2016.
 */
public class orsAvailableServicesAdapter  extends RecyclerView.Adapter<orsAvailableServicesAdapter.MyViewHolder>{
    ArrayList<orsAvailableServices> arrayList=new ArrayList<>();
    public orsAvailableServicesAdapter(ArrayList<orsAvailableServices> arrayList)
    {
        this.arrayList =arrayList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_available_services_row_item,parent,false);
        view.getBackground().setAlpha(160);
        //view.setAlpha(.5f);
        MyViewHolder myViewHolder = new MyViewHolder(view);

        return myViewHolder;
    }
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.TripRoute.setText(arrayList.get(position).getBusType() +": "+arrayList.get(position).getTripRoute());
        SimpleDateFormat output = new SimpleDateFormat("dd-MMM-yyyy HH:mm");
        holder.TripID.setText(arrayList.get(position).getTripID()+"");
        holder.Jtime1.setText(output.format(arrayList.get(position).getjTime1()));
        holder.rFare.setText(Html.fromHtml("Seats: "+arrayList.get(position).getAvailableSeats()+"<br/>Rs."+arrayList.get(position).getrFare()+"."));

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView TripID, TripRoute, Jtime1, rFare;
        public MyViewHolder(View itemView) {
            super(itemView);
            TripID = (TextView)itemView.findViewById(R.id.tripID);
            Jtime1 = (TextView)itemView.findViewById(R.id.jTime1);
            TripRoute = (TextView)itemView.findViewById(R.id.tripRoute);
            rFare = (TextView)itemView.findViewById(R.id.rFare);
        }
    }
}
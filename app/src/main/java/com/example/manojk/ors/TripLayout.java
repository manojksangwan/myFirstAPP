package com.example.manojk.ors;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.manojk.ors.Models.*;
import com.example.manojk.ors.R;

import java.util.ArrayList;

public class TripLayout extends AppCompatActivity implements orsTripLayout_iResult, orsTripLayoutAdapter.ItemClickCallback {
    RecyclerView recyclerView;
    //RecyclerView.Adapter adapter;
    orsTripLayoutAdapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<orsTripLayout> arList = new ArrayList<>();
    int seats_selected = 0;
    ArrayList<String> selected_seatNo = new ArrayList<>(4);
    orsAvailableServices orsAS;

    int basic_fare_amount = 0;
    int reservarion_charges = 0;
    int total_fare = 0;

    int pSeat1 = 0, pSeat2=0, pSeat3=0, pSeat4=0, rAmount=0, fareAmount=0;
    Button bt_book_eTicket;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_layout);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
/*
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
*/
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);


        Intent i = getIntent();
        orsAS = i.getExtras().getParcelable("orsAvailableServices");
        orsTripLayoutSearch orsTLS = new orsTripLayoutSearch(orsAS.getTripID() + "", orsAS.getBusType());

        orsTripLayoutTask orsTLT = new orsTripLayoutTask(TripLayout.this);
        orsTLT.getTripLayout(orsTLS);

        RelativeLayout rl_footer = (RelativeLayout) findViewById(R.id.rl_footer);
        rl_footer.setVisibility(View.INVISIBLE);

        bt_book_eTicket = (Button) findViewById(R.id.bt_book_eticket);
        bt_book_eTicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                orsAS.setPrfSeats(selected_seatNo.toString());
                orsAS.settSeats(seats_selected);
                orsAS.setpSeat1(pSeat1);
                orsAS.setpSeat2(pSeat2);
                orsAS.setpSeat3(pSeat3);
                orsAS.setpSeat4(pSeat4);
                orsAS.setTotalFare(fareAmount);
                orsAS.setrCharges(rAmount);

                Intent intent = new Intent(TripLayout.this, PassengerInfo.class);
                intent.putExtra("orsAvailableServices", orsAS);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.trip_layout_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /*
    @Override
             public boolean onCreateOptionsMenu(Menu menu) {

             getMenuInflater().inflate(R.menu.main, menu);
             menu.getItem(0).getSubMenu().getItem(3).setVisible(false);
             menu.getItem(0).getSubMenu().getItem(4).setVisible(true);
             return super.onCreateOptionsMenu(menu);

             }
    */
    @Override
    public void notifyError(VolleyError error) {
        //Log.d("myApp", "orsTripLayout Adapter  -response  " + error.printStackTrace());
        error.printStackTrace();
    }

    @Override
    public void notifySuccess(ArrayList<orsTripLayout> orsTL) {
        //Log.d("myApp", "ors_availableServices TASK -response  " + orsAS);
        adapter = new orsTripLayoutAdapter(orsTL);
        recyclerView.setAdapter(adapter);
        adapter.setItemClickCallback(this);
        arList = orsTL;

        //String myTrip_layout_title = R.string.my_trip_layout_title.toString();
        //+ " - " + arList.get(0).getTripID()

        // custom toolbar settings
        Toolbar my_toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(my_toolbar);
        getSupportActionBar().setTitle("Trip Layout - " + arList.get(0).getTripID());
        getSupportActionBar().setSubtitle(R.string.my_subtitle);
        getSupportActionBar().setIcon(R.mipmap.ic_toolbar);
    }

    @Override
    public void onLayout_c1_Click(View view, int p) {
        if (seats_selected >= 4 && !arList.get(p).isLayout_c1_Selected()) {
            Toast.makeText(TripLayout.this, "Maximum FOUR seats can be selected.", Toast.LENGTH_SHORT).show();
            return;
        }
        //Log.d("myAPP", "Name Click at " +p + "  " + view.getId());
        if (arList.get(p).isLayout_c1_Online() && !arList.get(p).isLayout_c1_Reserved()) {
            if (arList.get(p).isLayout_c1_Selected()) {
                view.setBackgroundResource(R.drawable.bs_avl);
                arList.get(p).setLayout_c1_Selected(false);
                seats_selected--;
                selected_seatNo.remove(arList.get(p).getLayout_c1());
            } else {
                view.setBackgroundResource(R.drawable.bs_sel);
                arList.get(p).setLayout_c1_Selected(true);
                seats_selected++;
                selected_seatNo.add(arList.get(p).getLayout_c1());
            }
        }
        this.display_footer(seats_selected);
    }

    @Override
    public void onLayout_c2_Click(View view, int p) {
        if (seats_selected >= 4 && !arList.get(p).isLayout_c2_Selected()) {
            Toast.makeText(TripLayout.this, "Maximum FOUR seats can be selected.", Toast.LENGTH_SHORT).show();
            return;
        }
        if (arList.get(p).isLayout_c2_Online() && !arList.get(p).isLayout_c2_Reserved()) {
            if (arList.get(p).isLayout_c2_Selected()) {
                view.setBackgroundResource(R.drawable.bs_avl);
                arList.get(p).setLayout_c2_Selected(false);
                seats_selected--;
                selected_seatNo.remove(arList.get(p).getLayout_c2());
            } else {
                view.setBackgroundResource(R.drawable.bs_sel);
                arList.get(p).setLayout_c2_Selected(true);
                seats_selected++;
                selected_seatNo.add(arList.get(p).getLayout_c2());
            }
        }
        this.display_footer(seats_selected);
    }

    @Override
    public void onLayout_c3_Click(View view, int p) {
        if (seats_selected >= 4 && !arList.get(p).isLayout_c3_Selected()) {
            Toast.makeText(TripLayout.this, "Maximum FOUR seats can be selected.", Toast.LENGTH_SHORT).show();
            return;
        }
        if (arList.get(p).isLayout_c3_Online() && !arList.get(p).isLayout_c3_Reserved()) {
            if (arList.get(p).isLayout_c3_Selected()) {
                view.setBackgroundResource(R.drawable.bs_avl);
                arList.get(p).setLayout_c3_Selected(false);
                seats_selected--;
                selected_seatNo.remove(arList.get(p).getLayout_c3());
            } else {
                view.setBackgroundResource(R.drawable.bs_sel);
                arList.get(p).setLayout_c3_Selected(true);
                seats_selected++;
                selected_seatNo.add(arList.get(p).getLayout_c3());
            }
        }
        this.display_footer(seats_selected);
    }

    @Override
    public void onLayout_c4_Click(View view, int p) {
        if (seats_selected >= 4 && !arList.get(p).isLayout_c4_Selected()) {
            Toast.makeText(TripLayout.this, "Maximum FOUR seats can be selected.", Toast.LENGTH_SHORT).show();
            return;
        }
        if (arList.get(p).isLayout_c4_Online() && !arList.get(p).isLayout_c4_Reserved()) {
            if (arList.get(p).isLayout_c4_Selected()) {
                view.setBackgroundResource(R.drawable.bs_avl);
                arList.get(p).setLayout_c4_Selected(false);
                seats_selected--;
                selected_seatNo.remove(arList.get(p).getLayout_c4());
            } else {
                view.setBackgroundResource(R.drawable.bs_sel);
                arList.get(p).setLayout_c4_Selected(true);
                seats_selected++;
                selected_seatNo.add(arList.get(p).getLayout_c4());
            }
        }
        this.display_footer(seats_selected);
    }

    @Override
    public void onLayout_c5_Click(View view, int p) {
        if (seats_selected >= 4 && !arList.get(p).isLayout_c5_Selected()) {
            Toast.makeText(TripLayout.this, "Maximum FOUR seats can be selected.", Toast.LENGTH_SHORT).show();
            return;
        }
        if (arList.get(p).isLayout_c5_Online() && !arList.get(p).isLayout_c5_Reserved()) {
            if (arList.get(p).isLayout_c5_Selected()) {
                view.setBackgroundResource(R.drawable.bs_avl);
                arList.get(p).setLayout_c5_Selected(false);
                seats_selected--;
                selected_seatNo.remove(arList.get(p).getLayout_c5());
            } else {
                view.setBackgroundResource(R.drawable.bs_sel);
                arList.get(p).setLayout_c5_Selected(true);
                seats_selected++;
                selected_seatNo.add(arList.get(p).getLayout_c5());
            }
        }
        this.display_footer(seats_selected);
    }

    @Override
    public void onLayout_c6_Click(View view, int p) {
        if (seats_selected >= 4 && !arList.get(p).isLayout_c6_Selected()) {
            Toast.makeText(TripLayout.this, "Maximum FOUR seats can be selected.", Toast.LENGTH_SHORT).show();
            return;
        }
        if (arList.get(p).isLayout_c6_Online() && !arList.get(p).isLayout_c6_Reserved()) {
            if (arList.get(p).isLayout_c6_Selected()) {
                view.setBackgroundResource(R.drawable.bs_avl);
                arList.get(p).setLayout_c6_Selected(false);
                seats_selected--;
                selected_seatNo.remove(arList.get(p).getLayout_c6());
            } else {
                view.setBackgroundResource(R.drawable.bs_sel);
                arList.get(p).setLayout_c6_Selected(true);
                seats_selected++;
                selected_seatNo.add(arList.get(p).getLayout_c6());
            }
        }
        this.display_footer(seats_selected);
    }

    private void display_footer(int seats_Selected) {
        if (seats_selected > 0) {

            pSeat1 = Integer.parseInt(selected_seatNo.get(0));
            if (seats_Selected>=2){pSeat2 = Integer.parseInt(selected_seatNo.get(1));}
            if (seats_Selected>=3){pSeat3 = Integer.parseInt(selected_seatNo.get(2));}
            if (seats_Selected>=4){pSeat4 = Integer.parseInt(selected_seatNo.get(3));}

            RelativeLayout rl_footer = (RelativeLayout) findViewById(R.id.rl_footer);
            rl_footer.setVisibility(View.VISIBLE);
            TextView tv_seats_Selected = (TextView) findViewById(R.id.seats_selected);
            String selected_seatNos = selected_seatNo.toString();
            tv_seats_Selected.setText("Total Seats: " + seats_selected + " No. " + selected_seatNos);

            TextView tv_fareAmount = (TextView) findViewById(R.id.fareAmount);
            fareAmount = orsAS.getrFare() * seats_Selected;
            rAmount = orsAS.getReservationCharges() * seats_Selected;
            int tAmount = fareAmount + rAmount;
            int rFare = orsAS.getrFare();
            int rAmt = orsAS.getReservationCharges();
            String fAmount =""+
                    "Fare Amount &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Rs. <font size='4'>" + fareAmount + "</font><br/>"+
                    "Reservation charges &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Rs. <font size='4'>" + rAmount + "</font><br/>"+
                    "Total Amount to be paid Rs. <font size='4'>" + tAmount + "</font><br/>";
            fAmount += "<font size='1' color='red'>(Basic Fare Rs." + rFare + " & Reservation charges Rs." + rAmt + " per seat)</font>";
            tv_fareAmount.setText(Html.fromHtml(fAmount));
        } else {
            RelativeLayout rl_footer = (RelativeLayout) findViewById(R.id.rl_footer);
            rl_footer.setVisibility(View.INVISIBLE);
        }
    }
}
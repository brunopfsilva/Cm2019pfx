package com.example.cm2019pf.controller;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cm2019pf.R;
import com.example.cm2019pf.helpers.Calcular_proximidade;
import com.example.cm2019pf.model.Hospital;
import com.example.cm2019pf.view.hospitalDetalheActivity;
import com.google.android.gms.location.FusedLocationProviderClient;

import java.util.List;

public class HospitalAdapter extends RecyclerView.Adapter<HospitalAdapter.ViewHolder> {



     Context context;
     List<Hospital> hospitalList;
     Double km;



    public HospitalAdapter(Context context, List<Hospital> hospitalList) {
        this.context = context;
        this.hospitalList = hospitalList;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        View itemview = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardhospital,viewGroup,false);


        return new ViewHolder(itemview);



    }



    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        viewHolder.textViewnome.setText("Nome: "+ hospitalList.get(position).getName());
        viewHolder.textViewdescricao.setText("Descricao: "+ hospitalList.get(position).getDescription());
        viewHolder.textViewdistro.setText("Distro: "+ hospitalList.get(position).getDistrict());



        if(km !=null) {
            km = new Calcular_proximidade().Calcular_proximidade(Double.valueOf(Double.valueOf(hospitalList.get(position).getLatitude())),
                    Double.valueOf(Double.valueOf(hospitalList.get(position).getLongitude())));
            viewHolder.txtproximidade.setText(String.valueOf(km));
        }
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, hospitalDetalheActivity.class);

                intent.putExtra("hopitalnome",hospitalList.get(position).getName());
                intent.putExtra("hopitaldescricao",hospitalList.get(position).getDescription());
                intent.putExtra("hopitaltelefone",hospitalList.get(position).getPhone().toString());
                intent.putExtra("hopitalemail",hospitalList.get(position).getEmail());
                intent.putExtra("hopitalsite",hospitalList.get(position).getInstitutionURL());
                intent.putExtra("hospitalId",hospitalList.get(position).getId().toString());


              //  getdataApiController.get_data_from_server_urgency(hospitalList.get(position).getId().toString());

                context.startActivity(intent);

            }
        });


    }

    @Override
    public int getItemCount() {

        return hospitalList.size();

    }


    public static class ViewHolder extends RecyclerView.ViewHolder {


        public TextView textViewnome;
        public TextView textViewdescricao;
        public TextView textViewdistro;
        public TextView txtproximidade;




        public ViewHolder(View itemView) {
            super(itemView);


            textViewnome = (TextView)itemView.findViewById(R.id.tztnomeHosp);
            textViewdescricao = (TextView)itemView.findViewById(R.id.txtdescription);
            textViewdistro = (TextView)itemView.findViewById(R.id.txtDistrict);
            txtproximidade = (TextView)itemView.findViewById(R.id.txtproximidade);

    }

    }
}

package com.example.brewery;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.brewery.Models.Beer;
import com.example.brewery.Models.BeerType;
import com.example.brewery.Service.APIInterface;
import com.example.brewery.Service.RequestBuilder;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChequeAdapter extends RecyclerView.Adapter<ChequeAdapter.WaitViewHolder>{

    private ArrayList<Beer> beers;
    private Context context;
    APIInterface api;

    public ChequeAdapter(Context applicationContext, ArrayList<Beer> listbeer) {
        this.context = applicationContext;
        this.beers = listbeer;
    }

    @NonNull
    @Override
    public ChequeAdapter.WaitViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view =inflater.inflate(R.layout.card_beer, parent, false);
        return new ChequeAdapter.WaitViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChequeAdapter.WaitViewHolder holder, int position) {
        holder.bind(beers.get(position));
    }

    @Override
    public int getItemCount() {
        return (beers.size());
    }

    public class WaitViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView nameBeer;
        private TextView typeBeer;

        public WaitViewHolder(@NonNull View itemView) {
            super(itemView);
            nameBeer = itemView.findViewById(R.id.card_beer_name);
            typeBeer = itemView.findViewById(R.id.card_beer_type);
            itemView.setOnClickListener(this);
        }

        public void bind(Beer waitBeers){
            api = RequestBuilder.buildRequest().create(APIInterface.class);

            Call<BeerType> getBeerTypeById = api.getBeerTypeById(waitBeers.getBeerTypeId());

            getBeerTypeById.enqueue(new Callback<BeerType>() {
                @Override
                public void onResponse(Call<BeerType> call, Response<BeerType> response) {
                    BeerType beerType = response.body();

                    nameBeer.setText(waitBeers.getNameBeer());
                    typeBeer.setText(String.valueOf(beerType.getNameBeerType()));
                }

                @Override
                public void onFailure(Call<BeerType> call, Throwable t) {
                    Log.e("ашыпка:", t.toString());
                }
            });
        }

        @Override
        public void onClick(View view) {

        }
    }
}

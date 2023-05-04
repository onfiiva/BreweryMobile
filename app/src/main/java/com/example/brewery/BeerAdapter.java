package com.example.brewery;

import static com.example.brewery.MainActivity.phone;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.brewery.Models.Beer;
import com.example.brewery.Models.BeerCheque;
import com.example.brewery.Models.BeerType;
import com.example.brewery.Models.Cheque;
import com.example.brewery.Models.User;
import com.example.brewery.Service.APIInterface;
import com.example.brewery.Service.RequestBuilder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BeerAdapter extends RecyclerView.Adapter<BeerAdapter.WaitViewHolder> {
    private ArrayList<Beer> beers;
    private Context context;
    private APIInterface api;
    private int idBeer;
    public static int idUser, sum, idCheque;
    public static String timeOrder;


    public static int Order;

    public BeerAdapter(Context applicationContext, ArrayList<Beer> listbeer) {
        this.context = applicationContext;
        this.beers = listbeer;
    }


    @NonNull
    @Override
    public BeerAdapter.WaitViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view =inflater.inflate(R.layout.card_beer, parent, false);
        return new WaitViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BeerAdapter.WaitViewHolder holder, int position) {
        holder.bind(beers.get(position));
    }

    @Override
    public int getItemCount() {
        return beers.size();
    }

    public class WaitViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

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
            Order(((TextView)view.findViewById(R.id.card_beer_name)).getText().toString());
        }
        private void Order(String name){

            Beer beer = beers.stream().filter(r -> r.getNameBeer().equals(name)).findFirst().orElse(null);
            idBeer = beer.getIdBeer();


            api = RequestBuilder.buildRequest().create(APIInterface.class);
            Call<User> getUserByPhone = api.getUserByPhone(phone);

            getUserByPhone.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    User user = response.body();
                    idUser = user.getIdUser();

                    Order++;
                    if (Order == 1){
                        sum = 0;
                        LocalDateTime now = LocalDateTime.now();
                        timeOrder = now.toString();
                        Cheque cheque = new Cheque(idUser, sum, timeOrder, false);

                        Call<ResponseBody> postCheque = api.postCheque(cheque);

                        postCheque.enqueue(new Callback<ResponseBody>() {
                            @Override
                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                Toast.makeText(context, "Успешное создание чека", Toast.LENGTH_SHORT).show();

                                Call<ArrayList<Cheque>> getChequesByUser = api.getChequesByUser(idUser);

                                getChequesByUser.enqueue(new Callback<ArrayList<Cheque>>() {
                                    @Override
                                    public void onResponse(Call<ArrayList<Cheque>> call, Response<ArrayList<Cheque>> response) {
                                        ArrayList<Cheque> cheques = response.body();
                                        idCheque = cheques.get(cheques.size()-1).getIdCheque();

                                        BeerCheque beerCheque = new BeerCheque(idCheque, idBeer, false);

                                        Call<ResponseBody> postBeerCheque = api.postBeerCheque(beerCheque);

                                        postBeerCheque.enqueue(new Callback<ResponseBody>() {
                                            @Override
                                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                                                sum += 100;
                                                Cheque chequeToPut = new Cheque(idUser, sum, timeOrder, false);

                                                Call<ResponseBody> putCheque = api.putCheque(chequeToPut);

                                                putCheque.enqueue(new Callback<ResponseBody>() {
                                                    @Override
                                                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                                                        Toast.makeText(context, "Успешное добавление пива в чек.", Toast.LENGTH_SHORT).show();
                                                    }

                                                    @Override
                                                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                                                        Log.e("ашыпка:", t.toString());
                                                    }
                                                });
                                            }

                                            @Override
                                            public void onFailure(Call<ResponseBody> call, Throwable t) {
                                                Log.e("ашыпка:", t.toString());
                                            }
                                        });
                                    }

                                    @Override
                                    public void onFailure(Call<ArrayList<Cheque>> call, Throwable t) {
                                        Log.e("ашыпка:", t.toString());
                                    }
                                });


                            }

                            @Override
                            public void onFailure(Call<ResponseBody> call, Throwable t) {
                                Log.e("ашыпка:", t.toString());
                            }
                        });

                    } else {
                        Call<ArrayList<Cheque>> getChequesByUser = api.getChequesByUser(idUser);

                        getChequesByUser.enqueue(new Callback<ArrayList<Cheque>>() {
                            @Override
                            public void onResponse(Call<ArrayList<Cheque>> call, Response<ArrayList<Cheque>> response) {
                                ArrayList<Cheque> cheques = response.body();
                                idCheque = cheques.get(cheques.size()-1).getIdCheque();

                                BeerCheque beerCheque = new BeerCheque(idCheque, idBeer, false);

                                Call<ResponseBody> postBeerCheque = api.postBeerCheque(beerCheque);

                                postBeerCheque.enqueue(new Callback<ResponseBody>() {
                                    @Override
                                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                                        sum += 100;
                                        Cheque chequeToPut = new Cheque(idUser, sum, timeOrder, false);

                                        Call<ResponseBody> putCheque = api.putCheque(chequeToPut);

                                        putCheque.enqueue(new Callback<ResponseBody>() {
                                            @Override
                                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                                                Toast.makeText(context, "Успешное добавление пива в чек.", Toast.LENGTH_SHORT).show();
                                            }

                                            @Override
                                            public void onFailure(Call<ResponseBody> call, Throwable t) {
                                                Log.e("ашыпка:", t.toString());
                                            }
                                        });
                                    }

                                    @Override
                                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                                        Log.e("ашыпка:", t.toString());
                                    }
                                });
                            }

                            @Override
                            public void onFailure(Call<ArrayList<Cheque>> call, Throwable t) {
                                Log.e("ашыпка:", t.toString());
                            }
                        });
                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    Log.e("ашыпка:", t.toString());
                }
            });



        }
    }
}

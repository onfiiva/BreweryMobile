package com.example.brewery;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.brewery.Models.Beer;
import com.example.brewery.Models.BeerType;
import com.example.brewery.Service.APIInterface;
import com.example.brewery.Service.RequestBuilder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderFragment extends Fragment {

    TextView beerLoadingText;
    EditText pahinationFromSearch, pahinationToSearch, beerSearch;
    Button buttonSearch;
    RecyclerView recyclerView;
    APIInterface api;
    boolean asc;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_order, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        beerLoadingText = view.findViewById(R.id.beer_loading_text);
        pahinationFromSearch = view.findViewById(R.id.pahination_from_search);
        pahinationToSearch = view.findViewById(R.id.pahination_to_search);
        beerSearch = view.findViewById(R.id.beer_search);
        buttonSearch = view.findViewById(R.id.button_search);
        recyclerView = view.findViewById(R.id.recyclerViewBeer);

        beerLoadingText.setVisibility(View.VISIBLE);

        api = RequestBuilder.buildRequest().create(APIInterface.class);
        Call<ArrayList<Beer>> getBeersList = api.getBeersList();

        getBeersList.enqueue(new Callback<ArrayList<Beer>>() {
            @Override
            public void onResponse(Call<ArrayList<Beer>> call, Response<ArrayList<Beer>> response) {

                beerLoadingText.setVisibility(View.INVISIBLE);

                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                recyclerView.setHasFixedSize(true);

                BeerAdapter adapter = new BeerAdapter(getContext(), response.body());
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<ArrayList<Beer>> call, Throwable t) {
                Log.e("ашыпка:", t.toString());
            }
        });

        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(pahinationFromSearch.getText()) && !TextUtils.isEmpty(pahinationToSearch.getText())){
                    int from = Integer.parseInt(pahinationFromSearch.getText().toString());
                    int to = Integer.parseInt(pahinationToSearch.getText().toString());
                    String search = "";
                    if (!TextUtils.isEmpty(beerSearch.getText())){
                        search = beerSearch.getText().toString();
                    }

                    if (asc == true){
                        asc = false;
                    } else {
                        asc = true;
                    }

                    Call<ArrayList<Beer>> getBeersPagedList = api.getBeersPagedList(from, to);

                    String finalSearch = search;
                    getBeersPagedList.enqueue(new Callback<ArrayList<Beer>>() {
                        @Override
                        public void onResponse(Call<ArrayList<Beer>> call, Response<ArrayList<Beer>> response) {

                            ArrayList<Beer> beers = response.body();

                            if (!finalSearch.isEmpty()){


                                // Создаем новый список, содержащий только те элементы из beers, у которых поле NameBeer содержит строку finalSearch
                                ArrayList<Beer> beersUpd = new ArrayList<>();
                                for (Beer beer : beers) {
                                    if (beer.NameBeer.toLowerCase().contains(finalSearch.toLowerCase())) {
                                        beersUpd.add(beer);
                                    }
                                }
                                if (asc) {
                                    // Сортируем список по полю NameBeer в алфавитном порядке
                                    Collections.sort(beersUpd, new Comparator<Beer>() {
                                        @Override
                                        public int compare(Beer beer1, Beer beer2) {
                                            // Сравниваем имена пива в алфавитном порядке без учета регистра
                                            return beer1.NameBeer.toLowerCase().compareTo(beer2.NameBeer.toLowerCase());
                                        }
                                    });
                                } else {
                                    // Сортируем список по полю NameBeer в порядке убывания (от конца алфавита)
                                    Collections.sort(beersUpd, new Comparator<Beer>() {
                                        @Override
                                        public int compare(Beer beer1, Beer beer2) {
                                            // Сравниваем имена пива в порядке убывания (от конца алфавита) без учета регистра
                                            return beer2.NameBeer.toLowerCase().compareTo(beer1.NameBeer.toLowerCase());
                                        }
                                    });
                                }

                                beerLoadingText.setVisibility(View.INVISIBLE);

                                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                                recyclerView.setHasFixedSize(true);

                                BeerAdapter adapter = new BeerAdapter(getContext(), beersUpd);
                                recyclerView.setAdapter(adapter);
                            }
                            else
                            {
                                if (asc){
                                    // Сортируем список по полю NameBeer в алфавитном порядке
                                    Collections.sort(beers, new Comparator<Beer>() {
                                        @Override
                                        public int compare(Beer beer1, Beer beer2) {
                                            // Сравниваем имена пива в алфавитном порядке без учета регистра
                                            return beer1.NameBeer.toLowerCase().compareTo(beer2.NameBeer.toLowerCase());
                                        }
                                    });
                                } else {
                                    // Сортируем список по полю NameBeer в порядке убывания (от конца алфавита)
                                    Collections.sort(beers, new Comparator<Beer>() {
                                        @Override
                                        public int compare(Beer beer1, Beer beer2) {
                                            // Сравниваем имена пива в порядке убывания (от конца алфавита) без учета регистра
                                            return beer2.NameBeer.toLowerCase().compareTo(beer1.NameBeer.toLowerCase());
                                        }
                                    });
                                }



                                beerLoadingText.setVisibility(View.INVISIBLE);

                                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                                recyclerView.setHasFixedSize(true);

                                BeerAdapter adapter = new BeerAdapter(getContext(), beers);
                                recyclerView.setAdapter(adapter);
                            }


                        }

                        @Override
                        public void onFailure(Call<ArrayList<Beer>> call, Throwable t) {
                            Log.e("ашыпка:", t.toString());
                        }
                    });
                }
                else
                {
                    String search = "";
                    if (!TextUtils.isEmpty(beerSearch.getText())){
                        search = beerSearch.getText().toString();
                    }

                    Call<ArrayList<Beer>> getBeersList = api.getBeersList();

                    String finalSearch = search;

                    getBeersList.enqueue(new Callback<ArrayList<Beer>>() {
                        @Override
                        public void onResponse(Call<ArrayList<Beer>> call, Response<ArrayList<Beer>> response) {

                            ArrayList<Beer> beers = response.body();

                            if (!finalSearch.isEmpty()){
                                // Создаем новый список, содержащий только те элементы из beers, у которых поле NameBeer содержит строку finalSearch
                                ArrayList<Beer> beersUpd = new ArrayList<>();
                                for (Beer beer : beers) {
                                    if (beer.NameBeer.toLowerCase().contains(finalSearch.toLowerCase())) {
                                        beersUpd.add(beer);
                                    }
                                }

                                // Сортируем список по полю NameBeer
                                Collections.sort(beersUpd, new Comparator<Beer>() {
                                    @Override
                                    public int compare(Beer beer1, Beer beer2) {
                                        return beer1.NameBeer.compareToIgnoreCase(beer2.NameBeer);
                                    }
                                });

                                beerLoadingText.setVisibility(View.INVISIBLE);

                                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                                recyclerView.setHasFixedSize(true);

                                BeerAdapter adapter = new BeerAdapter(getContext(), beersUpd);
                                recyclerView.setAdapter(adapter);
                            }
                            else
                            {
                                beerLoadingText.setVisibility(View.INVISIBLE);

                                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                                recyclerView.setHasFixedSize(true);

                                BeerAdapter adapter = new BeerAdapter(getContext(), response.body());
                                recyclerView.setAdapter(adapter);
                            }

                        }

                        @Override
                        public void onFailure(Call<ArrayList<Beer>> call, Throwable t) {
                            Log.e("ашыпка:", t.toString());
                        }
                    });
                }
            }
        });
    }
}

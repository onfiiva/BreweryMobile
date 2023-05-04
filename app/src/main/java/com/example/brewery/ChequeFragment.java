package com.example.brewery;

import static com.example.brewery.BeerAdapter.idCheque;
import static com.example.brewery.BeerAdapter.Order;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.brewery.Models.Beer;
import com.example.brewery.Models.BeerCheque;
import com.example.brewery.Models.Cheque;
import com.example.brewery.Service.APIInterface;
import com.example.brewery.Service.RequestBuilder;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChequeFragment extends Fragment {

    RecyclerView recyclerView;
    Button confirmCheque, deleteCheque;
    APIInterface api;
    TextView chequeLoadingText;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cheque, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recyclerViewCheque);
        confirmCheque = view.findViewById(R.id.confirm_cheque);
        deleteCheque = view.findViewById(R.id.delete_cheque);
        chequeLoadingText = view.findViewById(R.id.cheque_loading_text);


        api = RequestBuilder.buildRequest().create(APIInterface.class);

        chequeLoadingText.setVisibility(View.VISIBLE);

        if (idCheque == 0) {
            Toast.makeText(getContext(), "Сначала создайте чек.", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Call<ArrayList<Beer>> getBeersByCheque = api.getBeersByCheque(idCheque);

            getBeersByCheque.enqueue(new Callback<ArrayList<Beer>>() {
                @Override
                public void onResponse(Call<ArrayList<Beer>> call, Response<ArrayList<Beer>> response) {

                    chequeLoadingText.setVisibility(View.INVISIBLE);

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

        }
        confirmCheque.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Чек успешно подтвержден", Toast.LENGTH_SHORT).show();
                Order = 0;
            }
        });
        deleteCheque.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (idCheque != 0){

                    int[] cheques = new int[2];
                    cheques[1] = idCheque;
                    Call<ResponseBody> deleteCheque = api.deleteCheque(cheques);

                    deleteCheque.enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                            Toast.makeText(getContext(), "Успешное удаление чека.", Toast.LENGTH_SHORT).show();
                            Order = 0;
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            Log.e("ашыпка:", t.toString());
                        }
                    });
                }
                else
                {
                    Toast.makeText(getContext(), "Сначала создайте чек.", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}

package com.example.brewery.Service;

import com.example.brewery.Models.Beer;
import com.example.brewery.Models.BeerCheque;
import com.example.brewery.Models.BeerType;
import com.example.brewery.Models.Brewery;
import com.example.brewery.Models.BreweryBeer;
import com.example.brewery.Models.Cheque;
import com.example.brewery.Models.Ingridient;
import com.example.brewery.Models.IngridientsBeer;
import com.example.brewery.Models.IngridientsType;
import com.example.brewery.Models.Subscription;
import com.example.brewery.Models.Supplier;
import com.example.brewery.Models.Token;
import com.example.brewery.Models.User;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIInterface {

    @GET("Beers")
    Call<ArrayList<Beer>> getBeersList();
    @GET("Beers/paged_beer_logs")
    Call<ArrayList<Beer>> getBeersPagedList(@Query("Pages") int from, @Query("Entities") int to);
    /*@GET("Beers//getType/{id}")
    Call<BeerType> getBeerType(@Query("id") int id);*/

    @GET("BeerCheques")
    Call<ArrayList<BeerCheque>> getBeerChequesList();
    @GET("BeerCheques/getBeersByCheque/{ChequeId}")
    Call<ArrayList<Beer>> getBeersByCheque(@Path("ChequeId") int ChequeId);
    @POST("BeerCheques")
    Call<ResponseBody> postBeerCheque(@Body BeerCheque beerCheque);

    @GET("BeerTypes")
    Call<ArrayList<BeerType>> getBeerTypesList();
    @GET("BeerTypes/{id}")
    Call<BeerType> getBeerTypeById(@Path("id") int id);

    @GET("Breweries")
    Call<ArrayList<Brewery>> getBreweriesList();

    @GET("BreweryBeers")
    Call<ArrayList<BreweryBeer>> getBreweryBeersList();

    @GET("Cheques")
    Call<ArrayList<Cheque>> getChequesList();
    @GET("Cheques/getChequesByUser/{UserId}")
    Call<ArrayList<Cheque>> getChequesByUser(@Path("UserId") int UserId);
    @POST("Cheques")
    Call<ResponseBody> postCheque(@Body Cheque cheque);
    @PUT("Cheques")
    Call<ResponseBody> putCheque(@Body Cheque cheque);
    @PUT("Cheques/LogicalDelete")
    Call<ResponseBody> deleteCheque(@Body int[] ChequeId);

    @GET("Ingridients")
    Call<ArrayList<Ingridient>> getIngridientsList();

    @GET("IngridientsBeers")
    Call<ArrayList<IngridientsBeer>> getIngridientsBeersList();

    @GET("IngridientsTypes")
    Call<ArrayList<IngridientsType>> getIngridientsTypesList();

    @GET("Subscriptions")
    Call<ArrayList<Subscription>> getSubscriptionsList();

    @GET("Supplier")
    Call<ArrayList<Supplier>> getSuppliersList();

    @GET("Tokens")
    Call<ArrayList<Token>> getTokensList();

    @GET("Users")
    Call<ArrayList<User>> getUsersList();
    @GET("Users/getUserByPhone/{UserPhone}")
    Call<User> getUserByPhone(@Path("UserPhone") String UserPhone);
    @GET("Users/{UserPhone}/{Password}")
    Call<ResponseBody> authUser(@Path("UserPhone") String UserPhone, @Path("Password") String Password);

}
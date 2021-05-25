package com.example.frontcafe;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ServerApi {

    @POST( "/sign-up")
    Call<String> signUpPerson(@Query(value = "login") String login,@Query(value = "password") String password);

    @POST("/item/add")
    Call<String> addItem(@Query(value = "nameOfItem") String nameOfItem,@Query(value = "priceOfItem") int priceOfItem,
                         @Query(value = "numOfItem") int numOfItem, @Query(value = "shopCartId") long shopCartId);

    @POST("/item/{shopCartId}/delete")
    Call<String> delItem(@Query(value = "itemId") long itemId);

    @POST("/shopCart/{shopCartId}")
    Call<String> getShopCart(@Query(value = "shopCartId") String shopCartId);

    @POST("/shopCart/{shopCartId}/delete")
    Call<String> delShopCart(@Query(value = "shopCartId") String shopCartId);
}
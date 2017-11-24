package com.t3h.bamibread.managers;

import com.t3h.bamibread.model.Directions;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Heart Of Dead on 9/16/2017.
 */

public interface ResApiGoogleMap {
    @GET("/maps/api/directions/json")
    Observable<Directions> direction(@Query("origin") String originsdfd,
                                     @Query("destination") String destination,
                                     @Query("key") String key);

    @GET("/maps/api/directions/json")
    Observable<Directions> directionMode(@Query("origin") String originsdfd, @Query("destination") String destination, @Query("mode") String mode, @Query("key") String key);
}

package com.immortal.memes.server;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ServerAPI {

    @GET("mems/offset={offset}")
    Call<ArrayList<Mem>> getMemes(@Path("offset") int offset);

//    @GET("getMem")
//    Call<Mem> getProduct(long productId);

}

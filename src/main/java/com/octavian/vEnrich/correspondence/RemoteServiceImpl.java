package com.octavian.vEnrich.correspondence;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.POST;
import retrofit2.http.Path;

public class RemoteServiceImpl {

    private static RemoteServiceImpl.RemoteService service = null;
    private static final String HOD_OCR_DOCUMENT_URL = "http://api.havenondemand.com/";
    private static Retrofit retrofit = (new Retrofit.Builder()).baseUrl("http://api.havenondemand.com/").addConverterFactory(GsonConverterFactory.create()).build();

    public RemoteServiceImpl() {
    }

    public static RemoteServiceImpl.RemoteService getInstance() {
        if (service == null) {
            service = (RemoteServiceImpl.RemoteService)retrofit.create(RemoteServiceImpl.RemoteService.class);
        }

        return service;
    }

    public interface RemoteService {
        @POST("1/api/sync/ocrdocument/v1 --form \"file=@{filename}")
        Call<Object> extractText(@Path("filename") String var1);
    }

}

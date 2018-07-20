package com.octavian.vEnrich.correspondence;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OverviewPresenter {

    private RemoteServiceImpl.RemoteService service = RemoteServiceImpl.getInstance();
    private RetrofitBridge bridge;

    public OverviewPresenter(RetrofitBridge bridge) {
        this.bridge = bridge;
    }

    public void extractText(String fileName) {
        Call<Object> call = this.service.extractText(fileName);
        call.enqueue(new Callback<Object>() {
            public void onResponse(Call<Object> call, Response<Object> response) {
                System.out.println(response);
                OverviewPresenter.this.bridge.receiveText((String)response.body());
            }

            public void onFailure(Call<Object> call, Throwable throwable) {
                OverviewPresenter.this.bridge.error(throwable);
            }
        });
    }

}

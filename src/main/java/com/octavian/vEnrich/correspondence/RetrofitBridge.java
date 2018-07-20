package com.octavian.vEnrich.correspondence;

public interface RetrofitBridge {

    void receiveText(String var1);

    void message(String var1);

    void error(Throwable var1);

}

package com.ezenbooks.backend.service;

import com.ezenbooks.backend.BootpayObject;
import com.ezenbooks.backend.pay.request.Cancel;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

public class CancelService {
    static public HttpResponse receiptCancel(BootpayObject bootpay, Cancel cancel) throws Exception {
        if(bootpay.token == null || bootpay.token.isEmpty()) throw new Exception("token 값이 비어있습니다.");
        if(cancel.receipt_id == null || cancel.receipt_id.isEmpty()) throw new Exception("receiptId 값을 입력해주세요.");


        HttpClient client = HttpClientBuilder.create().build();
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
        HttpPost post = bootpay.httpPost("cancel", new StringEntity(gson.toJson(cancel), "UTF-8"));
        post.setHeader("Authorization", bootpay.token);
        return client.execute(post);
    }
}

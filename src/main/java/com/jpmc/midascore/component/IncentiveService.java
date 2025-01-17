package com.jpmc.midascore.component;

import com.jpmc.midascore.foundation.Incentive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.jpmc.midascore.foundation.Transaction;

@Service
public class IncentiveService {

    private final RestTemplate restTemplate;

    @Autowired
    public IncentiveService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public Incentive fetchIncentive(Transaction transaction) {
        String url = "http://localhost:8080/incentive";  // URL for the Incentive Service
        return restTemplate.postForObject(url, transaction, Incentive.class);
    }
}
package com.exam.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;
import java.util.List;
import java.util.Arrays;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.exam.modal.AverageResponse;


import java.util.*;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;




@Service
public class Number {

    private final RestTemplate restTemplate;
    private final Set<Integer> window = new LinkedHashSet<>();
    private final int windowSize = 10;
    private final String testServerUrl = "http://20.244.56.144/test/{numberid}";
    private final String bearerToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJNYXBDbGFpbXMiOnsiZXhwIjoxNzE4MjYxNTUzLCJpYXQiOjE3MTgyNjEyNTMsImlzcyI6IkFmZm9yZG1lZCIsImp0aSI6IjZlNzAzYTMzLTVmYjUtNDczZS1iMWJjLTY4NGJmYTEyZGM2ZiIsInN1YiI6IjIxMDAwMzA4NzVjc2VoQGdtYWlsLmNvbSJ9LCJjb21wYW55TmFtZSI6IksgTCBVbml2ZXJzaXR5IiwiY2xpZW50SUQiOiI2ZTcwM2EzMy01ZmI1LTQ3M2UtYjFiYy02ODRiZmExMmRjNmYiLCJjbGllbnRTZWNyZXQiOiJucnpsYUhTdHlSS3VERWNBIiwib3duZXJOYW1lIjoiUHJhZGVlcCIsIm93bmVyRW1haWwiOiIyMTAwMDMwODc1Y3NlaEBnbWFpbC5jb20iLCJyb2xsTm8iOiIyMTAwMDMwODc1In0.0VTfUPhv89CcPi4Vk-cN9kF1ANskZ81AtuXieqD6Pbk";

    @Autowired
    public Number(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public AverageResponse fetchAndCalculate(String numberId) {
        String url = UriComponentsBuilder.fromUriString(testServerUrl)
                .buildAndExpand(numberId)
                .toUriString();

        System.out.println("Fetching data from URL: " + url); // Debug line

        List<Integer> numbers = fetchNumbers(url);

        // Debug logs for fetched data
        System.out.println("Fetched numbers: " + numbers);
        System.out.println("Window state: " + window);

        // Calculate window state and average as before...

        AverageResponse response = new AverageResponse();
        response.setNumbers(numbers);
        response.setWindowPrevState(new ArrayList<>(window));
        response.setWindowCurrState(new ArrayList<>(window));
        response.setAvg(numbers.stream().mapToDouble(Integer::intValue).average().orElse(0.0));

        System.out.println("AverageResponse: " + response); // Debug line

        return response;
    }

    private List<Integer> fetchNumbers(String url) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + bearerToken);
            HttpEntity<String> entity = new HttpEntity<>(headers);

            ResponseEntity<Object> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, Object.class);
            Object responseBody = responseEntity.getBody();

            if (responseBody instanceof Integer[]) {
                // If response is already an array of integers
                return Arrays.asList((Integer[]) responseBody);
            } else if (responseBody instanceof Map) {
                // If response is an object, extract the "numbers" array
                ObjectMapper mapper = new ObjectMapper();
                Integer[] numbersArray = mapper.convertValue(((Map<?, ?>) responseBody).get("numbers"), Integer[].class);
                if (numbersArray != null) {
                    return Arrays.asList(numbersArray);
                }
            }

            System.out.println("Received unexpected response format: " + responseBody);
        } catch (Exception e) {
            e.printStackTrace(); // Optional: replace with proper logging
        }
        return Collections.emptyList();
    }

}

package com.pmyo.pmyo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class GPTService2 {
    @Autowired
    private RestTemplate restTemplate;

    @Value("${openai.api.key}")
    private String apiKey;

    public int analyzeRecyclingCorrectness(String item, String imageBase64) {
        String prompt = "사진에 보이는 쓰레기들은 " + item + "을 버린건데 잘 분류한건지 판단해줘";
        String gptResponse = callGPTAPI(prompt, imageBase64);

        // GPT의 응답을 분석하여 올바르게 분류되었는지 판단
        return parseGptResponseForCorrectness(gptResponse);
    }

    private String callGPTAPI(String prompt, String imageBase64) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey);

        String requestBody = buildRequestBody(prompt, imageBase64);

        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);
        ResponseEntity<String> response = restTemplate.postForEntity("https://api.openai.com/v1/chat/completions", entity, String.class);

        return response.getBody();
    }

    private String buildRequestBody(String prompt, String img) {
        return "{\n" +
                "    \"model\": \"gpt-4-vision-preview\",\n" +
                "    \"messages\": [\n" +
                "        {\n" +
                "            \"role\": \"user\",\n" +
                "            \"content\": \"" + prompt + "\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"role\": \"system\",\n" +
                "            \"content\": {\n" +
                "                \"type\": \"image_url\",\n" +
                "                \"image_url\": \"data:image/jpeg;base64," + img + "\"\n" +
                "            }\n" +
                "        }\n" +
                "    ],\n" +
                "    \"max_tokens\": 1000\n" +
                "}";
    }

    private int parseGptResponseForCorrectness(String response) {
        // GPT의 응답 분석 로직 구현
        if (response.contains("올바르게 분류됨") || response.contains("정확함")) {
            return 1;
        } else {
            return 0;
        }
    }
}

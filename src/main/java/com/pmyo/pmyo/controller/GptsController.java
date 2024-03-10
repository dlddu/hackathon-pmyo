package com.pmyo.pmyo.controller;

import com.pmyo.pmyo.service.GptsService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.text.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class GptsController {
    private final GptsService gptsService;
    @Value("${openai.api-key}")
    private String openAiApiKey;

    @PostMapping("/gpts")
    public ReturnClass gptController(@RequestParam("file") MultipartFile file) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        String returnValue = gptsService.savePic(file);
        System.out.println(returnValue);//s3에 저장
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(openAiApiKey);

        String requestBody = "{\"model\": \"gpt-4-vision-preview\", \"messages\": [{\"role\": \"user\", \"content\": [{\"type\": \"text\", \"text\": \"사진에 보이는 쓰레기들을 어떻게 분리수거 할 수 있는지 번호를 매겨서 설명해줘\"}, {\"type\": \"image_url\", \"image_url\": {\"url\": \"" + returnValue + "\"}}]}], \"max_tokens\": 1000}";

        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

        RestTemplate restTemplate = new RestTemplate();

        String apiUrl = "https://api.openai.com/v1/chat/completions";

        ReturnClass returnClass = new ReturnClass();
        returnClass.setUrl(returnValue);
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                apiUrl,
                HttpMethod.POST,
                requestEntity,
                String.class
        );
        String body = responseEntity.getBody();
        String conversation = StringEscapeUtils.unescapeJava(body);
        returnClass.setConversation(conversation);
        return returnClass;
    }

    public static class ReturnClass {
        String url, conversation;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getConversation() {
            return conversation;
        }

        public void setConversation(String conversation) {
            this.conversation = conversation;
        }
    }
}


package com.jiubo.buildstore.controller;


import com.alibaba.fastjson.JSONObject;

import com.jiubo.buildstore.service.CounselorCommentService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@RestController
@RequestMapping("/SendMessageController")
public class SendMessageController {

    @PostMapping("/sendMessage")
    public JSONObject sendMessage(@RequestBody String phone) {

        Random random = new Random();
        String result="";
        for (int i=0;i<6;i++)
        {
            result+=random.nextInt(10);
        }

        String url = "https://api.mysubmail.com/message/send.json";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("appid","49267");
        paramMap.put("to",phone);
        paramMap.put("content","【寄居找房】您的短信验证码："+ result +",请在10分钟内输入,如非本人操作,请忽略本短信");
        paramMap.put("signature","a825cf5d6d76d51013d1839aebb09989");
        String requestString = JSONObject.toJSONString(paramMap);

        String httpContent = CounselorCommentService.sendHttpContent(url, requestString);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code",result);
        jsonObject.put("returnData",httpContent);
        return jsonObject;
    }
}

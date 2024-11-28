package com.bjpowernode.controller;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;

@RestController
public class ChatController {

    /**
     * spring-ai 自动装配的，可以直接注入使用
     */
    @Resource
    private OpenAiChatModel openAiChatModel;

    /**
     * 调用OpenAI的接口
     *
     * @param msg 我们提的问题
     * @return
     */
    @RequestMapping(value = "/ai/chat")
    public String chat(@RequestParam(value = "msg") String msg) {
        // 直接使用消息字符串作为参数调用
        String response = openAiChatModel.call(msg);
        return response;
    }

    /**
     * 调用OpenAI的接口
     *
     * @param msg 我们提的问题
     * @return
     */
    @RequestMapping(value = "/ai/chat3")
    public String chat3(@RequestParam(value = "msg") String msg) {
        // 使用配置的温度参数并直接调用消息
        OpenAiChatOptions options = OpenAiChatOptions.builder()
                .withTemperature(0.4)
                .build();
        // 直接使用消息字符串作为参数调用
        String response = openAiChatModel.call(msg);
        return response;
    }

    /**
     * 调用OpenAI的接口
     *
     * @param msg 我们提的问题
     * @return
     */
    @RequestMapping(value = "/ai/chat4")
    public Object chat4(@RequestParam(value = "msg") String msg) {
        // 使用流的方式处理响应
        Flux<String> flux = openAiChatModel.stream(msg);

        // 打印每一个响应部分
        flux.toStream().forEach(System.out::println);
        return flux.collectList(); // 收集所有响应返回
    }
}

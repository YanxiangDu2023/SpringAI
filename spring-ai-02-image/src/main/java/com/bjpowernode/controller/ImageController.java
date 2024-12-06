package com.bjpowernode.controller;

import jakarta.annotation.Resource;
import org.springframework.ai.image.ImageOptionsBuilder;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageResponse;
import org.springframework.ai.image.ImageModel;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiImageOptions;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ImageController {

//    @Resource
//    private OpenAiChatModel openAiChatModel;
//
//
//    @RequestMapping("/ai/image")
//    private Object image(@RequestParam(value = "msg") String msg) {
//        ImageResponse imageResponse = openAiChatModel.call(new ImagePrompt(msg));
//
//        System.out.println(imageResponse);
//
//        String imageUrl = imageResponse.getResult().getOutput().getUrl();
//        //把图片进行业务处理
//
//        return imageResponse.getResult().getOutput();
//    }

    @Resource
    private ImageModel imageModel; // 确保这里注入了正确的 ImageModel 实例

    @RequestMapping("/ai/image")
    public Object generateImage(@RequestParam(value = "msg") String msg) {
        // 使用 ImagePrompt 和默认选项生成图片
        ImagePrompt prompt = new ImagePrompt(msg);
        ImageResponse response = imageModel.call(prompt); // 调用 ImageModel 的 call 方法

        // 返回生成的图片信息
        return response.getResult().getOutput();
    }





    @RequestMapping("/ai/image2")
    public Object generateCustomImage(@RequestParam(value = "msg") String msg) {
        // 使用 ImagePrompt 和自定义选项生成图片
        ImagePrompt prompt = new ImagePrompt(
                msg,
                ImageOptionsBuilder.builder()
                        .withModel("gpt-4o") // 指定 gpt-4o 模型
//                        .withQuality("hd") // 高清质量
                        .withN(1)          // 生成 1 张图片
                        .withHeight(1024)  // 图片高度
                        .withWidth(1024)   // 图片宽度
                        .build()
        );
        ImageResponse response = imageModel.call(prompt);

        // 返回生成的图片信息
        return response.getResult().getOutput();
    }
}
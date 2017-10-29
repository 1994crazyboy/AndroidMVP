package me.heyboy.mymvpdemo.services;

import org.junit.Test;
import com.alibaba.fastjson.JSONArray;

import java.util.List;

import me.heyboy.mymvpdemo.model.entities.ImgRecorder;

import static org.junit.Assert.*;

/**
 * 创 建 人： Henning
 * 创建时间： 17-10-30 上午7:05
 * 工程名称： MyApplication
 * 包   名： me.heyboy.mymvpdemo.services
 */
public class PhotoImgServiceTest {
    @Test
    public void getImgRecorders() throws Exception {
        List<ImgRecorder> imgRecorderList= PhotoImgService.getImgRecorders();
        System.out.println(JSONArray.toJSONString(imgRecorderList));
    }

}
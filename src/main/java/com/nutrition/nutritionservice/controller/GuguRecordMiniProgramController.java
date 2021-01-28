package com.nutrition.nutritionservice.controller;

import com.nutrition.nutritionservice.service.ConfigPropertiesService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.nio.file.Files;

/**
 * 咕咕记录小程序
 *
 * @author heng.liu
 * @since 2021/1/28
 */
@Controller
@RequestMapping("/gugu-record-mp")
public class GuguRecordMiniProgramController {

    @Resource
    private ConfigPropertiesService configPropertiesService;

    @RequestMapping("/{fileName}")
    @ResponseBody
    public void download(HttpServletRequest req, HttpServletResponse resp, @PathVariable("fileName") String fileName)
            throws Exception {
        File file = new File(configPropertiesService.getFileDownloadPath() + fileName);
        // 设置响应类型 告诉浏览器当前是下载操作
        resp.setContentType("application/x-msdownload");
        // 设置下载时文件的显示类型(即文件名称-后缀) ex：txt为文本类型
        resp.setHeader("Content-Disposition", "attachment;filename=" + fileName);
        // 下载文件：将一个路径下的文件数据转到一个输出流中，也就是把服务器文件通过流写(复制)到浏览器端
        Files.copy(file.toPath(), resp.getOutputStream());// Files.copy(要下载的文件的路径,响应的输出流)
    }

}

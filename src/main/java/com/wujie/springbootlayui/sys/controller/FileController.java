package com.wujie.springbootlayui.sys.controller;

import cn.hutool.core.date.DateUtil;
import com.wujie.springbootlayui.sys.common.AppFileUtils;
import com.wujie.springbootlayui.sys.common.PicUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("file")
public class FileController {

    /**
     * 文件上传
     *
     * @param mf
     * @return
     */
    @RequestMapping("uploadFile")
    public Map<String, Object> uploadFile(MultipartFile mf) {
        //1.得到文件名
        String oldName = mf.getOriginalFilename();
        //2.根据旧的文件名生成新的文件名
        String newName = AppFileUtils.createNewFileName(oldName);
        //3.得到当前日期的字符串
        String dirName = DateUtil.format(new Date(), "yyyy-MM-dd");
        //4.构造文件夹
        File dirFile = new File(AppFileUtils.UPLOAD_PATH, dirName);
        //5.判断当前文件夹是否存在
        if (!dirFile.exists()) {
            //如果不存在则创建新文件夹
            dirFile.mkdirs();
        }
        //6.构造文件对象
        File file = new File(dirFile, newName);
        //7.把mf里面的图片信息写入file
        try {
            mf.transferTo(file);
        } catch (IllegalStateException | IOException e) {
            e.printStackTrace();
        }


        try {
            if (file.length() > 1000000) {
                if (oldName.endsWith(".jpg") || oldName.endsWith(".png")) {
                    byte[] imgBytes = PicUtil.getByteByPic(file.getAbsolutePath());
                    byte[] resultImg = PicUtil.compressUnderSize(imgBytes, 1024 * 1024);
                    String newName2 = AppFileUtils.createNewFileName(oldName);
                    File file2 = new File(dirFile, newName2);
                    file2.createNewFile();
                    PicUtil.byte2image(resultImg, file2.getAbsolutePath());
                    newName = newName2;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("path", dirName + "/" + newName);
        map.put("oldName", oldName);

        return map;
    }

    /**
     * 图片下载
     */
    @RequestMapping("showImageByPath")
    public ResponseEntity<Object> showImageByPath(String path) {
        return AppFileUtils.createResponseEntity(path);
    }

    /**
     * 下载文件
     */
    @RequestMapping("getFileByPath")
    public void getFileByPath(String path, HttpServletResponse response) {
        AppFileUtils.downloadFile(path, response);
    }

}

package com.wujie.springbootlayui.sys.common;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class AppFileUtils {

    /**
     * 文件上传的保存路径 默认值
     */
    public static String UPLOAD_PATH = "D:/upload/";

    public static void load(String str) {
        // 读取配置文件的存储地址
        InputStream stream = AppFileUtils.class.getClassLoader().getResourceAsStream(str);
        Properties properties = new Properties();
        try {
            properties.load(stream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String property = properties.getProperty("filepath");
        if (null != property) {
            UPLOAD_PATH = property;
        }
    }

    /**
     * 根据文件老名字得到新名字
     *
     * @param oldName 文件老名字
     * @return 新名字由32位随机数加图片后缀组成
     */
    public static String createNewFileName(String oldName) {
        // 获取文件名后缀
        String stuff = oldName.substring(oldName.lastIndexOf("."), oldName.length());
        // 将UUID与文件名后缀进行拼接，生成新的文件名 生成的UUID为32位
        return IdUtil.simpleUUID().toUpperCase() + stuff;
    }

    /**
     * 文件下载
     *
     * @param path
     * @return
     */
    public static ResponseEntity<Object> createResponseEntity(String path) {
        // 1,构造文件对象
        File file = new File(UPLOAD_PATH, path);
        if (file.exists()) {
            // 将下载的文件，封装byte[]
            byte[] bytes = null;
            try {
                bytes = FileUtil.readBytes(file);
            } catch (Exception e) {
                e.printStackTrace();
            }
            // 创建封装响应头信息的对象
            HttpHeaders header = new HttpHeaders();
            // 封装响应内容类型(APPLICATION_OCTET_STREAM 响应的内容不限定)
            header.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            // 创建ResponseEntity对象
            ResponseEntity<Object> entity = new ResponseEntity<Object>(bytes, header, HttpStatus.CREATED);
            return entity;
        }
        return null;
    }

    /**
     * 更该图片的名字 去掉_temp
     *
     * @param goodsimg
     * @return
     */
    public static String renameFile(String goodsimg) {
        File file = new File(UPLOAD_PATH, goodsimg);
        String replace = goodsimg.replace("_temp", "");
        if (file.exists()) {
            file.renameTo(new File(UPLOAD_PATH, replace));
        }
        return replace;
    }

    /**
     * 根据老路径删除图片
     *
     * @param oldPath
     */
    public static void removeFileByPath(String oldPath) {
        // 图片的路径不是默认图片的路径
        if (!oldPath.equals(Constast.DEFAULT_IMG)) {
            File file = new File(UPLOAD_PATH, oldPath);
            if (file.exists()) {
                file.delete();
            }
        }
    }

    public static void downloadFile(String content, HttpServletResponse response) {
        try {
            // 要下载的文件的路径。
            String filePath = UPLOAD_PATH + content;
            File file = new File(filePath);
            if(!file.exists()){
                return;
            }


            // 取得文件名。
            String filename = file.getName();

            // 以流的形式下载文件。
            InputStream fis = new BufferedInputStream(new FileInputStream(filePath));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // 清空response
            response.reset();
            // 设置response的Header
            response.addHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes()));
            response.addHeader("Content-Length", "" + file.length());
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            response.setCharacterEncoding("utf-8");
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static String uploadFile(String path) {
        String result = "";
        try {
            File oldFile = new File(path);
            if (oldFile.exists()) {
                String[] names = path.split("\\\\");
                String oldName = names[names.length - 1];
                System.out.println("oldName" + oldName);
                String newName = AppFileUtils.createNewFileName(oldName);
                // 3.得到当前日期的字符串
                String dirName = DateUtil.format(new Date(), "yyyy-MM-dd");
                // 4.构造文件夹
                File dirFile = new File(AppFileUtils.UPLOAD_PATH, dirName);
                // 5.判断当前文件夹是否存在
                if (!dirFile.exists()) {
                    // 如果不存在则创建新文件夹
                    dirFile.mkdirs();
                }
                // 6.构造文件对象
                File file = new File(dirFile, newName);
                if (oldFile.length() > 1000000) {
                    if (oldName.endsWith(".jpg") || oldName.endsWith(".png")) {
                        System.out.println(oldName + " " + oldFile.length());
                        byte[] imgBytes = PicUtil.getByteByPic(oldFile.getAbsolutePath());
                        byte[] resultImg = PicUtil.compressUnderSize(imgBytes, 1000000);
                        PicUtil.byte2image(resultImg, file.getAbsolutePath());
                    }
                } else {
                    InputStream input = new FileInputStream(oldFile);
                    FileOutputStream output = new FileOutputStream(file);
                    FileCopyUtils.copy(input, output);
                }


                result = dirName + "/" + newName;
            }

        } catch (Exception e) {
            return "";
        }

        return result;
    }

    public static String extractZip(String exName, String filePath, String extraDir) {

        String excel = "";
        try {
            ZipInputStream in = new ZipInputStream(new FileInputStream(filePath), Charset.forName("GBK"));
            // zip文件实体类
            ZipEntry entry;
            // 遍历压缩文件内部 文件数量

            while ((entry = in.getNextEntry()) != null) {
                if (!entry.isDirectory()) {
                    System.out.println(entry.getName());
                    if (entry.getName().contains(exName)) {
                        excel = extraDir + "/" + entry.getName();
                    }
                    // 文件输出流
                    FileOutputStream out = new FileOutputStream(extraDir + "/" + entry.getName());
                    BufferedOutputStream bos = new BufferedOutputStream(out);
                    int len;
                    byte[] buf = new byte[1024];
                    while ((len = in.read(buf)) != -1) {
                        bos.write(buf, 0, len);
                    }
                    // 关流顺序，先打开的后关闭
                    bos.close();
                    out.close();

                }

            }

            in.close();
        } catch (Exception e) {
            return "";
        }

        return excel;
    }

}

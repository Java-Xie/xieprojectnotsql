package com.chu.xieproject.controller;

import com.spire.doc.Document;
import com.spire.doc.FileFormat;
import com.spire.doc.Section;
import com.spire.doc.TextWatermark;
import com.spire.doc.documents.WatermarkLayout;
import com.spire.pdf.PdfDocument;
import com.spire.pdf.PdfPageBase;
import com.spire.pdf.graphics.*;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.geom.Dimension2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.*;
import java.util.Iterator;

@Controller
@RequestMapping("/file")
public class FileController {

    private static byte[] bufferdown;


    /**
     * 文件上传
     * @return fileuploaded.html
     */
    @RequestMapping("fileTest")
    @ResponseBody
    public String fileTest(HttpServletRequest request, HttpServletRequest response){
        System.out.println("测试文件上传");
        //检测是不是存在上传文件
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);

//        if(isMultipart){
//            DiskFileItemFactory factory = new DiskFileItemFactory();
//
//            //指定在内存中缓存数据大小,单位为byte,这里设为1Mb
//            factory.setSizeThreshold(1024*1024);
//
//            //设置一旦文件大小超过getSizeThreshold()的值时数据存放在硬盘的目录
//            factory.setRepository(new File("D:\\temp"));
//            // Create a new file upload handler
//            ServletFileUpload upload = new ServletFileUpload(factory);
//            // 指定单个上传文件的最大尺寸,单位:字节，这里设为50Mb
//            upload.setFileSizeMax(50 * 1024 * 1024);
//            //指定一次上传多个文件的总尺寸,单位:字节，这里设为50Mb
//            upload.setSizeMax(50 * 1024 * 1024);
//            upload.setHeaderEncoding("UTF-8");
//            List items = null;
//            try {
//                 // 解析request请求
//                 items = upload.parseRequest()
//            } catch (FileUploadException e) {
//                 e.printStackTrace();
//            }
//            if(items!=null){
//                    //解析表单项目
//                Iterator<FileItem> iter = items.iterator();
//                while (iter.hasNext()) {
//                    FileItem item = iter.next();
//                    //如果是普通表单属性
//                    if (item.isFormField()) {
//                        //相当于input的name属性   <input type="text" name="content">
//                        String name = item.getFieldName();
//
//                        //input的value属性
//                        String value = item.getString();
//                        System.out.println("属性:" + name + " 属性值:" + value);
//                    }
//                    //如果是上传文件
//                    else {
//                    //属性名
//                        String fieldName = item.getFieldName();
//                        //上传文件路径
//                        String fileName = item.getName();
//                        fileName = fileName.substring(fileName.lastIndexOf("/") + 1);// 获得上传文件的文件名
//                        try {
//                            item.write(new File(uploadPath, fileName));
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                    }
//                 }
//            }
//        }
        return "true";
    }


    /**
     * 文件上传
     * @return fileuploaded.html
     */
    @RequestMapping("fileuploaded")
    @ResponseBody
    public String fileuploaded(@RequestParam("file") MultipartFile file){
        System.out.println(file);
        byte[] buffer = new byte[0];
        try {
            buffer = file.getBytes();
            bufferdown = buffer;
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(buffer);
        System.out.println("文件上传成功");
        return "true";
    }

    /**
     * 文件下载
     * @return filedown.html
     */
    @RequestMapping("filedown")
    public void filedown(HttpServletResponse resp){
        System.out.println(bufferdown);
        InputStream inputStream = null;
        OutputStream os = null;
        resp.reset();
        // 浏览器文件下载响应头
//        resp.setContentType("application/octet-stream");
        resp.setContentType("text/html");
//        resp.setHeader("Content-Disposition","attachment;filename=test.pdf");
        inputStream = new ByteArrayInputStream(bufferdown);
        byte[] buffer = new byte[1024];
        try {
            os = resp.getOutputStream();
            int len = 0;
            while ((len = inputStream.read(buffer)) > 0){
                os.write(buffer, 0, len);
            }
            os.flush();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null){
                    inputStream.close();
                }
                if (os != null){
                    os.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.out.println("文件下载");
    }

    /**
     * 文件上传
     * @return fileuploaded.html
     * Spire.Doc for Java
     */
    @RequestMapping("fileUploadedSpire")
    @ResponseBody
    public String fileUploadedSpire(@RequestParam("file") MultipartFile file){
        //得到文件的字节流
        byte[] buffer = new byte[0];
        try {
            buffer = file.getBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //把文件的字节流输出到对应的文件夹
        saveFile(file.getOriginalFilename(), buffer);

        if (".docx".equals(file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")))){
            //把上传后的文件添加水印并保存到指定位置
            Document document = new Document();
            document.loadFromFile("D:\\fileupload\\original\\" + file.getOriginalFilename());
            insertTextWatermark(document.getSections().get(0));
            document.saveToFile("D:\\fileupload\\originalWatermark\\" + file.getOriginalFilename(),FileFormat.Docx);
        } else if (".pdf".equals(file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")))){
            //创建PdfDocument对象
            PdfDocument pdf = new PdfDocument();

            //加载示例文档
            pdf.loadFromFile("D:\\fileupload\\original\\" + file.getOriginalFilename());
            //获取第一页
            PdfPageBase page = pdf.getPages().get(0);

            //调用insertWatermark方法插入文本水印
            insertWatermark(page, "DIMINE");
            //保存文档
            pdf.saveToFile("D:\\fileupload\\originalWatermark\\" + file.getOriginalFilename());
        }



        return "true";
    }

    static void insertWatermark(PdfPageBase page, String watermark) {
        Dimension2D dimension2D = new Dimension();
        dimension2D.setSize(page.getCanvas().getClientSize().getWidth() / 2, page.getCanvas().getClientSize().getHeight() / 3);
        PdfTilingBrush brush = new PdfTilingBrush(dimension2D);
        brush.getGraphics().setTransparency(0.3F);
        brush.getGraphics().save();
        brush.getGraphics().translateTransform((float) brush.getSize().getWidth() / 2, (float) brush.getSize().getHeight() / 2);
        brush.getGraphics().rotateTransform(-45);
        brush.getGraphics().drawString(watermark, new PdfFont(PdfFontFamily.Helvetica, 24), PdfBrushes.getViolet(), 0, 0, new PdfStringFormat(PdfTextAlignment.Center));
        brush.getGraphics().restore();
        brush.getGraphics().setTransparency(1);
        Rectangle2D loRect = new Rectangle2D.Float();
        loRect.setFrame(new Point2D.Float(0, 0), page.getCanvas().getClientSize());
        page.getCanvas().drawRectangle(brush, loRect);
    }

    private static void insertTextWatermark(Section section) {
        TextWatermark txtWatermark = new TextWatermark();
        txtWatermark.setText("DIMINE");
        txtWatermark.setFontSize(50);
        txtWatermark.setColor(Color.red);
        txtWatermark.setLayout(WatermarkLayout.Diagonal);
        section.getDocument().setWatermark(txtWatermark);
    }

    /**
     * 将字节流转换成文件
     * @param filename
     * @param data
     * @throws Exception
     */
    public void saveFile(String filename,byte [] data){
        if(data != null){
            String filepath ="D:\\fileupload\\original\\" + filename;
            File file  = new File(filepath);
            if(file.exists()){
                file.delete();
            }
            FileOutputStream fos = null;
            try {
                fos = new FileOutputStream(file);
                fos.write(data,0,data.length);
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                try {
                    if (fos != null){
                        fos.flush();
                        fos.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}

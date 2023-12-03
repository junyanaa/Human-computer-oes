package com.oes.util;

import com.oes.bean.Question;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.RequestContext;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
//fileUpload
public class FileUtil {
    public String filePath;
    //设置缓存大小以及临时文件保存位置
    public DiskFileItemFactory getDiskFileItemFactory(File file) {
        DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
        diskFileItemFactory.setSizeThreshold(1024 * 1024);
        diskFileItemFactory.setRepository(file);
        return diskFileItemFactory;
    }
    //  创建一个上传工具，指定使用缓存区与临时文件存储位置
    public ServletFileUpload getServletFileUpload(DiskFileItemFactory diskFileItemFactory) {
        ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);
        servletFileUpload.setProgressListener((pBytesRead, pContentLength, pItems) -> System.out.printf("总大小：%d，已上传：%d，%d\n", pContentLength, pBytesRead, pItems));
        servletFileUpload.setHeaderEncoding("utf-8");
        servletFileUpload.setFileSizeMax(1024 * 1024 * 10);
        return servletFileUpload;
    }

    static class Rc implements RequestContext {
        HttpServletRequest request;
        public Rc(HttpServletRequest request) {
            this.request = request;
        }
        @Override
        public String getCharacterEncoding() {
            return request.getCharacterEncoding();
        }
        @Override
        public String getContentType() {
            return request.getContentType();
        }
        @Override
        public int getContentLength() {
            return request.getContentLength();
        }
        @Override
        public InputStream getInputStream() throws IOException {
            return request.getInputStream();
        }
    }
    //上传文件
    public boolean uploadParseRequest(ServletFileUpload upload, HttpServletRequest req, String saveFilePath) throws FileUploadException, IOException {
        boolean flag = false;
        List<FileItem> fileItems = upload.parseRequest(new Rc(req));
        System.out.println("进入uploadParseRequest");
        for (FileItem fileItem : fileItems) {
            System.out.println("进入循环");
            if (fileItem.isFormField()) {
                System.out.println("不是文件");
                System.out.println(fileItem.getFieldName() + "---" + fileItem.getString("utf-8"));
            } else {
                System.out.println("接收文件");
                String uploadpath = fileItem.getName();
                String uploadfilename = uploadpath.substring(uploadpath.lastIndexOf("/") + 1);
                String uploadfiletype = uploadfilename.substring(uploadfilename.lastIndexOf(".") + 1);
                System.out.println(uploadpath);
                if (uploadfilename.trim().equals("") && uploadfilename == null) {
                    continue;
                }
                System.out.println("文件名：" + uploadfilename);

                if (uploadfiletype.trim().equals("jpg") && uploadfiletype == null) {
                    continue;
                }
                System.out.println("文件类型：" + uploadfiletype);
                System.out.println("上传的文件名为：" + uploadfilename + "，文件类型：" + uploadfiletype);
                String uuid = UUID.randomUUID().toString();
                String realpath = saveFilePath + "/" + uuid;
                System.out.println("保存路径为：" + realpath);
                File realfilepath = new File(realpath);
                if (!realfilepath.exists()) {
                    realfilepath.mkdir();
                }
                filePath=realpath+"\\"+uploadfilename;
                InputStream ips = fileItem.getInputStream();
                FileOutputStream fops = new FileOutputStream(realfilepath + "/" + uploadfilename);
                System.out.println(realfilepath);
                byte[] buffer = new byte[1024 * 1024];
                int len;
                while ((len = ips.read(buffer)) > 0) {
                    fops.write(buffer, 0, len);
                }
                fops.close();
                ips.close();
                flag = true;
            }
        }
        return flag;
    }
    //解析文档
    public List<Question> getQuestion(InputStream stream) throws IOException {
        List<Question> questions = new ArrayList<>();//所有题目
        int flag = 0, typeCount = 0, optionCount = 0, answerCount = 0, contentCount = 0,score=0, m = 0, v = 0, x = 0, questionCount = 0;
        boolean ifOption = true;
        XWPFDocument doc = new XWPFDocument(stream);
        List<XWPFParagraph> xwpfParagraphs = doc.getParagraphs();
        int length = xwpfParagraphs.size();
        String[] queType = new String[length];
        String[] content = new String[length];
        String[] options = new String[length];
        String[] answer = new String[length];
        Float[] scores=new Float[length];
        for (XWPFParagraph xwpfParagraph : xwpfParagraphs) {
            if (flag == 0) {
                questionCount++;
                if (xwpfParagraph.getText().equals("选择题")) {
                    queType[typeCount] = xwpfParagraph.getText();
                    typeCount++;
                } else if (xwpfParagraph.getText().equals("多选题")) {
                    queType[typeCount] = xwpfParagraph.getText();
                    typeCount++;
                } else if (xwpfParagraph.getText().equals("判断题")) {
                    queType[typeCount] = xwpfParagraph.getText();
                    typeCount++;
                    ifOption = false;
                } else if (xwpfParagraph.getText().equals("填空题")) {
                    queType[typeCount] = xwpfParagraph.getText();
                    typeCount++;
                    ifOption = false;
                }
                flag = 5;
            } else if (flag == 5) {
                content[contentCount] = xwpfParagraph.getText();
                contentCount++;
                if (ifOption)
                    flag = 6;
                else flag = 10;
            } else if (5 < flag && flag < 10) {
                options[optionCount] = xwpfParagraph.getText();
                optionCount++;
                flag++;
            } else if (flag == 10) {
                answer[answerCount] = xwpfParagraph.getText();
                answerCount++;
                flag = 11;
            } else if (flag==11) {
                scores[score]= Float.valueOf(xwpfParagraph.getText());
                score++;
                flag=0;
            }
        }
        for (int k = 0; k < questionCount; k++) {
            Question question = new Question();
            if (Objects.equals(queType[m], "选择题")) {
                question.setQuestion_type("单选题");
                question.setQuestion_content(content[m]);
                question.setScore(scores[m]);
                m++;
                question.setQuestion_answer(answer[v]);
                v++;
                question.setQuestion_optionA(options[x]);
                x++;
                question.setQuestion_optionB(options[x]);
                x++;
                question.setQuestion_optionC(options[x]);
                x++;
                question.setQuestion_optionD(options[x]);
                x++;
                questions.add(question);
            }
            else if (Objects.equals(queType[m], "多选题")) {
                question.setQuestion_type("多选题");
                question.setQuestion_content(content[m]);
                question.setScore(scores[m]);
                m++;
                question.setQuestion_answer(answer[v]);
                v++;
                question.setQuestion_optionA(options[x]);
                x++;
                question.setQuestion_optionB(options[x]);
                x++;
                question.setQuestion_optionC(options[x]);
                x++;
                question.setQuestion_optionD(options[x]);
                x++;
                questions.add(question);
            }
            else if (Objects.equals(queType[m], "判断题")) {
                question.setQuestion_type("判断题");
                question.setQuestion_content(content[m]);
                question.setScore(scores[m]);
                m++;
                question.setQuestion_answer(answer[v]);
                v++;
                questions.add(question);
            }
            else if (Objects.equals(queType[m], "填空题")) {
                question.setQuestion_type("填空题");
                question.setQuestion_content(content[m]);
                question.setScore(scores[m]);
                m++;
                question.setQuestion_answer(answer[v]);
                v++;
                questions.add(question);
            }
        }
        return questions;
    }

}
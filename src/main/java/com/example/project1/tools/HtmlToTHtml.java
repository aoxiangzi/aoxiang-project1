package com.example.project1.tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class HtmlToTHtml {

    public static void main(String[] args) throws Exception {
        String fileName = "verify.html";
        String fileContent = readToString(fileName);
        String newFileContent = fileContent;
        List<String> list = new ArrayList<>();
        String c;
        assert fileContent != null;
        while(fileContent.contains("src=") && fileContent.contains("href=")) {
            if(fileContent.indexOf("src") < fileContent.indexOf("href")) {
                c = fileContent.substring(fileContent.indexOf("src=\""));
                c = c.substring(0,6+c.substring(5).indexOf('\"'));
                fileContent = fileContent.substring(fileContent.indexOf(c)+c.length());
                if(!(c.contains("\"\"")||c.contains("@"))) {
                    list.add(c);
                }
            }else{
                c = fileContent.substring(fileContent.indexOf("href=\""));
                c = c.substring(0,7+c.substring(6).indexOf('\"'));
                fileContent = fileContent.substring(fileContent.indexOf(c)+c.length());
                if(!(c.contains("\"\"")||c.contains("@"))) {
                    list.add(c);
                }
            }
        }
        if(fileContent.contains("src=")) {
            c = fileContent.substring(fileContent.indexOf("src=\""));
            c = c.substring(0,6+c.substring(5).indexOf('\"'));
            fileContent = fileContent.substring(fileContent.indexOf(c)+c.length());
            if(!(c.contains("\"\"")||c.contains("@"))) {
                list.add(c);
            }
        }
        if(fileContent.contains("href=")){
            c = fileContent.substring(fileContent.indexOf("href=\""));
            c = c.substring(0,7+c.substring(6).indexOf('\"'));
            fileContent = fileContent.substring(fileContent.indexOf(c)+c.length());
            if(!(c.contains("\"\"")||c.contains("@"))) {
                list.add(c);
            }
        }

        for(String l:list){
            String ll;
            if(l.contains("css") || l.contains("js")) {
                ll = l.substring(l.indexOf('\"')+1,l.lastIndexOf("\""));
                if(ll.contains("css")) {
                    ll = "href=\"\" th:href=\"@{/"+ll+"}\"";
                }else {
                    ll = "src=\"\" th:src=\"@{/"+ll+"}\"";
                }
            }else {
                ll = l.substring(l.indexOf('\"')+1,l.lastIndexOf("\""));
                ll = "href=\"\" th:href=\"@{\'"+ll+"\'}\"";
            }
            newFileContent = newFileContent.replace(l, ll);
        }

        FileOutputStream  fos=new FileOutputStream("L:/GONGSI/project1/src/main/resources/templates/"+fileName);
        OutputStreamWriter oStreamWriter = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
        oStreamWriter.append(newFileContent);
        oStreamWriter.flush();
        oStreamWriter.close();

    }

    private static String readToString(String fileName) {
        String encoding = "UTF-8";
        File file = new File("L:/GONGSI/project1/src/main/resources/templates/" + fileName);
        Long filelength = file.length();
        byte[] filecontent = new byte[filelength.intValue()];
        try {
            FileInputStream in = new FileInputStream(file);
            int flag = in.read(filecontent);
            return new String(filecontent, encoding);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}


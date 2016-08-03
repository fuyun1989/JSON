package com.fuyun.json;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.io.*;

/**
 * Created by Fuyun on 2016/8/3.
 */
public class ReNameByJson {
    public static void main(String[] args) throws FileNotFoundException {
        File root = new File("G:\\慕课网\\cn.com.open.mooc\\video");
        //获取根目录下所有的文件夹数组
        File [] subFileDirectory = root.listFiles();
        for (int i=0;i<subFileDirectory.length;i++){
            File []sunFileDirectory = subFileDirectory[i].listFiles();
            for (int j=0;j<sunFileDirectory.length;j++){
                File file = new File(sunFileDirectory[j].getAbsolutePath()+"\\json.txt");
                File newFileDirectory= new File(root.getAbsolutePath()+"\\"+getName(file));
                //如果文件夹不存在，则创建文件夹
                if (!newFileDirectory.exists()) {
                    newFileDirectory.mkdir();
                    System.out.println("Make dir successfully!");
                }
                File videoFile = new File(sunFileDirectory[j].getAbsolutePath()+"\\"+"down.mp4");
                File newVideoFile = new File(newFileDirectory.getAbsolutePath()+"\\"+sunFileDirectory[j].getName()+"\\"+"down.mp4");
                File parrentDirectory = new File(newFileDirectory.getAbsolutePath()+"\\"+sunFileDirectory[j].getName());
                if (!parrentDirectory.exists()){
                    parrentDirectory.mkdir();
                    System.out.println("make parrent directory!"+parrentDirectory.getAbsolutePath());
                }
                if(videoFile.renameTo(newVideoFile))
                    System.out.println("Move successfully");
            }
        }
    }
    public static String getName(File file){
        String content = getContent(file);
        JSONArray jsonArray = JSONArray.fromObject(content);
        JSONObject jsonObject = JSONObject.fromObject(jsonArray.get(0));
        return jsonObject.get("courseName").toString();
    }
    public static  String getContent(File file){
        Reader reader;
        String content = "[";
        try{
            reader = new InputStreamReader(new FileInputStream(file));
            int n=0;
            while ((n = reader.read())!=-1){
                content += (char)n;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        content+="]";
        return  content;
    }
}

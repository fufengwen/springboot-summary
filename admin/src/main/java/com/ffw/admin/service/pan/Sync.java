package com.ffw.admin.service.pan;

import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description TODO
 * @Author fufengwen
 * @Time 2020/7/28 13:01
 */
@Service
public class Sync {
    public void syncGooglePan(){
        File f1 = null;
        File f2 = null;
        f1 = checkPan("local");
        f2 = checkPan("google");
        Map map1 = new HashMap<>();
        Map map2 = new HashMap<>();
        for(File f:f2.listFiles()){
            map1.put(f.getName(),1);
        }
        for(File f:f1.listFiles()){
            if(map1.get(f.getName()) == null && !(f.getName().contains("downloading"))){
                Thread thread = new Thread(new Runnable() {
                    @SneakyThrows
                    @Override
                    public void run() {
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");
                        String now = dateFormat.format(new Date());
                        int length = f.getName().length();
                        int index = f.getName().lastIndexOf(".");
                        File file = new File("D://task_log//"+f.getName().substring(0,index)+"-上传日志.txt");
                        if(!file.exists()){
                            file.createNewFile();
                        }
                        OutputStream os = new FileOutputStream(file);
                        try{
                            String startMes = "发现"+f.getName()+"在谷歌盘中缺失，正在同步...";
                            System.out.println(startMes);
                            os.write(startMes.getBytes());
                            os.write("\n".getBytes());
                            long startTime = System.currentTimeMillis();
                            File to = new File("G:\\f1187567976@gmail.com\\upload\\"+f.getName());
                            copyFile(f,to,file);
                            long endTime = System.currentTimeMillis();
                            String endMessage = f.getName()+"在谷歌盘中同步成功!耗时"+(endTime - startTime)/1000 +"秒";
                            System.out.println(endMessage);
                            os.write(endMessage.getBytes());
                            os.write("\n".getBytes());
                        }catch (Exception e){
                            String errorMessage = f.getName()+"在谷歌盘中同步失败!失败原因:"+e.getMessage();
                            System.out.println();
                            os.write(errorMessage.getBytes());
                            os.write("\n".getBytes());
                        }finally {
                            os.close();
                        }
                    }
                });
                thread.start();
            }
        }
    }

    public File checkPan(String type){
        System.out.println();
        String panName = "";
        String path = "";
        switch(type){
            case "local":
                panName = "本地盘";
                path = "D:\\upload\\迪迦奥特曼 国日台三语1080P修复版";
                break;
            case "google":
                panName = "谷歌盘";
                path = "G:\\f1187567976@gmail.com\\upload";
                break;
            default:throw new RuntimeException("没找到指定的盘!");
        }
        System.out.println("开始扫描"+panName+"文件....");
        File files = new File(path);
        System.out.println("盘内文件一共"+files.listFiles().length+"个");
        System.out.println("以下是文件名称:");
        int i = 1;
        for (File f: files.listFiles()) {
            System.out.println(i++ +"、" + f.getName());
        }
        return files;
    }

    public boolean valid(String fileName){
        String[] suffix = {".exe",".zip",".rar",".pdf",".txt",".xls",".xlxs",".chm",".xmind",".tar",".flac",".wav",".mp3",".ape"};
        boolean flag = false;
        fileName = fileName.toLowerCase();
        for(String s:suffix){
            if(fileName.endsWith(s)){
                flag = true;
            }
        }
        return flag;
    }

    public void copyFile(File f1,File f2,File f3) throws IOException {
        OutputStream os = null;
        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;
        BufferedInputStream bufferedInputStream = null;
        BufferedOutputStream bufferedOutputStream = null;
        try{
            os = new FileOutputStream(f3);
            fileInputStream = new FileInputStream(f1);
            fileOutputStream = new FileOutputStream(f2);
            bufferedInputStream = new BufferedInputStream(fileInputStream);
            bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
            byte[] by=new byte[1024*1024*150];//byte[]数组的大小，根据复制文件的大小可以调整，1G一下可以5M。1G以上150M，自己多试试
            int len;
            boolean flag=true;
            double begin=bufferedInputStream.available();
            while(flag)
            {
                len=bufferedInputStream.read(by);
                if(len==-1)
                {
                    flag=false;
                    continue;
                }
                bufferedOutputStream.write(by,0,len);
                bufferedOutputStream.flush();
                String mes = "\r                                         \r"+(1-bufferedInputStream.available()/begin)*100+"%";
                os.write(mes.getBytes());
                os.write("\n".getBytes());
                //显示进度，如果文件过大（2G以上，可能一开始会一直显示0.0%，因为算出的数据过小，丢失了，不过还是在复制，要等等
            }
            bufferedOutputStream.close();
            bufferedInputStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            os.close();
            fileInputStream.close();
            fileOutputStream.close();
            bufferedInputStream.close();
            bufferedOutputStream.close();
        }
    }
}

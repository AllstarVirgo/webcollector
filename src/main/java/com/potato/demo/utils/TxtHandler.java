package com.potato.demo.utils;


import java.io.*;

public class TxtHandler {
    private static String txtHandle(String line){
        int len=line.length();
        int start=line.indexOf("【");
        if(start!=-1)return line.substring(0,start-1);
        return line;
    }

    private static void test() {
        String line="1:...群人里所发生的一轮轮波纹的差序。“释名”于沦字下也说“伦也，[水文]相次有伦理也。”潘光旦先生曾说：凡是有“仑”作公分母的意义都相...\t\t  【文件名:\\当代\\CWAC\\ASB0100.txt\t文章标题:\t作者:】";
        String res= txtHandle(line);
        System.out.println(res.trim());
    }

    private static void convert(){
        String path="sample/normaldata/hydrology.txt";
        File file=new File(path);
        if(file.isFile()&&file.exists()){
            try {
                InputStreamReader read=new InputStreamReader(new FileInputStream(file),"gbk");
                BufferedReader bufferedReader=new BufferedReader(read);
                String lineTxt=null;
                int i=0;
                while ((lineTxt=bufferedReader.readLine())!=null){
                    String res=txtHandle(lineTxt);
                    File file1=new File("sample/normaldata/水文语料库/水文/"+i+".txt");
                    OutputStreamWriter writer=new OutputStreamWriter(new FileOutputStream(file1),"utf-8");
                    BufferedWriter bw=new BufferedWriter(writer);
                    bw.write(res);
                    bw.flush();
                    bw.close();
                    i++;
                }
                bufferedReader.close();
                read.close();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        convert();
    }
}

package com.potato.demo.domain;

import cn.edu.hfut.dmic.webcollector.model.Page;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class HuangRiver {
    private String riverName;

    private String localName;

    private double waterLevel;

    private double rateOfFlow;

    private double sandContent;

    private Date currentDate;

    public HuangRiver() {
    }

    public HuangRiver(String riverName, String localName, double waterLevel, double rateOfFlow, double sandContent) {
        this.riverName = riverName;
        this.localName = localName;
        this.waterLevel = waterLevel;
        this.rateOfFlow = rateOfFlow;
        this.sandContent = sandContent;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        currentDate = Date.valueOf(sdf.format(new java.util.Date()));
    }

    public void setRiverName(String riverName) {
        this.riverName = riverName;
    }

    public void setLocalName(String localName) {
        this.localName = localName;
    }

    public void setWaterLevel(double waterLevel) {
        this.waterLevel = waterLevel;
    }

    public void setRateOfFlow(double rateOfFlow) {
        this.rateOfFlow = rateOfFlow;
    }

    public void setSandContent(double sandContent) {
        this.sandContent = sandContent;
    }

    public String getRiverName() {
        return riverName;
    }

    public String getLocalName() {
        return localName;
    }

    public double getWaterLevel() {
        return waterLevel;
    }

    public double getRateOfFlow() {
        return rateOfFlow;
    }

    public double getSandContent() {
        return sandContent;
    }

    public Date getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
    }

    @Override
    public String toString() {
        return "HuangRiver{" +
                "riverName='" + riverName + '\'' +
                ", localName='" + localName + '\'' +
                ", waterLevel=" + waterLevel +
                ", rateOfFlow=" + rateOfFlow +
                ", sandContent=" + sandContent +
                '}';
    }

    public ArrayList<HuangRiver> analysisPage(Page page) {
        ArrayList<HuangRiver>list=new ArrayList<HuangRiver>();

        Elements contents = page.select("tr[align]");

        for (Element element : contents) {
            String content = element.text();
            if (content.contains("河名")) continue;
            String[] strArray = content.split(" ");

            double[] datas = new double[3];
            for (int i = 2; i < strArray.length; i++) {
                //空缺值转为1
                if (strArray[i].equals("-")) datas[i - 2] = -1;
                    //处理含亿的值
                else if (strArray[i].contains("亿")) {
                    String subStr = strArray[i].substring(1, strArray[i].length() - 2);
                    datas[i - 2] = (Double.parseDouble(subStr)) * Math.pow(10, 9);
                } else datas[i - 2] = Double.parseDouble(strArray[i]);
            }

            list.add(new HuangRiver(strArray[0], strArray[1], datas[0], datas[1], datas[2]));
        }
        return list;
    }
}

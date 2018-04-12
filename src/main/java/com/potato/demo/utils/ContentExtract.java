package com.potato.demo.utils;

import com.hankcs.hanlp.corpus.tag.Nature;
import com.hankcs.hanlp.seg.common.Term;
import com.potato.demo.constant.HubeiPlaceNames;
import com.potato.demo.domain.HubeiDayRainfall;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ContentExtract {

    public static HashMap<String, String[]> hashMap = new HashMap<String, String[]>();

    private static void initializeHash() {

        hashMap.put("湖北省", HubeiPlaceNames.cityNames);

        hashMap.put("武汉市", HubeiPlaceNames.countriesInWuhan);

        hashMap.put("十堰市", HubeiPlaceNames.countriesInShiyan);

        hashMap.put("荆州市", HubeiPlaceNames.countriesInJinzhou);

        hashMap.put("荆门市", HubeiPlaceNames.countriesInJinmen);

        hashMap.put("鄂州市", HubeiPlaceNames.countriesInHuangshi);

        hashMap.put("孝感市", HubeiPlaceNames.countriesInXiaogan);

        hashMap.put("黄冈市", HubeiPlaceNames.countriesInHuanggang);

        hashMap.put("黄石市", HubeiPlaceNames.countriesInHuangshi);

        hashMap.put("咸宁市", HubeiPlaceNames.countriesInXianning);

        hashMap.put("随州市", HubeiPlaceNames.countriesInSuizhou);

        hashMap.put("恩施市", HubeiPlaceNames.countriesInEnshi);

        hashMap.put("襄阳市",HubeiPlaceNames.countriesInXiangyang);

        hashMap.put("宜昌市",HubeiPlaceNames.countriesInYichang);
    }

    public static double extractDouble(String string) {
        String regex = "\\d+(\\.)?(\\d+)?";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(string);
        if (m.find()) return Double.parseDouble(m.group());
        else return -1;
    }

    public static boolean extractDate(String string, Date date) {
        String regex = "\\d+年\\d+月\\d+日";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(string);

        if (m.find()) {
            String res = m.group();
            int year = Integer.parseInt(res.substring(0, res.indexOf("年")));
            int month = Integer.parseInt(res.substring(res.indexOf("年") + 1, res.indexOf("月")));
            int day = Integer.parseInt(res.substring(res.indexOf("月") + 1, res.indexOf("日")));

            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month - 1);
            calendar.set(Calendar.DAY_OF_MONTH, day);

            date = new Date(calendar.getTimeInMillis());
            return true;
        } else return false;
    }

    public static void setObjFromList(List<Term> list, HubeiDayRainfall hubeiDayRainfall) {
        if (!list.contains(new Term("降雨量",Nature.n))) return;

        ResultInfo resultInfoInCity = new ResultInfo();

        ResultInfo resultInfoInCountry = new ResultInfo();

        String cityName = null, countryName = null;

        if (!list.contains(new Term("湖北省",Nature.ns))) return;

        else if (containsInArray(hashMap.get("湖北省"), list, resultInfoInCity).isContains()) {
            cityName = resultInfoInCity.getS();
            if (cityName.equals("仙桃市") || cityName.equals("潜江市") || cityName.equals("天门市") || cityName.equals("神农架林区")) {
                countryName = "省直辖市";
            } else if (containsInArray(hashMap.get(cityName), list, resultInfoInCountry).isContains()) {
                countryName = resultInfoInCountry.getS();
            } else return;
        }
        hubeiDayRainfall.setCityName(cityName);

        hubeiDayRainfall.setCountryName(countryName);

        double rainfall=getRainfall(list);

        if(rainfall==-1)return;

        else hubeiDayRainfall.setRainfall(rainfall);

    }

    private static double getRainfall(List<Term> list) {
        int index = list.indexOf(new Term("降雨量",Nature.n));

        double rainfall=-1;
        int add=index;
        //向降雨量之后寻找数字
        while (++add<list.size())if(list.get(add).nature.equals("/m")){
            rainfall=Double.parseDouble(list.get(add).word);
        }

        return rainfall;
    }

    private static ResultInfo containsInArray(String[] array, List<Term> list, ResultInfo resultInfo) {
        resultInfo.setContains(false);
        for (String s :
                array) {
            if (list.contains(new Term(s,Nature.ns))) {
                resultInfo.setContains(true);
                resultInfo.setS(s);
            }
        }
        return resultInfo;
    }

    static class ResultInfo {
        boolean isContains;

        String s;

        public void setContains(boolean contains) {
            isContains = contains;
        }

        public void setS(String s) {
            this.s = s;
        }

        public boolean isContains() {
            return isContains;
        }

        public String getS() {
            return s;
        }
    }
}

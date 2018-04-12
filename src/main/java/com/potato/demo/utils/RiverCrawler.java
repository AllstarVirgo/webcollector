package com.potato.demo.utils;

import cn.edu.hfut.dmic.webcollector.model.CrawlDatum;
import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.plugin.berkeley.BreadthCrawler;
import com.potato.demo.dao.*;
import com.potato.demo.domain.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.text.ParseException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class RiverCrawler extends BreadthCrawler {
    private HuangRiver huangRiver;

    private SongliaoRiver songliaoRiver;

    private SongliaoReservoir songliaoReservoir;

    private HuangRiverDao huangRiverDao;

    private SongliaoRiverDao songliaoRiverDao;

    private SongliaoReserviorDao songliaoReserviorDao;


    private SongliaoRainCondition songliaoRainCondition;

    private DayRainfall dayRainfall;

    private static final int INI_DELAY =24*60*60;

    private static final int PERIOD =24*60*60;


    public RiverCrawler(String crawlPath, boolean autoParse,HuangRiver huangRiver,SongliaoRiver songliaoRiver,
                        HuangRiverDao huangRiverDao,SongliaoRiverDao songliaoRiverDao,SongliaoReserviorDao songliaoReserviorDao,
                        SongliaoReservoir songliaoReservoir,SongliaoRainCondition songliaoRainCondition,
                        DayRainfall dayRainfall) {
        super(crawlPath, autoParse);
        this.huangRiver=huangRiver;
        this.songliaoRiver=songliaoRiver;
        this.huangRiverDao=huangRiverDao;
        this.songliaoRiverDao=songliaoRiverDao;
        this.songliaoReserviorDao=songliaoReserviorDao;
        this.songliaoReservoir=songliaoReservoir;
        this.songliaoRainCondition=songliaoRainCondition;
        this.dayRainfall=dayRainfall;
        //黄河委url
        this.addSeed(new CrawlDatum("http://61.163.88.227:8006/hwsq.aspx").meta("flag", "yellowRiver"));
        //松辽水利url
        this.addSeed(new CrawlDatum("http://www.slwr.gov.cn/swjgzfw/jhsq.asp").meta("flag","songliaoRiver"));
        //松辽水库url
        this.addSeed(new CrawlDatum("http://www.slwr.gov.cn/swjgzfw/sksq.asp").meta("flag","songliaoReservior"));
        //松辽雨水情
        this.addSeed(new CrawlDatum("http://www.slwr.gov.cn/swjgzfw/jhsqysq.asp").meta("flag","songliaoRainCondition"));
        //松辽日累计雨量
        this.addSeed(new CrawlDatum("http://www.slwr.gov.cn/swjgzfw/jsfp.asp").meta("flag","dayRainfall"));
    }

    @Override
    public void visit(Page page, CrawlDatums crawlDatums) {

        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

        HuangRiverDao huangRiverDao = (HuangRiverDao) context.getBean("huangRiverDao");

        SongliaoRiverDao songliaoRiverDao= (SongliaoRiverDao) context.getBean("songliaoRiverDao");

        SongliaoRainConditionDao songliaoRainConditionDao= (SongliaoRainConditionDao) context.getBean("songliaoRainConditionDao");

        DayRainfallDao dayRainfallDao= (DayRainfallDao) context.getBean("dayRainfallDao");
        String flag = page.meta("flag");
        if (flag.equals("yellowRiver")) {
            huangRiverDao.insertList(huangRiver.analysisPage(page));
        }else if(flag.equals("songliaoRiver")){
            try {
                songliaoRiverDao.insertList(songliaoRiver.analysisPage(page));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }else if(flag.equals("songliaoReservior")){
            songliaoReserviorDao.insertList(songliaoReservoir.analysisPage(page));
        }else if(flag.equals("songliaoRainCondition")){
            songliaoRainConditionDao.insertList(songliaoRainCondition.analysisPage(page));
        }else if(flag.equals("dayRainfall")){
            dayRainfallDao.insertList(dayRainfall.analysisPage(page));
        }
    }

    public static void main(String[] args) {
        Runnable runnable=new Runnable() {
            @Override
            public void run() {
                Logger logger=LoggerFactory.getLogger(BreadthCrawler.class);

                ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

                logger.info("开始水利相关网站的抓取");

                RiverCrawler yr = (RiverCrawler) context.getBean("riverCrawler");
                try {
                    yr.start(1);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                logger.info("已完成水利相关网站的抓取");
            }
        };
        ScheduledExecutorService service = Executors
                .newSingleThreadScheduledExecutor();
        // 第二个参数为首次执行的延时时间，第三个参数为定时执行的间隔时间
        service.scheduleAtFixedRate(runnable, INI_DELAY, PERIOD, TimeUnit.SECONDS);

    }
}

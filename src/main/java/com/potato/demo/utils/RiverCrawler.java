package com.potato.demo.utils;

import cn.edu.hfut.dmic.webcollector.model.CrawlDatum;
import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.plugin.berkeley.BreadthCrawler;
import com.potato.demo.dao.HuangRiverDao;
import com.potato.demo.dao.SongliaoReserviorDao;
import com.potato.demo.dao.SongliaoRiverDao;
import com.potato.demo.domain.HuangRiver;
import com.potato.demo.domain.SongliaoReservoir;
import com.potato.demo.domain.SongliaoRiver;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.text.ParseException;


public class RiverCrawler extends BreadthCrawler {
    private HuangRiver huangRiver;

    private SongliaoRiver songliaoRiver;

    private SongliaoReservoir songliaoReservoir;

    private HuangRiverDao huangRiverDao;

    private SongliaoRiverDao songliaoRiverDao;

    private SongliaoReserviorDao songliaoReserviorDao;


    public RiverCrawler(String crawlPath, boolean autoParse,HuangRiver huangRiver,SongliaoRiver songliaoRiver,
                        HuangRiverDao huangRiverDao,SongliaoRiverDao songliaoRiverDao,SongliaoReserviorDao songliaoReserviorDao,
                        SongliaoReservoir songliaoReservoir) {
        super(crawlPath, autoParse);
        this.huangRiver=huangRiver;
        this.songliaoRiver=songliaoRiver;
        this.huangRiverDao=huangRiverDao;
        this.songliaoRiverDao=songliaoRiverDao;
        this.songliaoReserviorDao=songliaoReserviorDao;
        this.songliaoReservoir=songliaoReservoir;
        //黄河委url
        this.addSeed(new CrawlDatum("http://61.163.88.227:8006/hwsq.aspx").meta("flag", "yellowRiver"));
        //松辽水利url
        this.addSeed(new CrawlDatum("http://www.slwr.gov.cn/swjgzfw/jhsq.asp").meta("flag","songliaoRiver"));
        //松辽水库url
        this.addSeed(new CrawlDatum("http://www.slwr.gov.cn/swjgzfw/sksq.asp").meta("flag","songliaoReservior"));
    }

    @Override
    public void visit(Page page, CrawlDatums crawlDatums) {

        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

        HuangRiverDao huangRiverDao = (HuangRiverDao) context.getBean("huangRiverDao");

        SongliaoRiverDao songliaoRiverDao= (SongliaoRiverDao) context.getBean("songliaoRiverDao");

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
        }
    }

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

        RiverCrawler yr = (RiverCrawler) context.getBean("riverCrawler");
        try {
            yr.start(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

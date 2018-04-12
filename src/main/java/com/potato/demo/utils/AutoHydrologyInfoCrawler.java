package com.potato.demo.utils;

import cn.edu.hfut.dmic.contentextractor.ContentExtractor;
import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.plugin.berkeley.BreadthCrawler;
import com.hankcs.hanlp.classification.classifiers.IClassifier;
import com.hankcs.hanlp.classification.classifiers.NaiveBayesClassifier;
import com.hankcs.hanlp.classification.models.NaiveBayesModel;
import com.hankcs.hanlp.corpus.io.IOUtil;
import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.tokenizer.StandardTokenizer;
import com.potato.demo.dao.HubeiDayRainfallDao;
import com.potato.demo.domain.HubeiDayRainfall;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

public class AutoHydrologyInfoCrawler extends BreadthCrawler {
    private HubeiDayRainfallDao hubeiDayRainfallDao;

    private HubeiDayRainfall hubeiDayRainfall;
    /**
     * 搜狗文本分类语料库5个类目，每个类目下1000篇文章，共计5000篇文章
     */
    public static final String CORPUS_FOLDER = "sample/test/搜狗文本分类语料库迷你版";
    /**
     * 模型保存路径
     */
    public static final String MODEL_PATH = "sample/test/classification-model.ser";

    private static Logger logger=Logger.getLogger(AutoHydrologyInfoCrawler.class);

    public AutoHydrologyInfoCrawler(String crawlPath, boolean autoParse,HubeiDayRainfallDao hubeiDayRainfallDao,HubeiDayRainfall hubeiDayRainfall) {
        super(crawlPath, autoParse);
        this.addSeed("https://www.hao123.com/");
        this.hubeiDayRainfallDao=hubeiDayRainfallDao;
        this.hubeiDayRainfall=hubeiDayRainfall;
    }

    @Override
    public void visit(Page page, CrawlDatums next) {
        //提取正文
        String content = null;
        try {
            content = ContentExtractor.getContentByHtml(page.html());
        } catch (Exception e) {
            e.printStackTrace();
        }

        IClassifier classifier = null;
        if (content != null) {
            try {
                classifier = new NaiveBayesClassifier(trainOrLoadModel());
                String res = classifier.classify(content);
                if (res.equals("水文")) {
                    List<Term> terms = StandardTokenizer.segment(res);
                    java.util.Date date=new java.util.Date();
                    if(!ContentExtract.extractDate(content, date))return;
                    hubeiDayRainfall.setRainDate((new Date((date.getTime()))));
                    ContentExtract.setObjFromList(terms,hubeiDayRainfall);
                    //如果各项数据正常，则写入到数据库
                    if(hubeiDayRainfall.notNull()){
                        hubeiDayRainfallDao.insert(hubeiDayRainfall);
                    }
                    //重置为空
                    hubeiDayRainfall.reset();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private static NaiveBayesModel trainOrLoadModel() throws IOException {
        NaiveBayesModel model = (NaiveBayesModel) IOUtil.readObjectFrom(MODEL_PATH);
        if (model != null) return model;

        File corpusFolder = new File(CORPUS_FOLDER);
        if (!corpusFolder.exists() || !corpusFolder.isDirectory()) {
            System.err.println("没有文本分类语料，请阅读IClassifier.train(java.lang.String)中定义的语料格式与语料下载：" +
                    "https://github.com/hankcs/HanLP/wiki/%E6%96%87%E6%9C%AC%E5%88%86%E7%B1%BB%E4%B8%8E%E6%83%85%E6%84%9F%E5%88%86%E6%9E%90");
            System.exit(1);
        }

        IClassifier classifier = new NaiveBayesClassifier(); // 创建分类器，更高级的功能请参考IClassifier的接口定义
        classifier.train(CORPUS_FOLDER);                     // 训练后的模型支持持久化，下次就不必训练了
        model = (NaiveBayesModel) classifier.getModel();
        IOUtil.saveObjectTo(model, MODEL_PATH);
        return model;
    }

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

        AutoHydrologyInfoCrawler autoHydrologyInfoCrawler= (AutoHydrologyInfoCrawler) context.getBean("autoHydrologyInfoCrawler");

        try {
            autoHydrologyInfoCrawler.start(10);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

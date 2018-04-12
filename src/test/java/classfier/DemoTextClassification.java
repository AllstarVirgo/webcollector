package classfier;

/*
 * <summary></summary>
 * <author>He Han</author>
 * <email>me@hankcs.com</email>
 * <create-date>16/2/20 AM11:46</create-date>
 *
 * <copyright file="DemoAtFirstSight.java" company="码农场">
 * Copyright (c) 2008-2016, 码农场. All Right Reserved, http://www.hankcs.com/
 * This source is subject to Hankcs. Please contact Hankcs to get more information.
 * </copyright>
 */


import com.hankcs.hanlp.classification.classifiers.IClassifier;
import com.hankcs.hanlp.classification.classifiers.NaiveBayesClassifier;
import com.hankcs.hanlp.classification.models.NaiveBayesModel;
import com.hankcs.hanlp.corpus.io.IOUtil;

import java.io.File;
import java.io.IOException;

/**
 * 第一个demo,演示文本分类最基本的调用方式
 *
 * @author hankcs
 */
public class DemoTextClassification
{
    /**
     * 搜狗文本分类语料库5个类目，每个类目下1000篇文章，共计5000篇文章
     */
    public static final String CORPUS_FOLDER = "sample/test/搜狗文本分类语料库迷你版";
    /**
     * 模型保存路径
     */
    public static final String MODEL_PATH = "sample/test/classification-model.ser";

    public static void main(String[] args) throws IOException
    {
        IClassifier classifier = new NaiveBayesClassifier(trainOrLoadModel());
        predict(classifier, "中国气象报通信员蒋鑫淼报道 为迎接2016年世界气象日的到来，3月22日，湖北省秭归县气象局工作人员走进秭归县三峡工程希望小学，开展气象科普进校园活动。\n" +
                "\n" +
                "　　围绕“直面更热、更旱、更涝的未来”的气象日主题，工作人员组织学生观看了《2016年世界气象日宣传片》和《风雨记录：1985年秭归新滩大滑坡》科教片；向同学们发放《秭归县气象灾害防御指南》、《气象灾害防御知识图解》等气象科普资料，并讲解了应对高温、干旱、暴雨、雷电等气象灾害的防范措施。随后，在工作人员和老师们的指导下，同学们分组进行气象观测站、多普雷天气雷达楼、气象应急指挥车、人工影响天气作业车四个模型的拼装，在实践中同学们增加了对气象仪器设备和气象知识的了解，现场气氛十分活跃。\n" +
                "\n" +
                "　　据悉，该学校3月25日还将组织学生深入气象局、气象观测站进行实地参观，让同学们对气象及气象灾害防御知识有更加全面的认识。");
        predict(classifier, "英国造航母耗时8年仍未服役 被中国速度远远甩在身后");
        predict(classifier, "研究生考录模式亟待进一步专业化");
        predict(classifier, "如果真想用食物解压,建议可以食用燕麦");
        predict(classifier, "通用及其部分竞争对手目前正在考虑解决库存问题");
    }

    private static void predict(IClassifier classifier, String text)
    {
        System.out.printf("《%s》 属于分类 【%s】\n", text, classifier.classify(text));
    }

    private static NaiveBayesModel trainOrLoadModel() throws IOException
    {
        NaiveBayesModel model = (NaiveBayesModel) IOUtil.readObjectFrom(MODEL_PATH);
        if (model != null) return model;

        File corpusFolder = new File(CORPUS_FOLDER);
        if (!corpusFolder.exists() || !corpusFolder.isDirectory())
        {
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
}

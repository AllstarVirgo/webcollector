import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.corpus.tag.Nature;
import com.hankcs.hanlp.seg.Segment;
import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.tokenizer.NLPTokenizer;
import com.hankcs.hanlp.tokenizer.StandardTokenizer;

import java.util.List;

public class FenciTest {
    public static void main(String[] args) {
        List<Term> termList = StandardTokenizer.segment("湖北省年降雨量空间分布数据是地理国情监测云平台推出的气象气候类数据产品之一");

        Term term=new Term("武汉市",Nature.ns);

        int i=termList.indexOf(term);

        System.out.println(i);
        System.out.println(termList);}
}

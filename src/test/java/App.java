import com.potato.demo.dao.HuangRiverDao;
import com.potato.demo.domain.HuangRiver;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main(String[] args) {
        ApplicationContext context=new ClassPathXmlApplicationContext("beans.xml");

        HuangRiverDao huangRiverDao= (HuangRiverDao) context.getBean("huangRiverDao");
        HuangRiver huangRiver=new HuangRiver("黄河","包头",1003.58f,420f,0f);
        huangRiverDao.insert(huangRiver);
        HuangRiver huangRiver1=huangRiverDao.findByStationName("包头");
        System.out.println(huangRiver1);
    }
}

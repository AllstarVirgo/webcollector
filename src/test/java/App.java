import com.potato.demo.dao.HuangRiverDao;
import com.potato.demo.dao.HubeiDayRainfallDao;
import com.potato.demo.dao.UserDao;
import com.potato.demo.domain.HuangRiver;
import com.potato.demo.domain.HubeiDayRainfall;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.Date;

public class App {
    public static void main(String[] args) {
        Logger logger =Logger.getLogger(App.class);
        logger.info("初始化容器");
        ApplicationContext context=new ClassPathXmlApplicationContext("beans.xml");

//        HuangRiverDao huangRiverDao= (HuangRiverDao) context.getBean("huangRiverDao");
//        HuangRiver huangRiver=new HuangRiver("黄河","包头",1003.58f,420f,0f);
//        huangRiverDao.insert(huangRiver);
//        HuangRiver huangRiver1=huangRiverDao.findByStationName("包头");
//        System.out.println(huangRiver1);
//        HubeiDayRainfallDao hubeiDayRainfallDao= (HubeiDayRainfallDao) context.getBean("hubeiDayRainfallDao");
//
//        HubeiDayRainfall hubeiDayRainfall=new HubeiDayRainfall("湖北省","宜昌市","秭归县",13.1,new Date(new java.util.Date().getTime()));
//
//        hubeiDayRainfallDao.insert(hubeiDayRainfall);
//
//        logger.debug("插入数据成功");
        UserDao dao= (UserDao) context.getBean("userDao");
        System.out.println(dao.selectByName("potato"));
    }
}

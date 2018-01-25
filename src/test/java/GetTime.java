import java.sql.Timestamp;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GetTime {
    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd hh:mm");

        Date date= null;
        try {
            date = sdf.parse("2017/11/12 8:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        java.sql.Timestamp date1=new Timestamp(date.getTime());
        System.out.println(date1);
    }
}

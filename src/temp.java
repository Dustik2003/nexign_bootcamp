import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class temp {
    public static void main(String[] args) throws ParseException {
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(Main.convertDateTime(20230725141448L));
        System.out.println(df.parse("2023-07-25 14:14:48"));
    }






}

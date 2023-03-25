import java.text.ParseException;
import java.text.SimpleDateFormat;

public class MinByMin implements Tarifing{

    @Override
    public void tarif(CallsArr callsArr) throws ParseException {
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for(Call call:callsArr){
            call.duration=(df.parse(Main.convertDateTime(call.endDate)).getTime()-df.parse(Main.convertDateTime(call.startDate)).getTime())/1000;
            call.cost=(call.duration/60d*1.5);
            call.cost=((int)(100*call.cost))/100d;
            callsArr.sumCost +=call.cost;
        }

        callsArr.sumCost=((int)(100* callsArr.sumCost))/100d;
    }
}

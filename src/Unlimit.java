import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Unlimit implements Tarifing {
    long limit=18000;

    @Override
    public void tarif(CallsArr callsArr) throws ParseException {
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for(Call call:callsArr){
            call.duration=(df.parse(Main.convertDateTime(call.endDate)).getTime()-df.parse(Main.convertDateTime(call.startDate)).getTime())/1000;
            if(limit>call.duration) {
            limit-=call.duration;
            call.cost=0;
            continue;
            }
            else if(call.duration>limit && limit!=0){
                call.cost=(call.duration-limit)/60d;
                limit=0;
            }
            call.cost=((int)(100*call.cost))/100d;
            callsArr.sumCost +=call.cost;
        }
        callsArr.sumCost +=100;

        callsArr.sumCost=((int)(100* callsArr.sumCost))/100d;
    }
}

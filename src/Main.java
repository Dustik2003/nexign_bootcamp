import java.io.*;
import java.text.ParseException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    static HashMap<Long, CallsArr> calls=new HashMap<>();
    public static void main(String[] args) throws IOException, ParseException {
        Scanner cin=new Scanner(new File("cdr.txt"));
        while(cin.hasNextLine()) {
            String[] str= cin.nextLine().split(",");
            Long num=Long.parseLong(str[1].trim());
            int type=Integer.parseInt(str[0].trim());
            long startDate=Long.parseLong(str[2].trim()),endDate=Long.parseLong(str[3].trim());
            if(!calls.containsKey(num)){
                calls.put(num,new CallsArr(Integer.parseInt(str[4].trim())));
            }
            calls.get(num).add(new Call(type,startDate,endDate));
        }
        for(Long num:calls.keySet()){
            calls.replace(num,  new CallsArr(calls.get(num).stream().sorted(Comparator.comparing(d -> d.startDate)).collect(Collectors.toList()),calls.get(num).tarif));
            System.out.println(calls.get(num).tarif);

            switch (calls.get(num).tarif) {
                case (3) -> {
                    new MinByMin().tarif(calls.get(num));
                }
                case (6) -> {
                    new Unlimit().tarif(calls.get(num));
                }
                case (11) -> {
                    new Simple().tarif(calls.get(num));
                }
            }
            generateReport(num);
        }


    }



    static String convertDate(long date){
        String str=date+"";
        return str.substring(0, 4) + "-" + str.substring(4, 6) + "-" + str.substring(6, 8);
    }
    static String convertTime(long date){
        String str=date+"";
        return str.substring(8, 10) + ":" + str.substring(10, 12) + ":" + str.substring(12, 14);
    }
    static String convertDateTime(long date){
        return convertDate(date)+" "+convertTime(date);
    }


    static void generateReport(Long num) throws IOException {
        CallsArr callsArr=calls.get(num);
        OutputStreamWriter file=new OutputStreamWriter(new FileOutputStream("Reports/"+num+".txt"));
        file.write("Tariff index: "+ (callsArr.tarif>10?callsArr.tarif:"0"+callsArr.tarif)+"\n" +
                "----------------------------------------------------------------------------\n" +
                "Report for phone number "+num+":\n" +
                "----------------------------------------------------------------------------\n" +
                "| Call Type |   Start Time        |     End Time        | Duration | Cost  |\n" +
                "----------------------------------------------------------------------------\n");
        for(Call call:callsArr){
            file.write("|     0"+ call.type+"    | "+convertDateTime(call.startDate)+" | "+convertDateTime(call.endDate)+" | "+timeToStr(call.duration)+" |  "+call.cost+" |\n");

        }

        file.write("----------------------------------------------------------------------------\n" +
                "|                                           Total Cost: |     "+callsArr.sumCost +" rubles |\n" +
                "----------------------------------------------------------------------------");

        file.flush();

    }




    static String timeToStr(long duration){
        String dur="";
        long tmp=duration/3600;
        dur+=(tmp>9?tmp:"0"+tmp)+":";
        tmp=duration%3600/60;
        dur+=(tmp>9?tmp:"0"+tmp ) +":";
        tmp=duration%60;
        dur+=tmp>9?tmp:"0"+tmp;
        return dur;
    }



}

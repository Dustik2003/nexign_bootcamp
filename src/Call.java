import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class Call {
    int type;
    Long startDate;
    Long endDate;
    long duration;
    double cost;


    public Call(int type, Long startDate, Long endDate) {
        this.type = type;
        this.startDate = startDate;
        this.endDate = endDate;
    }


    @Override
    public String toString() {
        return "Call{" +
                "type=" + type +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", duration=" + duration +
                ", cost=" + cost +
                '}';
    }




}

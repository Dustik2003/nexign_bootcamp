import java.util.ArrayList;
import java.util.Collection;

public class CallsArr extends ArrayList<Call> {
    int tarif;
    double sumCost =0;

    public CallsArr(int tarif) {
        this.tarif = tarif;
    }

    public CallsArr(Collection<? extends Call> c, int tarif) {
        super(c);
        this.tarif = tarif;
    }

}

package proyecto;

import java.util.List;

public class ResultDistr {
    private List<Almacen> result;
    private int totalDistance;

    public ResultDistr(){
        
    }
    public ResultDistr(List<Almacen> result, int totalDistance) {
        this.result = result;
        this.totalDistance = totalDistance;
    }

    public List<Almacen> getResult() {
        return result;
    }

    public void setResult(List<Almacen> result) {
        this.result = result;
    }

    public int getTotalDistance() {
        return totalDistance;
    }

    public void setTotalDistance(int totalDistance) {
        this.totalDistance = totalDistance;
    }

    @Override
    public String toString() {
        return "ResultDistr{" +
                "result=" + result +
                ", totalDistance=" + totalDistance +
                '}';
    }
}

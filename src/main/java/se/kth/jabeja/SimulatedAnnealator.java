package se.kth.jabeja;
import se.kth.jabeja.Node;
import static java.lang.Math.exp;

public class SimulatedAnnealator {
    float Temp;
    //float delta;
    float alpha;

    public SimulatedAnnealator(float temp) {
        Temp = temp;
        alpha = 0.9f;
    }

    public float getAlpha() {
        return alpha;
    }

    public void setAlpha(float alpha) {
        this.alpha = alpha;
    }

    public float getTemp() {
        return Temp;
    }

    public void setTemp(float temp) {
        Temp = temp;
    }

    public float updateTemp(){
        if (Temp>0.00001f){
            Temp*=alpha;
        }
        //Temp*=alpha;
        return Temp;
    }

    public double acceptance_probability(double old_c, double new_c) {
        double ap = exp((new_c-old_c)/Temp);
        if (ap>1) return 1;
        else return ap;
    }
}

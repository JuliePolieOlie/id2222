package se.kth.jabeja;
import se.kth.jabeja.Node;
import static java.lang.Math.exp;

public class SimulatedAnnealator {
    float Temp;
    //float delta;
    float alpha;
    private int rounds;           // 当前轮数
    private final int MAX_ROUNDS_BEFORE_RESET = 400;  // 温度重置间隔
    private final float MIN_TEMP = 0.00001f;          // 最小温度
    private final float MAX_TEMP = 1.0f;              // 最大温度

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
    public float updateTemp2() {
        if (Temp > MIN_TEMP) {
            Temp *= alpha;  // 降温
        } else {
            Temp = MIN_TEMP;  // 确保温度不低于最小值
        }

        rounds++;  // 记录当前轮数

        // 每 MAX_ROUNDS_BEFORE_RESET 轮重置温度
        if (rounds >= MAX_ROUNDS_BEFORE_RESET) {
            Temp = MAX_TEMP;  // 重置温度
            rounds = 0;       // 重置轮数
        }

        // 调试日志
        System.out.println("Updated Temperature: " + Temp);
        return Temp;
    }
    public float updateTemp(){
        if (Temp>0.00001f){
            Temp*=alpha;
        }
        //Temp*=alpha;
        return Temp;
    }
    public double acceptance_probability2(double old_c, double new_c) {
        double ap = exp((new_c - old_c) / Math.max(Temp, MIN_TEMP));
        return Math.min(1.0, ap);  // 确保概率在 [0, 1] 内
    }

    public double acceptance_probability(double old_c, double new_c) {
        double ap = exp((new_c-old_c)/Temp);
        if (ap>1) return 1;
        else return ap;
    }
}

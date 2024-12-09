package se.kth.jabeja;
import se.kth.jabeja.Node;
import static java.lang.Math.exp;
import static java.lang.Math.log; 

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
    
    //task2.2
    public float updateTemp() {
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
    
    // original
    public float updateTemp2(){
        if (Temp>0.00001f){
            Temp*=alpha;
        }
        //Temp*=alpha;
        return Temp;
    }

    //optional1 
    /**
     * 指数退火接受概率公式work
     */
    public double acceptance_probability1(double old_c, double new_c) {
        double deltaCost = new_c - old_c;
        double expValue = -deltaCost / (Temp * log(1 + rounds + 1));
        double prob = 1 / (1 + exp(expValue));
        return Math.min(1.0, prob);
    }
    //optional2
        /**
     * 对数缩放退火公式not work, worse
     */
    public double acceptance_probability3(double old_c, double new_c) {
        double deltaCost = new_c - old_c;
        double ap = exp(-deltaCost / (Temp * log(rounds + 2)));
        return Math.min(1.0, ap);
    }
    //optional3
        /**
     *Sigmoid 函数公式, work
     */
    public double acceptance_probability4(double old_c, double new_c) {
        double deltaCost = new_c - old_c;
        double expValue = -deltaCost / (Temp * log(1 + rounds + 1));
        double prob = 1 / (1 + exp(expValue));
        //System.out.println("Sigmoid Acceptance Probability: " + prob);
        return Math.min(1.0, prob);
    }
    //optional4
        /**
     * does not work, worse
     */
    public double acceptance_probability5(double old_c, double new_c) {
        double deltaCost = new_c - old_c;
        double scalingFactor = 1 + (rounds / 100.0);  // 自适应缩放因子
        double prob = exp(-deltaCost / (Temp * scalingFactor));
        //System.out.println("Adaptive Acceptance Probability: " + prob);
        return Math.min(1.0, prob);
    }
    

    //original
    public double acceptance_probability(double old_c, double new_c) {
        double ap = exp((new_c-old_c)/Temp);
        if (ap>1) return 1;
        else return ap;
    }
}

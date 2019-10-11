package strategy;

/**
 *  策略模式
 *  1，策略(Strategy)：策略是一个接口，该接口定义若干个算法标示，即定义了若干个抽象方法
 *  2，具体策略(ConcreteStrategy):具体策略是实现策略接口的类，具体策略实现策略接口的类，即给出算法标示的具体算法
 *  3，上下文(Context):上下文是依赖于策略接口的类，即上下文包含有策略声明的变量。上下文提供一个方法，该方法委托
 *  策略变量调用具体策略实现的策略接口中的方法
 */

import java.util.Arrays;

/**
 * 策略
 */
interface ComputableStrategy{
    public abstract double computeScore(double[] a);
}

/**
 * 具体策略
 */
class StrategyOne implements ComputableStrategy{
    /**
     * 返回代数平均值
     * @param a
     * @return
     */
    @Override
    public double computeScore(double[] a) {
        double score = 0, sum = 0;
        for (int i=0; i<a.length; i++){
            sum += a[i];
        }
        score = sum / a.length;
        return score;
    }
}

class StrategyTwo implements ComputableStrategy{
    /**
     * 返回集合平均值
     * @param a
     * @return
     */
    @Override
    public double computeScore(double[] a) {
        double socre = 0, multi = 1;
        for (int i=0; i<a.length; i++){
            socre *= a[i];
        }
        socre = Math.pow(multi, 1.0/a.length);
        return socre;
    }
}

class StrategyThree implements ComputableStrategy{
    /**
     * 去掉最大值和去掉最小值的代数平均值
     * @param a
     * @return
     */
    @Override
    public double computeScore(double[] a) {
        if (a.length < 2)
            return 0;
        double score = 0, sum = 0;
        Arrays.sort(a);
        for (int i=1; i<a.length-1; i++){
            sum += a[i];
        }
        score = sum / (a.length - 2);
        return score;
    }
}

/**
 * 上下文
 */
class GymnasticsGame{
    ComputableStrategy strategy;
    public void setStrategy(ComputableStrategy strategy){
        this.strategy = strategy;
    }
    public double getPersonScore(double[] a){
        if (strategy != null)
            return strategy.computeScore(a);
        return 0;
    }
}

class Person{
    String name;
    double score;

    public Person() {
    }

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
}

public class StrategyPattern {
    public static void main(String[] args) {
        GymnasticsGame game = new GymnasticsGame();//上下文对象
        game.setStrategy(new StrategyOne());//使用策略一
        Person jack = new Person();
        jack.setName("jack");
        double[] a = new double[]{
                4.34, 23.234, 45.343, 12.43, 56.23
        };
        jack.setScore(game.getPersonScore(a));
        System.out.println(jack.getScore());

        game.setStrategy(new StrategyTwo());
        Person luse = new Person();
        luse.setName("luse");
        double[] b = new double[]{
                34.54, 23.434, 34.23, 12.333
        };
        luse.setScore(game.getPersonScore(b));
        System.out.println(luse.getScore());
    }
}

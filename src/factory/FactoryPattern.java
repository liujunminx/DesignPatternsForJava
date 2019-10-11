package factory;

/**
 * 工厂方法模式
 * 1，抽象产品(Product):抽象类或接口，负责定义具体产品必须实现的方法
 * 2，具体产品(ConcreteProduct):具体产品是一个类，如果Product是一个抽象类，那么具体产品是Product的子类
 * 如果Product是一个接口，那么具体产品是实现Product接口的类
 * 3，构造者(Creator):一个接口或者抽象类，构造者负责定义一个称为工厂方法的抽象方法，该方法返回一个具体产品类的实例
 * 4，具体构造者(ConcreteCreator):如果构造者是抽象类，具体构造者是实现构造者的子类，如果构造者是接口，那么具体构造者是实现
 * 构造者的类。具体构造者重写工厂方法并返回一个具体产品实例
 */

/**
 * 抽象产品，笔芯类，属性颜色，方法写字
 */
abstract class PenCore{
    String color;
    abstract void writeWord(String s);
}

/**
 * 具体产品，三种不同颜色的笔
 */
class RedPenCore extends PenCore{
    RedPenCore(){
        color = "红色";
    }

    @Override
    void writeWord(String s) {
        System.out.println("写出" + color + "的字： " + s);
    }
}

class BluePenCore extends PenCore{
    BluePenCore(){
        color = "蓝色";
    }

    @Override
    void writeWord(String s) {
        System.out.println("写出" + color + "的字： " + s);
    }
}

class BlackPenCore extends PenCore{
    BlackPenCore(){
        color = "黑色";
    }

    @Override
    void writeWord(String s) {
        System.out.println("写出" + color + "的字： " + s);
    }
}

/**
 * 构造者，圆珠笔
 */
abstract class BallPen{
    BallPen(){
        System.out.println("生产了一只装有" + getPenCore().color + "笔芯的圆珠笔");
    }
    public abstract PenCore getPenCore();//工厂方法
}

/**
 * 具体构造者
 */
class RedBallPen extends BallPen{
    @Override
    public PenCore getPenCore() {
        return new RedPenCore();
    }
}

class BlueBallPen extends BallPen{
    @Override
    public PenCore getPenCore() {
        return new BluePenCore();
    }
}

class BlackBallPen extends BallPen{
    @Override
    public PenCore getPenCore() {
        return new BlackPenCore();
    }
}

public class FactoryPattern {
    public static void main(String[] args) {
        PenCore penCore;
        BallPen ballPen = new BlueBallPen();
        penCore = ballPen.getPenCore();
        penCore.writeWord("你好，很高心认识你");
        ballPen = new RedBallPen();
        penCore = ballPen.getPenCore();
        penCore.writeWord("How are you");
        ballPen = new BlackBallPen();
        penCore = ballPen.getPenCore();
        penCore.writeWord("Nice to meet you");
    }
}

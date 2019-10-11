package adapter;

/**
 *  适配器模式
 *  １，目标(Target):目标是一个接口，该接口是客户想使用的接口
 *  ２，被适配者(Adaptee):被适配者是一个已经存在的接口或抽象类，这个接口或抽象类需要适配。
 *  ３，适配器(Adapter):适配器是一个类，该类实现了目标接口并包含有被适配者的引用，即适配器的职责是
 *  对被适配者接口(抽象类)与目标接口进行适配
 */

/**
 * 问题描述：
 * 用户已有一个两相的插座，但最近用户又有了一个心得三相插座，用户现有一台洗衣机和一台电视机，洗衣机配三相插座，电视机配两相插座
 * 现在用户想用新的三相插座可以接通电视机和洗衣机
 */

/**
 * 目标：三相插座
 */
interface ThreeElectricOutlet{
    public abstract void connectElectricCurrent();
}

/**
 * 被适配者：两相插座
 */
interface TwoElectricOutlet{
    public abstract void connectElectricCurrent();
}

class ThreeElectricAdapter implements ThreeElectricOutlet{
    TwoElectricOutlet outlet;
    ThreeElectricAdapter(TwoElectricOutlet outlet){
        this.outlet = outlet;
    }

    @Override
    public void connectElectricCurrent() {
        outlet.connectElectricCurrent();
    }
}

/**
 * 洗衣机使用三相插座
 */
class Wash implements ThreeElectricOutlet{
    String name;
    Wash(){
        name = "海尔洗衣机";
    }

    Wash(String s){
        name = s;
    }

    @Override
    public void connectElectricCurrent() {
        turnOn();
    }
    public void turnOn(){
        System.out.println(name + "开始洗衣服");
    }
}

/**
 * 电视机使用两相插座
 */
class TV implements TwoElectricOutlet{
    String name;
    TV(){
        name = "智能电视机";
    }
    TV(String s){
        name = s;
    }

    @Override
    public void connectElectricCurrent() {
        turnOn();
    }
    public void turnOn(){
        System.out.println(name + "开始播放节目");
    }
}

/**
 * 适配器
 */
class ThreeAndTwoElectricAdapter implements ThreeElectricOutlet,TwoElectricOutlet{
    TwoElectricOutlet twoElectricOutlet;
    ThreeElectricOutlet threeElectricOutlet;
    ThreeAndTwoElectricAdapter(TwoElectricOutlet twoElectricOutlet,
                               ThreeElectricOutlet threeElectricOutlet){
        this.threeElectricOutlet = threeElectricOutlet;
        this.twoElectricOutlet = twoElectricOutlet;
    }

    @Override
    public void connectElectricCurrent() {
        if (this instanceof ThreeElectricOutlet)
            twoElectricOutlet.connectElectricCurrent();//twoElectricOutlet是被适配者
        if (this instanceof TwoElectricOutlet)
            threeElectricOutlet.connectElectricCurrent();
    }
}

public class AdapterPattern {
    public static void main(String[] args) {
        ThreeElectricOutlet outlet;//目标接口：三相接口
        Wash wash = new Wash();//洗衣
        outlet = wash;//接口接在三相插座上
        System.out.println("使用三相插座接通电流");
        outlet.connectElectricCurrent();
        TV tv = new TV();
        ThreeElectricAdapter adapter = new ThreeElectricAdapter(tv);//插座接在两相插座上
        outlet = adapter;
        System.out.println("使用三相插座接通电流");
        outlet.connectElectricCurrent();
    }
}

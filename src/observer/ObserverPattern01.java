package observer;

/**
 * 拉数据的方式，拉数据方式是指具体主题不将变化后的主题交给具体观察者，而是提供了这些数据的方法，具体观察者在得到通知后
 * 可以调用具体主题提供的方法得到数据（观察者自己把数据拉过来），但需要自己判断数据是否发生了变化。
 * 当具体主题不知道具体观察者是否需要这次变化后的数据时往往采用拉数据的方式
 */

import java.util.ArrayList;

/**
 * 主题
 */
interface Subject01{
    public void addObserver(Observer01 o);
    public void deleteObserver(Observer01 o);
    public void notifyObservers();
}

/**
 * 观察者
 */
interface Observer01{
    public void update();
}

class ShopSubject implements Subject01{
    String goodsName;
    double oldPrice, newPrice;
    ArrayList<Observer01> customerList;
    ShopSubject(){
        customerList = new ArrayList<>();
    }

    @Override
    public void addObserver(Observer01 o) {
        if (!customerList.contains(o))
            customerList.add(o);
    }

    @Override
    public void deleteObserver(Observer01 o) {
        if (customerList.contains(o))
            customerList.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (int i=0; i<customerList.size(); i++){
            Observer01 observer01 = customerList.get(i);
            observer01.update();
        }
    }
    public void setDiscountGoods(String goodsName, double oldPrice, double newPrice){
        this.goodsName = goodsName;
        this.oldPrice = oldPrice;
        this.newPrice = newPrice;
        notifyObservers();
    }

    public String getGoodsName() {
        return goodsName;
    }

    public double getNewPrice() {
        return newPrice;
    }

    public double getOldPrice() {
        return oldPrice;
    }
}

/**
 * 用户一只关心打折商品的名称
 * 用户二只关心打折商品的前后价格
 */
class CustomerOne implements Observer01{
    Subject01 subject01;
    String goodsName, personName;
    CustomerOne(Subject01 subject01, String personName){
        this.subject01 = subject01;
        this.personName = personName;
        subject01.addObserver(this);
    }

    @Override
    public void update() {
        if (subject01 instanceof ShopSubject){
            goodsName = ((ShopSubject)subject01).getGoodsName();
            System.out.println(personName + "只对打折商品的名称感兴趣");
            System.out.println("打折的商品是： " + goodsName);
        }
    }
}

class CustomerTwo implements Observer01{
    Subject01 subject01;
    String personName;
    double oldPrice, newPrice;
    CustomerTwo(Subject01 subject01, String personName){
        this.subject01 = subject01;
        this.personName = personName;
        subject01.addObserver(this);
    }

    @Override
    public void update() {
        if (subject01 instanceof ShopSubject){
            oldPrice = ((ShopSubject)subject01).getOldPrice();
            newPrice = ((ShopSubject)subject01).getNewPrice();
            System.out.println(personName + "只对打折商品的前后价格感兴趣");
            System.out.println("打折前的价格是： " + newPrice + " 打折后是： " + oldPrice);
        }
    }
}

public class ObserverPattern01{
    public static void main(String[] args) {
        ShopSubject shop = new ShopSubject();
        CustomerOne boy = new CustomerOne(shop, "boy");
        CustomerTwo girl = new CustomerTwo(shop, "girl");
        shop.setDiscountGoods("Mac笔记本电脑",19998.99, 100.00);
        shop.setDiscountGoods("别无所求",100,0);
    }
}
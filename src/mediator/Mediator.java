package mediator;

/**
 * 中介者模式：
 * 1，中介者(Mediator):中介者是一个接口，该接口定义了同事(Colleague)对象之间进行通信的方法
 * 2，具体中介者(ConcreteMediator):具体中介者是实现中介者接口的类，具体中介者需要包含所有具体同事
 * (ConcreteColleague)的引用，并通过中介者接口中的方法来满足具体同事之间的通信请求
 * 3，同事(Colleague):一个接口，规定了具体同事需要实现的方法
 * 4，具体同事(ConcreteColleague):实现同事接口的类，具体同事需要包含具体中介者的引用，一个具体同事
 * 需要和其他具体同事交互时，只需将自己的请求通知给他所包含的具体中介者的引用
 */

/**
 * 同事
 */
interface Colleague{
    public void giveMess(String[] mess);
    public void receiverMess(String mess);
    public void setName(String name);
    public String getName();
}

/**
 * 只需要一个中介者，所以不需要一个中介者接口
 */
class ConcreteMediator{
    ColleagueA colleagueA;
    ColleagueB colleagueB;
    ColleagueC colleagueC;
    public void registerColleagueA(ColleagueA colleagueA){
        this.colleagueA = colleagueA;
    }
    public void registerColleagueB(ColleagueB colleagueB){
        this.colleagueB = colleagueB;
    }
    public void registerColleagueC(ColleagueC colleagueC){
        this.colleagueC = colleagueC;
    }
    public void deliverMess(Colleague colleague, String[] mess){
        if (colleague == colleagueA){
            if (mess.length >= 2){
                colleagueB.receiverMess(colleague.getName() + mess[0]);
                colleagueC.receiverMess(colleague.getName() + mess[1]);
            }
        }
        else if (colleague == colleagueB){
            if (mess.length >= 2){
                colleagueA.receiverMess(colleague.getName() + mess[0]);
                colleagueC.receiverMess(colleague.getName() + mess[1]);
            }
        }
        else if (colleague == colleagueC){
            if (mess.length >= 2){
                colleagueA.receiverMess(colleague.getName() + mess[0]);
                colleagueB.receiverMess(colleague.getName() + mess[1]);
            }
        }
    }
}

/**
 * 具体同事
 */
class ColleagueA implements Colleague{
    ConcreteMediator mediator;
    String name;
    ColleagueA(ConcreteMediator mediator){
        this.mediator = mediator;
        mediator.registerColleagueA(this);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void giveMess(String[] mess) {
        mediator.deliverMess(this,mess);
    }

    @Override
    public void receiverMess(String mess) {
        System.out.println(name + "收到的信息：");
        System.out.println("\t" + mess);
    }
}
class ColleagueB implements Colleague{
    ConcreteMediator mediator;
    String name;
    ColleagueB(ConcreteMediator mediator){
        this.mediator = mediator;
        mediator.registerColleagueB(this);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void giveMess(String[] mess) {
        mediator.deliverMess(this, mess);
    }

    @Override
    public void receiverMess(String mess) {
        System.out.println(name + "收到的信息:");
        System.out.println("\t" + mess);
    }
}
class ColleagueC implements Colleague{
    ConcreteMediator mediator;
    String name;
    ColleagueC(ConcreteMediator mediator){
        this.mediator = mediator;
        mediator.registerColleagueC(this);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void giveMess(String[] mess) {
        mediator.deliverMess(this, mess);
    }

    @Override
    public void receiverMess(String mess) {
        System.out.println(name + "收到的信息：");
        System.out.println("\t" + mess);
    }
}
public class Mediator {
    public static void main(String[] args) {
        //commit error
        ConcreteMediator mediator = new ConcreteMediator();
        ColleagueA colleagueA = new ColleagueA(mediator);
        ColleagueB colleagueB = new ColleagueB(mediator);
        ColleagueC colleagueC = new ColleagueC(mediator);
        colleagueA.setName("a国");
        colleagueB.setName("b国");
        colleagueC.setName("c国");
        String[] messA = {"要求归还曾被抢夺的100斤土豆", "要求归还曾被抢夺的20头牛"};
        colleagueA.giveMess(messA);
        String[] messB = {"要求归还曾被抢夺的10只公鸡", "要求归还曾被抢夺的15匹马"};
        colleagueB.giveMess(messB);
        String[] messC = {"要求归还曾被抢夺的300斤小麦", "要求归还曾被抢夺的50头驴"};
        colleagueC.giveMess(messC);
    }
}

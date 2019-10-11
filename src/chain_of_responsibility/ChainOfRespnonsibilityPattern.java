package chain_of_responsibility;

/**
 * 责任链模式：
 * １，处理者(Handler)：处理者是一个接口，负责规定具体处理用户请求的方法以及具体处理者设置后继对象的方法
 * ２，具体处理者(ConcreteHandler)：具体处理者是实现处理者接口的类的实例，具体处理者通过处理者接口规定的方法处理的用户请求，
 * 即在接到用户的请求后，处理者将调用接口规定的方法，在执行该方法的过程中，如果发现能够处理用户的请求，就处理相关数据，
 * 否则就反馈无法处理的信息给用户，然后将用户的请求传递给自己的后继对象
 */


import java.util.ArrayList;

/**
 * 处理者
 */
interface Handler{
    public abstract void handleRequest(String number);
    public abstract void setNextHandler(Handler handler);
}

/**
 * 具体处理者：北京，上海，天津
 */
class Beijing implements Handler{
    private Handler handler;//存放后继对象
    private ArrayList<String> numberList;//存放身份证号码的线性数组表
    Beijing(){
        numberList = new ArrayList<>();
        numberList.add("11129812340930034");
        numberList.add("10120810340930632");
        numberList.add("22029812340930034");
        numberList.add("32620810340930632");
    }

    @Override
    public void handleRequest(String number) {
        if (numberList.contains(number))
            System.out.println("该人在北京居住");
        else {
            System.out.println("该人不在北京居住");
            if (handler != null)
                handler.handleRequest(number);//将请求传递给下一个对象
        }
    }

    @Override
    public void setNextHandler(Handler handler) {
        this.handler = handler;
    }
}

class Shanghai implements Handler{
    private Handler handler;
    private ArrayList<String> numberList;
    Shanghai(){
        numberList = new ArrayList<>();
        numberList.add("34529812340930034");
        numberList.add("98720810340430632");
        numberList.add("36529812340930034");
        numberList.add("77720810340930632");
    }

    @Override
    public void handleRequest(String number) {
        if (numberList.contains(number))
            System.out.println("该人在上海居住");
        else {
            System.out.println("该人不在上海居住");
            if (handler != null)
                handler.handleRequest(number);
        }
    }

    @Override
    public void setNextHandler(Handler handler) {
        this.handler = handler;
    }
}

class Tianjin implements Handler{
    private Handler handler;
    private ArrayList<String> numberList;
    Tianjin(){
        numberList = new ArrayList<>();
        numberList.add("10029812340930034");
        numberList.add("20020810340430632");
        numberList.add("30029812340930034");
        numberList.add("50020810340930632");
    }

    @Override
    public void handleRequest(String number) {
        if (numberList.contains(number))
            System.out.println("该人在天津");
        else{
            System.out.println("该人不在天津");
            if (handler != null)
                handler.handleRequest(number);
        }
    }

    @Override
    public void setNextHandler(Handler handler) {
        this.handler = handler;
    }
}

public class ChainOfRespnonsibilityPattern {
    private Handler beijing, shanghai, tianjin; //责任链上的对象
    //建立责任链
    public void createChain(){
        beijing = new Beijing();
        shanghai = new Shanghai();
        tianjin = new Tianjin();
        beijing.setNextHandler(shanghai);
        shanghai.setNextHandler(tianjin);
    }
    public void responseClient(String number){
        beijing.handleRequest(number);
    }
    public static void main(String[] args) {
        ChainOfRespnonsibilityPattern chain = new ChainOfRespnonsibilityPattern();
        chain.createChain();
        chain.responseClient("50020810340930632");
    }
}

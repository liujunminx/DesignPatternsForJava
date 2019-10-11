package facade;

/**
 * 外观模式
 * 1,子系统(SubSystem):子系统是若干个类的集合，这些类的实例协同合作为用户提供所需要的功能，
 * 子系统中的任何类都不包含外观类的引用
 * ２，外观(Facade):外观是一个类，该类包含子系统中全部或者部分的类的实例引用，当用户想要和系统中的若干个类的实例打交道时
 * 可以替代的和子系统的外观类的实例打交道
 */

/**
 * 三个子系统
 */
class CheckWord{
    public final int basicAmount = 85;
    String advertisement;
    int amount;
    public CheckWord(String advertisement){
        this.advertisement = advertisement;
    }
    public void setChargeAmount(){
        amount = advertisement.length() + basicAmount;
    }

    public int getAmount() {
        return amount;
    }
}

class Charge{
    public final int basicCharge = 12;
    CheckWord checkWord;
    Charge(CheckWord checkWord){
        this.checkWord = checkWord;
    }
    public void giveCharge(){
        int charge = checkWord.getAmount() + basicCharge;
        System.out.println("广告费用：" + charge + "元");
    }
}

class TypeSeting{
    String advertisement;
    public TypeSeting(String advertisement){
        this.advertisement = advertisement;
    }
    public void typeSeting(){
        System.out.println("广告排版格式：");
        System.out.println("************");
        System.out.println(advertisement);
        System.out.println("*************");
    }
}

class ClientServerFacade{
    private CheckWord checkWord;
    private Charge charge;
    private TypeSeting typeSeting;
    String advertisement;
    public ClientServerFacade(String advertisement){
        this.advertisement = advertisement;
        checkWord = new CheckWord(advertisement);
        charge = new Charge(checkWord);
        typeSeting = new TypeSeting(advertisement);
    }
    public void doAdvertisement(){
        checkWord.setChargeAmount();
        charge.giveCharge();
        typeSeting.typeSeting();
    }
}

public class FacadePattern {
    public static void main(String[] args) {
        String clientAdvertisement = "Ｍａｃ笔记本只要９９８，联系电话：１１０１３１３１００４";
        ClientServerFacade facade = new ClientServerFacade(clientAdvertisement);
        facade.doAdvertisement();
    }
}

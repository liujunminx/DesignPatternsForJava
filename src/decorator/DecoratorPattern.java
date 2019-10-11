package decorator;

/**
 * 装饰模式
 * 1，抽象组建(Component)：抽象组件是一个抽象类。抽象组件定义了“被装饰着”需要进行“装饰”的方法
 * 2，具体组件(ConcreteComponent):具体组件是抽象组件的一个子类，具体组件的实现称作“被装饰者”
 * 3，装饰(Decorator):装饰也是一个抽象组件的一个子类，但装饰还包含一个抽象组建声明的变量以保存
 * “被修饰者”的引用，装饰可以是一个抽象类也可以是一个非抽象类，如果他是一个非抽象类，则该类是一个
 * “装饰者”
 * 4，具体装饰(ConcreteDecorator):具体装饰是一个非抽象子类，具体装饰的实例称为“装饰者”
 */

/**
 * 下面将对麻雀类进行装饰
 * 现在，用户需要两只鸟，无论那只鸟都可以，但必须能连续飞150米和200米
 * 但是现有的鸟都只能飞100米，所以需要改进，我们发现
 * 如果使装饰模式，不必修改系统的代码，只需要在系统中进行添加装饰，该系统就可创建出用户需要的鸟
 */

/**
 * 抽象组件，定义飞的方法
 */
abstract class Bird{
    public abstract int fly();
}

/**
 * 具体组件，只能飞100米，后面可以修饰
 */
class Sparrow extends Bird{
    public final int DISTANCE = 100;

    @Override
    public int fly() {
        return DISTANCE;
    }
}

/**
 * 装饰，内含一个Bird类以保存被修饰者的引用
 */
abstract class Decorator extends Bird{
    protected Bird bird;
    public Decorator(){

    }
    public Decorator(Bird bird){
        this.bird = bird;
    }
}

/**
 * 具体装饰，加50米
 */
class SparrowDecorator extends Decorator{
    public final int DISTANCE = 50;//此方法加50米
    SparrowDecorator(Bird bird){
        super(bird);
    }

    /**
     * 重写并添加额外的功能
     * @return
     */
    @Override
    public int fly() {
        int distance = 0;
        distance = bird.fly() + eleFly();
        return distance;
    }

    /**
     * 额外的功能
     * @return
     */
    private int eleFly(){
        return DISTANCE;
    }
}

public class DecoratorPattern {

    public void needBird(Bird bird){
        int flyDistance = bird.fly();
        System.out.println("这只鸟可以飞" + flyDistance + "米");
    }

    public static void main(String[] args) {
        DecoratorPattern client = new DecoratorPattern();
        Bird sparrow = new Sparrow();
        client.needBird(sparrow);
        //都是继承一个抽象组件
        Bird sparrow150 = new SparrowDecorator(sparrow);
        client.needBird(sparrow150);
        Bird sparrow200 = new SparrowDecorator(sparrow150);
        client.needBird(sparrow200);
    }
}

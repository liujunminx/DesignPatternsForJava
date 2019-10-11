package single;

/**
 * 饿汉式单例
 * Singleton通过将构造方法限定为private避免了类在外部被实例化，在同一个虚拟机范围内，
 * Singleton的唯一实例只能通过getInstance()方法访问。（事实上，通过Java反射机制是能够实例化构造方法为private的类的，
 * 那基本上会使所有的Java单例实现失效。此问题在此处不做讨论，姑且闭着眼就认为反射机制不存在。哈哈哈）
 */
public class HungrySingle {
    private HungrySingle(){}
    private static final HungrySingle hungrySingle = new HungrySingle();
    public static HungrySingle getInstance(){
        return hungrySingle;
    }
}

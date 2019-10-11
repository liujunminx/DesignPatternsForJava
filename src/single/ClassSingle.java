package single;

/**
 * 基于静态内部类的单例模式，与饿汉方式不同的是：
 * 只要Singleton类被装载就会实例化，没有Lazy-Loading的作用，而静态内部类方式在Singleton类被装载时并不会立即实例化，而是在需要实例化时，
 * 调用getInstance方法，再回装载SingletonInstance类，从而完成Singleton的实例化。
 * 类的静态属性只会在第一次加载类的时候初始化，所以在这里，JVM帮助我们保证了线程的安全性，在类进行初始化时，别的线程时无法进入的。
 */
public class ClassSingle {
    private ClassSingle(){}

    private static class SingletonInstance{
        private static final ClassSingle single = new ClassSingle();
    }
    public static ClassSingle getInstance(){
        return SingletonInstance.single;
    }
}

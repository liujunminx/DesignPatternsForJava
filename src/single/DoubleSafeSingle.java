package single;

/**
 * volatile关键字双重检查
 */
public class DoubleSafeSingle {
    private DoubleSafeSingle(){}
    //volatile防止重排序
    private static volatile DoubleSafeSingle single = null;
    //双重检查
    public static DoubleSafeSingle getInstance(){
        if (single == null){
            synchronized (DoubleSafeSingle.class){
                if (single == null)
                    single = new DoubleSafeSingle();
            }
        }
        return single;
    }
}

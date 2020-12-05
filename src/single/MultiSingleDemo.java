package single;

import java.util.ArrayList;
import java.util.Random;

/**
 * 有上限的多例模式，修正单例可能存在的性能问题
 */
public class MultiSingleDemo {

    public static void main(String[] args) {
        int len = 10;
        for (int i = 0; i < len; i++){
            MultiSingle multiSingle = MultiSingle.getInstance();
            MultiSingle.say();
        }
    }

}

class MultiSingle{
    // 定义最多能产生的实例变量
    private static int maxNumOfSingle = 2;
    // 使用ArrayList来容纳单例私有属性
    private static ArrayList<String> nameList = new ArrayList<>();
    // 定义一个列表，容纳所有单例
    private static ArrayList<MultiSingle> multiSingleList = new ArrayList<>();
    // 当前单例序列号
    private static int countNumOfMultiSingle = 0;

    static {
        for (int i = 0; i < maxNumOfSingle; i++){
            multiSingleList.add(new MultiSingle("单例" + (i + 1)));
        }
    }

    private MultiSingle(){

    }

    private MultiSingle(String name){
        nameList.add(name);
    }

    /**
     * 随机生成单例
     * @return
     */
    public static MultiSingle getInstance(){
        Random random = new Random();
        countNumOfMultiSingle = random.nextInt(maxNumOfSingle);
        return multiSingleList.get(countNumOfMultiSingle);
    }

    public static void say(){
        System.out.println(nameList.get(countNumOfMultiSingle));
    }
}

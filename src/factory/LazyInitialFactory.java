package factory;

import java.util.HashMap;
import java.util.Map;

/**
 * 工厂方法模式的延迟初始化应用
 */
public class LazyInitialFactory {
    private static final Map<String, Product> prMap = new HashMap<>();
    public static synchronized Product createProduct(String type) throws Exception{
        Product product = null;
        if (prMap.containsKey(type)){
            product = prMap.get(type);
        }else {
            if (type.equals("product1")){
                product = new ConcreteProduct1();
            }else {
                product = new ConcreteProduct2();
            }
        }
        return product;
    }
}

interface Product{

}

class ConcreteProduct1 implements Product{

}

class ConcreteProduct2 implements Product{

}


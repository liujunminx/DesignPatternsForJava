package decorator;

import java.io.*;
import java.util.ArrayList;

/**
 * 设计要求：
 * 当前系统有一个抽象类ReadWord类，可以读取文件word.txt
 * 现在有用户希望调用ReadWord的对象调用readWord方法读取文件word.txt,并希望得到英文的翻译
 * 还有其他的用户希望得到句子的例子
 * 还有其他的用户希望修改为其他的功能
 */

/**
 * 抽象组件类，内含readWord方法
 */
abstract class ReadWord{
    public abstract ArrayList<String> readWord(File file);
}

/**
 * 原来的方法只能读英语文本
 */
class ReadEnglishWord extends ReadWord{
    @Override
    public ArrayList<String> readWord(File file) {
        ArrayList<String> wordList = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String s =null;
            while ((s = reader.readLine()) != null){
                wordList.add(s);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wordList;
    }
}

/**
 * 修饰组件
 */
abstract class TxtDecorator extends ReadWord{
    protected ReadWord readWord;
    public TxtDecorator(){

    }
    public TxtDecorator(ReadWord readWord){
        this.readWord = readWord;
    }
}

/**
 * 具体修饰类
 */
class WordDecorator extends TxtDecorator{
    File decoratorFile;

    /**
     * 构造函数
     * @param readWord  被修饰的类
     * @param decoratorFile 修饰的文件
     */
    WordDecorator(ReadWord readWord, File decoratorFile){
        super(readWord);
        this.decoratorFile = decoratorFile;
    }

    /**
     * 修饰的方法
     * @param file 被修饰的文件
     * @return ArrayList数组
     */
    @Override
    public ArrayList<String> readWord(File file) {
        ArrayList<String> wordList = readWord.readWord(file);
        try {
            BufferedReader reader = new BufferedReader(new FileReader(decoratorFile));
            String s = null;
            int m = 0;
            while ((s = reader.readLine()) != null){
                String word = wordList.get(m);
                word = word.concat(" | " + s);
                //替换指定位置的元素
                wordList.set(m, word);
                m++;
                if (m > wordList.size())
                    break;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wordList;
    }
}

public class ReadWordList {
    public static void main(String[] args) {
        ArrayList<String> wordList = new ArrayList<>();
        ReadEnglishWord readEnglishWord = new ReadEnglishWord();
        WordDecorator wordDecorator = new WordDecorator(readEnglishWord, new File("chinese.txt"));
        ReadWord readWord = wordDecorator;
        wordList = readWord.readWord(new File("word.txt"));
        for (int i=0; i<wordList.size(); i++){
            System.out.println(wordList.get(i));
        }

        WordDecorator wordDecorator1 = new WordDecorator(readWord, new File("englishSentence.txt"));
        readWord = wordDecorator1;
        wordList = readWord.readWord(new File("word.txt"));
        for (int i=0; i<wordList.size(); i++){
            System.out.println(wordList.get(i));
        }
    }
}

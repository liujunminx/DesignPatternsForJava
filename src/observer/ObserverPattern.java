package observer;

/*
 * 观察者模式
 * 1，主题(Subject): 主题是一个接口，该接口规定了具体主题实现的方法，比如，添加，删除观察者以及通知观察者更新数据的方法
 * 2.观察者(Observer): 观察者是一个接口，该接口规定了具体观察者用来更新数据的方法
 * 3.具体主题(ConcreteSubject): 具体主题是实现主题接口类的一个实例，该实例包含了可以经常发生变化的数据，具体主题需要
 * 使用一个集合，比如ArrayList，存放观察者的引用，以便数据变化时通知具体观察者
 * 4.具体观察者(ConcreteObserver): 具体观察者是实现观察者接口类的一个实例，具体观察者包含有可以存放具体主题接口变量
 * 以便观察者让具体主题将自己的引用添加到集体主题的集合中，是自己成为他的观察者，或者让这个具体主题将自己从具体主题的集合中
 * 删除，是自己不是它的观察者
 * 这里是推数据的方式
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

/**
 * 主题
 */
interface Subject{
    public void addObserver(Observer o);
    public void deleteObserver(Observer o);
    public void notifyObserver();
}

/**
 * 观察者
 */
interface Observer{
    public void hearTelephone(String hearMess);
}

class SeekJobCenter implements Subject{
    String mess;
    boolean changed;
    ArrayList<Observer> personList;
    SeekJobCenter(){
        mess = "";
        changed = false;
        personList = new ArrayList<>();
    }

    @Override
    public void addObserver(Observer o) {
        if (!personList.contains(o))
            personList.add(o);
    }

    @Override
    public void deleteObserver(Observer o) {
        if (personList.contains(o))
            personList.remove(o);
    }

    @Override
    public void notifyObserver() {
        if (changed){
            for (int i=0; i<personList.size(); i++){
                Observer observer = personList.get(i);
                observer.hearTelephone(mess);
            }
            changed = false;
        }
    }

    public void giveNewMess(String str){
        if (str.equals(mess)){
            changed = false;
        }else{
            mess = str;
            changed = true;
        }
    }
}

class UniversityStudent implements Observer{
    Subject subject;
    File myFile;
    UniversityStudent(Subject subject, String fileName){
        this.subject = subject;
        subject.addObserver(this);
        myFile = new File(fileName);
        if (!myFile.exists()){
            try {
                myFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void hearTelephone(String hearMess) {
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile(myFile,"rw");
            //向文末追加
            randomAccessFile.seek(randomAccessFile.length());
            byte[] buff = hearMess.getBytes();
            randomAccessFile.write(buff);
            System.out.println("我是一个大学生");
            System.out.println("我向文件 " + myFile.getName() + "写入以下内容：");
            System.out.println(hearMess);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class HaiGui implements Observer{
    Subject subject;
    File myFile;
    HaiGui(Subject subject, String fileName){
        this.subject = subject;
        subject.addObserver(this);
        myFile = new File(fileName);
        if (!myFile.exists()){
            try {
                myFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void hearTelephone(String hearMess) {
        try {
            boolean boo = hearMess.contains("Java程序员") || hearMess.contains("软件");
            if (boo){
                RandomAccessFile accessFile = new RandomAccessFile(myFile, "rw");
                accessFile.seek(accessFile.length());
                byte[] buff = hearMess.getBytes();
                accessFile.write(buff);
                System.out.println("我是一个海龟");
                System.out.println("我向文件 " + myFile.getName() + "写入以下内容：");
                System.out.println(hearMess);
            }else{
                System.out.println("我是海龟，这次没有我想要的信息");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

public class ObserverPattern {
    public static void main(String[] args) {
        SeekJobCenter center = new SeekJobCenter();//具体主题center
        UniversityStudent liujunmin = new UniversityStudent(center, "liujunmin.txt");
        HaiGui haiGui = new HaiGui(center, "haigui.txt");
        center.giveNewMess("阿里巴巴需要10个Java程序员");
        center.notifyObserver();
        center.giveNewMess("bilibili需要8个原画师");
        center.notifyObserver();
        center.giveNewMess("软件公司要人");
        center.notifyObserver();
    }
}

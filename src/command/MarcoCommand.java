package command;

/**
 * 宏命令，当一个宏命令执行时，会导致其所引用的其他命令执行其方法（ execute ）
 */

import java.util.ArrayList;

/**
 * 两个接受者
 */
class PrintLetter{
    public void printEnglish(){
        for (char c = 'a'; c < 'z'; c++){
            System.out.print(" " + c);
        }
    }
    public void print10(){
        for (int i = 0; i < 10; i++) {
            System.out.print(" " + i);
        }
    }
}

class PrintNumber{
    int n;
    PrintNumber(int n){
        this.n = n;
    }
    public void printEvenNumber(){
        for (int i = 1; i < n; i++) {
            if (i % 2 == 0) {
                System.out.print(" " + i);
            }
        }
    }
}

/**
 * 命令接口
 */
interface CommandMarco{
    public abstract void execute();
}

class PrintEnglishCommand implements CommandMarco{
    PrintLetter printLetter;

    public PrintEnglishCommand(PrintLetter printLetter) {
        this.printLetter = printLetter;
    }

    @Override
    public void execute() {
        printLetter.printEnglish();
    }
}

class Print10Command implements CommandMarco{
    PrintLetter printLetter;

    public Print10Command(PrintLetter printLetter) {
        this.printLetter = printLetter;
    }

    @Override
    public void execute() {
        printLetter.print10();
    }
}

class PrintNumberCommand implements CommandMarco{
    PrintNumber printNumber;

    public PrintNumberCommand(PrintNumber printNumber) {
        this.printNumber = printNumber;
    }

    @Override
    public void execute() {
        printNumber.printEvenNumber();
    }
}

class MyMarcoCommand implements CommandMarco{
    ArrayList<CommandMarco> marcoArrayList;
    MyMarcoCommand(ArrayList<CommandMarco> marcoArrayList){
        this.marcoArrayList = marcoArrayList;
    }
    @Override
    public void execute() {
        for (int i = 0; i < marcoArrayList.size(); i++) {
            CommandMarco commandMarco =  marcoArrayList.get(i);
            commandMarco.execute();
        }
    }
}

class RequestPerson{
    CommandMarco commandMarco;

    public void setCommandMarco(CommandMarco commandMarco) {
        this.commandMarco = commandMarco;
    }

    public void startMarcoCommand(){
        commandMarco.execute();
    }
}

public class MarcoCommand {
    public static void main(String[] args) {
        ArrayList<CommandMarco> marcoCommands = new ArrayList<>();
        RequestPerson person = new RequestPerson();
        CommandMarco commandMarco = new PrintEnglishCommand(new PrintLetter());
        CommandMarco commandMarco1 = new Print10Command(new PrintLetter());
        CommandMarco commandMarco2 = new PrintNumberCommand(new PrintNumber(100));
        marcoCommands.add(commandMarco);
        marcoCommands.add(commandMarco1);
        marcoCommands.add(commandMarco2);
        CommandMarco marco = new MyMarcoCommand(marcoCommands);
        person.setCommandMarco(commandMarco);
        person.startMarcoCommand();
        System.out.println();
        person.setCommandMarco(commandMarco2);
        person.startMarcoCommand();
    }
}

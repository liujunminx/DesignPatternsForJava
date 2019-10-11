package command;

/**
 * 接受者(Receiver)：接受者是一个类的实例，该实例负责执行与请求相关的操作
 * 命令接口(Command)：命令是一个接口，规定了用来封装“请求”的若干个方法，比如execute（），undo（）等方法
 * 具体命令(ConcreteCommand)：具体命令是实现命令接口的类的实例。具体命令必须实现接口中的方法，比如execute（），是该方法封装成一个“请求”
 * 请求者(Invoker)：请求者是一个包含接口变量的类的实例，请求者中的command接口的变量可以存放任何具体命令的引用，
 * 请求者负责调用具体命令，让具体命令执行那些封装了“请求”的方法，比如execute方法
 */

/**
 * 接受者
 */
class CompanyArmy{
    public void sneakAttack(){
        System.out.println("我们知道怎么偷袭敌人，保证完成任务");
    }
}

/**
 * 命令接口
 */
interface Command{
    public abstract void execute();
}

/**
 * 具体命令
 */
class ConcreteCommand implements Command{
    CompanyArmy army = new CompanyArmy();
    public ConcreteCommand(CompanyArmy army){
        this.army = army;
    }
    public void execute(){
        army.sneakAttack();
    }
}

/**
 * 请求者
 */
class ArmySuperior{
    Command command;
    public void setComand(Command command){
        this.command = command;
    }
    public void startExecuteCommand(){
        command.execute();
    }
}

public class CommandPattern {
    public static void main(String[] args) {
        CompanyArmy threeCompany = new CompanyArmy();//创建接受者
        Command command = new ConcreteCommand(threeCompany);//创建具体名利并指定接受者
        ArmySuperior commander = new ArmySuperior();//创建请求者
        commander.setComand(command);
        commander.startExecuteCommand();
    }
}

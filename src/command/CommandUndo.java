package command;

import java.io.File;
import java.util.ArrayList;

/**
 * 命令接口中的撤销方法
 */

/**
 * 接受者
 */
class MakeDir{
    public void CreateDir(String name){
        File dir = new File(name);
        dir.mkdir();
    }

    public void deleteDir(String name){
        File dir = new File(name);
        dir.delete();
    }
}

/**
 * 接口命令
 */
interface CommandToUndo{
    public abstract void execute(String name);
    public abstract void undo();
}

class ConcreteCommandToUndo implements CommandToUndo{
    ArrayList<String> dirNameList;
    MakeDir makeDir;
    ConcreteCommandToUndo(MakeDir makeDir){
        dirNameList = new ArrayList<>();
        this.makeDir = makeDir;
    }

    @Override
    public void execute(String name) {
        makeDir.CreateDir(name);
        dirNameList.add(name);
    }

    @Override
    public void undo() {
        if (dirNameList.size() > 0){
            int m = dirNameList.size();
            String str = dirNameList.get(m-1);
            makeDir.deleteDir(str);
            dirNameList.remove(m-1);
        }
        else
            System.out.println("没有需要撤销的操作");
    }
}

class RequestMakeDir{
    CommandToUndo commandToUndo;
    public void setCommandToUndo(CommandToUndo commandToUndo){
        this.commandToUndo = commandToUndo;
    }
    public void startExecuteCommandToUndo(String name){
        commandToUndo.execute(name);
    }
    public void undoCommand(){
        commandToUndo.undo();
    }
}

public class CommandUndo {
    public static void main(String[] args) {
        MakeDir makeDir = new MakeDir();
        CommandToUndo commandToUndo = new ConcreteCommandToUndo(makeDir);
        RequestMakeDir askMakeDir = new RequestMakeDir();
        askMakeDir.setCommandToUndo(commandToUndo);
        askMakeDir.startExecuteCommandToUndo("one");
        askMakeDir.startExecuteCommandToUndo("two");
        askMakeDir.startExecuteCommandToUndo("three");
        askMakeDir.undoCommand();
        askMakeDir.undoCommand();
    }
}

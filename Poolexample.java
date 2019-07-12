package poolexample;

import java.util.Stack;

class Tool{
    String tooltype;
    public Tool(String ttype){
        this.tooltype = ttype;
    }
}

class User{
    public Tool currenttool;
    public User(){
        currenttool = null;
    }
    public void setTool(Tool tool){
        currenttool = tool;
    }
    public void returnTool(ObjectPool op){
        op.returnTool(this);
        currenttool = null;
    }
}

class ObjectPool{
    Stack<Tool> toolpool;
    public ObjectPool(){
        toolpool = new Stack<Tool>();
    }
    public void addToPool(Tool tool){
        toolpool.push(tool);
    }
    public Tool getTool(){
        return toolpool.pop();
    }
    public void returnTool(User user){
        toolpool.push(user.currenttool);
    }
}

public class Poolexample {
    public static void main(String[] args) {
        Tool t1 = new Tool("hammer");
        Tool t2 = new Tool("wrench");
        Tool t3 = new Tool("sickle");
        ObjectPool op = new ObjectPool();
        User u1 = new User();
        User u2 = new User();
        
        op.addToPool(t1);
        op.addToPool(t2);
        op.addToPool(t3);
        
        u1.setTool(op.getTool());
        u2.setTool(op.getTool());
        System.out.println(u1.currenttool.tooltype);
        System.out.println(u2.currenttool.tooltype);
        u1.returnTool(op);
        u1.setTool(op.getTool());
        u1.setTool(op.getTool());
        System.out.println(u1.currenttool.tooltype);
    }
}

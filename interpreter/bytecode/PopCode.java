package interpreter.bytecode;

import java.util.ArrayList;
import interpreter.VirtualMachine;

public class PopCode extends ByteCode {
  private int level;

  public PopCode() {
  }

  @Override
  public void execute(VirtualMachine vm) {
    for (int i = 0; i < level; i++) {
      vm.getRunStack().pop();
    }

    if (vm.dumpIsOn()) {
      System.out.println("POP" + " " + level);
      
    }
  }

  public int getLevel(){
    return level;
  }

  @Override
  public void init(ArrayList<String> parameters) {
    level = Integer.parseInt(parameters.get(0));
  }
}
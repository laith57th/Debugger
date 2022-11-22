package interpreter.bytecode;

import java.util.ArrayList;
import interpreter.VirtualMachine;

public class FalseBranchCode extends BranchCode {
  private String label;
  private int target;

  public FalseBranchCode() {
  }

  @Override
  public void execute(VirtualMachine vm) {
    if (vm.getRunStack().pop() == 0) {
      vm.setpc(target);
    }

    if (vm.dumpIsOn()) {
      System.out.println("FALSEBRANCH " + label);
    }
  }

  @Override
  public void init(ArrayList<String> parameters) {
    label = parameters.get(0);
  }

  @Override
  public String getLabel() {
    return label;
  }

  @Override
  public int getTarget(){
    return target;
  }

  @Override
  public void setTarget(int newTarget){
    target = newTarget;
  }

  @Override
  public void setLabel(String name) {
    this.label = name;
  }
}
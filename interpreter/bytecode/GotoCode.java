package interpreter.bytecode;

import java.util.ArrayList;
import interpreter.VirtualMachine;

public class GotoCode extends BranchCode {
  private String label;
  private int target;

  public GotoCode() {
  }

  @Override
  public void execute(VirtualMachine vm) {
    vm.setpc(target);
    if (vm.dumpIsOn()) {
      System.out.println("GOTO " + label);
      
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
  public void setLabel(String newLabel){
    label = newLabel;
  }

  @Override
  public int getTarget(){
    return target;
  }

  @Override
  public void setTarget(int newTarget){
    target = newTarget;
  }
}
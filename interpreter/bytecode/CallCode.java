package interpreter.bytecode;

import java.util.ArrayList;
import interpreter.VirtualMachine;

public class CallCode extends BranchCode {
  private String functionName;
  private int target;
  private int parameter;

  public CallCode() {
  }

  @Override
  public void execute(VirtualMachine vm) {
    int pc = vm.getpc();
    vm.getReturnAddresses().push(pc);
    vm.setpc(target);
    parameter = vm.getRunStack().peek();

    if (vm.dumpIsOn()) {
      int n = functionName.indexOf("<");
      String temp;
      if (n < 0) {
        temp = functionName;
      } else {
        temp = functionName.substring(0, n);
      }
      System.out.printf("%-25s%-26s\n", "CALL", functionName + " " + temp + "(" + parameter + ")");
    }
  }

  @Override
  public int getTarget() {
    return target;
  }

  @Override
  public void setTarget(int n) {
    target = n;
  }
  
  @Override
  public String getLabel() {
    return functionName;
  }

  @Override
  public void init(ArrayList<String> parameters) {
    this.functionName = parameters.get(0);
  }

  public String getFunctionName() {
    return functionName;
  }

  @Override
  public void setLabel(String name) {
    this.functionName = name;
  }
}
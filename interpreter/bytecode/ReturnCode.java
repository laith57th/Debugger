package interpreter.bytecode;

import java.util.ArrayList;
import interpreter.VirtualMachine;

public class ReturnCode extends ByteCode {
  private String functionName;
  private int value;

  public ReturnCode() {
  }

  @Override
  public void execute(VirtualMachine vm) {
    vm.getRunStack().popFrame();
    vm.setpc(vm.getReturnAddresses().pop());
    value = vm.getRunStack().peek();

    if (vm.dumpIsOn()) {
      String temp;
      if (functionName != null) {
        int angle = functionName.indexOf("<");
        if (angle < 0) {
          temp = functionName;
        } else {
          temp = functionName.substring(0, angle);
        }
        System.out.printf("%-25s%-26s\n", "RETURN " + functionName, "exit " + temp + ": " + value);
      } else{
        System.out.println("RETURN");
      }
      
    }
  }

  @Override
  public void init(ArrayList<String> parameters) {
    if (parameters.size() > 0) {
      functionName = parameters.get(0);
    } else {
      functionName = null;
    }
  }
}
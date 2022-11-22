package interpreter.bytecode;

import java.util.ArrayList;
import interpreter.VirtualMachine;

public class LoadCode extends ByteCode {
  private int value;
  private String id;

  public LoadCode() {
  }

  @Override
  public void execute(VirtualMachine vm) {
    vm.getRunStack().load(value);
    if (vm.dumpIsOn()) {
      System.out.printf("%-25s%-26s\n", "LOAD " + value + " " + id, "<load " + id + ">");
      
    }
  }

  @Override
  public void init(ArrayList<String> parameters) {
    value = Integer.parseInt(parameters.get(0));
    id = parameters.get(1);
  }
}
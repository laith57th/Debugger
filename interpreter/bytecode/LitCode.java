package interpreter.bytecode;

import java.util.ArrayList;
import interpreter.VirtualMachine;

public class LitCode extends ByteCode {
  private int value;
  private String id;

  public LitCode() {
  }

  @Override
  public void execute(VirtualMachine vm) {
    String output = "LIT " + value;
    if (id == null) {
      vm.getRunStack().push(value);
    } else {
      vm.getRunStack().push(0);
      output = String.format("%-25s%-26s", output + " " + id, "int " + id);
    }
    if (vm.dumpIsOn()) {
      System.out.println(output);
    }
  }

  @Override
  public void init(ArrayList<String> parameters) {
    value = Integer.parseInt(parameters.get(0));
    if (parameters.size() > 1) {
      id = parameters.get(1);
    } else {
      id = null;
    }
  }

  public String getId(){
    return id;
  }

  public int getValue(){
    return value;
  }
}
package interpreter.bytecode;

import java.util.ArrayList;
import interpreter.VirtualMachine;

public class LabelCode extends ByteCode {
  private String label;

  public LabelCode() {
  }

  @Override
  public void execute(VirtualMachine vm) {
    if (vm.dumpIsOn()) {
      System.out.println("LABEL " + label);
      
    }
  }

  @Override
  public void init(ArrayList<String> parameters) {
    this.label = parameters.get(0);
  }

  public String getLabel() {
    return this.label;
  }
}
package interpreter.bytecode;

import java.util.ArrayList;
import interpreter.VirtualMachine;

public class HaltCode extends ByteCode {
  public HaltCode() {
  }

  @Override
  public void execute(VirtualMachine vm) {
    vm.setRunningStatus(false);
    if (vm.dumpIsOn()) {
      System.out.println("HALT");
    }
  }

  @Override
  public void init(ArrayList<String> parameters) {

  }
}
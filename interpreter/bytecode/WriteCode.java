package interpreter.bytecode;

import java.util.ArrayList;
import interpreter.VirtualMachine;

public class WriteCode extends ByteCode {
  public WriteCode() {
  }

  @Override
  public void execute(VirtualMachine vm) {
    System.out.println(vm.getRunStack().peek());
  }

  @Override
  public void init(ArrayList<String> parameters) {

  }
}
package interpreter.bytecode;

import java.util.ArrayList;
import interpreter.VirtualMachine;

public class ArgsCode extends ByteCode {
  private String arguments;

  public ArgsCode() {
  }

  @Override
  public void execute(VirtualMachine vm) {
    vm.getRunStack().newFrameAt(Integer.parseInt(arguments));
    vm.getRunStack().setFrameIndex(vm.getRunStack().length());

    if (vm.dumpIsOn()) {
      System.out.println("ARGS " + arguments);
    }
  }

  @Override
  public void init(ArrayList<String> args) {
    arguments = args.get(0);
  }
}
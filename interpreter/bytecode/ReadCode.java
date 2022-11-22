package interpreter.bytecode;

import java.util.ArrayList;
import java.util.Scanner;

import interpreter.VirtualMachine;

public class ReadCode extends ByteCode {

  private Scanner scanner = new Scanner(System.in);
  public ReadCode() {
  }

  @Override
  public void execute(VirtualMachine vm) {
    System.out.print("Please input an integer: ");
    int input = scanner.nextInt();
    vm.getRunStack().push(input);

    if (vm.dumpIsOn()) {
      System.out.println("READ");
    }
  }

  @Override
  public void init(ArrayList<String> parameters) {

  }
}
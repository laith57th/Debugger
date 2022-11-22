package interpreter.bytecode;

import java.util.ArrayList;
import interpreter.VirtualMachine;

public class StoreCode extends ByteCode {
  private int offset;
  private String id;
  private int value;

  public StoreCode() {
  }

  @Override
  public void execute(VirtualMachine vm) {
    value = vm.getRunStack().peek();
    vm.getRunStack().store(offset);

    if (vm.dumpIsOn()) {
      System.out.printf("%-25s%-26s\n", "STORE " + offset + " " + id, id + " = " + value);
      
    }
  }

  @Override
  public void init(ArrayList<String> parameters) {
    // if(parameters.size() == 1){
    //   value = Integer.parseInt(parameters.get(0));
    // } else{
    //   id = parameters.get(parameters.size()-1);
    //   value = Integer.parseInt(parameters.get(0));
    // }
    offset = Integer.parseInt(parameters.get(0));
    id = parameters.get(1);
  }
}
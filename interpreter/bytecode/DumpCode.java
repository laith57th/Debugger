package interpreter.bytecode;

import java.util.ArrayList;
import interpreter.VirtualMachine;

public class DumpCode extends ByteCode{
  private String switcher;
  public DumpCode(){}
  @Override
  public void execute(VirtualMachine vm){
    if(switcher.equals("ON")){
      vm.setDump(true);
    }else{
      vm.setDump(false);
    }
  }
  @Override
  public void init(ArrayList<String> parameters){
    switcher = parameters.get(0);
  }
}
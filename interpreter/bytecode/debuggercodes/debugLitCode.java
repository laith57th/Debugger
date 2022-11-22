package interpreter.bytecode.debuggercodes;

import interpreter.VirtualMachine;
import interpreter.bytecode.LitCode;
import interpreter.debugger.DebuggerVirtualMachine;

public class debugLitCode extends LitCode{
 
 @Override
 public void execute(VirtualMachine vm){
  DebuggerVirtualMachine dvm = (DebuggerVirtualMachine)vm;
  super.execute(vm);

  int currentOffset = (vm.getRunStack().length()-vm.getRunStack().getLastFrameValue());
  String id = getId();

  if(id != null){
   dvm.environmentRecordPeek().enter(id, currentOffset);
  }
 }

}

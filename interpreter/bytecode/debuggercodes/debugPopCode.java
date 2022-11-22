package interpreter.bytecode.debuggercodes;

import interpreter.VirtualMachine;
import interpreter.bytecode.PopCode;
import interpreter.debugger.DebuggerVirtualMachine;

public class debugPopCode extends PopCode {
 @Override
 public void execute(VirtualMachine vm) {
  DebuggerVirtualMachine dvm = (DebuggerVirtualMachine) vm;
  super.execute(vm);

  dvm.popEnvironmentRecord(getLevel());
 }
}

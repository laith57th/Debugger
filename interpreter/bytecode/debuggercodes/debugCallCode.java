package interpreter.bytecode.debuggercodes;

import interpreter.VirtualMachine;
import interpreter.bytecode.CallCode;
import interpreter.debugger.DebuggerVirtualMachine;
import interpreter.debugger.FunctionEnvironmentRecord;

public class debugCallCode extends CallCode{

 @Override
 public void execute(VirtualMachine vm){
  DebuggerVirtualMachine dvm = (DebuggerVirtualMachine)vm;
  super.execute(vm);
  dvm.pushOntoFunctionEnvRecord(new FunctionEnvironmentRecord());
 }
 
}

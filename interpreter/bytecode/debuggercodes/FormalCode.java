package interpreter.bytecode.debuggercodes;

import interpreter.VirtualMachine;
import interpreter.bytecode.ByteCode;
import interpreter.debugger.DebuggerVirtualMachine;

import java.util.ArrayList;

public class FormalCode extends ByteCode {
 String name;
 int offset;

 @Override
 public void init(ArrayList<String> args) {
  name = args.get(0);
  offset = Integer.parseInt(args.get(args.size()-1));
 }

 @Override
 public void execute(VirtualMachine vm) {
  DebuggerVirtualMachine dvm = (DebuggerVirtualMachine) vm;
  dvm.environmentRecordPeek().enter(name, offset);
 }
}

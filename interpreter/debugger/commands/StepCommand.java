package interpreter.debugger.commands;

import interpreter.debugger.DebuggerVirtualMachine;
import interpreter.debugger.ui.DebuggerCommand;
import interpreter.debugger.ui.DebuggerShell;

public class StepCommand extends DebuggerCommand{

 @Override
 public void execute(DebuggerVirtualMachine dvm, DebuggerShell debugShell){
  dvm.stepExecute();
  debugShell.prompt();
 }
}

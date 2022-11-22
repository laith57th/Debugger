package interpreter.debugger.commands;

import interpreter.debugger.DebuggerVirtualMachine;
import interpreter.debugger.ui.DebuggerCommand;
import interpreter.debugger.ui.DebuggerShell;

public class SourceCommand extends DebuggerCommand{
 @Override
 public void execute(DebuggerVirtualMachine dvm, DebuggerShell debugShell){
  dvm.printSourceCode();
  debugShell.prompt();
 }
}

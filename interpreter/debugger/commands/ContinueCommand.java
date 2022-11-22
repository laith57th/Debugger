package interpreter.debugger.commands;

import interpreter.debugger.DebuggerVirtualMachine;
import interpreter.debugger.ui.DebuggerCommand;
import interpreter.debugger.ui.DebuggerShell;

public class ContinueCommand extends DebuggerCommand {

 @Override
 public void execute(DebuggerVirtualMachine dvm, DebuggerShell debugShell) {
  dvm.continueExecute();
  debugShell.prompt();
 }
}

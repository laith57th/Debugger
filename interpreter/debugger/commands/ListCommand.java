package interpreter.debugger.commands;

import interpreter.debugger.DebuggerVirtualMachine;
import interpreter.debugger.ui.DebuggerCommand;
import interpreter.debugger.ui.DebuggerShell;

public class ListCommand extends DebuggerCommand {

 @Override
 public void execute(DebuggerVirtualMachine dvm, DebuggerShell debugShell) {
  for (int i = 0; i < dvm.getEntriesSize(); i++) {
   if (dvm.hasBreakpoint(i)) {
    System.out.println("* " + dvm.getLineNumber(i) + ": " + dvm.getSourceLine(i));
   }
  }
  debugShell.prompt();
 }

}

package interpreter.debugger.ui;

import interpreter.debugger.DebuggerVirtualMachine;

public abstract class DebuggerCommand {
  public abstract void execute(DebuggerVirtualMachine dvm, DebuggerShell debugShell);
}
package interpreter.debugger.commands;

import java.util.Scanner;

import interpreter.debugger.DebuggerVirtualMachine;
import interpreter.debugger.ui.DebuggerCommand;
import interpreter.debugger.ui.DebuggerShell;

public class SetCommand extends DebuggerCommand {
 private int lineNumber;
 private Scanner scanner;

 @Override
 public void execute(DebuggerVirtualMachine dvm, DebuggerShell debugShell) {
  scanner = new Scanner(System.in);
  System.out.println("\nSet a break point at line: >");

  while (true) {
   lineNumber = scanner.nextInt();
   if (lineNumber > dvm.getEntriesSize() || lineNumber <= 0) {
    System.out.println("Not a valid line!\ntry again >");
    continue;
   }
   dvm.setBreakPoint(true, lineNumber-1);
   break;
  }
  debugShell.prompt();
 }

}

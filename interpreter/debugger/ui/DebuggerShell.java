package interpreter.debugger.ui;

import java.util.Scanner;
import interpreter.debugger.DebuggerVirtualMachine;
import interpreter.debugger.commands.ContinueCommand;
import interpreter.debugger.commands.ListCommand;
import interpreter.debugger.commands.LocalsCommand;
import interpreter.debugger.commands.SetCommand;
import interpreter.debugger.commands.SourceCommand;
import interpreter.debugger.commands.StepCommand;

public class DebuggerShell {
  private Scanner scanner;
  private String userInput;
  private DebuggerVirtualMachine dvm;

  public DebuggerShell(DebuggerVirtualMachine dvm) {
    scanner = new Scanner(System.in);
    this.dvm = dvm;
  }

  public DebuggerCommand prompt() {
    DebuggerCommand command = null;
    while (userInput != "exit") {
      System.out.println();
      System.out.println("Type ? for help");
      System.out.print(">");
      userInput = scanner.next();

      switch (userInput) {
        case "set":
          command = new SetCommand();
          break;
        case "list":
          command = new ListCommand();
          break;
        case "locals":
          command = new LocalsCommand();
          break;
        case "source":
          command = new SourceCommand();
          break;
        case "step":
          command = new StepCommand();
          break;
        case "continue":
          command = new ContinueCommand();
          break;
        case "?":
          System.out.print("\n" +
              "[set]\n" +
              "[list]\n" +
              "[locals]\n" +
              "[source]\n" +
              "[step]\n" +
              "[continue]\n" +
              "[exit]\n");
          break;
        case "exit":
          System.exit(0);
          break;
        default:
          System.out.println("Wrong entry, try again...");
          continue;
      }
      if(!userInput.equals("?")){
        command.execute(dvm, this);
      }
    }
    return command;
  }
}
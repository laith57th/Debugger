package interpreter.debugger;

import interpreter.Interpreter;
import interpreter.Program;
import interpreter.debugger.ui.DebuggerShell;

public class Debugger extends Interpreter {
  private DebuggerShell shell;
  private String source;

  public Debugger(String baseFileName) {
    super(baseFileName, true);
    // Update to add the correct extensions to the base file name to
    // load the byte code file, as well as to load the source file
    this.source = baseFileName + ".x";
    DebuggerCodeTable.init();
  }

  @Override
  public void run() {
    try{
      Program debugProgram = super.getProgram();
      DebuggerVirtualMachine vm = new DebuggerVirtualMachine(debugProgram, source);
      shell = new DebuggerShell(vm);
      vm.printSourceCode();
      shell.prompt().execute(vm, shell);
    }catch(Exception e){
      System.out.println("****" + e);
    }
    
    
  }
}
package interpreter;

import java.io.*;

import interpreter.debugger.Debugger;

public class Interpreter {

  protected ByteCodeLoader byteCodeLoader;
  protected VirtualMachine vm;

  public Interpreter(String codeFile, boolean debug) {
    codeFile += ".x.cod";
    try {
      CodeTable.init(debug);
      byteCodeLoader = new ByteCodeLoader(codeFile);
    } catch (IOException e) {
      System.out.println("**** " + e);
    }
  }

  public Program getProgram(){
    Program debug = new Program();
    try{
      debug = byteCodeLoader.loadCodes();
    } catch(Exception e){
      System.out.println(e);
    }
    return debug;
  }

  public ByteCodeLoader getByteCodeLoader(){
    return this.byteCodeLoader;
  }

  public void run() {
    try{
      Program program = byteCodeLoader.loadCodes();
      VirtualMachine vm = new VirtualMachine(program);
      vm.executeProgram();
    }catch(Exception e){
      System.out.println(e);
    }
  }

  public static void main(String args[]) {
    // Interpreter test = new Interpreter("sample_files/factorial.x.cod", false);
    // test.run();

    if(args.length == 2 && !args[0].equals("-d")) {
      System.out.println("*** Incorrect usage, try: java interpreter.Interpreter -d <basefilename>");
      System.exit(1);
    }

    if(args.length == 2 && args[0].equals("-d")) {
      (new Debugger(args[1])).run();
    } else {
      (new Interpreter(args[0], false)).run();
    }
    // Debugger test = new Debugger("sample_files/factorial");
    // test.run();
  }
}
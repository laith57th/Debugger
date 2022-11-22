package interpreter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.ArrayList;

import interpreter.bytecode.*;
import interpreter.debugger.DebuggerCodeTable;

public class ByteCodeLoader {
  private StringTokenizer tokenizer;
  private BufferedReader sourceFile;

  public ByteCodeLoader(String byteCodeFile) throws IOException {
    this.sourceFile = new BufferedReader(new FileReader(byteCodeFile));
  }

  public Program loadCodes() throws IOException {
    Program program = new Program();
    String source = sourceFile.readLine();
    ArrayList<String> args = new ArrayList<String>();

    while (source != null) {
      args.clear();
      tokenizer = new StringTokenizer(source);
      String byteType = tokenize(source, args);
      
      try {
        ByteCode byteCode;
        boolean debugCode = DebuggerCodeTable.getDebugMap().containsValue(byteType);
        if (byteType != null) {
          if(debugCode){
            byteCode = (ByteCode) (Class.forName("interpreter.bytecode.debuggercodes." + byteType).getDeclaredConstructor()
              .newInstance());
          } else{
            byteCode = (ByteCode) (Class.forName("interpreter.bytecode." + byteType).getDeclaredConstructor()
              .newInstance());
          }
          

          byteCode.init(args);
          program.addCode(byteCode);
        }
        source = sourceFile.readLine();
      } catch (Exception e) {
        System.out.println("Exception occurred " + e);
        source = null;
      }
    }

    program.resolveAddresses(program);
    return program;
  }

  private String tokenize(String code, ArrayList<String> args) throws IOException {
    tokenizer = new StringTokenizer(code);
    String nextTok = tokenizer.nextToken();
    String type;
    if (nextTok != null) {
      type = CodeTable.getClass(nextTok);
      if(type == null){
        type = DebuggerCodeTable.getClass(nextTok);
      }
      
      while (tokenizer.hasMoreTokens()) {
        args.add(tokenizer.nextToken());
      }
      return type;
    }
    return null;
  }

}
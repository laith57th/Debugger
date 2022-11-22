package interpreter.debugger;

import java.util.HashMap;

import interpreter.CodeTable;

public class DebuggerCodeTable{
  private static HashMap<String, String> codeMap = new HashMap<>();

  public static void init() {
    codeMap.put("CALL", "debugCallCode");
    codeMap.put("FORMAL", "FormalCode");
    codeMap.put("FUNCTION", "FunctionCode");
    codeMap.put("LINE", "LineCode");
    codeMap.put("LIT", "debugLitCode");
    codeMap.put("POP", "debugPopCode");
    codeMap.put("RETURN", "debugReturnCode");
    // Initialize the new byte codes required for debugging:
    // codeMap.put(... etc. ...)
  }

  public static HashMap<String, String> getDebugMap(){
    return codeMap;
  }

  public static String getClass(String code) {
    String result = codeMap.get(code.trim().toUpperCase());

    if (result == null) {
      return CodeTable.getClass(code);
    } else {
      return result;
    }
  }
}
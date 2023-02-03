# Execution and Development Environment
To complete this project, I used Visual Studio Code on my Macbook M1 pro.
openjdk version "17.0.2" 2022-01-18
OpenJDK Runtime Environment Temurin-17.0.2+8 (build 17.0.2+8)
OpenJDK 64-Bit Server VM Temurin-17.0.2+8 (build 17.0.2+8, mixed mode)
Compilation Result
Using the terminal, I used the following commands on the project files:
>javac interpreter/debugger/commands/*.java javac

>interpreter/bytecode/*.java javac

>interpreter/bytecode/debuggercodes/*.java javac

>interpreter/Interpreter.java java interpreter.Interpreter

The program ran as expected and no error messages were displayed
# Assumptions
I assumed a correct and existing file name will be passed to the debugger.
# Summary of technical work
To complete this project I implemented a strategy design pattern to dynamically choose the
behavior executed for each scanned debugger bytecodes from the token stream. Also, I created
an instance of each command that executes according to user input. Finally, I incorporated
object oriented programming when creating an entry object for each source file and loading the
information to each entry in the arraylist for easier access and better organization.
# Implementation
## New ByteCode subclasses
1. LineCode
> This debugger code sets the current line number to the value read from the
bytecode file. This was somewhat straightforward to implement and test.
2. FunctionCode
a. The function code takes a start, end, and function name instance variables from the
token file and sets the function information inside the function environment record.
3. FormalCode
a. The formal code takes a name and an offset and initializes them with the information
read from the token stream. The execute method adds this information to the
HashMap of the environment record on top of the functionEnvironmentRecord stack.
4. debugReturnCode
a. The new behavior required for this bytecode is to pop the FunctionEnvironmentStack
in the debugger virtual machine.
5. debugPopCode
a. This bytecode reads a level integer then deletes that amount of items from the
symbol table.
6. DebugLitCode
a. This bytecode adds the literal value to the hash map on top of the environment
record stack if an identifier is present in the token stream.
7. debugCallCode
a. This bytecode simply pushes a new function environment record on top of the stack.
# FunctionEnvironmentRecord class
To implement this class, I used the Table class algorithm from the constrainer. First, I created the
symbol table as an instance of the Table class. Then I implemented all the necessary functions to
carry out the correct behavior. Finally, I tested the methods using the provided main method which
returned the desired output.
# DebuggerCodeTable class
This class was fairly straightforward. I first added all the debugger bytecodes, as well as, the
bytecodes that required additional behavior to the codeMap object. Next, I created a static method
to access the hashMap and use in the bytecodeLoader class.
# Debugger class
To implement this class’s constructor, I called the super constructor and passed the baseFileName
as well as a debug boolean to differentiate between modes. Next, I concatenated the correct
extension to the baseFileName. Finally, I initialized the DebuggerCodeTable with the new
bytecodes. For the run method, I created a new DebuggerVirtualMachine instance and passed the
current program as well as the source file. Then I printed the source code to the screen followed
by the prompt method inside the shell class.
# DebuggerVirtualMachine class
This class was essentially the motherboard to my implementation. The main job of this class is to
create the source file entries using an arraylist of an Entry object. This class also initializes the
functionEnvironmentStack and loads the byteCodes from the file. The methods implemented in this
class could be considered helper methods for all the command instances.
# DebuggerShell Class
This class is used to interact with the user and carry out the entered commands. The prompt
method is essentially a while loop that continues to ask the user for new commands until they
decide to exit the program. The user input is checked through a switch statement that passes an
instance of the entered command and executes the respective method for that command.
# Code Organization
To better organize my code, I used single-function methods for each command. Also, I created an
entry object that holds all source line information. Finally, I placed all command subclasses inside a
commands package inside the debugger package for easier access and debugging.

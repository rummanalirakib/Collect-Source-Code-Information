Objectives:
Write a program that takes a Java source file as an input. The
program needs to collect the following pieces of information:

1. For each method declaration, identify the variables
declared in the method body along with the line number


2. For each method call (mc) located in the source code,
determine the signature of the method. A method signature
consists of the method name, number of parameters and types
of parameters. Consider that the method declarations
correspond to method calls are located in the same file.


3. For each method call (mc) located in a method body
(mb), determine the receiver variable (v) and collect
those methods that are called on the same variable
(including the method call that define the variable)
prior to calling mc and are located within mb. 

4. For each method call (mc) located in a method body (mb)
whose receiver is a variable (v), determine those methods
that are called prior to calling mc, use v in their
argument and are located within mb. 

Files:
CPU.java - The CPU.java file contains the main/parent Process that fetches instructions and executes it.
Memory.java - The Memory.java file is executed from CPU, and is the child process that either reads from or writes into memory.
sample5.txt - This sample5 contains very simple instructions and adds 2 numbers that add up to 6316.

To execute the project:

Compile the 2 java files (eg. javac CPU.java) and have the sample5.txt file in the same folder.
The command to execute CPU is: java CPU sample5.txt 30
It consists of the java keyword, then the CPU file, then a sample file, and then a timer value for the interrupt.
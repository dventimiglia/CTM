# README #

Redacted Coding Assignment Solution for Problem Two:  Conference Track Management

Author:  **REDACTED**

### What is this repository for? ###

This solution packs conference talk proposals into scheduled talks.
See the `info.txt` file for a full description of the problem.

### How do I get set up? ###

#### Build

This application uses [Apache Maven](https://maven.apache.org/).  With
Maven installed, to build it issue this command on the command line in
the directory that contains this file.

    mvn compile

#### Configuration

This application adopts [one](https://12factor.net/config) of the
factors of [The Twelve-Factor App](https://12factor.net/config) in
that it stores configuration in the environment.  It relies on these
environment variables being defined.

    START - start time for each new track (HH:MM)
    MINEND - earliest end time for each new track (HH:MM)
    MAXEND - latest end time for each new track (HH:MM)
    LUNCHSTART - lunch start time for each new track (HH:MM)
    LUNCHEND - lunch end time for each new track (HH:MM)
    CAPSTONE - name of a capstone event at the end of each track
	
#### Usage

To run the application on the provided test input in the file
`test.txt`, issue this command from the command line in the directory
containing this file.

    java -cp target/classes/ com.redacted.ctm.CTMJava test.txt
	
The calculated schedule will be printed to standard output.
Naturally, a different input file can be substituted for `test.txt` in
this example.  Moreover, multiple input files can also be listed on
the command line.  In that case, each input file is treated separately
from the others, i.e., each input file corresponds to a schedule.
Multiple input files produce multiple schedules.  Finally, if no input
file is provided, if one or more the environment variable
configuration parameters are not provided, or if they are invalid,
then the application prints a usage message to the screen.

    Usage:
      java -cp <classpath> com.redacted.ctm.CTMJava <input file>...
    Environment Variables:
      START - start time for each new track (HH:MM)
      MINEND - earliest end time for each new track (HH:MM)
      MAXEND - latest end time for each new track (HH:MM)
      LUNCHSTART - lunch start time for each new track (HH:MM)
      LUNCHEND - lunch end time for each new track (HH:MM)
      CAPSTONE - name of a capstone event at the end of each track

#### How to run tests

To run the [JUnit](http://junit.org/junit4/) tests run this command
from the command line in the directory containing this file.

    mvn test

### Additional Notes

* The instructions said to "name your code with the problem and
  language used."  I decided to interpret that to mean that the main
  application name (in this case, a Java class with a `main` method
  should somehow reflect the problem name, and that it should include
  the word "Java."  I abbreviated the problem name, which is
  "Conference Track Management," to "CTM" and appended "Java" to
  obtain `CTMJava`.
* I tipped my hat to testing but didn't go "all-in."  There is one
  test class which does some light unit testing of perhaps the most
  important class, the `Bin` class.  That points out the direction for
  writing more tests, but in the interest of time I quit the effort
  early.
* Likewise I tipped my hat to Javadoc comments but didn't go "all-in."
  Mostly, they're descriptive guideposts to code readers and future
  developers.  They make haphazard use of the JavaDoc syntax.  Again,
  in the interest of time I elected not to polish them further.  That
  can be an exercise for later.
* With [Java 9](https://docs.oracle.com/javase/9/whatsnew/toc.htm) one
  can play with the code in an interactive environment with
  [JShell](https://docs.oracle.com/javase/9/jshell/introduction-jshell.htm).
  
  
        jshell --class-path target/classes
		jshell> /open scratch.java


# Bonus for TP1

Create a file `Slide19.g4` with the grammar used in the course on descendant syntactic analysis (slide 19).  Add a `start` rule as axiom:

```ANTLR4
grammar Slide19;

start : expr EOF;
```


Generate the Python code of the analyser with:

```
antlr4 -Dlanguage=Python3 Slide19.g4
```


The generated file `Slide19Parser.py` file is a recursive descent parser
similar to the one studied in the course (slides 25-27).  Look at the
code of the methods corresponding to each non terminal symbol to get
convinced.

You can also generate the same analyser as a java program with:
```
antlr4 -no-listener Slide19.g4
```


In file `Slide19Parser.java`, look especially at the `public final`
methods.

Antlr provides a `grun` command to try a java parser.  First compile
the java files:

```
javac Slide19*.java
```


then run `grun` to test the lexer and display the lexical units:

```
grun Slide19 tokens -tokens
```


You can also try the parser and look at the tree

```
grun Slide19 start -tree
```


And even see it displayed graphically!

```
grun Slide19 start -gui
```

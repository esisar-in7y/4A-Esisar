# LAB1, arithmetic expressions interpreter
* initial version MIF08, 2020-2021, Laure Gonnord & Matthieu Moy
* Esisar 2022, minor changes 
* YOURNAME HERE

# Content

This directory contains an interpreter for simple arithmetic
expressions like 5+3, for instance. The intepreter evaluates the
(sequence of) arithmetic expressions and prints their value on the
standard output.


# Code infrastructure:

* `Arit.g4` : ANTLR grammar and evaluator rules.
* `arit.py` : main file to execute after parser generation.
* `*test*` : test infrastructure based on pytest.
* `tests/` : directory for unit testss.

# Usage

* `make` to generate AritLexer.py and AritParser.py (once)

Input is either a file or standard input.

* `python3 arit1.py <path/and/test/name>` to test a given file, for
 instance: 
 `python3 arit1.py tests/test01.txt`  should print `1+2 = 3`

* `make test` to test on all tests files of the `testfile` directory

# Syntax of our language/restrictions

The syntax is the one textually given in the Lab1 sheet. 
Restriction : we did not implement minus nor unary minus.

# Design choices

TODO

# Known bugs

TODO, if applicable

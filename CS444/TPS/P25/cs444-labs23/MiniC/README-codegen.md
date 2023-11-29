# MiniC Compiler 
LAB4 (simple code generation), MIF08 / CAP 2022-23

# Authors

LEPRAT
MONCORGE

# Contents

TODO for STUDENTS : Say a bit about the code infrastructure ...

- `TP04/tests/` : Ce dossier contient les tests unitaires pour vérifier les fonctionnalités du compilateur.


# Test design 

Nous avons conçu des tests pour chaque fonctionnalité du compilateur `TP04/tests/`.
Chaque test vérifie une fonctionnalité spécifique et s'assure qu'elle fonctionne comme prévu.


# Design choices

TODO: explain your choices. How did you implement boolean not? Did you implement an extension?
We implemented the boolean not with the xor operator, sowe can you it for boolean and integer.

# Known bugs

None

# Checklists

A check ([X]) means that the feature is implemented 
and *tested* with appropriate test cases.

## Code generation

- [x] Number Atom
- [x] Boolean Atom
- [x] Id Atom
- [x] Additive expression
- [x] Multiplicative expression
- [x] UnaryMinus expression
- [x] Or expression
- [x] And expression
- [x] Equality expression
- [x] Relational expression (! many cases -> many tests)
- [x] Not expression

## Statements

- [x] Prog, assignements
- [x] While
- [x] Cond Block
- [x] If
- [x] Nested ifs
- [x] Nested whiles

## Allocation

- [x] Naive allocation
- [x] All in memory allocation
- [x] Massive tests of memory allocation


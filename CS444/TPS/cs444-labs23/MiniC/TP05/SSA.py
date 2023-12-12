#!/usr/bin/python3
# -*- coding: utf-8 -*-

"""
CAP, SSA Intro, Elimination and Optimisations
Classes for a SSA operations.
"""

from dataclasses import dataclass
from typing import cast, List, Dict, Set, Any, Tuple
from graphviz import Digraph
from Lib import RiscV
from Lib.Graphes import DiGraph
from Lib.CFG import (Block, BlockInstr, CFG)
from Lib.Operands import (
    Operand, Register, Temporary, DataLocation,
    Renamer)
from Lib.Statement import (
    Statement, Instruction, AbsoluteJump, Label)
from Lib.Terminator import (BranchingTerminator, Return)


@dataclass
class PhiNode(Statement):
    """ A phi node is a renaming """
    var: DataLocation
    srcs: Dict[Label, Operand]

    def defined(self):
        return [self.var]

    def used(self):
        return self.srcs

    def rename(self, renamer: Renamer):
        if isinstance(self.var, Temporary):
            self.var = renamer.fresh(self.var)

    def rename_from(self, renamer: Renamer, label: Label):
        if label in self.srcs:
            t = self.srcs[label]
            if isinstance(t, Temporary):
                if renamer.defined(t):
                    self.srcs[label] = renamer.replace(t)
                else:
                    del self.srcs[label]

    def __str__(self):
        return "{} = φ({})".format(self.var, self.srcs)

    def __hash__(self):
        return hash((self.var, *self.srcs.items()))

    def printIns(self, stream):
        print('        # ' + str(self), file=stream)


def computeDom(function: CFG) -> Dict[Block, Set[Block]]:
    """
    `computeDom(function)` computes the table associating nodes to their
    dominators in `function`.

    Works by solving the equation system
    """
    all_blocks: Set[Block] = set(function.get_blocks())
    dominators: Dict[Block, Set[Block]] = dict()
    for b in all_blocks:
        if b._in:
            dominators[b] = all_blocks
        else:
            dominators[b] = {b}
    new_dominators: Dict[Block, Set[Block]] = dict()
    while True:
        for b in all_blocks:
            pass # TODO compute new_dominators
        if dominators == new_dominators:
            break
        else:
            dominators = new_dominators
            new_dominators = dict()
    return dominators


def computeDT(function: CFG, dominators) -> Dict[Block, Set[Block]]:
    """
    `computeDT(function, dominators)` computes the domination tree of `function`
    using the previously computed `dominators`.

    returns `DT`, a dictionnary which associates a node with its children
    in the dominator tree.
    """
    # First, compute the immediate dominators
    idominators: Dict[Block, Block] = {}
    for b, doms in dominators.items():
        # The immediate dominator of b is the unique vertex n ≠ b
        # which dominates b and is dominated by all vertices in Dom(b) − b.
        strict_doms = doms - {b}
        idoms = set()
        for n in strict_doms:
            if strict_doms.issubset(dominators[n]):
                idoms.add(n)
        if idoms:
            assert (len(idoms) == 1)
            idominators[b] = idoms.pop()
    # Then, simply inverse the relation to obtain the domination tree
    DT = {b: set() for b in function.get_blocks()}
    for i, idominator in idominators.items():
        DT[idominator].add(i)
    return DT


def computeDF_at_node(
        function: CFG,
        dominators: Dict[Block, Set[Block]],
        DT: Dict[Block, Set[Block]],
        b: Block,
        DF: Dict[Block, Set[Block]]) -> None:
    """
    `computeDF_at_node(...)` computes the dominance frontier at the given
    node.
    Complete the dictionnary `DF` which associates a node to its frontier.
    """
    S: Set[Block] = set()
    # TODO compute S
    DF[b] = S


def computeDF(function: CFG, dominators, DT) -> Dict[Block, Set[Block]]:
    """
    `computeDF(...)` computes the dominance frontier of a function.
    Return `DF` which associates a node to its frontier.
    """
    DF: Dict[Block, Set[Block]] = dict()
    for b_entry in function.get_entries():
        computeDF_at_node(function, dominators, DT, b_entry, DF)
    return DF


def insertPhis(function: CFG, DF: Dict[Block, Set[Block]]):
    """
    'insertPhis(CFG, DF)' inserts phi nodes in 'CFG' where needed.
    """
    for var, defs in function.gather_defs().items():
        has_phi: Set[Block] = set()
        queue: List[Block] = list(defs)
        while queue:
            d = queue.pop(0)
            for b in DF[d]:
                if b not in has_phi:
                    pass # TODO add a phi node to 'b'


def rename_node(function: CFG, DT, renamer: Renamer, b: Block):
    renamer = renamer.copy()
    for i in b.get_all_statements():
        if isinstance(i, Instruction | PhiNode):
            i.rename(renamer)
    for succ in b.get_terminator().targets():
        b_succ = function.get_block(succ)
        for i in b_succ._phis:
            assert (isinstance(i, PhiNode))
            i.rename_from(renamer, b.get_label())
    for b_succ in DT[b]:
        rename_node(function, DT, renamer, b_succ)


def rename_variables(function: CFG, DT):
    for b in function.get_entries():
        rename_node(function, DT, Renamer(function.fdata._pool), b)


def print_ssa_graph(basename, fname, comment, graph):  # pragma: no cover
    dot = Digraph(comment=comment)
    for k in graph:
        dot.node(str(k.get_label()))
    for k in graph:
        for v in graph[k]:
            dot.edge(str(k.get_label()), str(v.get_label()))
    dot.render(f"{basename}.{fname}.ssa.{comment}.dot", view=True)


def enter_ssa(function: CFG, basename="prog", debug=False, debug_graphs=False):
    # TODO (lab5): Move the raise statement below down as you progress
    # TODO (lab5): in the lab. It must be removed from the final version.
#    raise NotImplementedError("run enter SSA: stopping here for now")

    # Compute the dominators
    dominators = computeDom(function)
    if debug:
        print("SSA - dominators:", dominators)

    # Compute the domination tree
    DT = computeDT(function, dominators)
    if debug:
        print("SSA - domination tree:", DT)
    if debug_graphs:
        print_ssa_graph(basename, function.fdata.get_name(), "DT", DT)

    # Compute the dominance frontier
    DF = computeDF(function, dominators, DT)
    if debug:
        print("SSA - dominance frontier:", DF)

    # Insert phi nodes
    insertPhis(function, DF)

    # Rename variables
    rename_variables(function, DT)
    return DF


def generate_moves_from_phis(phis: List[PhiNode], parent: Block) -> List[BlockInstr]:
    """
    'generate_moves_from_phis(phis, parent)' builds a list of move instructions
    to be inserted in a new block between 'parent' and the block with phi nodes
    'phis'. This is an helper function called during SSA exit.
    """
    moves: List[BlockInstr] = []
    # TODO compute 'moves', a list of 'mv' instructions to insert under parent
    # 'rename_variables' has already set the right temporaries in the phi nodes
    return moves


def exit_ssa(function: CFG):
    """
    'exit_ssa(function)' replaces phi nodes with move instructions
    to exit SSA form.
    """
    for b in function.get_blocks():
        phis = cast(List[PhiNode], b._phis)
        parents: List[Block] = [p for p in b._in]
        b._phis = []
        for parent in parents:
            moves = generate_moves_from_phis(phis, parent)
            # TODO Add the block containing 'moves' to 'function'
            # and update edges and jumps accordingly


def generate_smart_move(dest: Operand, src: Operand) -> List[BlockInstr]:
    """Generate a list of move, store and load instructions, depending if the
    operands are registers or memory locations"""
    instr: List[BlockInstr] = []
    return instr


def sequentialize_moves(tmp: Register,
                        parallel_moves: Set[Tuple[Any, Any]]) -> List[Tuple[Any, Any]]:
    """Take a set of parallel moves represented as (destination, source) pairs,
    and return a list of sequential moves which respect the cycles.
    Use the specified tmp for cycles.
    Returns a list of (destination, source) pairs"""
    move_graph = DiGraph()
    for dest, src in parallel_moves:
        move_graph.add_edge((src, dest))
    moves = []
    # First iteratively remove all the edges without successors.
    vars_without_successor = {src
                              for src, dests in move_graph.neighbourhoods()
                              if len(dests) == 0}
    while vars_without_successor:
        var = vars_without_successor.pop()
        for src, dests in move_graph.neighbourhoods():
            if var in dests:
                moves.append((var, src))
                dests.remove(var)
                if len(dests) == 0:
                    vars_without_successor.add(src)
        move_graph.delete_vertex(var)
    # Then handle the cycles.
    cycles = move_graph.connex_components()
    for cycle in cycles:
        if len(cycle) == 1:
            v = cycle.pop()
            moves.append((v, v))
        else:
            previous = tmp
            for v in reversed(cycle):
                moves.append((previous, v))
                previous = v
            moves.append((previous, tmp))
    return moves

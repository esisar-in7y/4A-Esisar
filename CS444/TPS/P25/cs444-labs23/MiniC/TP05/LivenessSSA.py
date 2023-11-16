from typing import Dict, Set, Tuple
from Lib.Operands import Operand
from Lib.Statement import Statement, regset_to_string
from Lib.CFG import Block, Terminator, CFG
from TP05.SSA import PhiNode


class LivenessSSA:

    def __init__(self, function: CFG, debug=False):
        self._function = function
        self._debug = debug
        self._seen: Dict[Block, Set[Operand]] = dict()
        # Live Operands at outputs of instructions
        self._liveout: Dict[Statement, Set[Operand]] = dict()

    def run(self):
        # Initialization
        for block in self._function.get_blocks():
            self._seen[block] = set()
            for instr in block.get_all_statements():
                self._liveout[instr] = set()
        # Start the use-def chains
        for var, uses in self.gather_uses().items():
            for block, pos, instr in uses:
                self.live_start(block, pos, instr, var)
        # Add conflicts on phis
        self.conflict_on_phis()
        # Final debuging print
        if self._debug:
            self.print_map_in_out()

    def live_start(self, block: Block, pos: int | None,
                   s: Statement, op: Operand) -> None:
        """Start backward propagation of liveness information"""
        if isinstance(s, PhiNode):
            for label, var in s.used().items():
                if label == op:
                    prev_block = self._function.get_block(label)
                    self.liveout_at_block(prev_block, var)
        else:
            if pos is None:
                assert (isinstance(s, Terminator))
                self.livein_at_terminator(block, op)
            else:
                self.livein_at_instruction(block, pos, op)

    def liveout_at_block(self, block: Block, var: Operand) -> None:
        """Backward propagation of liveness information at a block."""
        pass # TODO (lab5b, exercise 1)

    def liveout_at_terminator(self, block: Block, var: Operand) -> None:
        """Backward propagation of liveness information at a terminator."""
        instr = block.get_terminator()
        # TODO (lab5b, exercise 1)

    def livein_at_terminator(self, block: Block, var: Operand) -> None:
        """Backward propagation of liveness information at a terminator."""
        pass # TODO (lab5b, exercise 1)

    def liveout_at_instruction(self, block: Block, pos: int, var: Operand) -> None:
        """Backward propagation of liveness information at an instruction."""
        instr = block.get_body()[pos]
        # TODO (lab5b, exercise 1)

    def livein_at_instruction(self, block: Block, pos: int, var: Operand) -> None:
        """Backward propagation of liveness information at an instruction."""
        pass # TODO (lab5b, exercise 1)

    def gather_uses(self) -> Dict[Operand, Set[Tuple[Block, int | None, Statement]]]:
        uses: Dict[Operand, Set[Tuple[Block, int | None, Statement]]] = dict()
        for block in self._function.get_blocks():
            for instr in block._phis:
                assert (isinstance(instr, PhiNode))
                for label in instr.used():
                    if label is not None:
                        var_uses = uses.get(label, set())
                        uses[label] = var_uses.union({(block, None, instr)})
            for pos, instr in enumerate(block.get_body()):
                for var in instr.used():
                    assert (var is not None)
                    var_uses = uses.get(var, set())
                    uses[var] = var_uses.union({(block, pos, instr)})
            instr = block.get_terminator()
            for var in instr.used():
                if var is not None:
                    var_uses = uses.get(var, set())
                    uses[var] = var_uses.union({(block, None, instr)})
        return uses

    def conflict_on_phis(self):
        """Ensures that variables defined by phi instructions are in conflict
        with one-another"""
        for b in self._function.get_blocks():
            phis = b._phis
            previous_vars = set()
            for phi in phis:
                previous_vars.update(phi.defined())
                self._liveout[phi].update(previous_vars)

    def print_map_in_out(self):  # pragma: no cover
        """Print out sets, useful for debug!"""
        print("Out: {" +
              ",\n ".join("\"{}\": {}"
                          .format(x, regset_to_string(self._liveout[x]))
                          for x in self._liveout.keys()) +
              "}")

from Lib.Operands import Temporary, Operand, S, Register, DataLocation, GP_REGS, FP
from Lib.Statement import Instruction
from Lib.Allocator import Allocator
from Lib import RiscV
from Lib.Graphes import Graph  # For Graph coloring utility functions
from Lib.Errors import MiniCInternalError
from typing import List, Dict


class SmartAllocator(Allocator):
    _igraph: Graph  # interference graph

    def __init__(self, fdata, basename, liveness, debug=False, debug_graphs=False):
        self._liveness = liveness
        self._basename = basename
        self._debug = debug
        self._debug_graphs = debug_graphs
        super().__init__(fdata)

    def replace(self, old_instr: Instruction) -> List[Instruction]:
        """Replace Temporary operands with the corresponding allocated
        physical register OR memory location."""
        before: List[Instruction] = []
        after: List[Instruction] = []
        subst: Dict[Operand, Operand] = {}
        # TODO (lab5): compute before,after,args. This is similar to what
        # TODO (lab5): replace_mem and replace_reg from TP04 do.
        # and now return the new list!
        num_registre = 1
        old_instrs = old_instr.args()
        for arg in old_instrs:
            old_arg=arg
            if isinstance(arg, Temporary) :
                loc = arg.get_alloced_loc()
                if loc not in GP_REGS: 
                    if not old_instr.is_read_only():
                        if num_registre == 1:
                            after.append(Instruction('sd', S[num_registre], loc))
                        else:
                            before.append(Instruction('ld', S[num_registre], loc))
                    else:
                        arg = S[num_registre]
                        before.append(Instruction('ld', S[num_registre], loc))
                else:
                    arg = arg.get_alloced_loc()
                subst[old_arg]=arg
                num_registre += 1
        instr = old_instr.substitute(subst)
        return before + [instr] + after

    def prepare(self):
        """Perform all steps related to smart register allocation:

        - Dataflow analysis to compute liveness range of each
          temporary.

        - Interference graph construction

        - Graph coloring

        - Substitution of temporaries by actual locations in the
          3-address code.
        """
        # TODO (lab5): Move the raise statement below down as you progress
        # TODO (lab5): in the lab. It must be removed from the final version.
        # liveness analysis
        self._liveness.run()

        # conflict graph
        self.build_interference_graph()

        if self._debug_graphs:
            print("printing the conflict graph")
            self._igraph.print_dot(self._basename + "_conflicts.dot")
        if self._debug:
            self._liveness.print_gen_kill()
        # Smart Alloc via graph coloring
        self.smart_alloc(self._basename + "_colored.dot")

    def interfere(self, t1, t2):
        """Interfere function: True if t1 and t2 are in conflit anywhere in
        the function."""
        for _, set in self._liveness._liveout.items() :
            if t1 in set and t2 in set:
                return True
        return False

    def build_interference_graph(self):
        """Build the interference graph. Nodes of the graph are temporaries,
        and an edge exists between temporaries iff they are in conflict (i.e.
        iff self.interfere(t1, t2)."""
        self._igraph = Graph()
        t = self._fdata._pool.get_all_temps()
        for v in t:
            # print("add vertex "+str(v))
            self._igraph.add_vertex(v)
        tpairs = [(p1, p2) for p1 in t for p2 in t]
        # print(tpairs)
        for (t1, t2) in tpairs:
            if t1 == t2:
                continue  # A temporary cannot conflict with itself
            if self.interfere(t1, t2):
                # print("add edge "+str(t1)+" - "+str(t2))
                self._igraph.add_edge((t1, t2))

    def smart_alloc(self, outputname):
        """Allocate all temporaries with graph coloring
        also prints the colored graph if debug.

        Precondition: the interference graph (_igraph) must have been
        initialized.
        """
        if not self._igraph:
            raise MiniCInternalError("hum, the interference graph seems to be empty")
        # Temporary -> Operand (register or offset) dictionary,
        # specifying where a given Temporary should be allocated:
        alloc_dict = {}
        # TODO (lab5): color the graph and get back the coloring (see
        # Graph.color() in LibGraphes.py). Then, construct a dictionary Temporary ->
        # Register or Offset. Our version is less than 15 lines
        # including debug log. You can get all temporaries with
        # self._fdata._pool.get_all_temps.
        coloringreg = self._igraph.color()
        if self._debug_graphs:
            print("coloring = " + str(coloringreg))
            self._igraph.print_dot(outputname, coloringreg)
        
        for reg in self._fdata._pool._all_temps:
            if coloringreg[reg] >= len(GP_REGS):
                alloc_dict[reg] = self._igraph.new_offset(FP)
            else:
                alloc_dict[reg] = GP_REGS[len(GP_REGS) - 1 - coloringreg[reg]]
        print(alloc_dict)
        self._fdata._pool.set_temp_allocation(alloc_dict)
        self._igraph._stacksize = self._fdata.get_offset()
        if self._debug:
            print("Allocation:")
            print(alloc_dict)
        self._fdata._pool.set_temp_allocation(alloc_dict)

from Lib import RiscV
from Lib.Operands import Temporary, Operand, S
from Lib.Statement import Instruction
from Lib.Allocator import Allocator
from typing import List, Dict


class AllInMemAllocator(Allocator):

    def replace(self, old_instr: Instruction) -> List[Instruction]:
        """ Replace the current 3A instruction with its allocated version.
        A given instruction could be replaced by more than one instruction, 
        for instance ins = "li tmp0 7" should be replaced by before+[ins']+after
        where : before is empty; ins'= li s2 7, after=[sd s2 <somewhere>]
        """
        numreg = 1
        before: List[Instruction] = []
        after: List[Instruction] = []
        subst: Dict[Operand, Operand] = {}
        # TODO (Exercise 7): compute before, after (lists of instructions)
        # TODO (Exercise 7): 
        # TODO (Exercise 7): iterate over old_args, check which argument
        # TODO (Exercise 7): is a temporary (e.g. isinstance(..., Temporary)),
        # TODO (Exercise 7): and if so, generate ld/sd accordingly. Replace the
        # TODO (Exercise 7): temporary with S[1], S[2] or S[3] physical registers.
        
        #before
        for data in old_instr.used():
            if isinstance(data,Temporary):
                before.append(RiscV.ld( S[numreg+1], data.get_alloced_loc()))
                subst[data] = S[numreg+1]
                numreg=(numreg+1)%3
        
        #after
        for data in old_instr.defined():
            if isinstance(data,Temporary):
                after.append(RiscV.sd( S[numreg+1], data.get_alloced_loc()))
                subst[data] = S[numreg+1]
                numreg=(numreg+1)%3
        
        new_instr = old_instr.substitute(subst)
        return before + [new_instr] + after

    def prepare(self):
        """Allocate all temporaries to memory.
        Invariants:
        - Expanded instructions can use s2 and s3
          (to store the values of temporaries before the actual instruction).
        """
        self._fdata._pool.set_temp_allocation(
            {temp: self._fdata.fresh_offset()
             for temp in self._fdata._pool.get_all_temps()})

from typing import List, Tuple
from MiniCVisitor import MiniCVisitor
from MiniCParser import MiniCParser
from Lib.LinearCode import LinearCode
from Lib import RiscV
from Lib.RiscV import Condition
from Lib import Operands
from antlr4.tree.Trees import Trees
from Lib.Errors import MiniCInternalError, MiniCUnsupportedError, MiniCRuntimeError

"""
CAP, MIF08, three-address code generation + simple alloc
This visitor constructs an object of type "LinearCode".
"""


class MiniCCodeGen3AVisitor(MiniCVisitor):

    _current_function: LinearCode

    def __init__(self, debug, parser):
        super().__init__()
        self.has_main = False
        self._parser = parser
        self._debug = debug
        self._functions = []
        self._lastlabel = ""

    def get_functions(self) -> List[LinearCode]:
        return self._functions

    def printSymbolTable(self):  # pragma: no cover
        print("--variables to temporaries map--")
        for keys, values in self._symbol_table.items():
            print(keys + '-->' + str(values))

    # handle variable decl

    def visitVarDecl(self, ctx) -> None:
        type_str = ctx.typee().getText()
        vars_l = self.visit(ctx.id_l())
        for name in vars_l:
            if name in self._symbol_table:
                raise MiniCInternalError("Variable {} has already been declared".format(name))
            else:
                tmp = self._current_function.fdata.fresh_tmp()
                self._symbol_table[name] = tmp
                if type_str not in ("int", "bool"):
                    raise MiniCUnsupportedError("Unsupported type " + type_str)
                # Initialization to 0 or False, both represented with 0
                self._current_function.add_instruction(
                    RiscV.li(tmp, Operands.Immediate(0)))

    def visitIdList(self, ctx) -> List[str]:
        t = self.visit(ctx.id_l())
        t.append(ctx.ID().getText())
        return t

    def visitIdListBase(self, ctx) -> List[str]:
        return [ctx.ID().getText()]

    # expressions

    def visitParExpr(self, ctx) -> Operands.Temporary:
        return self.visit(ctx.expr())

    def visitIntAtom(self, ctx) -> Operands.Temporary:
        val = Operands.Immediate(int(ctx.getText()))
        dest_temp = self._current_function.fdata.fresh_tmp()
        self._current_function.add_instruction(RiscV.li(dest_temp, val))
        return dest_temp

    def visitFloatAtom(self, ctx) -> Operands.Temporary:
        raise MiniCUnsupportedError("float literal")

    def visitBooleanAtom(self, ctx) -> Operands.Temporary:
        # true is 1 false is 0
        # DONE
        valeur = 1 if ctx.getText() == 'true' else 0
        dest_temp = self._current_function.fdata.fresh_tmp()
        self._current_function.add_instruction(RiscV.li(dest_temp, valeur))
        return dest_temp

    def visitIdAtom(self, ctx) -> Operands.Temporary:
        try:
            # get the temporary associated to id
            return self._symbol_table[ctx.getText()]
        except KeyError:  # pragma: no cover
            raise MiniCInternalError(
                "Undefined variable {}, this should have failed to typecheck."
                .format(ctx.getText())
            )

    def visitStringAtom(self, ctx) -> Operands.Temporary:
        raise MiniCUnsupportedError("string atom")

    # now visit expressions

    def visitAtomExpr(self, ctx) -> Operands.Temporary:
        return self.visit(ctx.atom())

    def visitAdditiveExpr(self, ctx) -> Operands.Temporary:
        assert ctx.myop is not None
        #DONE
        tmp1 = self.visit(ctx.expr(0))
        tmp2 = self.visit(ctx.expr(1))
        dest = self._current_function.fdata.fresh_tmp()
        if ctx.myop.type == MiniCParser.PLUS:
            self._current_function.add_instruction(RiscV.add(dest, tmp1, tmp2))
        else:
            self._current_function.add_instruction(RiscV.sub(dest, tmp1, tmp2))
        return dest


    def visitOrExpr(self, ctx) -> Operands.Temporary:
        #DONE
        tmp1 = self.visit(ctx.expr(0))
        tmp2 = self.visit(ctx.expr(1))
        dest = self._current_function.fdata.fresh_tmp()
        self._current_function.add_instruction((RiscV.lor(dest, tmp1, tmp2)))
        return dest



    def visitAndExpr(self, ctx) -> Operands.Temporary:
        #DONE
        tmp1 = self.visit(ctx.expr(0))
        tmp2 = self.visit(ctx.expr(1))
        dest = self._current_function.fdata.fresh_tmp()
        self._current_function.add_instruction((RiscV.land(dest, tmp1, tmp2)))
        return dest


    def visitEqualityExpr(self, ctx) -> Operands.Temporary:
        return self.visitRelationalExpr(ctx)

    def visitRelationalExpr(self, ctx) -> Operands.Temporary:
        # DONE
        assert ctx.myop is not None
        c = Condition(ctx.myop.type)
        if self._debug:
            print("relational expression:")
            print(Trees.toStringTree(ctx, None, self._parser))
            print("Condition:", c)
        dest = self._current_function.fdata.fresh_tmp()
        tmp1 = self.visit(ctx.expr(0))
        tmp2 = self.visit(ctx.expr(1))
        endrel = self._current_function.fdata.fresh_label("end_if")
        self._current_function.add_instruction(RiscV.li(dest, 0))
        self._current_function.add_instruction(RiscV.conditional_jump(endrel, tmp1, c.negate(), tmp2))
        self._current_function.add_instruction(RiscV.li(dest, 1))
        self._current_function.add_label(endrel)
        return dest

    def visitMultiplicativeExpr(self, ctx) -> Operands.Temporary:
        assert ctx.myop is not None
        div_by_zero_lbl = self._current_function.fdata.get_label_div_by_zero()
        #DONE
        tmp1 = self.visit(ctx.expr(0))
        tmp2 = self.visit(ctx.expr(1))
        dest = self._current_function.fdata.fresh_tmp()
        if ctx.myop.type == MiniCParser.MULT:
            self._current_function.add_instruction(RiscV.mul(dest, tmp1, tmp2))
        elif ctx.myop.type == MiniCParser.DIV:
            self._current_function.add_instruction(RiscV.conditional_jump(div_by_zero_lbl, tmp2, Condition("beq"), Operands.ZERO))
            self._current_function.add_instruction(RiscV.div(dest, tmp1, tmp2))
        elif ctx.myop.type == MiniCParser.MOD:
            self._current_function.add_instruction(RiscV.rem(dest, tmp1, tmp2))
        return dest

    def visitNotExpr(self, ctx) -> Operands.Temporary:
        # DONE
        tmp1 = self.visit(ctx.expr())
        dest = self._current_function.fdata.fresh_tmp()
        self._current_function.add_instruction(RiscV.xor(dest, tmp1, Operands.Immediate(1)))
        return dest

    def visitUnaryMinusExpr(self, ctx) -> Operands.Temporary:
        #DONE
        tmp1 = self.visit(ctx.expr())
        dest = self._current_function.fdata.fresh_tmp()
        self._current_function.add_instruction((RiscV.sub(dest, Operands.ZERO, tmp1)))
        return dest

    def visitProgRule(self, ctx) -> None:
        self.visitChildren(ctx)
        if not self.has_main:
            raise MiniCRuntimeError("No main function in file")

    def visitFuncDef(self, ctx) -> None:
        funcname = ctx.ID().getText()
        if funcname == "main":
            self.has_main = True
        self._current_function = LinearCode(funcname)
        self._symbol_table = dict()

        self.visit(ctx.vardecl_l())
        self.visit(ctx.block())
        self._current_function.add_comment("Return at end of function:")
        # This skeleton doesn't deal properly with functions, and
        # hardcodes a "return 0;" at the end of function. Generate
        # code for this "return 0;".
        self._current_function.add_instruction(
        	RiscV.li(Operands.A0, Operands.Immediate(0)))
        self._functions.append(self._current_function)
        del self._current_function

    def visitAssignStat(self, ctx) -> None:
        if self._debug:
            print("assign statement, rightexpression is:")
            print(Trees.toStringTree(ctx.expr(), None, self._parser))
        expr_temp = self.visit(ctx.expr())
        name = ctx.ID().getText()
        self._current_function.add_instruction(RiscV.mv(self._symbol_table[name], expr_temp))

    def visitIfStat(self, ctx) -> None:
        if self._debug:
            print("if statement")
        else_label = self._current_function.fdata.fresh_label("else")
        end_if_label = self._current_function.fdata.fresh_label("end_if")
        #DONE
        tmp1 = self.visit(ctx.expr())
        self._current_function.add_instruction(RiscV.conditional_jump(else_label, tmp1, Condition("beq"), Operands.ZERO))
        self.visitStat(ctx.then_block)
        self._current_function.add_instruction(RiscV.jump(end_if_label))
        self._current_function.add_label(else_label)
        if ctx.else_block:
            self.visitStat(ctx.else_block)
        self._current_function.add_label(end_if_label)

    def visitWhileStat(self, ctx) -> None:
        if self._debug:
            print("while statement, condition is:")
            print(Trees.toStringTree(ctx.expr(), None, self._parser))
            print("and block is:")
            print(Trees.toStringTree(ctx.stat_block(), None, self._parser))
        #DONE
        test_label = self._current_function.fdata.fresh_label("test")
        end_while_label = self._current_function.fdata.fresh_label("end_while")
        self._current_function.add_label(test_label)
        tmp1 = self.visit(ctx.expr())
        self._current_function.add_instruction(RiscV.conditional_jump(end_while_label, tmp1, Condition("beq"), Operands.ZERO))
        self.visitStat(ctx.stat_block())
        self._current_function.add_instruction(RiscV.jump(test_label))
        self._current_function.add_label(end_while_label)
    # visit statements

    def visitPrintlnintStat(self, ctx) -> None:
        expr_loc = self.visit(ctx.expr())
        if self._debug:
            print("print_int statement, expression is:")
            print(Trees.toStringTree(ctx.expr(), None, self._parser))
        self._current_function.add_instruction_PRINTLN_INT(expr_loc)

    def visitPrintlnboolStat(self, ctx) -> None:
        expr_loc = self.visit(ctx.expr())
        self._current_function.add_instruction_PRINTLN_INT(expr_loc)

    def visitPrintlnfloatStat(self, ctx) -> None:
        raise MiniCUnsupportedError("Unsupported type float")

    def visitPrintlnstringStat(self, ctx) -> None:
        raise MiniCUnsupportedError("Unsupported type string")

    def visitStatList(self, ctx) -> None:
        for stat in ctx.stat():
            self._current_function.add_comment(Trees.toStringTree(stat, None, self._parser))
            self.visit(stat)

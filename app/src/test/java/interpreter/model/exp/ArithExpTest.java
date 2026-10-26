package interpreter.model.exp;

import interpreter.exceptions.ExpressionException;
import interpreter.exceptions.MyException;
import interpreter.model.adt.IDictionary;
import interpreter.model.adt.MyDictionary;
import interpreter.model.value.BoolValue;
import interpreter.model.value.IValue;
import interpreter.model.value.IntValue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArithExpTest {
    private IDictionary<String, IValue> symTable;

    private static IExp num(int n) {
        return new ValueExp(new IntValue(n));
    }

    @BeforeEach
    void setUp() {
        symTable = new MyDictionary<>();
    }

    @Test void addition() throws MyException {
        assertEquals(new IntValue(5),new ArithExp(ArithOp.ADD,num(2),num(3)).eval(symTable));
    }
    @Test void substraction() throws MyException {
        assertEquals(new IntValue(2),new ArithExp(ArithOp.SUB,num(5),num(3)).eval(symTable));
    }
    @Test void multiplication() throws MyException {
        assertEquals(new IntValue(10),new ArithExp(ArithOp.MUL,num(5),num(2)).eval(symTable));
    }
    @Test void integerDivision() throws MyException {
        assertEquals(new IntValue(5),new ArithExp(ArithOp.DIV,num(11),num(2)).eval(symTable));
    }
    @Test void respectsOperatorOrderInTree() throws MyException {
        assertEquals(new IntValue(17),new ArithExp(ArithOp.ADD,num(2),new ArithExp(ArithOp.MUL,num(3),num(5))).eval(symTable));
    }
    @Test void divisionByZeroThrows() {
        IExp e = new ArithExp(ArithOp.DIV,num(1),num(0));
        var ex = assertThrows(ExpressionException.class, ()->e.eval(symTable));
        assertEquals("division by zero",ex.getMessage());
    }
    @Test void nonIntegerFirstOperandThrows() {
        IExp e = new ArithExp(ArithOp.ADD,new ValueExp(new BoolValue(true)),num(1));
        var ex = assertThrows(ExpressionException.class, ()->e.eval(symTable));
        assertEquals("first operand is not an integer",ex.getMessage());
    }
    @Test void nonIntegerSecondOperandThrows() {
        IExp e = new ArithExp(ArithOp.ADD,num(1),new ValueExp(new BoolValue(true)));
        var ex = assertThrows(ExpressionException.class, ()->e.eval(symTable));
        assertEquals("second operand is not an integer",ex.getMessage());
    }
    @Test void useVariableValues() throws MyException{
        symTable.put("w",new IntValue(1));
        assertEquals(new IntValue(3),new ArithExp(ArithOp.ADD,new VarExp("w"),num(2)).eval(symTable));
    }

}
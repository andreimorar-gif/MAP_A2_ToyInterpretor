package interpreter.model.exp;

import interpreter.exceptions.ExpressionException;
import interpreter.exceptions.MyException;
import interpreter.model.adt.IDictionary;
import interpreter.model.adt.MyDictionary;
import interpreter.model.value.IValue;
import interpreter.model.value.IntValue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VarExpTest {
    private IDictionary<String, IValue> symTable;
    @BeforeEach
    void setUp() {
        symTable = new MyDictionary<>();
    }
    @Test void evaluatesToVariablesValue() throws MyException {
        symTable.put("w",new IntValue(2));
        assertEquals(new IntValue(2),new VarExp("w").eval(symTable));
    }
    @Test void undefinedVariableThrows() {
        var e = assertThrows(ExpressionException.class, ()->new VarExp("a").eval(symTable));
        assertEquals("variable a is not defined", e.getMessage());
    }

}
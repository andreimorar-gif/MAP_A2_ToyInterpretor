package interpreter.model.value;

import interpreter.model.type.BoolType;
import interpreter.model.type.IntType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ValueTest {
    @Test void intValueHasIntType() {
        assertEquals(new IntType(),new IntValue(2).getType());
    }
    @Test void boolValueHasBoolType() {
        assertEquals(new BoolType(), new BoolValue(true).getType());
    }
    @Test void valuesWithSameContentAreEqual() {
        assertEquals(new IntValue(2),new IntValue(2));
        assertEquals(new BoolValue(true), new BoolValue(true));
    }
    @Test void valuesWithDifferentContentAreNotEqual() {
        assertNotEquals(new IntValue(2), new IntValue(3));
        assertNotEquals(new BoolValue(false), new BoolValue(true));
    }
    @Test void valuesPrintAsText() {
        assertEquals("2", new IntValue(2).toString());
        assertEquals("true", new BoolValue(true).toString());
    }
}

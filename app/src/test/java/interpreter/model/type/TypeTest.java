package interpreter.model.type;

import interpreter.model.value.BoolValue;
import interpreter.model.value.IntValue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TypeTest {
    @Test void intDefaultValueIsZero() {
        assertEquals(new IntValue(0),new IntType().defaultValue());
    }
    @Test void boolDefaultValueIsFalse() {
        assertEquals(new BoolValue(false),new BoolType().defaultValue());
    }
    @Test void sameTypesAreEqual() {
        assertEquals(new IntType(),new IntType());
        assertEquals(new BoolType(), new BoolType());
    }
    @Test void differentTypesAreNotEqual() {
        assertNotEquals(new IntType(), new BoolType());
    }
    @Test void typesAsText() {
        assertEquals("int",new IntType().toString());
        assertEquals("bool",new BoolType().toString());
    }
}

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class CostTest {

    @ParameterizedTest
    @MethodSource ("validData")
    void deliveryCostCalc(double result, double distanceValue, boolean bigValue, boolean fragileValue, String loadValue) {
        Cost cost = new Cost();
        assertEquals(result, cost.deliveryCostCalc(distanceValue, bigValue, fragileValue, loadValue));
    }
    static Stream<Arguments> validData() {
        return Stream.of(
                arguments(400, 1, true, false, "очень высокая"),
                arguments(540, 2, false, true, "повышенная"),
                arguments(600, 6.6, true, true, "обычная"),
                arguments(420, 10.1, false, false, "высокая"),
                arguments(1120, 29.9, true, true, "очень высокая"),
                arguments(560, 30, true, false, "высокая"),
                arguments(480, 45, false, false, "повышенная")
        );
    }

    @ParameterizedTest
    @MethodSource ("invalidData")
    void deliveryCostCalcNegativeResult(double result, double distanceValue, boolean bigValue, boolean fragileValue, String loadValue) {
        Cost cost = new Cost();
        assertNotEquals(result, cost.deliveryCostCalc(distanceValue, bigValue, fragileValue, loadValue));
    }
    static Stream<Arguments> invalidData() {
        return Stream.of(
                arguments(400, 1.9, true, true, "очень высокая"),
                arguments(420, 0, false, true, "повышенная"),
                arguments(640, 1.9, false, false, "обычная"),
                arguments(420, 10, true, false, "обычная"),
                arguments(980, 12, true, true, "очень высокая"),
                arguments(460, 30.1, false, false, "высокая"),
                arguments(400, 45, false, true, "повышенная")
        );
    }
}
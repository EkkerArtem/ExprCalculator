package org.javaclasses.calculator.impl.bracers;

import org.javaclasses.calculator.impl.MathExpressionElementParser;
import org.javaclasses.calculator.impl.MathExpressionPosition;
import org.javaclasses.calculator.impl.MathExpressionReader;

import java.util.Optional;

public class ClosingBracerParser implements MathExpressionElementParser<ClosingBracerElement> {

    private static final String CLOSING_BRACER = ")";

    @Override
    public Optional<ClosingBracerElement> parse(MathExpressionReader reader) {

        if (reader.getRemainingExpression().startsWith(CLOSING_BRACER)) {

            MathExpressionPosition parsePosition = reader.getParsePosition();
            reader.movePosition(CLOSING_BRACER.length());

            return Optional.of(new ClosingBracerElement(parsePosition));
        }

        return Optional.empty();
    }
}

package org.javaclasses.calculator.impl.bracers;

import org.javaclasses.calculator.impl.MathExpressionElementParser;
import org.javaclasses.calculator.impl.MathExpressionPosition;
import org.javaclasses.calculator.impl.MathExpressionReader;

import java.util.Optional;

public class OpeningBracerParser implements MathExpressionElementParser<OpeningBracerElement> {

    private static final String OPENING_BRACER = "(";

    @Override
    public Optional<OpeningBracerElement> parse(MathExpressionReader reader) {

        if (reader.getRemainingExpression().startsWith(OPENING_BRACER)) {

            MathExpressionPosition parsePosition = reader.getParsePosition();
            reader.movePosition(OPENING_BRACER.length());

            return Optional.of(new OpeningBracerElement(parsePosition));
        }

        return Optional.empty();
    }
}

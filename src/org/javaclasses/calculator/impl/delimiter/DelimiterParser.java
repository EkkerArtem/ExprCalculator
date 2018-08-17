package org.javaclasses.calculator.impl.delimiter;

import org.javaclasses.calculator.impl.MathExpressionElementParser;
import org.javaclasses.calculator.impl.MathExpressionPosition;
import org.javaclasses.calculator.impl.MathExpressionReader;

import java.util.Optional;

public class DelimiterParser implements MathExpressionElementParser<DelimiterElement> {

    private static final String DELIMITER = " , ";

    @Override
    public Optional<DelimiterElement> parse(MathExpressionReader reader) {

        if (reader.getRemainingExpression().startsWith(DELIMITER)) {

            MathExpressionPosition parsePosition = reader.getParsePosition();
            reader.movePosition(DELIMITER.length());

            return Optional.of(new DelimiterElement(parsePosition));
        }

        return Optional.empty();
    }
}

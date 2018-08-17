package org.javaclasses.calculator.impl.finish;

import org.javaclasses.calculator.impl.MathExpressionElement;
import org.javaclasses.calculator.impl.MathExpressionElementParser;
import org.javaclasses.calculator.impl.MathExpressionReader;

import java.util.Optional;

public class EndOfExpressionParser implements MathExpressionElementParser<EndOfExpressionElement> {

    @Override
    public Optional<EndOfExpressionElement> parse(MathExpressionReader reader) {

        if (reader.hasMore()) {
            return Optional.empty();
        }

        return Optional.of(new EndOfExpressionElement(reader.getParsePosition()));
    }
}

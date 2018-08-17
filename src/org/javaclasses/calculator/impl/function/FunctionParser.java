package org.javaclasses.calculator.impl.function;

import org.javaclasses.calculator.impl.MathExpressionElementParser;
import org.javaclasses.calculator.impl.MathExpressionPosition;
import org.javaclasses.calculator.impl.MathExpressionReader;

import java.util.Optional;

public class FunctionParser implements MathExpressionElementParser<FunctionElement> {

    private final FunctionFactory factory = new FunctionFactory();

    @Override
    public Optional<FunctionElement> parse(MathExpressionReader reader) {

        String expression = reader.getRemainingExpression();

        for (String sign : factory.getAllSigns()) {
            if (expression.startsWith(sign)) {

                MathExpressionPosition parsePosition = reader.getParsePosition();



                reader.movePosition(sign.length());
                return Optional.of(new FunctionElement(parsePosition, factory.create(sign)));
            }
        }

        return Optional.empty();
    }
}

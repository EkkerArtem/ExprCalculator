package org.javaclasses.calculator.impl.operator;

import org.javaclasses.calculator.impl.MathExpressionElementParser;
import org.javaclasses.calculator.impl.MathExpressionPosition;
import org.javaclasses.calculator.impl.MathExpressionReader;

import java.util.Optional;

public class OperatorParser implements MathExpressionElementParser<OperatorElement> {

    private final BinaryOperatorFactory factory = new BinaryOperatorFactory();

    @Override
    public Optional<OperatorElement> parse(MathExpressionReader reader) {

        String expression = reader.getRemainingExpression();

        for (String sign : factory.getAllSigns()) {
            if (expression.startsWith(sign)) {

                MathExpressionPosition parsePosition = reader.getParsePosition();

                reader.movePosition(sign.length());
                return Optional.of(new OperatorElement(parsePosition, factory.create(sign)));
            }
        }

        return Optional.empty();
    }
}

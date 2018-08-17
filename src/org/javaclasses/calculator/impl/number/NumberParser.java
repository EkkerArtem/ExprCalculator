package org.javaclasses.calculator.impl.number;

import org.javaclasses.calculator.impl.MathExpressionElement;
import org.javaclasses.calculator.impl.MathExpressionElementParser;
import org.javaclasses.calculator.impl.MathExpressionPosition;
import org.javaclasses.calculator.impl.MathExpressionReader;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.Optional;

public class NumberParser implements MathExpressionElementParser<NumberElement> {

    private final NumberFormat numberFormat = new DecimalFormat("0.0");

    @Override
    public Optional<NumberElement> parse(MathExpressionReader reader) {

        ParsePosition parsePosition = new ParsePosition(0);
        Number value = numberFormat.parse(reader.getRemainingExpression(), parsePosition);

        if (parsePosition.getErrorIndex() > -1) {

            return Optional.empty();
        }

        MathExpressionPosition mathExpressionPosition = reader.getParsePosition();

        reader.movePosition(parsePosition.getIndex());

        return Optional.of(new NumberElement(mathExpressionPosition, value.doubleValue()));
    }
}

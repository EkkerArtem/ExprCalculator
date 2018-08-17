package org.javaclasses.calculator.impl;

import java.util.Optional;

public interface MathExpressionElementParser<Element extends MathExpressionElement> {

     Optional<Element> parse(MathExpressionReader reader);
}

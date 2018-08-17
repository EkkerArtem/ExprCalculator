package org.javaclasses.calculator.impl.operator;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class BinaryOperatorFactory {

    private final Map<String, BinaryOperator> operators = new HashMap<String, BinaryOperator>(){{

        put("+", new BinaryOperator() {
            @Override
            public double execute(double leftOperand, double rightOperand) {
                return leftOperand + rightOperand;
            }

            @Override
            public int getPriority() {
                return 2;
            }
        });

        put("-", new BinaryOperator() {
            @Override
            public double execute(double leftOperand, double rightOperand) {
                return leftOperand - rightOperand;
            }

            @Override
            public int getPriority() {
                return 2;
            }
        });

        put("*", new BinaryOperator() {
            @Override
            public double execute(double leftOperand, double rightOperand) {
                return leftOperand * rightOperand;
            }

            @Override
            public int getPriority() {
                return 3;
            }
        });

        put("/", new BinaryOperator() {
            @Override
            public double execute(double leftOperand, double rightOperand) {
                return leftOperand / rightOperand;
            }

            @Override
            public int getPriority() {
                return 3;
            }
        });

    }};

    public BinaryOperator create(String operatorSign) {

        if (!operators.containsKey(operatorSign)) {
            throw new IllegalArgumentException("Operator not found: " + operatorSign);
        }

        return operators.get(operatorSign);
    }

    public Set<String> getAllSigns() {
        return operators.keySet();
    }
}

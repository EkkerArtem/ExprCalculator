package org.javaclasses.calculator.impl.function;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class FunctionFactory {

    private final Map<String, Function> operators = new HashMap<String, Function>(){{

        put("sqrt(", new Function() {
            @Override
            public double execute(double... args) {
                return Math.sqrt(args[0]);
            }
        });

        put("pi(", new Function() {
            @Override
            public double execute(double... args) {
                return 3.14;
            }
        });

        put("log(", new Function() {
            @Override
            public double execute(double... args) {
                return Math.log(args[0]);
            }
        });

        put("max(", new Function() {
            @Override
            public double execute(double... args) {
                return Arrays.stream(args).max().getAsDouble();
            }
        });

        put("min(", new Function() {
            @Override
            public double execute(double... args) {
                return Arrays.stream(args).min().getAsDouble();
            }
        });

        put("pow(", new Function() {
            @Override
            public double execute(double... args) {
                return Math.pow(args[1],args[0]);
            }
        });


    }};

    public Function create(String operatorSign) {

        if (!operators.containsKey(operatorSign)) {
            throw new IllegalArgumentException("Operator not found: " + operatorSign);
        }

        return operators.get(operatorSign);
    }

    public Set<String> getAllSigns() {
        return operators.keySet();
    }
}

package design_patterns.factory_pattern;

import design_patterns.factory_pattern.operations.Operation;
import design_patterns.factory_pattern.operations.OperationFactory;

public class FactoryPatternTest
{
    public static void main(String[] args) throws Exception {
        Operation operation = OperationFactory.getOperation("+");
        operation.setNumberA(66.0);
        operation.setNumberB(22.0);
        System.out.println(operation.getResult());

        operation = OperationFactory.getOperation("*");
        operation.setNumberA(66.0);
        operation.setNumberB(22.0);
        System.out.println(operation.getResult());
    }
}

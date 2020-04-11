package design_patterns.factory_pattern.operations;

import design_patterns.factory_pattern.operations.*;

public class OperationFactory
{
    public static Operation getOperation(String operate)
    {
        Operation operation;
        switch (operate)
        {
            case "+":
                operation = new AddOperation();
                break;
            case "-":
                operation = new SubOperation();
                break;
            case "*":
                operation = new MulOperation();
                break;
            case "/":
                operation = new DivOperation();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + operate);
        }

        return operation;
    }
}

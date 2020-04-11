package design_patterns.factory_pattern.operations;

public class DivOperation extends Operation
{
    @Override
    public double getResult() throws Exception {
        if(numberB == 0)
            throw new Exception("Cannot divide to 0");

        return numberA / numberB;
    }
}

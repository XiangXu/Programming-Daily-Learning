package design_patterns.factory_pattern.operations;

public class AddOperation extends Operation
{
    @Override
    public double getResult()
    {
        return numberA + numberB;
    }
}

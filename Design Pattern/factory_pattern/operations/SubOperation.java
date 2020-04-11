package design_patterns.factory_pattern.operations;

public class SubOperation extends Operation
{
    @Override
    public double getResult()
    {
        return numberA - numberB;
    }
}

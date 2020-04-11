package design_patterns.factory_pattern.operations;

public class MulOperation extends Operation
{
    @Override
    public double getResult() {
        return numberA * numberB;
    }
}

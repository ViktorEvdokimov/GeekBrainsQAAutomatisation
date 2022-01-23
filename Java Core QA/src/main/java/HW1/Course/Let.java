package HW1.Course;

public abstract class Let {

    protected int length;

    public Let(int length) {
        this.length = length;
    }

    public Let() {
        setRandomLength();
    }

    public int getLength() {
        return length;
    }


    protected abstract void setRandomLength();

    @Override
    public abstract String toString();
}

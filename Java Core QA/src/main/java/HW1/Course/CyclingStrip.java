package HW1.Course;

public class CyclingStrip  extends Let {

    private double incline;

    public CyclingStrip() {
        super();
        setRandomIncline();
    }

    public CyclingStrip(int length, double incline) {
        super(length);
        this.incline = incline;
    }

    private void setRandomIncline (){
        incline = Math.random() * 2;
    }

    public double getIncline() {
        return incline;
    }

    protected void setRandomLength(){
        super.length = ((int) (Math.random() * 10 + 1)) * 500;
    }

    @Override
    public String toString() {
        return String.format("CyclingStrip length = %d with inline = %.2f", length, incline);
    }
}

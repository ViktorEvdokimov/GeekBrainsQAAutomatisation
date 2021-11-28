package HW1.Course;

public class RunningStrip extends Let {

    private double incline;

    public RunningStrip() {
        super();
        setRandomIncline();
    }

    public RunningStrip(int length, double incline) {
        super(length);
        this.incline = incline;
    }

    private void setRandomIncline (){
        incline = Math.random() * 2;
    }

    protected void setRandomLength(){
        super.length = ((int) (Math.random() * 10 + 1)) * 100;
    }


    public double getIncline() {
        return incline;
    }

    @Override
    public String toString() {
        return String.format("runningStrip length = %d with inline = %.2f", length, incline);
    }
}

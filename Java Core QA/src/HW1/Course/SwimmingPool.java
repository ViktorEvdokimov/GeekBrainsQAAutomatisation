package HW1.Course;

public class SwimmingPool extends Let {

    public SwimmingPool() {
        super();
    }


    protected void setRandomLength(){
        super.length = ((int) (Math.random() * 10 + 1)) * 20;
    }

    @Override
    public String toString() {
        return "SwimmingPool{" +
                "length=" + length +
                '}';
    }
}

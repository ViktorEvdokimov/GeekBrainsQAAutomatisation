package HW1.Course;

import java.util.ArrayList;
import java.util.List;

public class Course {

    private List<Let> lets;

    public Course(List<Let> lets) {
        this.lets = lets;
    }

    public Course(int letsCount) {
        lets = new ArrayList<>();
        setRandomLets(letsCount);
    }

    private void setRandomLets (int letsCount){
        for (int i = 0; i < letsCount; i++) {
            int letNumber = (int) (Math.random() * 3);
            switch (letNumber){
                case (0):
                    lets.add(new RunningStrip());
                    break;
                case (1):
                    lets.add(new CyclingStrip());
                    break;
                case (2):
                    lets.add(new SwimmingPool());
            }
        }
    }

    public List<Let> getLets() {
        return lets;
    }

    @Override
    public String toString() {
        return "Course{" +
                "lets=" + lets +
                '}';
    }
}

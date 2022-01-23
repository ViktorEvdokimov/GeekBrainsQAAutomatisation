package HW1;

import HW1.Course.*;
import org.w3c.dom.ranges.RangeException;

import java.util.List;

public class Member {

    private String name;
    private double swimSpeed;
    private double runSpeed;
    private double cyclingSpeed;

    public Member(String name) {
        this.name = name;
        setRandomParams ();
    }

    public Member(String name, float swimSpeed, float runSpeed, float cyclingSpeed) {
        this.name = name;
        this.swimSpeed = swimSpeed;
        this.runSpeed = runSpeed;
        this.cyclingSpeed = cyclingSpeed;
    }

    public double startRace (Course course){
        List<Let> lets = course.getLets();
        double time = 0;
        for (Let let : lets) {
            time += overcomingLet(let);
        }
        time = time / 60;
        System.out.printf("%s overcome race with  total time %.2fm.\n", name, time);
        return time;
    }

    private double overcomingLet (Let let){
        switch (let.getClass().getName()){
            case("HW1.Course.RunningStrip"):
                return ((RunningStrip) let).getIncline() * let.getLength() / runSpeed;
            case("HW1.Course.CyclingStrip"):
                return ((CyclingStrip) let).getIncline() * let.getLength() / cyclingSpeed;
            case("HW1.Course.SwimmingPool"):
                return let.getLength() / swimSpeed;
            default:
                throw new RuntimeException("Unknown let");
        }

    }

    private void setRandomParams (){
        this.runSpeed = Math.random() * 5 + 8;
        this.swimSpeed = Math.random() * 3 + 3;
        this.cyclingSpeed = Math.random() * 10 + 40;
    }

    @Override
    public String toString() {
        return "Member{" +
                "name='" + name + '\'' +
                ", swimSpeed=" + swimSpeed +
                ", runSpeed=" + runSpeed +
                ", cyclingSpeed=" + cyclingSpeed +
                '}';
    }

    public String getName() {
        return name;
    }
}

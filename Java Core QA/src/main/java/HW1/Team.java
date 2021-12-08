package HW1;

import HW1.Course.Course;
import HW1.Course.Let;

import java.util.ArrayList;
import java.util.List;

public class Team {

    private String teamName;
    private List<Member> members;

    public Team(String teamName, List<Member> members) {
        this.teamName = teamName;
        this.members = members;
    }

    public Team(String teamName, int membersCount) {
        members = new ArrayList<>();
        this.teamName = teamName;
        setRandomTeam(membersCount);
    }

    public void startRace (Course course){
        System.out.println("Team " + teamName + " start race:");
        System.out.println(course);
        double totalTime = 0;
        for (Member member : members) {
            totalTime += member.startRace(course);
        }
        System.out.printf("Team %s end race with total time %.2f m.\n", teamName, totalTime);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(teamName);
        sb.append("with members: ");
        for (Member member : members) {
            sb.append(member.getName());
            sb.append(", ");
        }
        sb.setLength(sb.length() - 2);
        return sb.toString();
    }

    private void setRandomTeam (int membersCount){
        for (int i = 0; i < membersCount; i++) {
            members.add(new Member("Member " + i));
        }
    }
}

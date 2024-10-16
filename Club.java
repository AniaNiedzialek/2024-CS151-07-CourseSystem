import java.util.ArrayList;
import java.util.List;

public class Club implements Registerable {
    private String clubName;
    private String description;
    private List<Student> members;
    private Campus campus;
    private int maxMembers;

    public Club(String clubName, String description, Campus campus, int maxMembers) {
        this.clubName = clubName;
        this.description = description;
        this.members = new ArrayList<>();
        this.campus = campus;
        this.maxMembers = maxMembers;
        campus.addClub(this);
    }

    public boolean addMember(Student student) {
        if (members.size() < maxMembers && !members.contains(student)) {
            members.add(student);
            return true;
        }
        return false;
    }

    public boolean removeMember(Student student) {
        return members.remove(student);
    }

    public List<Student> getMembers() {
        return new ArrayList<>(members);
    }

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Campus getCampus() {
        return campus;
    }

    public int getMaxMembers() {
        return maxMembers;
    }

    public void setMaxMembers(int maxMembers) {
        this.maxMembers = maxMembers;
    }

    public boolean isFull() {
        return members.size() >= maxMembers;
    }

    public String listMembers() {
        if (members.isEmpty()) {
            return "No students are in this club.";
        } else {
            StringBuilder memberList = new StringBuilder();
            for (int i = 0; i < members.size(); i++) {
                memberList.append(members.get(i).getName());
                if (i < members.size() - 1) {
                    memberList.append(", ");
                }
            }
            return memberList.toString();
        }
    }

    public int getMemberCount() {
        return members.size();
    }

    public boolean hasMember(Student student) {
        return members.contains(student);
    }

    public String toString() {
        return "Club: " + clubName
                + " (Members: " + getMemberCount() + "/" + maxMembers + ")";
    }

    @Override
    public void register(Student student) {
        if (student != null && !members.contains(student) && members.size() < maxMembers) {
            members.add(student);
            student.joinClub(this);  // Add this line (assuming Student class has a joinClub method)
            System.out.println(student.getName() + " has been registered for the club: " + this.getClubName());
        } else if (members.contains(student)) {
            System.out.println(student.getName() + " is already a member of the club: " + this.getClubName());
        } else if (members.size() >= maxMembers) {
            System.out.println("Club is full. Registration failed for " + student.getName());
        } else {
            System.out.println("Invalid student. Club registration failed.");
        }
    }

    @Override
    public void drop(Student student) {
        if (student != null && members.contains(student)) {
            members.remove(student);
            student.leaveClub(this);  // Add this line (assuming Student class has a leaveClub method)
            System.out.println(student.getName() + " has been dropped from the club: " + this.getClubName());
        } else if (!members.contains(student)) {
            System.out.println(student.getName() + " is not a member of the club: " + this.getClubName());
        } else {
            System.out.println("Invalid student. Club drop failed.");
        }
    }
}
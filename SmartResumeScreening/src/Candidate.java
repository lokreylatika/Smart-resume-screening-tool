public class Candidate {

    private String name;
    private String[] skills;
    private int experienceYears;
    private String[] certifications;
    private int score;

    public Candidate(String name,
                     String[] skills,
                     int experienceYears,
                     String[] certifications) {

        this.name = name;
        this.skills = skills;
        this.experienceYears = experienceYears;
        this.certifications = certifications;
        this.score = 0;
    }

    public String getName() {
        return name;
    }

    public String[] getSkills() {
        return skills;
    }

    public int getExperienceYears() {
        return experienceYears;
    }

    public String[] getCertifications() {
        return certifications;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void display() {

        System.out.println(
                "Candidate: " + name);

        System.out.print(
                "Skills: ");

        for(String skill : skills) {

            System.out.print(
                    skill + " ");
        }

        System.out.println();

        System.out.println(
                "Experience: "
                        + experienceYears
                        + " years");

        System.out.print(
                "Certifications: ");

        for(String cert :
                certifications) {

            System.out.print(
                    cert + " ");
        }

        System.out.println();

        System.out.println(
                "Score: " + score);
    }
}
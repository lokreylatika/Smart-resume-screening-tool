public class SkillExtractor {

    private static final String[] SKILL_DATABASE = {

            "Java",
            "Python",
            "SQL",
            "Spring Boot",
            "AWS",
            "Machine Learning",
            "Data Structures",
            "Algorithms",
            "JavaScript",
            "HTML",
            "CSS",
            "React",
            "NodeJS",
            "MySQL",
            "MongoDB",
            "C",
            "C++"
    };

    public static String[] extractSkills(
            String resumeText) {

        String[] temp =
                new String[50];

        int count = 0;

        for(String skill :
                SKILL_DATABASE) {

            if(KMP.search(
                    resumeText.toLowerCase(),
                    skill.toLowerCase())) {

                temp[count++] = skill;
            }
        }

        String[] result =
                new String[count];

        for(int i=0;i<count;i++) {

            result[i] = temp[i];
        }

        return result;
    }
}
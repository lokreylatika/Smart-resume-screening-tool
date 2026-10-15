import java.io.*;
import java.util.regex.*;

public class JobDescription {

    private String[] requiredSkills;
    private int requiredExperience;
    private String[] requiredCertifications;

    public JobDescription(String[] requiredSkills,
                          int requiredExperience,
                          String[] requiredCertifications) {

        this.requiredSkills = requiredSkills;
        this.requiredExperience = requiredExperience;
        this.requiredCertifications = requiredCertifications;
    }

    public String[] getRequiredSkills() {
        return requiredSkills;
    }

    public int getRequiredExperience() {
        return requiredExperience;
    }

    public String[] getRequiredCertifications() {
        return requiredCertifications;
    }

    public static JobDescription loadJobDescription(String filePath) {

        StringBuilder text = new StringBuilder();

        try {

            BufferedReader br =
                    new BufferedReader(
                            new FileReader(filePath));

            String line;

            while ((line = br.readLine()) != null) {

                text.append(line).append(" ");
            }

            br.close();

        } catch (Exception e) {

            System.out.println(
                    "Error Reading Job Description");
        }

        String fullText = text.toString();

        String[] skills =
                SkillExtractor.extractSkills(
                        fullText);

        int experience =
                extractExperience(fullText);

        String[] certifications =
                extractCertifications(fullText);

        return new JobDescription(
                skills,
                experience,
                certifications);
    }

    private static int extractExperience(
            String text) {

        Pattern pattern =
                Pattern.compile(
                        "(\\d+)\\s*years",
                        Pattern.CASE_INSENSITIVE);

        Matcher matcher =
                pattern.matcher(text);

        if (matcher.find()) {

            return Integer.parseInt(
                    matcher.group(1));
        }

        return 0;
    }

    private static String[] extractCertifications(
            String text) {

        String[] certDB = {
                "AWS",
                "Oracle",
                "Azure",
                "Google Cloud"
        };

        String[] temp =
                new String[10];

        int count = 0;

        for (String cert : certDB) {

            if (text.toLowerCase()
                    .contains(
                            cert.toLowerCase())) {

                temp[count++] = cert;
            }
        }

        String[] result =
                new String[count];

        for (int i = 0; i < count; i++) {

            result[i] = temp[i];
        }

        return result;
    }
}
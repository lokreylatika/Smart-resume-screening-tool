import java.io.*;
import java.util.regex.*;

public class ResumeParser {

    public static Candidate parseResume(String filePath) {

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
            System.out.println(e);
        }

        String fullText = text.toString();

        String name = extractName(fullText);

        String[] skills =
                SkillExtractor.extractSkills(
                        fullText);

        int experience =
                extractExperience(fullText);

        String[] certifications =
                extractCertifications(fullText);

        return new Candidate(
                name,
                skills,
                experience,
                certifications);
    }

    private static String extractName(String text) {

        Pattern pattern = Pattern.compile(
                "Name\\s*:\\s*([A-Za-z ]+?)\\s+Email",
                Pattern.CASE_INSENSITIVE);

        Matcher matcher = pattern.matcher(text);

        if (matcher.find()) {

            return matcher.group(1).trim();
        }

        return "Unknown";
    }

    private static int extractExperience(String text) {

        Pattern p1 = Pattern.compile(
                "Experience\\s*:?\\s*(\\d+)",
                Pattern.CASE_INSENSITIVE);

        Matcher m1 = p1.matcher(text);

        if (m1.find()) {
            return Integer.parseInt(m1.group(1));
        }

        Pattern p2 = Pattern.compile(
                "(\\d+)\\s*years",
                Pattern.CASE_INSENSITIVE);

        Matcher m2 = p2.matcher(text);

        if (m2.find()) {
            return Integer.parseInt(m2.group(1));
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

        for(String cert : certDB) {

            if(text.toLowerCase()
                    .contains(
                            cert.toLowerCase())) {

                temp[count++] = cert;
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
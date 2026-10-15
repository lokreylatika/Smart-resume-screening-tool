public class ResumeScorer {

    public static int calculateScore(
            Candidate candidate,
            String[] requiredSkills,
            int requiredExperience,
            String[] requiredCertifications) {

        int score = 0;

        /* ==========================
           SKILL MATCHING
           ========================== */

        String[] candidateSkills =
                candidate.getSkills();

        for (String reqSkill : requiredSkills) {

            boolean matched = false;

            for (String candSkill :
                    candidateSkills) {

                /* Exact Match */

                if (KMP.search(
                        candSkill.toLowerCase(),
                        reqSkill.toLowerCase())) {

                    score += 10;
                    matched = true;
                    break;
                }

                /* Fuzzy Match */

                int distance =
                        EditDistance.calculate(
                                candSkill,
                                reqSkill);

                if (distance <= 2) {

                    score += 5;
                    matched = true;
                    break;
                }
            }

            if (!matched) {
                score += 0;
            }
        }

        /* ==========================
           EXPERIENCE SCORING
           ========================== */

        int candidateExperience =
                candidate.getExperienceYears();

        if (candidateExperience >=
                requiredExperience) {

            score += 20;
        }
        else {

            score +=
                    candidateExperience * 5;
        }

        /* ==========================
           CERTIFICATION SCORING
           ========================== */

        String[] candidateCerts =
                candidate.getCertifications();

        for (String requiredCert :
                requiredCertifications) {

            for (String cert :
                    candidateCerts) {

                if (cert.equalsIgnoreCase(
                        requiredCert)) {

                    score += 10;
                }
            }
        }

        candidate.setScore(score);

        return score;
    }
}
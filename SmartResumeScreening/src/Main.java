public class Main {

    public static void main(String[] args) {
    	
        System.out.println(
                "======================================");
        System.out.println(
                " SMART RESUME SCREENING TOOL ");
        System.out.println(
                "======================================");

        /* Load Job Description */

        JobDescription jd =
                JobDescription.loadJobDescription(
                        "src/job_description.txt");

        /* Read Resumes */

        Candidate c1 =
                ResumeParser.parseResume(
                        "src/resumes/resume1.txt");

        Candidate c2 =
                ResumeParser.parseResume(
                        "src/resumes/resume2.txt");

        Candidate c3 =
                ResumeParser.parseResume(
                        "src/resumes/resume3.txt");

        Candidate[] candidates = {
                c1,
                c2,
                c3
        };

        /* Score Candidates */

        for (Candidate c : candidates) {

            ResumeScorer.calculateScore(
                    c,
                    jd.getRequiredSkills(),
                    jd.getRequiredExperience(),
                    jd.getRequiredCertifications());
        }

        /* Rank Using Max Heap */

        MaxHeap heap =
                new MaxHeap(candidates.length);

        for (Candidate c : candidates) {

            heap.insert(c);
        }

        /* Display Ranking */

        System.out.println(
                "\n===== FINAL RANKING =====");

        int rank = 1;

        while (!heap.isEmpty()) {

            Candidate c =
                    heap.extractMax();

            System.out.println(
                    "\nRank #" + rank);

            c.display();

            rank++;
        }

        System.out.println(
                "\nScreening Completed.");
    }
}
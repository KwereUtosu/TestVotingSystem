import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author JoyU
 * @date 10/17/2018
 */
public class AutomatedVotingSystem {
    String abiaFilePath = "C:\\Users\\JoyU\\IdeaProjects\\Techtest\\src\\resource\\Abia_votes.txt";
    String enuguFilePath = "C:\\Users\\JoyU\\IdeaProjects\\Techtest\\src\\resource\\Enugu_votes.txt";
    String kadunaFilePath = "C:\\Users\\JoyU\\IdeaProjects\\Techtest\\src\\resource\\Kaduna_votes.txt";
    String lagosFilePath = "C:\\Users\\JoyU\\IdeaProjects\\Techtest\\src\\resource\\Lagos_votes.txt";
    String oyoFilePath = "C:\\Users\\JoyU\\IdeaProjects\\Techtest\\src\\resource\\Oyo_votes.txt";


    public static void main(String[] args) {
        String filePath = "C:\\Users\\JoyU\\IdeaProjects\\Techtest\\src\\Abia_votes.txt";
        AutomatedVotingSystem automatedVotingSystem = new AutomatedVotingSystem();
        /**
         * This is a small Documentation, follow the console print out below
         * to see answer for all questions, a comment is placed above each method
         * that returns each answer for proper guidance
         */

        /**
         * This is the answer for Questions (a) and (b), see below
         */
        automatedVotingSystem.showPartyWithHighestVoteEachState();

        /**
         * This is the answer for Question (c) see below
         */
        List<List<String>> stateVotes = new ArrayList<>();
        stateVotes.add(automatedVotingSystem.readFile(automatedVotingSystem.abiaFilePath));
        stateVotes.add(automatedVotingSystem.readFile(automatedVotingSystem.enuguFilePath));
        stateVotes.add(automatedVotingSystem.readFile(automatedVotingSystem.kadunaFilePath));
        stateVotes.add(automatedVotingSystem.readFile(automatedVotingSystem.lagosFilePath));
        stateVotes.add(automatedVotingSystem.readFile(automatedVotingSystem.oyoFilePath));
        automatedVotingSystem.getElectionWinner(stateVotes);

        /**
         * This is the answer for Question (d) see below, two state had falsified votes
         * Enugu state and Lagos state
         */
        List<List<String>> stateVotesQuestionD = new ArrayList<>();
        stateVotesQuestionD.add(automatedVotingSystem.readFile(automatedVotingSystem.abiaFilePath));
        stateVotesQuestionD.add(automatedVotingSystem.getNewEnuguPdpVotes());
        stateVotesQuestionD.add(automatedVotingSystem.readFile(automatedVotingSystem.kadunaFilePath));
        stateVotesQuestionD.add(automatedVotingSystem.getNewLagosApcVotes());
        stateVotesQuestionD.add(automatedVotingSystem.readFile(automatedVotingSystem.oyoFilePath));
        automatedVotingSystem.getElectionWinner(stateVotesQuestionD);

        /**
         * This is the answer for Question (e) see below, YYP had falsified votes
         * in the previous compilation
         */
        List<List<String>> stateVotesQuestionE = new ArrayList<>();
        stateVotesQuestionE.add(automatedVotingSystem.readFile(automatedVotingSystem.abiaFilePath));
        stateVotesQuestionE.add(automatedVotingSystem.readFile(automatedVotingSystem.enuguFilePath));
        stateVotesQuestionE.add(automatedVotingSystem.readFile(automatedVotingSystem.kadunaFilePath));
        stateVotesQuestionE.add(automatedVotingSystem.readFile(automatedVotingSystem.lagosFilePath));
        stateVotesQuestionE.add(automatedVotingSystem.readFile(automatedVotingSystem.oyoFilePath));
        automatedVotingSystem.getNewElectionWinner(stateVotesQuestionE);

        /**
         * This is the answer for Question (f) see below
         */
        long daysTilElectionDay = automatedVotingSystem.getDaysTillElectionDay();

        /**
         * For Question (g), I'm most likely to vote for, SDP
         */

    }


    /**
     * This Method reads a file from a file Path and converts that file to a
     * list of strings
     * @return List<String>
     */
    public List<String> readFile(String filePath){
        List<String> eachStateAsList = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File(filePath));
            while (scanner.hasNext()){
                eachStateAsList.add(scanner.next());
            }
            scanner.close();
//            System.out.println(eachStateAsList);
        }catch (Exception e){
            System.out.println(e.getCause().getMessage());
        }
        return eachStateAsList;
    }

    /**
     * This Method is used to calculate the highest number of votes
     * @return Integer
     */
    public int getHighestVote(List<Integer> voteCounts){
        int maximumValue = voteCounts.get(0);
        for (int index = 0; index < voteCounts.size(); index++){
            if (voteCounts.get(index) > maximumValue){
                maximumValue = voteCounts.get(index);
            }
        }
        return maximumValue;
    }

    /**
     * This Method is used to calculate the votes in each party
     * and returns the party name along side the votes
     * @return Map<String, Integer>
     */
    public Map<String, Integer> countVotes(List<String> stateVotes){
        String output = "";
        Map<String, Integer> partyAndVotes = new HashMap<String, Integer>();
        int aCPVotes = 0;
        int aPCVotes = 0;
        int pDPVotes = 0;
        int sDPVotes = 0;
        int yYPVotes = 0;
        int voteSize = stateVotes.size();
        for (int i = 0; i < voteSize; i++){
            if (stateVotes.get(i).equalsIgnoreCase("ACP")){
                aCPVotes++;
            }else if (stateVotes.get(i).equalsIgnoreCase("APC")){
                aPCVotes++;
            }else if (stateVotes.get(i).equalsIgnoreCase("PDP")){
                pDPVotes++;
            }else if (stateVotes.get(i).equalsIgnoreCase("SDP")){
                sDPVotes++;
            }else if (stateVotes.get(i).equalsIgnoreCase("YYP")){
                yYPVotes++;
            }
        }
        partyAndVotes.put("ACP" ,aCPVotes);
        partyAndVotes.put("APC" ,aPCVotes);
        partyAndVotes.put("PDP" ,pDPVotes);
        partyAndVotes.put("SDP" ,sDPVotes);
        partyAndVotes.put("YYP" ,yYPVotes);
//        System.out.println(partyAndVotes);
//        output = "For " + state + ":\n" + " ACP = " + aCPVotes + "Number of votes\n" + " APC = " + aPCVotes + "Number of votes\n" + " PDP = " + pDPVotes + "Number of votes\n" + " SDP = " + sDPVotes + "Number of votes\n" + " YYP = " + yYPVotes + "Number of votes\n" + ;
//                System.out.println(output);
        return partyAndVotes;
    }

    /**
     * This Method takes in a Mapped String and Integer of votes counted also
     * the state which those vote belongs to
     * @return String
     */
    String showPartyWithHigestVote(Map<String, Integer> partyAndVotes, String state){
        String output = "";
        String highest = "";
        int aCPVotes = partyAndVotes.get("ACP");
        int aPCVotes = partyAndVotes.get("APC");
        int pDPVotes = partyAndVotes.get("PDP");
        int sDPVotes = partyAndVotes.get("SDP");
        int yYPVotes = partyAndVotes.get("YYP");
        ArrayList<Integer> allVotesCounted = new ArrayList<>();
        allVotesCounted.add(aCPVotes);
        allVotesCounted.add(aPCVotes);
        allVotesCounted.add(pDPVotes);
        allVotesCounted.add(sDPVotes);
        allVotesCounted.add(yYPVotes);
        int maximumVotes = getHighestVote(allVotesCounted);
        output = "For " + state + ":\n" + " ACP = " + aCPVotes + "Number of votes\n" + " APC = " + aPCVotes + "Number of votes\n" + " PDP = " + pDPVotes + "Number of votes\n" + " SDP = " + sDPVotes + "Number of votes\n" + " YYP = " + yYPVotes + "Number of votes\n";
        System.out.println(output);
        if (maximumVotes == aCPVotes){
            highest = "ACP," + aCPVotes;
            System.out.println("ACP had the highest number of Votes in "+state+" with "+aCPVotes+" votes\n");
        }else if (maximumVotes == aPCVotes){
            highest = "APC," + aPCVotes;
            System.out.println("APC had the highest number of Votes in "+state+" with "+aPCVotes+" votes\n");
        }else if (maximumVotes == pDPVotes){
            highest = "PDP," + pDPVotes;
            System.out.println("PDP had the highest number of Votes in "+state+" with "+pDPVotes+" votes\n");
        }else if (maximumVotes == sDPVotes){
            highest = "SDP," + sDPVotes;
            System.out.println("SDP has the highest number of Votes in "+state+" with "+sDPVotes+" votes\n");
        }else if (maximumVotes == yYPVotes){
            highest = "YYP," + yYPVotes;
            System.out.println("YYP has the highest number of Votes in "+state+" with "+yYPVotes+" votes\n");
        }
        return highest;
    }

    /**
     * This Method puts all the state paths in a List, iterates through
     * the list and converts each file to a String and add it to another list
     * @return Map<String, List<String>>
     */
    Map<String, List<String>> showPartyWithHighestVoteEachState(){
        Map<String, List<String>> stateListAndName = new HashMap<String, List<String>>();
        stateListAndName.put("AbiaState", readFile(abiaFilePath));
        stateListAndName.put("EnuguState", readFile(enuguFilePath));
        stateListAndName.put("KadunaState", readFile(kadunaFilePath));
        stateListAndName.put("LagosState", readFile(lagosFilePath));
        stateListAndName.put("OyoState", readFile(oyoFilePath));
        stateListAndName.forEach((stateName,stateVotes)->{
            Map<String, Integer> partyAndVotes = countVotes(stateVotes);
            showPartyWithHigestVote(partyAndVotes,stateName);
        });
        return stateListAndName;
    }

    /**
     * This Method calculate the winner of the election in the country
     * @return Map<String, Integer>
     */
    public Map<String, Integer> getElectionWinner(List<List<String>> stateVotes){
        Map<String, Integer> totalVotesCountedNParty = new HashMap<>();
        int aCPVotes=0 , aPCVotes=0 , pDPVotes=0 , sDPVotes=0 , yYPVotes=0;
        for (int i = 0; i < stateVotes.size();i++){
            Map<String, Integer> partyAndVotes = countVotes(stateVotes.get(i));
            for (int index = 0; index < partyAndVotes.size();index++){
                aCPVotes = aCPVotes + partyAndVotes.get("ACP");
                aPCVotes = aPCVotes + partyAndVotes.get("APC");
                pDPVotes = pDPVotes + partyAndVotes.get("PDP");
                sDPVotes = sDPVotes + partyAndVotes.get("SDP");
                yYPVotes = yYPVotes + partyAndVotes.get("YYP");
            }
        }
        totalVotesCountedNParty.put("ACP", aCPVotes);
        totalVotesCountedNParty.put("APC", aPCVotes);
        totalVotesCountedNParty.put("PDP", pDPVotes);
        totalVotesCountedNParty.put("SDP", sDPVotes);
        totalVotesCountedNParty.put("YYP", yYPVotes);
        ArrayList<Integer> totalVotesCounted = new ArrayList<>();
        totalVotesCounted.add(aCPVotes);
        totalVotesCounted.add(aPCVotes);
        totalVotesCounted.add(pDPVotes);
        totalVotesCounted.add(sDPVotes);
        totalVotesCounted.add(yYPVotes);
        System.out.println(totalVotesCounted);
        int maximumVotes = getHighestVote(totalVotesCounted);
        if (maximumVotes == aCPVotes){
            System.out.println("ACP had the highest number of Votes in the country with "+aCPVotes+" votes, ACP won the Election\n");
        }else if (maximumVotes == aPCVotes){
            System.out.println("APC had the highest number of Votes in the country with "+aPCVotes+" votes, APC won the Election\n");
        }else if (maximumVotes == pDPVotes){
            System.out.println("PDP had the highest number of Votes in the country with "+pDPVotes+" votes, PDP won the Election\n");
        }else if (maximumVotes == sDPVotes){
            System.out.println("SDP has the highest number of Votes in the country with "+sDPVotes+" votes, SDP won the Election\n");
        }else if (maximumVotes == yYPVotes){
            System.out.println("YYP has the highest number of Votes in the country with "+yYPVotes+" votes, YYP won the Election\n");
        }
        return totalVotesCountedNParty;
    }

    /**
     * This Method calculate a new ACP votes for Lagos State
     * because 20% of the old one was falsified
     * @return List<String>
     */
    public List<String> getNewLagosApcVotes() {
        Map<String, Integer> lagosVotes = countVotes(readFile(lagosFilePath));
        int lagosApcVotes = lagosVotes.get("APC");
        int twentyPercent = (30 * lagosApcVotes)/100;
        int newLagosApcVotes= lagosApcVotes-twentyPercent;
        lagosVotes.replace("APC", lagosApcVotes, newLagosApcVotes);
        List<String> lagosNew = new ArrayList<>();
        for (int i = 0; i < newLagosApcVotes; i++){
            lagosNew.add("APC");
        }
        for (int i = 0; i < lagosVotes.get("ACP"); i++){
            lagosNew.add("ACP");
        }
        for (int i = 0; i < lagosVotes.get("PDP"); i++){
            lagosNew.add("PDP");
        }
        for (int i = 0; i < lagosVotes.get("SDP"); i++){
            lagosNew.add("SDP");
        }
        for (int i = 0; i < lagosVotes.get("YYP"); i++){
            lagosNew.add("YYP");
        }
        return lagosNew;
    }

    /**
     * This Method calculate a new PDP votes for Enugu State
     * because 30% of the old one was falsified
     * @return List<String>
     */
    public List<String> getNewEnuguPdpVotes() {
        Map<String, Integer> enuguVotes = countVotes(readFile(enuguFilePath));
        int enuguPdpVotes = enuguVotes.get("PDP");
        int thirtyPercent = (30 * enuguPdpVotes)/100;
        int newEnuguPdpVotes= enuguPdpVotes-thirtyPercent;
        enuguVotes.replace("PDP", enuguPdpVotes, newEnuguPdpVotes);
        List<String> enuguNew = new ArrayList<>();
//        enuguVotes.forEach((party, votes) -> {
//
//        });
        for (int i = 0; i < newEnuguPdpVotes; i++){
            enuguNew.add("PDP");
        }
        for (int i = 0; i < enuguVotes.get("ACP"); i++){
            enuguNew.add("ACP");
        }
        for (int i = 0; i < enuguVotes.get("APC"); i++){
            enuguNew.add("APC");
        }
        for (int i = 0; i < enuguVotes.get("SDP"); i++){
            enuguNew.add("SDP");
        }
        for (int i = 0; i < enuguVotes.get("YYP"); i++){
            enuguNew.add("YYP");
        }
        return enuguNew;
    }

    /**
     * This Method calculate a new election winner in the country
     * because 30% of YYP votes was changed
     * @return Map<String, Integer>
     */
    public  void getNewElectionWinner(List<List<String>> stateVotes){
        Map<String, Integer> totalVotesCountedNParty = new HashMap<>();
        totalVotesCountedNParty = getElectionWinner(stateVotes);
        int totalAcpVotes = totalVotesCountedNParty.get("ACP");
        int totalApcVotes = totalVotesCountedNParty.get("APC");
        int totalPdpVotes = totalVotesCountedNParty.get("PDP");
        int totalSdpVotes = totalVotesCountedNParty.get("SDP");
        int totalYypVotes = totalVotesCountedNParty.get("YYP");
        int thirtyPercent = (30 * totalYypVotes)/100;
        int newYypVotes= totalYypVotes-thirtyPercent;
        ArrayList<Integer> totalVotesCounted = new ArrayList<>();
        totalVotesCounted.add(totalAcpVotes);
        totalVotesCounted.add(totalApcVotes);
        totalVotesCounted.add(totalPdpVotes);
        totalVotesCounted.add(totalSdpVotes);
        totalVotesCounted.add(newYypVotes);
        System.out.println(totalVotesCounted);
        int maximumVotes = getHighestVote(totalVotesCounted);
        if (maximumVotes == totalAcpVotes){
            System.out.println("ACP had the highest number of Votes in the country with "+totalAcpVotes+" votes, ACP won the Election\n");
        }else if (maximumVotes == totalApcVotes){
            System.out.println("APC had the highest number of Votes in the country with "+totalApcVotes+" votes, APC won the Election\n");
        }else if (maximumVotes == totalPdpVotes){
            System.out.println("PDP had the highest number of Votes in the country with "+totalPdpVotes+" votes, PDP won the Election\n");
        }else if (maximumVotes == totalSdpVotes){
            System.out.println("SDP has the highest number of Votes in the country with "+totalSdpVotes+" votes, SDP won the Election\n");
        }else if (maximumVotes == newYypVotes){
            System.out.println("YYP has the highest number of Votes in the country with "+newYypVotes+" votes, YYP won the Election\n");
        }
    }

    /**
     * This Method returns the amount of days remaining
     * till the election day
     */
    long getDaysTillElectionDay(){
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        TimeUnit timeUnit = TimeUnit.DAYS;
        Date todaysDate = new Date();
        Date electionDay = new Date();
        String electionDate = "15/05/2019";
        try {
            electionDay = format.parse(electionDate);
        }catch (ParseException e){ }
        long dateDifferene = electionDay.getTime()-todaysDate.getTime();
        long intervalTilElection = timeUnit.convert(dateDifferene, TimeUnit.DAYS);
        long daysTillElection = TimeUnit.MILLISECONDS.toDays(intervalTilElection);
        System.out.println(daysTillElection + "Days till Election day");
        return dateDifferene;
    }
}

package design.pattern.behavioral.chainofresponsibilitypattern;

public class SupportRequest {
    private String issueType;

    public SupportRequest(String issueType){
        this.issueType = issueType;
    }

    public String getIssueType(){
        return issueType;
    }

}

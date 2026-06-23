package design.pattern.behavioral.chainofresponsibilitypattern.handler;

import design.pattern.behavioral.chainofresponsibilitypattern.SupportRequest;

public class ManagerSupport extends SupportHanlder{

    @Override
    public void handle(SupportRequest request) {
        System.out.println("Manager handled : " + request.getIssueType()
        );
    }
}

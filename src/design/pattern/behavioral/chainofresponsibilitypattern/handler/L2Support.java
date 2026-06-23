package design.pattern.behavioral.chainofresponsibilitypattern.handler;

import design.pattern.behavioral.chainofresponsibilitypattern.SupportRequest;

public class L2Support extends SupportHanlder{

    @Override
    public void handle(SupportRequest request) {
        if("DATABASE".equalsIgnoreCase(request.getIssueType())){
            System.out.println("L2 handled Database Issue");
        }else if(nextHanlder != null){
            nextHanlder.handle(request);
        }

    }
}

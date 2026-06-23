package design.pattern.behavioral.chainofresponsibilitypattern.handler;

import design.pattern.behavioral.chainofresponsibilitypattern.SupportRequest;

public class L1Support extends SupportHanlder {

    @Override
    public void handle(SupportRequest request) {
        if("PASSWORD".equalsIgnoreCase(request.getIssueType())){
            System.out.println("L1 handled Password Reset");
        }else if(nextHanlder != null){
            nextHanlder.handle(request);
        }

    }
}

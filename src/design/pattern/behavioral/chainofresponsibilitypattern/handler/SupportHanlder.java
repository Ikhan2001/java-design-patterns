package design.pattern.behavioral.chainofresponsibilitypattern.handler;

import design.pattern.behavioral.chainofresponsibilitypattern.SupportRequest;

public abstract class SupportHanlder {

    protected SupportHanlder nextHanlder;

    public void setNextHanlder(SupportHanlder nextHanlder){
        this.nextHanlder = nextHanlder;
    }

    public abstract void handle(SupportRequest request);
}

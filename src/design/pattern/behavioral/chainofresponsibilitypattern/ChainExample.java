package design.pattern.behavioral.chainofresponsibilitypattern;

import design.pattern.behavioral.chainofresponsibilitypattern.handler.L1Support;
import design.pattern.behavioral.chainofresponsibilitypattern.handler.L2Support;
import design.pattern.behavioral.chainofresponsibilitypattern.handler.ManagerSupport;
import design.pattern.behavioral.chainofresponsibilitypattern.handler.SupportHanlder;

public class ChainExample {
    public static void main(String[] args) {
        SupportHanlder l1 = new L1Support();
        SupportHanlder l2 = new L2Support();
        SupportHanlder manager = new ManagerSupport();

        l1.setNextHanlder(l2);
        l2.setNextHanlder(manager);

        l1.handle(new SupportRequest("PASSWORD"));
        l1.handle(new SupportRequest("DATABASE"));
        l1.handle(new SupportRequest("REFUND"));
    }
}

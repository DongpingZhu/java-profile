package com.test.pattern;

import java.util.*;

public class EventListenerTest {
    public static void main(String[] args) {
        EventSource eventSource = new EventSource();
        eventSource.addListener(new Listener1());
        eventSource.notifyEvent();
    }
}
// 事件源
class EventSource{
    private List<EventListener1> repos = new ArrayList<>();
    public EventSource(){}
    public void addListener(EventListener1 el){
        repos.add(el);
    }
    public void notifyEvent(){
        for (EventListener1 eventListener1 : repos) {
            eventListener1.eventHandler(new EventObject1(this));
        }
    }
}

// 事件对象
class EventObject1 extends EventObject {

    public EventObject1(Object source) {
        super(source);
    }
    public void say(){
        System.out.println("say method...");
    }
}

// 事件监听器
class Listener1 implements EventListener1{
    public void eventHandler(EventObject1 eventObject1) {
        System.out.println("事件源：" + eventObject1.getSource().toString());
        eventObject1.say();
    }
}
interface EventListener1 extends EventListener {
    void eventHandler(EventObject1 eventObject1);
}

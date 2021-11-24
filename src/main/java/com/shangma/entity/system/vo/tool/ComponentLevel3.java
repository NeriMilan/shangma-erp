package com.shangma.entity.system.vo.tool;

import java.util.ArrayList;
import java.util.List;


public class ComponentLevel3 extends AbstComponent {

    List<AbstComponent> components = new ArrayList<AbstComponent>();

    @Override
    public void add(AbstComponent component) {
        components.add(component);
    }

    @Override
    public void remove(AbstComponent component) {
        components.remove(component);
    }

    @Override
    public void print() {
        System.out.println("\t\t" + this.operate);
        for (AbstComponent component : components) {
            component.print();
        }
    }
}

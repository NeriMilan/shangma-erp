package com.shangma.entity.system.vo.tool;

import com.shangma.entity.system.Operate;
import org.apache.commons.collections4.list.TreeList;

import java.util.List;

/**
 * @author : Ryze
 * @create : 2021-11-24 15:50
 * @Description :
 */
public class ComponentLevel4 extends AbstComponent{

    List<Operate> operates = new TreeList<>();

    public void add(Operate operate) {
        operates.add(operate);
    }

    @Override
    public void print() {
        System.out.println("\t\t\t" + this.operate);
    }
}

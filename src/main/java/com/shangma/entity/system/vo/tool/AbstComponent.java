package com.shangma.entity.system.vo.tool;

import com.shangma.entity.system.Operate;
import lombok.Data;

/**
抽象基类, 定义了子类共通的成员
 */
@Data
public abstract class AbstComponent {

    Operate operate;

    public void add(AbstComponent component){
        throw new UnsupportedOperationException();
    };

    public void remove(AbstComponent component){
        throw new UnsupportedOperationException();
    }

    public abstract void print();
}
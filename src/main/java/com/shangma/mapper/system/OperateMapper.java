package com.shangma.mapper.system;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shangma.entity.system.Operate;
import com.shangma.entity.system.OperateExample;
import java.util.List;

import com.shangma.entity.system.RolePermission;
import org.apache.ibatis.annotations.Param;

public interface OperateMapper  extends BaseMapper<Operate> {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_system_operate
     *
     * @mbg.generated Tue Nov 23 11:13:48 CST 2021
     */
    long countByExample(OperateExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_system_operate
     *
     * @mbg.generated Tue Nov 23 11:13:48 CST 2021
     */
    int deleteByExample(OperateExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_system_operate
     *
     * @mbg.generated Tue Nov 23 11:13:48 CST 2021
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_system_operate
     *
     * @mbg.generated Tue Nov 23 11:13:48 CST 2021
     */
    int insert(Operate record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_system_operate
     *
     * @mbg.generated Tue Nov 23 11:13:48 CST 2021
     */
    int insertSelective(Operate record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_system_operate
     *
     * @mbg.generated Tue Nov 23 11:13:48 CST 2021
     */
    List<Operate> selectByExample(OperateExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_system_operate
     *
     * @mbg.generated Tue Nov 23 11:13:48 CST 2021
     */
    Operate selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_system_operate
     *
     * @mbg.generated Tue Nov 23 11:13:48 CST 2021
     */
    int updateByExampleSelective(@Param("record") Operate record, @Param("example") OperateExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_system_operate
     *
     * @mbg.generated Tue Nov 23 11:13:48 CST 2021
     */
    int updateByExample(@Param("record") Operate record, @Param("example") OperateExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_system_operate
     *
     * @mbg.generated Tue Nov 23 11:13:48 CST 2021
     */
    int updateByPrimaryKeySelective(Operate record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_system_operate
     *
     * @mbg.generated Tue Nov 23 11:13:48 CST 2021
     */
    int updateByPrimaryKey(Operate record);
}
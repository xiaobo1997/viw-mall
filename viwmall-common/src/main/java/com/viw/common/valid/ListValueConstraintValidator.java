package com.viw.common.valid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * 自定义的校验器
 * @Author: xhb
 * @Date: 2021/2/25 0:12
 *
 *  ConstraintValidator<A extends Annotation, T>
 *      A 我们使用的注解
 *      T 校验什么类型的数据
 */
public class ListValueConstraintValidator implements ConstraintValidator<ListValue, Integer> {
    private Set<Integer> set = new HashSet<>();
    /**
     初始化方法
     */
    @Override
    public void initialize(ListValue constraintAnnotation) {

        int[] vals = constraintAnnotation.vals();
        for (int val : vals) {
            set.add(val);
        }
    }
    //判断是否校验成功
    /**
     * 校验逻辑处理
     * @param value   需要校验的值
     * @param context
     * @return
     */
    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        return set.contains(value);
    }
}

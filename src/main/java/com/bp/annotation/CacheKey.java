package com.bp.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * @author current_bp
 * @createTime 20161102
 *
 */
@Retention(RetentionPolicy.RUNTIME)  
@Target({ElementType.PARAMETER})  
public @interface CacheKey {} 

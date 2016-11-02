package com.bp.aopProject.aop;

import java.lang.annotation.Annotation;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.bp.annotation.CacheKey;
import com.bp.annotation.Cacheable;
import com.bp.annotation.Cacheable.KeyMode;

/**
 * 
 * @author current_bp
 * @createTime 20161102
 *
 */
@Aspect
@Component
public class CacheableAop {
	
	@Around("@annotation(cache)")
	public Object cached(final ProceedingJoinPoint pjp, Cacheable cache) throws Throwable {

		System.out.println("===>cached1:"+JSON.toJSONString(cache));
		String key = getCacheKey(pjp, cache);
		System.out.println("===>cached2:"+key);
		return "value_"+key;
	}

	/**
	 * 获取缓存的key值
	 * 
	 * @param pjp
	 * @param cache
	 * @return
	 */
	private String getCacheKey(ProceedingJoinPoint pjp, Cacheable cache) {

		StringBuilder buf = new StringBuilder();
		buf.append(pjp.getSignature().getDeclaringTypeName()).append(".").append(pjp.getSignature().getName());
		if (cache.key().length() > 0) {
			buf.append(".").append(cache.key());
		}

		Object[] args = pjp.getArgs();
		if (cache.keyMode() == KeyMode.DEFAULT) {
			Annotation[][] pas = ((MethodSignature) pjp.getSignature()).getMethod().getParameterAnnotations();
			for (int i = 0; i < pas.length; i++) {
				for (Annotation an : pas[i]) {
					if (an instanceof CacheKey) {
						buf.append(".").append(args[i].toString());
						break;
					}
				}
			}
		} else if (cache.keyMode() == KeyMode.BASIC) {
			for (Object arg : args) {
				if (arg instanceof String) {
					buf.append(".").append(arg);
				} else if (arg instanceof Integer || arg instanceof Long || arg instanceof Short) {
					buf.append(".").append(arg.toString());
				} else if (arg instanceof Boolean) {
					buf.append(".").append(arg.toString());
				}
			}
		} else if (cache.keyMode() == KeyMode.ALL) {
			for (Object arg : args) {
				buf.append(".").append(arg.toString());
			}
		}

		return buf.toString();
	}
}

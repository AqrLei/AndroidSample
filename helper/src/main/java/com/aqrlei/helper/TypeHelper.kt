package com.aqrlei.helper

import java.lang.reflect.GenericArrayType
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type
import java.lang.reflect.TypeVariable

/**
 * created by AqrLei on 2020/4/9
 */
object TypeHelper {
    fun getType(type: Type): Type {
        return when (type) {
            is ParameterizedType -> getGenericType(type)
            is TypeVariable<*> -> getType(type.bounds[0])
            else -> type
        }
    }

    fun getGenericType(type: ParameterizedType): Type {
        if (type.actualTypeArguments.isEmpty()) return type
        return when (val actualType = type.actualTypeArguments[0]) {
            is ParameterizedType -> actualType.rawType
            is GenericArrayType -> actualType.genericComponentType
            is TypeVariable<*> -> getType(actualType.bounds[0])
            else -> type
        }
    }
}
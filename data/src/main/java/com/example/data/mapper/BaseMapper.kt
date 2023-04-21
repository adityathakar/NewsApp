package com.example.data.mapper

abstract class BaseMapper<in T, out R> {

    abstract fun transform(input: T): R

    fun transform(list: List<T>): List<R> {
        if (list.isEmpty()) {
            return emptyList()
        }

        val transformedList = mutableListOf<R>()
        for (item in list) {
            transformedList.add(transform(item))
        }

        return transformedList
    }
}
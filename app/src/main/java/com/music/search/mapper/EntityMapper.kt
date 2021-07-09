package com.music.search.mapper

abstract class EntityMapper<in E, out M> {
    abstract fun mapFromRemote(type: E): M
}
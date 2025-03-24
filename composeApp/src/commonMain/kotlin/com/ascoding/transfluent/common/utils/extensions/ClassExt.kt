package com.ascoding.transfluent.common.utils.extensions

inline fun <reified T> getClassName() = T::class.simpleName ?: "Unknown"
package com.spinny.assignment.utilities

interface OnResult<T> {
    fun onSuccess(t: T)
    fun onFailure(msg: String)
}
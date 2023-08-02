package com.example.kmm_test_2

import java.util.UUID

actual fun getUUID():String{
    return UUID.randomUUID().toString()
}
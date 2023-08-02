package com.example.kmm_test_2

    /**
     * we need UUID from this function, for android we can use UUID, but for ios this is not available we need separate implementation for that, so this approach
     */
    expect fun getUUID():String // same signature is very important for this implementation.

package com.shubham.matchmate

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
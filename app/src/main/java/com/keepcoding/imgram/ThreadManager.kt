package com.keepcoding.imgram



class ThreadManager {


    fun calcSum(callback: (sum: Int) -> Unit) {
        Thread().run {
            var sum = 0

            for (i in 0..50) {
                Thread.sleep(100)
                sum += i
            }

            callback(sum)
        }
    }
}
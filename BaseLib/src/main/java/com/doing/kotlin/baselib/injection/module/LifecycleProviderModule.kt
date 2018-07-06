package com.doing.kotlin.baselib.injection.module

import com.trello.rxlifecycle2.LifecycleProvider
import dagger.Module
import dagger.Provides

@Module
class LifecycleProviderModule(private val mProvider: LifecycleProvider<*>) {

    @Provides
    fun providesLifecycleProvider(): LifecycleProvider<*> {
        return mProvider
    }
}
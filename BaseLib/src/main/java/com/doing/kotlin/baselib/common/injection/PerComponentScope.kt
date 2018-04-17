package com.doing.kotlin.baselib.common.injection

import javax.inject.Scope
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy.RUNTIME

@Scope
@Retention(RUNTIME)
annotation class PerComponentScope
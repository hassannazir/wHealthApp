package com.wHealth.di

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.wHealth.activities.BaseActivity
import com.wHealth.activities.base.BaseFragment
import org.koin.androidx.scope.lifecycleScope
import org.koin.core.definition.BeanDefinition
import org.koin.core.definition.IndexKey
import org.koin.core.definition.Kind
import org.koin.core.definition.indexKey
import org.koin.core.instance.InstanceFactory
import org.koin.core.instance.SingleInstanceFactory
import org.koin.core.scope.Scope

// This is kind of a trick stolen from https://github.com/InsertKoinIO/koin/issues/428#issuecomment-595950513
inline val <reified T : BaseActivity> T.activityScope: Scope
    get() = lifecycleScope
        .addInstance(this)
        .addInstance(this as Activity)
        .addInstance(this as AppCompatActivity)

inline val <reified T : BaseFragment> T.fragmentScope: Scope
    get() = lifecycleScope
        .addInstance(this)
        .addInstance(this as Fragment)

inline fun <reified T> Scope.addInstance(instance: T): Scope {
    val definition = BeanDefinition(
        scopeDefinition = this._scopeDefinition,
        primaryType = T::class,
        kind = Kind.Single,
        definition = { instance }
    )
    val factory = SingleInstanceFactory(this._koin, definition)
    (this._instanceRegistry.instances as HashMap<IndexKey, InstanceFactory<*>>)[indexKey(
        T::class, null
    )] = factory
    return this
}

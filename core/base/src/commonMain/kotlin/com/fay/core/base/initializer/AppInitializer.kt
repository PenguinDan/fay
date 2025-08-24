package com.fay.core.base.initializer

/**
 * Initializes application components upon app start, the first thing that the
 * application does.
 *
 * [Priority] helps define some pre-defined order for items where [Priority.IMMEDIATE] objects
 * will always be initialized before [Priority.STANDARD] objects. Note that items in each category may
 * be initialized in any order.
 *
 * We don't provide a more fine-grained approach here to prevent coupling between [AppInitializer]
 * components, these components should in most cases be completely decoupled from each other. If you need a
 * more fine grained approach, please consider other design patterns such as a Plugin into your initialized
 * component, broadcast, or listeners.
 */
interface AppInitializer {
    enum class Priority {
        IMMEDIATE,
        STANDARD,
    }

    val priority: Priority
        get() = Priority.STANDARD

    fun init()
}

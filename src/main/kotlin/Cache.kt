import java.util.concurrent.ConcurrentHashMap

enum class CacheKey {
    SUMMARY_TEXT,
}

class Cache<T> {
    private val cache = ConcurrentHashMap<CacheKey, T>()

    operator fun set(
        key: CacheKey,
        value: T,
    ) {
        cache[key] = value
    }

    operator fun get(key: CacheKey) = cache[key]
}

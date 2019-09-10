package dev.rodni.ru.data.store

import com.nhaarman.mockito_kotlin.mock
import org.junit.Test
import kotlin.test.assertEquals

/**
 * tests for
 * @see ProjectsDataStoreFactory
 */
class ProjectsDataStoreFactoryTest {

    //mocking with new(for me) version of mockito kotlin
    private val cacheStore = mock<ProjectsCacheDataStore>()
    private val remoteStore = mock<ProjectsRemoteDataStore>()
    private val factory = ProjectsDataStoreFactory(cacheStore, remoteStore)

    /**
     * test when cached data expired
     * then get back remote store
     */
    @Test
    fun getDataStoreShouldReturnRemoteStoreWhenCacheExpired() {
        assertEquals(remoteStore, factory.getDataStore(true, true))
    }

    /**
     * test when projects not cached
     * then get back remote store
     */
    @Test
    fun getDataStoreShouldReturnRemoteStoreWhenProjectsNotCached() {
        assertEquals(remoteStore, factory.getDataStore(false, false))
    }

    /**
     * test when cached data not expired
     * then get back cache store
     */
    @Test
    fun getDataStoreShouldReturnCacheStoreWhenCacheNotExpired() {
        assertEquals(cacheStore, factory.getDataStore(true, false))
    }

    /**
     * test when call to get cache store
     * then get back cach store simply with no args or something
     */
    @Test
    fun getCacheDataStoreShouldReturnCacheStore() {
        assertEquals(cacheStore, factory.getCacheDataStore())
    }
}
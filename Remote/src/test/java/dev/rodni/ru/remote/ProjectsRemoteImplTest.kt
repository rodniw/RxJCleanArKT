package dev.rodni.ru.remote

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import dev.rodni.ru.data.model.ProjectEntity
import dev.rodni.ru.remote.factory.ProjectModelFactory
import dev.rodni.ru.remote.mapper.ProjectsResponseModelMapper
import dev.rodni.ru.remote.model.ProjectModel
import dev.rodni.ru.remote.model.ProjectsResponseModel
import dev.rodni.ru.remote.service.GithubTrendingService
import io.reactivex.Observable
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

/**
 * this class tests correction of calls from the
 * @see ProjectsRemoteImpl
 *
 * check that the service can make requests
 *
 * and complete execution
 *
 * get back project entities
 *
 * and makes requests with correct params
 */
@RunWith(JUnit4::class)
class ProjectsRemoteImplTest {

    //setting up the dependencies
    private val mapper = mock<ProjectsResponseModelMapper>()
    private val service = mock<GithubTrendingService>()
    private val remote = ProjectsRemoteImpl(service, mapper)

    @Test
    fun getProjectsShouldComplete() {
        stubGithubTrendingServiceSearchRepositories(Observable.just(
            ProjectModelFactory.makeProjectsResponse()))
        stubProjectsResponseModelMapperMapFromModel(any(),
            ProjectModelFactory.makeProjectEntity())

        val testObserver = remote.getProjects().test()
        testObserver.assertComplete()
    }

    @Test
    fun getProjectsShouldCallServer() {
        stubGithubTrendingServiceSearchRepositories(Observable.just(
            ProjectModelFactory.makeProjectsResponse()))
        stubProjectsResponseModelMapperMapFromModel(any(),
            ProjectModelFactory.makeProjectEntity())

        remote.getProjects().test()
        verify(service).searchRepositories(any(), any(), any())
    }

    @Test
    fun getProjectsShouldReturnData() {
        val response = ProjectModelFactory.makeProjectsResponse()
        stubGithubTrendingServiceSearchRepositories(Observable.just(response))
        val entities = mutableListOf<ProjectEntity>()
        response.items.forEach {
            val entity = ProjectModelFactory.makeProjectEntity()
            entities.add(entity)
            stubProjectsResponseModelMapperMapFromModel(it, entity)
        }
        val testObserver = remote.getProjects().test()
        testObserver.assertValue(entities)
    }

    @Test
    fun getProjectsShouldCallServerWithCorrectParameters() {
        stubGithubTrendingServiceSearchRepositories(Observable.just(
            ProjectModelFactory.makeProjectsResponse()))
        stubProjectsResponseModelMapperMapFromModel(any(),
            ProjectModelFactory.makeProjectEntity())

        remote.getProjects().test()
        verify(service).searchRepositories("language:kotlin", "stars", "desc")
    }

    /**
     * tests that request from the service always get observable as a response
     */
    private fun stubGithubTrendingServiceSearchRepositories(observable:
                                                            Observable<ProjectsResponseModel>) {
        whenever(service.searchRepositories(any(), any(), any()))
            .thenReturn(observable)
    }

    /**
     * tests that project's mapper always works fine
     */
    private fun stubProjectsResponseModelMapperMapFromModel(model: ProjectModel,
                                                            entity: ProjectEntity) {
        whenever(mapper.mapFromModel(model))
            .thenReturn(entity)
    }

}
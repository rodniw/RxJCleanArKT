package dev.rodni.ru.remote.service

import dev.rodni.ru.remote.model.ProjectsResponseModel
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * my git hub api client for fetching trending repos
 */
interface GithubTrendingService {

    /**
     * fetching trending projects
     */
    @GET("search/repositories")
    fun searchRepositories(@Query("q") query: String,
                           @Query("sort") sortBy: String,
                           @Query("order") order: String)
            : Flowable<ProjectsResponseModel>
}
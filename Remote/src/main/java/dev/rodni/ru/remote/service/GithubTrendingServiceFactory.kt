package dev.rodni.ru.remote.service

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import java.util.concurrent.TimeUnit

/**
 * timeout in second for ok http client
 */
private const val TIMEOUT_DURATION: Long = 120

/**
 * base url for a retrofit instance
 */
private const val BASE_URL = "https://api.github.com/"

/**
 * common factory for GithubTrendingService
 */
object GithubTrendingServiceFactory {

    /**
     * combine creation of a retrofit instance, ok http client and logging interceptor in one place
     * SHOULD USE THIS METHOD TO CREATE AN INSTANCE OF THE SERVICE
     */
    fun makeGithubTrendingService(isDebug: Boolean): GithubTrendingService {
        val okHttpClient = makeOkHttpClient(
            makeLoggingInterceptor(isDebug)
        )
        return makeGithubTrendingService(okHttpClient)
    }

    /**
     * this function creates a retrofit instance
     * DO NOT USE THIS DIRECTLY EVEN BY REFLECTION
     */
    private fun makeGithubTrendingService(okHttpClient: OkHttpClient): GithubTrendingService {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
        return retrofit.create(GithubTrendingService::class.java)
    }

    /**
     * this sets up ok http client
     * and its connect timeout
     * and read timeout
     * also here adding a logging interceptor
     */
    private fun makeOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .connectTimeout(TIMEOUT_DURATION, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT_DURATION, TimeUnit.SECONDS)
            .build()
    }

    /**
     * this method creats logging interceptor
     * and finds out to use logging or not by checking isDebug parameter
     */
    private fun makeLoggingInterceptor(isDebug: Boolean): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = if (isDebug) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
        return logging
    }
}
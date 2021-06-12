package com.wHealth.activities.ui.feedbacklist

import ApiInterface
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wHealth.network.response.FeedbackListResponse
import com.wHealth.sharedpreferences.WHealthSharedPreference
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class FeedbackListViewModel(
    private val apiInterface: ApiInterface,
    private val sharedPreference: WHealthSharedPreference
) : ViewModel(),
    CoroutineScope {
    var cId: Int = 0

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Default + SupervisorJob() + CoroutineExceptionHandler { _, throwable ->
            //logger.e("TasksViewModel", "Error on coroutine", throwable)
        }

    var getFeedbackListSuccessLiveData: MutableLiveData<FeedbackListResponse> = MutableLiveData()


    fun getFeedbackList() {
        launch {
            var docId=sharedPreference.getCurrentUser().id
            val response = apiInterface.FeedbackList(docId)
            if (response.isSuccessful) {
                response.body()?.let { response ->
                    getFeedbackListSuccessLiveData.postValue(response)
                }
            } else {
                getFeedbackListSuccessLiveData.postValue(null)
            }
        }
    }


}

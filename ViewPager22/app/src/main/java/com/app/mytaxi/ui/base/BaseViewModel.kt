package com.app.mytaxi.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.mytaxi.R
import com.app.mytaxi.utils.network.NetworkHelper
import com.app.mytaxi.utils.common.Resource
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by Ajay Deepak on 19-11-2019, 14:13
 */
abstract class BaseViewModel(protected val compositeDisposable: CompositeDisposable,
    protected val networkHelper: NetworkHelper): ViewModel() {

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

    val messageStringId: MutableLiveData<Resource<Int>> = MutableLiveData()
    val messageString: MutableLiveData<Resource<String>> = MutableLiveData()

   protected fun checkInternetConnectionWithMessage():Boolean =
       if(networkHelper.isNetworkConnected()){
           true
       } else{
          messageStringId.postValue(Resource.error(R.string.connection_error))
           false
       }

    protected fun handleNetworkError(error: Throwable?) =
        error?.let {
            networkHelper.castToNetworkError(it).run {
                when(status){
                    -1 -> messageStringId.postValue(Resource.error(R.string.something_wrong))
                    0 -> messageStringId.postValue(Resource.error(R.string.server_error))
                    else -> messageString.postValue(Resource.error(message))
                }
            }
        }

    abstract fun onCreate()
    }


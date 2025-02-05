package huaan.com.mvvmdemo

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import huaan.com.mvvmdemo.http.base.BaseViewModel
import huaan.com.mvvmdemo.http.databean.Data
import huaan.com.mvvmdemo.http.repository.ArticleRepository
import kotlinx.coroutines.*

class ScrollingViewModel : BaseViewModel() {

    private val TAG = ScrollingViewModel::class.java.simpleName

    private val datas: MutableLiveData<List<Data>> by lazy { MutableLiveData<List<Data>>().also { loadDatas() } }

    private val repository = ArticleRepository()

    fun getActicle(): LiveData<List<Data>> {
        return datas
    }

    private fun loadDatas() {
        launchUI {
            val result = withContext(Dispatchers.IO) {
                repository.getDatas()
            }
            datas.value = result.data
        }
        // Do an asynchronous operation to fetch users.
    }

}
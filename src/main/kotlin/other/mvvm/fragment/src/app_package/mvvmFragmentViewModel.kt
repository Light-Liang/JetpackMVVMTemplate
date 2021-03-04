package other.mvvm.fragment.src.app_package
fun mvvmViewModel(
        packageName:String,
        activityClass:String
)="""
package $packageName

import me.hgj.jetpackmvvm.base.viewmodel.BaseViewModel
class ${activityClass}ViewModel : BaseViewModel() {

} 
"""

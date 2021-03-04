package other.mvvm.activity.src.app_package

fun mvvmAcitivityKt(
        applicationPackage: String?,
        activityClass: String,
        layoutName: String,
        packageName: String,
        needViewModel: Boolean
) = """
package $packageName
import android.os.Bundle
import ${applicationPackage}.app.base.BaseActivity
import ${applicationPackage}.R
import ${applicationPackage}.databinding.Activity${activityClass}Binding
import ${if (needViewModel) "${applicationPackage}.viewModel.${activityClass}ViewModel" else "me.hgj.jetpackmvvm.base.viewmodel.BaseViewModel"}

class ${activityClass}Activity : BaseActivity<${if (needViewModel) "$activityClass" else "Base"}ViewModel, Activity${activityClass}Binding>() {

    override fun layoutId() = R.layout.${layoutName}
    
    override fun createObserver() {
        
    }
    
    override fun initView(savedInstanceState: Bundle?) {

    }

}
"""

package other.mvvm.fragment.src.app_package

fun mvvmFragmentKt(
        applicationPackage: String,
        fragmentClass: String,
        layoutName: String,
        packageName: String,
        needViewModel: Boolean
) = """
package $packageName
import android.os.Bundle
import ${applicationPackage}.app.base.BaseFragment
import ${applicationPackage}.R
import ${applicationPackage}.databinding.Fragment${fragmentClass}Binding
import ${if (needViewModel) "${applicationPackage}.viewModel.${fragmentClass}ViewModel" else "me.hgj.jetpackmvvm.base.viewmodel.BaseViewModel"}

class ${fragmentClass}Fragment : BaseFragment<${if (needViewModel) "$fragmentClass" else "Base"}ViewModel, Fragment${fragmentClass}Binding>() {
    
    override fun layoutId() = R.layout.${layoutName}

    /**
     *	监听数据的变化
     */
    override fun createObserver() {
    }

    override fun initView(savedInstanceState: Bundle?) {
    }

    /**
     * Fragment执行onViewCreated后触发
     */
    override fun initData() {

    }

    /**
     * 懒加载 只有当前fragment视图显示时才会触发该方法
     */
    override fun lazyLoadData() {
    }

} 
"""

package other.mvvm.fragment

import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor
import other.mvvm.fragment.res.layout.mvvmFragmentXml
import other.mvvm.fragment.src.app_package.mvvmFragmentKt
import other.mvvm.fragment.src.app_package.mvvmViewModel
import java.io.File


fun RecipeExecutor.mvvmFragmentRecipe(
        moduleData: ModuleTemplateData,
        fragmentClass: String,
        layoutName: String,
        fragmentPackageName: String,
        needViewModel: Boolean,
        viewModelName: String,
        viewModelPackageName: String
) {
    val (projectData, srcOut, resOut) = moduleData
    val ktOrJavaExt = projectData.language.extension
//    generateManifest(
//            moduleData = moduleData,
//            fragmentClass = "${fragmentClass}Fragment",
//            fragmentTitle = fragmentClass,
//            packageName = packageName,
//            isLauncher = false,
//            hasNoActionBar = false,
//            generateFragmentTitle = true,
//            requireTheme = false,
//            useMaterial2 = false
//    )

    val mvvmFragment = mvvmFragmentKt(moduleData.packageName, fragmentClass, layoutName, fragmentPackageName, needViewModel)
    // 保存Fragment
    save(mvvmFragment, File("${moduleData.rootDir.absolutePath}/src/main/java/${fragmentPackageName.replace(".","/")}/${fragmentClass}Fragment.${ktOrJavaExt}"))
    // 保存xml
    save(mvvmFragmentXml(fragmentPackageName, fragmentClass), resOut.resolve("layout/${layoutName}.xml"))
    // 保存viewmodel
    if (needViewModel)
        save(mvvmViewModel(viewModelPackageName, fragmentClass), File("${moduleData.rootDir.absolutePath}/src/main/java/${viewModelPackageName.replace(".","/")}/${viewModelName}.${ktOrJavaExt}"))
    // 保存repository
//    save(mvvmRepository(packageName, fragmentClass), srcOut.resolve("${fragmentClass}Repository.${ktOrJavaExt}"))
}

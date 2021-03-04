package other.mvvm.activity

import android.util.Log
import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor
import other.mvvm.activity.res.layout.mvvmActivityXml
import other.mvvm.activity.src.app_package.mvvmAcitivityKt
import other.mvvm.activity.src.app_package.mvvmViewModel
import java.io.File


fun RecipeExecutor.mvvmActivityRecipe(
        moduleData: ModuleTemplateData,
        activityClass: String,
        layoutName: String,
        activityPackageName: String,
        needViewModel: Boolean,
        viewModelName: String,
        viewModelPackageName: String
) {
    val (projectData, srcOut, resOut) = moduleData
    val ktOrJavaExt = projectData.language.extension
//    generateManifest(
//            moduleData = moduleData,
//            activityClass = "${activityClass}Activity",
//            activityTitle = activityClass,
//            packageName = packageName,
//            isLauncher = false,
//            hasNoActionBar = false,
//            generateActivityTitle = true,
//            requireTheme = false,
//            useMaterial2 = false
//    )

    val mvvmActivity = mvvmAcitivityKt(moduleData.packageName, activityClass, layoutName, activityPackageName, needViewModel)
    // 保存Activity
    save(mvvmActivity, File("${moduleData.rootDir.absolutePath}/src/main/java/${activityPackageName.replace(".","/")}/${activityClass}Activity.${ktOrJavaExt}"))
    // 保存xml
    save(mvvmActivityXml(activityPackageName, activityClass), resOut.resolve("layout/${layoutName}.xml"))
    // 保存viewmodel
    if (needViewModel)
        save(mvvmViewModel(viewModelPackageName, activityClass), File("${moduleData.rootDir.absolutePath}/src/main/java/${viewModelPackageName.replace(".","/")}/${viewModelName}.${ktOrJavaExt}"))
    // 保存repository
//    save(mvvmRepository(packageName, activityClass), srcOut.resolve("${activityClass}Repository.${ktOrJavaExt}"))
}

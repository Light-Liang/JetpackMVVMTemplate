package other.mvvm.activity

import com.android.tools.idea.wizard.template.*
import com.android.tools.idea.wizard.template.impl.activities.common.MIN_API

val mvvmActivityTemplate
    get() = template {
        revision = 1
        name = "MVVM Activity"
        description = "适用于JetpackMvvm框架的Activity"
        minApi = MIN_API
        minBuildApi = MIN_API

        category = Category.Activity
        formFactor = FormFactor.Mobile
        screens = listOf(WizardUiContext.ActivityGallery, WizardUiContext.MenuEntry, WizardUiContext.NewProject, WizardUiContext.NewModule)

        lateinit var layoutName: StringParameter
        lateinit var activityPackageName: StringParameter
        lateinit var viewModelPackageName: StringParameter

        val activityClass = stringParameter {
            name = "Activity Name"
            default = "Main"
            help = "只输入名字，不要包含Activity"
            constraints = listOf(Constraint.NONEMPTY)
        }

        layoutName = stringParameter {
            name = "Layout Name"
            default = "activity_main"
            help = "请输入布局的名字"
            constraints = listOf(Constraint.LAYOUT, Constraint.UNIQUE, Constraint.NONEMPTY)
            suggest = { "${activityToLayout(activityClass.value.toLowerCase())}" }
        }

        var rootPackageName = stringParameter {
            name = "Root Package name"
            default = "com.ynhere.biodiversity"
            constraints = listOf(Constraint.APP_PACKAGE)
            help = "请填写你的项目包名,请认真核实此包名是否是正确的项目包名,不能包含子包,正确的格式如:com.ynhere.biodiversity"
            suggest = { packageName }
        }

        activityPackageName = stringParameter {
            name = "Activity Package name"
            visible = { !isNewModule }
            default = "${rootPackageName.value}.ui.activity"
            constraints = listOf(Constraint.PACKAGE)
            suggest = { "${rootPackageName.value}.ui.activity" }
        }

        var needViewModel = booleanParameter {
            name = "Generate ViewModel"
            default = true
            help = "是否需要生成ViewModel？不勾选则不生成"
        }

        val viewModelName = stringParameter {
            name = "ViewModel Name"
            visible = { needViewModel.value }
            default = "MainViewModel"
            help = "请输入ViewModel的名字"
            constraints = listOf(Constraint.CLASS, Constraint.UNIQUE, Constraint.NONEMPTY)
            suggest = { "${activityClass.value}ViewModel" }
        }

        viewModelPackageName = stringParameter {
            name = "viewModel Package name"
            visible = { needViewModel.value }
            default = "${rootPackageName.value}.viewModel"
            constraints = listOf(Constraint.PACKAGE)
            suggest = { "${rootPackageName.value}.viewModel" }
        }

        widgets(
                TextFieldWidget(activityClass),
                TextFieldWidget(layoutName),
                PackageNameWidget(rootPackageName),
                TextFieldWidget(activityPackageName),
                CheckBoxWidget(needViewModel),
                TextFieldWidget(viewModelName),
                TextFieldWidget(viewModelPackageName)
        )
//        thumb { File("logo.png") }
        recipe = { data: TemplateData ->
            mvvmActivityRecipe(
                    data as ModuleTemplateData,
                    activityClass.value,
                    layoutName.value,
                    activityPackageName.value,
                    needViewModel.value,
                    viewModelName.value,
                    viewModelPackageName.value)
        }
    }

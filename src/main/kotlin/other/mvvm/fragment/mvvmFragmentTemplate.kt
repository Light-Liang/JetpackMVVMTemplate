package other.mvvm.fragment

import com.android.tools.idea.wizard.template.*
import com.android.tools.idea.wizard.template.impl.activities.common.MIN_API

val mvvmFragmentTemplate
    get() = template {
        revision = 1
        name = "MVVM Fragment"
        description = "适用于JetpackMvvm框架的Fragment"
        minApi = MIN_API
        minBuildApi = MIN_API

        category = Category.Fragment
        formFactor = FormFactor.Mobile
        screens = listOf(WizardUiContext.FragmentGallery, WizardUiContext.MenuEntry, WizardUiContext.NewProject, WizardUiContext.NewModule)

        lateinit var layoutName: StringParameter
        lateinit var fragmentPackageName: StringParameter
        lateinit var viewModelPackageName: StringParameter

        val fragmentClass = stringParameter {
            name = "Fragment Name"
            default = "Main"
            help = "只输入名字，不要包含Fragment"
            constraints = listOf(Constraint.NONEMPTY)
        }

        layoutName = stringParameter {
            name = "Layout Name"
            default = "fragment_main"
            help = "请输入布局的名字"
            constraints = listOf(Constraint.LAYOUT, Constraint.UNIQUE, Constraint.NONEMPTY)
            suggest = { "${fragmentToLayout(fragmentClass.value.toLowerCase())}" }
        }

        var rootPackageName = stringParameter {
            name = "Root Package name"
            default = "com.ynhere.biodiversity"
            constraints = listOf(Constraint.APP_PACKAGE)
            help = "请填写你的项目包名,请认真核实此包名是否是正确的项目包名,不能包含子包,正确的格式如:com.ynhere.biodiversity"
            suggest = { packageName }
        }

        fragmentPackageName = stringParameter {
            name = "Fragment Package name"
            visible = { !isNewModule }
            default = "${rootPackageName.value}.ui.fragment"
            constraints = listOf(Constraint.PACKAGE)
            suggest = { "${rootPackageName.value}.ui.fragment" }
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
            suggest = { "${fragmentClass.value}ViewModel" }
        }

        viewModelPackageName = stringParameter {
            name = "viewModel Package name"
            visible = { needViewModel.value }
            default = "${rootPackageName.value}.viewModel"
            constraints = listOf(Constraint.PACKAGE)
            suggest = { "${rootPackageName.value}.viewModel" }
        }

        widgets(
                TextFieldWidget(fragmentClass),
                TextFieldWidget(layoutName),
                PackageNameWidget(rootPackageName),
                TextFieldWidget(fragmentPackageName),
                CheckBoxWidget(needViewModel),
                TextFieldWidget(viewModelName),
                TextFieldWidget(viewModelPackageName)
        )
//        thumb { File("logo.png") }
        recipe = { data: TemplateData ->
            mvvmFragmentRecipe(
                    data as ModuleTemplateData,
                    fragmentClass.value,
                    layoutName.value,
                    fragmentPackageName.value,
                    needViewModel.value,
                    viewModelName.value,
                    viewModelPackageName.value)
        }
    }

package com.github.lightliang.jetpackmvvmtemplate.services

import com.github.lightliang.jetpackmvvmtemplate.MyBundle
import com.intellij.openapi.project.Project

class MyProjectService(project: Project) {

    init {
        println(MyBundle.message("projectService", project.name))
    }
}

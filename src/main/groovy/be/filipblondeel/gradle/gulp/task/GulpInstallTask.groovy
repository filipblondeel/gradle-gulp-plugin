package be.filipblondeel.gradle.gulp.task

import be.filipblondeel.gradle.gulp.GulpExtension
import be.filipblondeel.gradle.gulp.GulpPlugin
import com.moowork.gradle.node.task.NpmTask

class GulpInstallTask extends NpmTask {

    private GulpExtension ext

    public GulpInstallTask() {
        super()

        this.group = GulpPlugin.GULP_GROUP_NAME
        this.description = "Runs 'npm install gulp' to install gulp"
        this.ext = GulpExtension.get(this.project)


        getOutputs().dir('node_modules/gulp')
    }

    void exec() {
        def gulpVersion = ext.version
        def gulpPackageName = gulpVersion ? "gulp@$gulpVersion" : 'gulp'
        println "Running 'npm install $gulpPackageName'"

        setArgs(['install', gulpPackageName])
        super.exec()
    }
}
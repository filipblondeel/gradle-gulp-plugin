package be.filipblondeel.gradle.gulp.task

import be.filipblondeel.gradle.gulp.GulpExtension
import be.filipblondeel.gradle.gulp.GulpPlugin
import com.moowork.gradle.node.task.NodeTask
import org.gradle.api.GradleException

class GulpTask extends NodeTask {

    private final static String GULP_SCRIPT = 'node_modules/gulp/bin/gulp.js'

    private GulpExtension ext

    public GulpTask() {
        this.ext = GulpExtension.get(this.project)
        this.group = GulpPlugin.GULP_GROUP_NAME
    }

    @Override
    void exec() {
        def localGulp
        if (ext.useRoot) {
            localGulp = this.project.rootProject.file(GULP_SCRIPT)
        } else {
            localGulp = this.project.file(GULP_SCRIPT)
        }

        if (!localGulp.isFile()) {
            throw new GradleException(
                    "Gulp not installed in node_modules, please first run 'gradle ${GulpPlugin.GULP_INSTALL_NAME}'"
            )
        }

        script = localGulp
        super.exec()
    }
}

package be.filipblondeel.gradle.gulp

import be.filipblondeel.gradle.gulp.task.GulpInstallTask
import be.filipblondeel.gradle.gulp.task.GulpTask
import com.moowork.gradle.node.NodePlugin
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.Task

class GulpPlugin implements Plugin<Project> {

    static final String GULP_GROUP_NAME = 'Gulp'
    static final String GULP_RULE_PATTERN_PREFIX = 'gulp_'
    static final String GULP_INSTALL_NAME = 'installGulp'

    @Override
    void apply(final Project project) {
        project.extensions.create(GulpExtension.NAME, GulpExtension)
        project.plugins.apply(NodePlugin.class)

        project.extensions.extraProperties.set('GulpTask', GulpTask.class)
        project.tasks.create(GULP_INSTALL_NAME, GulpInstallTask.class)

        // note this rule also makes it possible to specify e.g. "dependsOn gulp_install"
        project.tasks.addRule("Pattern: ${GULP_RULE_PATTERN_PREFIX}<ID>") { String taskName ->
            if (taskName.startsWith(GULP_RULE_PATTERN_PREFIX)) {
                Task gulpTask = project.tasks.create(taskName, GulpTask.class)
                gulpTask.args = [(taskName - GULP_RULE_PATTERN_PREFIX)]
                return gulpTask
            }
        }
    }
}
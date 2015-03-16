package be.filipblondeel.gradle.gulp

import org.gradle.api.Project

class GulpExtension {

    static final String NAME = "gulp"

    String version = ''

    def boolean useRoot = false

    static GulpExtension get(Project project) {
        return project.extensions.getByType(GulpExtension)
    }
}

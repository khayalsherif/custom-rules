package az.khayalsharifli.lint_rules.android_entry_point_rule

import com.android.tools.lint.client.api.UElementHandler
import com.android.tools.lint.detector.api.JavaContext
import org.jetbrains.uast.UClass

@Suppress("UnstableApiUsage")
class AndroidEntryPointVisitor(private val context: JavaContext) : UElementHandler() {

    private val superClassQualifiedName = "az.khayalsharifli.customapp.base.BaseFragment"

    override fun visitClass(node: UClass) {
        if (node.javaPsi.superClass?.qualifiedName == superClassQualifiedName &&
            !node.hasAnnotation("dagger.hilt.android.AndroidEntryPoint")
        ) {
            reportIssue(node)
        }
    }

    private fun reportIssue(node: UClass) {
        context.report(
            issue = AndroidEntryPointImplementationIssue.ISSUE,
            scopeClass = node,
            location = context.getNameLocation(node),
            "Use @AndroidEntryPoint before running the app"
        )
    }
}
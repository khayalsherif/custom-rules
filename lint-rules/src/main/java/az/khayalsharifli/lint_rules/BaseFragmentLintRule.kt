package az.khayalsharifli.lint_rules

import com.android.tools.lint.client.api.UElementHandler
import com.android.tools.lint.detector.api.*
import org.jetbrains.uast.UClass

import org.jetbrains.kotlin.psi.KtClass
import org.jetbrains.uast.*

@Suppress("DEPRECATION", "UnstableApiUsage")
class BaseFragmentLintRule : Detector(), SourceCodeScanner {

    companion object {
        val ISSUE = Issue.create(
            "AndroidEntryPoint",
            "BaseFragment should use @AndroidEntryPoint before running the app",
            "Extend BaseFragment with classes annotated with @AndroidEntryPoint.",
            Category.CORRECTNESS,
            5,
            Severity.ERROR,
            Implementation(
                BaseFragmentLintRule::class.java,
                Scope.JAVA_FILE_SCOPE
            )
        )
    }

    override fun getApplicableUastTypes(): List<Class<out UElement>>? {
        return listOf(UClass::class.java)
    }

    override fun createUastHandler(context: JavaContext): UElementHandler? {
        return object : UElementHandler() {
            override fun visitClass(node: UClass) {
                if (node.superClass?.qualifiedName == "az.khayalsharifli.customapp.base.BaseFragment" &&
                    !node.hasAnnotation("dagger.hilt.android.AndroidEntryPoint")) {
                    context.report(
                        issue = RxJavaUnconventionalMethodNamingIssue.ISSUE,
                        scopeClass = node,
                        location = context.getNameLocation(node),
                        "Use @AndroidEntryPoint before running the app"
                    )
                }
            }
        }
    }
}

package az.khayalsharifli.lint_rules.naming_rule

import com.android.tools.lint.client.api.UElementHandler
import com.android.tools.lint.detector.api.JavaContext
import com.intellij.psi.PsiClassType
import org.jetbrains.uast.UMethod

@Suppress("UnstableApiUsage")
class MethodNamingVisitor(private val context: JavaContext) : UElementHandler() {
    override fun visitMethod(node: UMethod) {
        if (node.returnClassName() == "String") {
            if (!node.name.endsWith("Once")) {
                reportIssue(node)
            }
        }
    }

    private fun UMethod.returnClassName(): String =
        (returnTypeReference?.type as? PsiClassType)?.className ?: ""

    private fun reportIssue(node: UMethod) {
        context.report(
            issue = MethodNamingIssue.ISSUE,
            scopeClass = node,
            location = context.getNameLocation(node),
            message = """
                [String] string parametrelerin sonunda Once keyi olmalıdir. 
                Example: removeAccountOnce()
            """
        )
    }
}
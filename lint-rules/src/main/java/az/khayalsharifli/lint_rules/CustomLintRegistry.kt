package az.khayalsharifli.lint_rules

import az.khayalsharifli.lint_rules.android_entry_point_rule.AndroidEntryPointImplementationIssue
import az.khayalsharifli.lint_rules.naming_rule.MethodNamingIssue
import com.android.tools.lint.client.api.IssueRegistry
import com.android.tools.lint.detector.api.CURRENT_API

@Suppress("UnstableApiUsage")
class CustomLintRegistry : IssueRegistry() {
    override val issues =
        listOf(
            AndroidEntryPointImplementationIssue.ISSUE,
            MethodNamingIssue.ISSUE
        )

    override val api: Int = CURRENT_API

    override val minApi: Int = 6
}